package vulpescloud.cluster.v2

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ClusterApiMigrationTest {
    @Test
    fun joinAndHeartbeatRoundTrip() {
        val request = JoinClusterRequestModel(
            nodeId = "node-1",
            nodeName = "node-a",
            grpcEndpoint = "node-a:50051",
            configuredMemoryBytes = 512u,
            labels = emptyMap(),
            bootGeneration = 1u
        )
        val response = JoinClusterResponseModel(
            accepted = true,
            message = "accepted",
            controllerLease = null,
            member = null,
            clusterRevision = 1u
        )

        assertTrue(response.accepted)
        assertEquals(1u, response.clusterRevision)
        assertEquals("node-1", request.nodeId)
    }

    @Test
    fun staleControllerTermIsRejected() {
        val heartbeat = HeartbeatResponseModel(
            accepted = false,
            message = "stale controller term",
            currentControllerTerm = 2u,
            currentClusterRevision = 3u,
            desiredNodeState = ClusterNodeState.SUSPECT,
            refreshMembership = true,
        )

        assertFalse(heartbeat.accepted)
        assertTrue(heartbeat.message.contains("stale"))
    }

    @Test
    fun allNodeStatesRoundTrip() {
        val states = listOf(
            ClusterNodeState.UNSPECIFIED,
            ClusterNodeState.REGISTERED,
            ClusterNodeState.JOINING,
            ClusterNodeState.ONLINE,
            ClusterNodeState.DRAINING,
            ClusterNodeState.SUSPECT,
            ClusterNodeState.LOST,
            ClusterNodeState.OFFLINE,
        )

        assertEquals(8, states.size)
        assertEquals(ClusterNodeState.LOST, states[6])
    }
}

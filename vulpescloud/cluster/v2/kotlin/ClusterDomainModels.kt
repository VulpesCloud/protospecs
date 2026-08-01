package vulpescloud.cluster.v2

import java.time.Instant

enum class ClusterNodeState {
    UNSPECIFIED,
    REGISTERED,
    JOINING,
    ONLINE,
    DRAINING,
    SUSPECT,
    LOST,
    OFFLINE,
}

data class ControllerLeaseModel(
    val leaderNodeId: String,
    val controllerTerm: ULong,
    val leaseExpiresAt: Instant,
    val clusterRevision: ULong,
)

data class ClusterMemberModel(
    val nodeId: String,
    val nodeName: String,
    val grpcEndpoint: String,
    val configuredMemoryBytes: UInt,
    val labels: Map<String, String>,
    val membershipState: ClusterNodeState,
    val bootGeneration: ULong,
    val stateRevision: ULong,
    val createdAt: Instant,
    val updatedAt: Instant,
)

data class NodeHeartbeatModel(
    val nodeId: String,
    val bootGeneration: ULong,
    val observedControllerTerm: ULong,
    val observedAt: Instant,
    val usedMemoryBytes: UInt,
    val cpuUsage: Double,
    val onlinePlayerCount: UInt,
    val runningServiceCount: UInt,
    val healthAttributes: Map<String, String>,
)

data class ClusterTimingConfigModel(
    val heartbeatIntervalMillis: Long,
    val heartbeatTimeoutMillis: Long,
    val suspectGracePeriodMillis: Long,
    val lostGracePeriodMillis: Long,
)

data class ClusterViewModel(
    val clusterRevision: ULong,
    val controllerLease: ControllerLease,
    val members: List<ClusterMember>,
    val timingConfig: ClusterTimingConfig,
)

data class JoinClusterRequestModel(
    val nodeId: String,
    val nodeName: String,
    val grpcEndpoint: String,
    val configuredMemoryBytes: UInt,
    val labels: Map<String, String>,
    val bootGeneration: ULong,
)

data class JoinClusterResponseModel(
    val accepted: Boolean,
    val message: String,
    val controllerLease: ControllerLease?,
    val member: ClusterMember?,
    val clusterRevision: ULong,
)

data class HeartbeatRequestModel(val heartbeat: NodeHeartbeatModel)

data class HeartbeatResponseModel(
    val accepted: Boolean,
    val message: String,
    val currentControllerTerm: ULong,
    val currentClusterRevision: ULong,
    val desiredNodeState: ClusterNodeState,
    val refreshMembership: Boolean,
)

data class ClusterEventEnvelopeModel(
    val clusterRevision: ULong,
    val controllerLease: ControllerLease,
    val eventType: String,
)

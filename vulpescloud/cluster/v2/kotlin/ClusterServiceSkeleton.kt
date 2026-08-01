package vulpescloud.cluster.v2

import de.vulpescloud.proto.cluster.v2.*
import io.grpc.stub.StreamObserver

class ClusterMembershipServiceSkeleton : ClusterMembershipServiceGrpc.ClusterMembershipServiceImplBase() {
    override fun joinCluster(request: JoinClusterRequest, responseObserver: StreamObserver<JoinClusterResponse>) {
        responseObserver.onNext(JoinClusterResponse.newBuilder().setAccepted(true).setMessage("accepted").build())
        responseObserver.onCompleted()
    }

    override fun leaveCluster(request: LeaveClusterRequest, responseObserver: StreamObserver<LeaveClusterResponse>) {
        responseObserver.onNext(LeaveClusterResponse.newBuilder().setAccepted(true).build())
        responseObserver.onCompleted()
    }

    override fun heartbeat(request: HeartbeatRequest, responseObserver: StreamObserver<HeartbeatResponse>) {
        responseObserver.onNext(HeartbeatResponse.newBuilder().setAccepted(true).setCurrentControllerTerm(1).setCurrentClusterRevision(1).setDesiredNodeState(NodeState.NODE_STATE_ONLINE).build())
        responseObserver.onCompleted()
    }

    override fun reportNodeInventory(request: ReportNodeInventoryRequest, responseObserver: StreamObserver<ReportNodeInventoryResponse>) {
        responseObserver.onNext(ReportNodeInventoryResponse.newBuilder().setAccepted(true).build())
        responseObserver.onCompleted()
    }
}

class ClusterQueryServiceSkeleton : ClusterQueryServiceGrpc.ClusterQueryServiceImplBase() {
    override fun getClusterView(request: GetClusterViewRequest, responseObserver: StreamObserver<GetClusterViewResponse>) {
        responseObserver.onNext(GetClusterViewResponse.newBuilder().build())
        responseObserver.onCompleted()
    }

    override fun getNode(request: GetNodeRequest, responseObserver: StreamObserver<GetNodeResponse>) {
        responseObserver.onNext(GetNodeResponse.newBuilder().build())
        responseObserver.onCompleted()
    }

    override fun watchCluster(request: WatchClusterRequest, responseObserver: StreamObserver<WatchClusterResponse>) {
        responseObserver.onCompleted()
    }
}

class ClusterAdminServiceSkeleton : ClusterAdminServiceGrpc.ClusterAdminServiceImplBase() {
    override fun registerNode(request: RegisterNodeRequest, responseObserver: StreamObserver<RegisterNodeResponse>) {
        responseObserver.onNext(RegisterNodeResponse.newBuilder().setAccepted(true).build())
        responseObserver.onCompleted()
    }

    override fun drainNode(request: DrainNodeRequest, responseObserver: StreamObserver<DrainNodeResponse>) {
        responseObserver.onNext(DrainNodeResponse.newBuilder().setAccepted(true).build())
        responseObserver.onCompleted()
    }

    override fun undrainNode(request: UndrainNodeRequest, responseObserver: StreamObserver<UndrainNodeResponse>) {
        responseObserver.onNext(UndrainNodeResponse.newBuilder().setAccepted(true).build())
        responseObserver.onCompleted()
    }

    override fun removeNode(request: RemoveNodeRequest, responseObserver: StreamObserver<RemoveNodeResponse>) {
        responseObserver.onNext(RemoveNodeResponse.newBuilder().setAccepted(true).build())
        responseObserver.onCompleted()
    }
}

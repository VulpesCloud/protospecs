"# protospecs

This repository contains protobuf contracts for VulpesCloud.

## Cluster API migration

The authoritative clustering API now lives in the new package `vulpescloud.cluster.v2`.
The legacy `vulpescloud.node.v1` contract remains available only as deprecated compatibility code and is not authoritative for new controller-cluster work.

### Migration plan

1. Introduce the new `cluster.v2` package with explicit cluster membership, controller lease, heartbeat, and watch APIs.
2. Migrate Kotlin consumers to use the new domain models and watch-based connection management.
3. Replace controller ownership checks that previously relied on a mutable `Node.head` flag with database-backed leader lease evaluation using `controller_term`.
4. Keep remote command RPCs in a separate `RemoteCommandService` so membership logic stays isolated.
5. Update implementations to persist node-state changes before publishing cluster events and to reject stale controller commands with term mismatches.

### Notes

- The old `ClusterAPIService` is retained only for compatibility and is marked deprecated.
- Docker and TLS are intentionally out of scope for this change.
" 

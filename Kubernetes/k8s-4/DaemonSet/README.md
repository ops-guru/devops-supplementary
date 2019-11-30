$ kubectl apply -f fluentd.yaml

$ kubectl describe daemonset fluentd

$ kubectl get pods -o wide

Limiting DaemonSets to Specific Nodes
    The most common use case for DaemonSets is to run a Pod across every node in a
    Kubernetes cluster. However, there are some cases where you want to deploy a Pod
    to only a subset of nodes. For example, maybe you have a workload that requires a
    GPU or access to fast storage only available on a subset of nodes in your cluster. In
    cases like these node labels can be used to tag specific nodes that meet workload
    requirements.

    The first step in limiting DaemonSets to specific nodes is to add the desired set of
    labels to a subset of nodes. This can be achieved using the kubectl label
    command.
    The following command adds the ssd=true label to a single node:

    $ kubectl label nodes k0-default-pool-35609c18-z7tb ssd=true

    $ kubectl apply -f nginx-fast-storage.yaml

    $ kubectl get pods -o wide

Updating a DaemonSet

    Updating a DaemonSet by Deleting Individual Pods
        If you are running a pre-1.6 version of Kubernetes, you can perform a rolling delete
        of the Pods a DaemonSet manages using a for loop on your own machine to update
        one DaemonSet Pod every 60 seconds:
            PODS=$(kubectl get pods -o jsonpath -template='{.items[*].metadata.name}'
            for x in $PODS; do
            kubectl delete pods ${x}
            sleep 60
            done
        An alternative, easier approach is to just delete the entire DaemonSet and create a
        new DaemonSet with the updated configuration. However, this approach has a major
        drawback—downtime. When a DaemonSet is deleted all Pods managed by that
        DaemonSet will also be deleted. Depending on the size of your container images,
        recreating a DaemonSet may push you outside of your SLA thresholds, so it might
        be worth considering pulling updated container images across your cluster before
        updating a DaemonSet.

    Rolling Update of a DaemonSet
        With Kubernetes 1.6, DaemonSets can now be rolled out using the same rolling
        update strategy that deployments use. However, for reasons of backward
        compatability, the current default update strategy is the delete method described in
        the previous section. To set a DaemonSet to use the rolling update strategy, you need
        to configure the update strategy using the spec.updateStrategy.type field. That
        field should have the value RollingUpdate. When a DaemonSet has an update
        strategy of RollingUpdate, any change to the spec.template field (or subfields) in
        the DaemonSet will initiate a rolling update.
        As with rolling updates of deployments, the rolling update strategy
        gradually updates members of a DaemonSet until all of the Pods are running the new
        configuration. There are two parameters that control the rolling update of a
        DaemonSet:
        spec.minReadySeconds, which determines how long a Pod must be “ready”
        before the rolling update proceeds to upgrade subsequent Pods
        spec.updateStrategy.rollingUpdate.maxUnavailable, which indicates how
        many Pods may be simultaneously updated by the rolling update
        
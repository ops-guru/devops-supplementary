==Creating a Pod (deployment)==

    kubectl run kuard --image=gcr.io/kuar-demo/kuard-amd64:1

    kubectl get pods

    kubectl delete deployments/kuard

==From yaml==

    kubectl create -f kuard-pod.yaml

    kubectl port-forward kuard 8080:8080

    kubectl exec kuard date

    kubectl exec -it kuard ash

    kubectl cp <pod-name>:/captures/capture3.txt ./capture3.txt

    kubectl cp $HOME/config.txt <pod-name>:/config.txt

==Pod with health check==

kubectl create -f kuard-pod-health.yaml

    # Details of the restart can be found with kubectl describe kuard.

    # Kubernetes also supports tcpSocket health checks that
    # open a TCP socket; if the connection is successful, the probe succeeds. This style of
    # probe is useful for non-HTTP applications; for example, databases or other non–
    # HTTP-based APIs.

    # Kubernetes allows exec probes. These execute a script or program in the
    # context of the container. Following typical convention, if this script returns a zero
    # exit code, the probe succeeds; otherwise, it fails. exec scripts are often useful for
    # custom application validation logic that doesn’t fit neatly into an HTTP call.


== Resource Management

    # With scheduling systems like Kubernetes managing resource packing, you can drive
    # your utilization to greater than 50%.

    # Kubernetes allows users to specify two different resource metrics. Resource requests
    # specify the minimum amount of a resource required to run the application. Resource
    # limits specify the maximum amount of a resource that an application can consume.

    # When you establish limits on a container, the kernel is configured to ensure that
    # consumption cannot exceed these limits. A container with a CPU limit of 0.5 cores
    # will only ever get 0.5 cores, even if the CPU is otherwise idle. A container with a
    # memory limit of 256 MB will not be allowed additional memory (e.g., malloc will
    # fail) if its memory usage exceeds 256 MB.

 == Persisting Data with Volimes
    # Few and recommended patterns :
        - Communication/synchronization
            Two containers used a shared volume to serve a site while keeping it synchronized to a remote Git location.
            To achive this. the Pod uses an emptyDir volume.
        - Cashe :
            application may use a volume that is valuable for performance, but not required
            for correct operation of the application. 
            emptyDir works well for the cache use case as well.
        - Persistent data :
            Sometimes you will use a volume for truly persistent data—data that is independent
            of the lifespan of a particular Pod, and should move between nodes in the cluster if a
            node fails or a Pod moves to a different machine for some reason. To achieve this,
            Kubernetes supports a wide variety of remote network storage volumes, including
            widely supported protocols like NFS or iSCSI as well as cloud provider network
            storage like Amazon’s Elastic Block Store, Azure’s Files and Disk Storage, as well
            as Google’s Persistent Disk.
        - Mounting the host filesystem :
            Other applications don’t actually need a persistent volume, but they do need some
            access to the underlying host filesystem. For example, they may need access to the
            /dev filesystem in order to perform raw block-level access to a device on the system.
            For these cases, Kubernetes supports the hostDir volume, which can mount
            arbitrary locations on the worker node into the container.



== Labels ==

kubectl run alpaca-prod --image=gcr.io/kuar-demo/kuard-amd64:1 --replicas=2 --labels="ver=1,app=alpaca,env=prod"
kubectl run alpaca-test --image=gcr.io/kuar-demo/kuard-amd64:2 --replicas=1 --labels="ver=2,app=alpaca,env=test"

kubectl run bandicoot-prod --image=gcr.io/kuar-demo/kuard-amd64:2 --replicas=2 --labels="ver=2,app=bandicoot,env=prod"
kubectl run bandicoot-staging --image=gcr.io/kuar-demo/kuard-amd64:2 --replicas=1 --labels="ver=2,app=bandicoot,env=staging"

kubectl get deployments --show-labels
kubectl label deployments alpaca-test "canary=true"
kubectl get deployments -L canary
kubectl label deployments alpaca-test "canary-"  # Remove label
kubectl get pods --show-labels
kubectl get pods --selector="ver=2"
kubectl get pods --selector="app=bandicoot,ver=2"
kubectl get pods --selector="app in (alpaca,bandicoot)"
kubectl get deployments --selector="canary"

== Cleanup ==

kubectl delete deployments --all

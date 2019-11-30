$ kubectl apply -f kuard-rs.yaml

$ kubectl get pods

$ kubectl describe rs kuard

$ kubectl get pods <pod-name> -o yaml

$ kubectl get pods -l app=kuard,version=2


Scaling ReplicaSets
    ReplicaSets are scaled up or down by updating the spec.replicas key on the
    ReplicaSet object stored in Kubernetes. When a ReplicaSet is scaled up, new Pods
    are submitted to the Kubernetes API using the Pod template defined on the
    ReplicaSet.

    $ kubectl scale kuard --replicas=4


    Declaratively Scaling with kubectl apply
        In a declarative world, we make changes by editing the configuration file in version
        control and then applying those changes to our cluster. To scale the kuard
        ReplicaSet, edit the kuard-rs.yaml configuration file and set the replicas count to
        3:
        ...
        spec:
        replicas: 3
        ...

        $ kubectl apply -f kuard-rs.yaml

    horizontal pod autoscaling (HPA) :

    Autoscaling based on CPU
        Scaling based on CPU usage is the most common use case for Pod autoscaling.
        Generally it is most useful for request-based systems that consume CPU
        proportionally to the number of requests they are receiving, while using a relatively
        static amount of memory.
        To scale a ReplicaSet, you can run a command like the following:

        $ kubectl autoscale rs kuard --min=2 --max=5 --cpu-percent=80

        This command creates an autoscaler that scales between two and five replicas with a
        CPU threshold of 80%. To view, modify, or delete this resource you can use the
        standard kubectl commands and the horizontalpodautoscalers resource.
        horizontalpodautoscalers is quite a bit to type, but it can be shortened to hpa:

        $ kubectl get hpa

    Deleting ReplicaSets

        $ kubectl delete rs kuard

        If you don’t want to delete the Pods that are being managed by the ReplicaSet you
        can set the --cascade flag to false to ensure only the ReplicaSet object is deleted
        and not the Pods:

        $ kubectl delete rs kuard --cascade=false

        
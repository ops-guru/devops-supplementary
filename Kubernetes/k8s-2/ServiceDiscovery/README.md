The Service Object
    Real service discovery in Kubernetes starts with a Service object.
    A Service object is a way to create a named label selector. As we will see, the
    Service object does some other nice things for us too.
    Just as the kubectl run command is an easy way to create a Kubernetes
    deployment, we can use kubectl expose to create a service. Let’s create some
    deployments and services so we can see how they work:

        microk8s.kubectl run alpaca-prod \
          --image=gcr.io/kuar-demo/kuard-amd64:1 \
          --replicas=3 \
          --port=8080 \
          --labels="ver=1,app=alpaca,env=prod"

        microk8s.kubectl expose deployment alpaca-prod

        microk8s.kubectl run bandicoot-prod \
          --image=gcr.io/kuar-demo/kuard-amd64:2 \
          --replicas=2 \
          --port=8080 \
          --labels="ver=2,app=bandicoot,env=prod"

        microk8s.kubectl expose deployment bandicoot-prod
        microk8s.kubectl get services -o wide

        $ ALPACA_POD=$(kubectl get pods -l app=alpaca \
          -o jsonpath='{.items[0].metadata.name}')

        microk8s.kubectl port-forward $ALPACA_POD 48858:8080

Service DNS
    Because the cluster IP is virtual it is stable and it is appropriate to give it a DNS
    address. All of the issues around clients caching DNS results no longer apply. Within
    a namespace, it is as easy as just using the service name to connect to one of the pods
    identified by a service.
    Kubernetes provides a DNS service exposed to Pods running in the cluster. This
    Kubernetes DNS service was installed as a system component when the cluster was
    first created. The DNS service is, itself, managed by Kubernetes and is a great
    example of Kubernetes building on Kubernetes. The Kubernetes DNS service
    provides DNS names for cluster IPs.
    You can try this out by expanding the “DNS Resolver” section on the kuard server
    status page. Query the A record for alpaca-prod also for alpaca-prod.default, and till FQDN.

    kubectl edit deployment/alpaca-prod

        spec:
            containers:
            - image: gcr.io/kuar-demo/kuard-amd64:1
                imagePullPolicy: IfNotPresent
                name: alpaca-prod
                ports:
                - containerPort: 8080
                protocol: TCP
                readinessProbe:
                failureThreshold: 3
                httpGet:
                    path: /ready
                    port: 8080
                    scheme: HTTP
                periodSeconds: 2
                successThreshold: 1
                timeoutSeconds: 1
                resources: {}

        $ ALPACA_POD=$(kubectl get pods -l app=alpaca \
          -o jsonpath='{.items[0].metadata.name}')

        microk8s.kubectl port-forward $ALPACA_POD 48858:8080

        microk8s.kubectl get endpoints alpaca-prod --watch

    This readiness check is a way for an overloaded or sick server to signal to the system
    that it doesn’t want to receive traffic anymore. This is a great way to implement
    graceful shutdown. The server can signal that it no longer wants traffic, wait until
    existing connections are closed, and then cleanly exit.

        microk8s.kubectl edit service alpaca-prod

    Change the spec.type field to NodePort. You can also do this when creating the
    service via kubectl expose by specifying --type=NodePort.

        microk8s.kubectl describe service alpaca-prodkubexc

        $(minikube ip):$NODE_PORT

==

There is also spec.type to LoadBalancer, for cloud LB provider

***
Endpoints :
    Some applications (and the system itself) want to be able to use services without
    using a cluster IP. This is done with another type of object called Endpoints. For
    every Service object, Kubernetes creates a buddy Endpoints object that contains
    the IP addresses for that service:

        microk8s.kubectl describe endpoints alpaca-prod    

    To use a service, an advanced application can talk to the Kubernetes API directly to
    look up endpoints and call them. The Kubernetes API even has the capability to
    “watch” objects and be notified as soon as they change. In this way a client can react
    immediately as soon as the IPs associated with a service change.
    Let’s demonstrate this. In a terminal window, start the following command and leave
    it running:
    microk8s.kubectl get endpoints alpaca-prod --watch
    It will output the current state of the endpoint and then “hang”:

    Now open up another terminal window and delete and recreate the deployment
    backing alpaca-prod:
    microk8s.kubectl delete deployment alpaca-prod
    microk8s.kubectl run alpaca-prod \
    --image=gcr.io/kuar-demo/kuard-amd64:1 \
    --replicas=3 \
    --port=8080 \
    --labels="ver=1,app=alpaca,env=prod"


Cleamup :
    microk8s.kubectl delete services,deployments -l app


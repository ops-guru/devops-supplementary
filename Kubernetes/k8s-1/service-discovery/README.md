microk8s.kubectl run alpaca-prod --image=gcr.io/kuar-demo/kuard-amd64:1 --replicas=3 --port=8080 --labels="ver=1,app=alpaca,env=prod"

microk8s.kubectl expose deployment alpaca-prod

microk8s.kubectl run bandicoot-prod --image=gcr.io/kuar-demo/kuard-amd64:2 --replicas=2 --port=8080 --labels="ver=2,app=bandicoot,env=prod"

microk8s.kubectl expose deployment bandicoot-prod

microk8s.kubectl get services -o wide

ALPACA_POD=$(kubectl get pods -l app=alpaca -o jsonpath='{.items[0].metadata.name}')

microk8s.kubectl port-forward $ALPACA_POD 48858:8080

> ssh -i jb_k8s_basic.pem -L 48858:localhost:48858 centos@3.120.54.39

==Readenes probe ==
microk8s.kubectl edit deployment/alpaca-prod

readinessProbe:
    httpGet:
        path: /ready
        port: 8080
    periodSeconds: 2
    initialDelaySeconds: 0
    failureThreshold: 3
    successThreshold: 1

ALPACA_POD=$(kubectl get pods -l app=alpaca -o jsonpath='{.items[0].metadata.name}')

microk8s.kubectl port-forward $ALPACA_POD 48858:8080

ssh -i jb_k8s_basic.pem -L 48858:localhost:48858 centos@3.120.54.39

microk8s.kubectl get endpoints alpaca-prod --watch

""do fail , and see in other terminal with watch end point is changed.
==

== NodePort

microk8s.kubectl edit service alpaca-prod

""Change the spec.type field to NodePort.

ssh -i jb_k8s_basic.pem -L <port>:localhost:<port> centos@3.120.54.39

== LoadBalancer

microk8s.kubectl edit service alpaca-prod

""Change the spec.type field to LoadBalancer.

ssh -i jb_k8s_basic.pem -L <port>:localhost:<port> centos@3.120.54.39


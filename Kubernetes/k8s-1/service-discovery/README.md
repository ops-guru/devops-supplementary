microk8s.kubectl run alpaca-prod --image=gcr.io/kuar-demo/kuard-amd64:1 --replicas=3 --port=8080 --labels="ver=1,app=alpaca,env=prod"

microk8s.kubectl expose deployment alpaca-prod

microk8s.kubectl run bandicoot-prod --image=gcr.io/kuar-demo/kuard-amd64:2 --replicas=2 --port=8080 --labels="ver=2,app=bandicoot,env=prod"

microk8s.kubectl expose deployment bandicoot-prod

microk8s.kubectl get services -o wide

== NodePort

microk8s.kubectl edit service alpaca-prod

""Change the spec.type field to NodePort.

microk8s.kubectl get services -o wide

in browser <>:<port of NodePort service>

== LoadBalancer

microk8s.kubectl edit service alpaca-prod

""Change the spec.type field to LoadBalancer.


Create Namespace + runnig pod + set context + delete namespace, intro to kubectl commandline
================================================

    $ microk8s.kubectl create -f namespaces-dev.json

    $ microk8s.kubectl get namespace

    $ microk8s.kubectl run  --generator=run-pod/v1 kuard --image=gcr.io/kuar-demo/kuard-amd64:1 -n development

    $ microk8s.kubectl get pods -n development

kubectl conventions: https://kubernetes.io/docs/reference/kubectl/conventions/
-------------------

    $  microk8s.kubectl config set-context --current --namespace=development

    $ microk8s.kubectl get pods kuard -o yaml

    $ microk8s.kubectl get pods kuard -o json

    $ microk8s.kubectl edit pods kuard

kubectl context and configuration: https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#config
---------------------------------

    $ microk8s.kubectl config set-context --current --namespace=default

    $ microk8s.kubectl delete namespace development

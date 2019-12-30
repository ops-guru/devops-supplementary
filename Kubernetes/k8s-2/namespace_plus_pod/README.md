Create Namespace + runnig deployment commandline
================================================

microk8s.kubectl create -f namespaces-dev.json

microk8s.kubectl get namespace

microk8s.kubectl run kuard --image=gcr.io/kuar-demo/kuard-amd64:1 -n development

microk8s.kubectl get deployments -n development

microk8s.kubectl get pods

microk8s.kubectl get pods -n development

microk8s.kubectl delete namespace development

++ to raead : kubectl context and configuration


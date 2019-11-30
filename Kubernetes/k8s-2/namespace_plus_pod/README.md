Create Namespace + runnig deployment commandline
================================================

kubectl create -f namespaces-dev.json

kubectl get namespace

kubectl run kuard --image=gcr.io/kuar-demo/kuard-amd64:1 -n development

kubectl get deployments -n development

kubectl get pods

kubectl get pods -n development

kubectl delete namespace development

++ to raead : kubectl context and configuration


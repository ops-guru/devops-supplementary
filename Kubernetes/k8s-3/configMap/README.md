microk8s.kubectl create configmap my-config --from-file=simple-config.txt --from-literal=extra-param=extra-value --from-literal=another-param=another-value

microk8s.kubectl get configmaps my-config -o yaml

microk8s.kubectl port-forward kuard-config 8080
az aks create \
 --resource-group myResourceGroup \
 --name nag-aks \
 --generate-ssh-keys \
 --node-count 3 \
 --zones 1 2 3
kubectl get nodes -o wide
kubectl get nodes --show-labels

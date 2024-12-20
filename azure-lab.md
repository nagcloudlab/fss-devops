# azure Lab

create a new resource group

```bash
az group create --name myResourceGroup --location centralindia
```

delete a resource group

```bash
az group delete --name myResourceGroup
```

create a new virtual machine with Ubuntu 22.04 with username and password

```bash
az vm create --resource-group myResourceGroup --name myVM --image Ubuntu2404 --admin-username azureuser --admin-password Azure12345678
```

connect to the virtual machine

```bash
ssh azureuser@98.70.125.146
```

install podman 5.3.0 in Ubuntu 24.04.1 LTS

```bash
sudo apt-get update
sudo apt-get install -y podman
podman --version
```

install podman-compose in ubuntu 22.04 using pip3

```bash
sudo apt install podman-compose
podman-compose --version
```

sudo nano /etc/containers/registries.conf

[registries.search]
registries = ['docker.io']

build image(s) using podman compose

```bash
podman-compose build
```

podman connect to the container e.g redis

```bash
podman exec -it redis /bin/bash
```

hello
hi

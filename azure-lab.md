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
az vm create --resource-group myResourceGroup --name myVM --image Ubuntu2204 --admin-username azureuser --admin-password Azure12345678
```

connect to the virtual machine

```bash
ssh azureuser@52.140.101.79
```

install podman in ubuntu 22.04

```bash
sudo apt-get update
sudo apt-get install -y podman
```

install podman-compose in ubuntu 22.04 using pip3

```bash
sudo apt-get install -y python3-pip
pip3 install podman-compose
export PATH=$PATH:~/.local/bin
podman-compose --version
```

build image(s) using podman compose

```bash
podman-compose build
```

sudo nano /etc/containers/registries.conf

[registries.search]
registries = ['docker.io']

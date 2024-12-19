### build each image separately

````bash

build shop-ui image

```bash
podman build -t shop-ui -f Dockerfile .
````

build product-service image

```bash
podman build -t product-service -f Dockerfile .
```

build order-service image

```bash
podman build -t order-service -f Dockerfile .
```

### or using podman-compose build all images

```bash
podman-compose build -f podman-compose-v1.yml
```

### create a network

```bash
podman network create my_network
```

start product-service container with my_network

```bash
podman run -d -p 8081:8080 --name product-service-1 --network my_network localhost/fss/product-service
```

start order-service container with my_network

```bash
podman run -d -p 8082:8080 --name order-service --network my_network localhost/fss/order-service
```

### create a volume for postgres

```bash
podman volume create pgdata
```

### start postgres container with my_network and pgdata volume

```bash

podman run -d --name postgres-db \
    --network my_network \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_DB=mydb \
    -v pgdata:/var/lib/postgresql/data \
    postgres:15

```

or using podman-compose

```bash
podman-compose -f podman-compose-v2.yml up -d
```

### or using podman pod

```bash
podman pod create --name my_pod --network my_network
```

```bash
podman run -d --name product-service --pod my_pod product-service
```

```bash
podman run -d --name order-service --pod my_pod order-service
```

```bash
podman run -d --name postgres-db --pod my_pod \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_DB=mydb \
    -v pgdata:/var/lib/postgresql/data \
    postgres:15
```

### or using podman-compose

```bash
podman-compose -f podman-compose-v3.yml up -d
```

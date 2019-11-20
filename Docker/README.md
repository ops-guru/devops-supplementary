# Docker 

## Part 1:      **Docker Basic**

### Some basic command

```
docker info
docker ps
docker images
```
### Docker **run** command:

```
docker run alpine echo "Hello World"
docker run -i -t -d --name nicedocker -p 8080:80 alpine ash
docker run -h niceDocker -i -t --rm debian /bin/bash
docker run --help
```

### Docker **Volumes**:

```
docker run -dti --name alpine1 --mount target=/app alpine ash
docker inspect alpine1
docker stop alpine1 && docker rm alpine1
```
### Docker Volumes Demo:

```
docker volume create fs_shared
docker volume ls
docker run --rm -tdi --name alpine1 --mount source=fs_shared,target=/app alpine ash
docker run --rm -tdi --name alpine2 --mount source=fs_shared,target=/app alpine ash
docker run --rm -tdi --name alpine3 --mount source=fs_shared,target=/app alpine ash
```

### Docker **BIND mounts**:

```
docker run --rm -tdi -v "$(pwd)"/source:/app alpine ash
docker run --rm -tdi --mount type=bind,source="$(pwd)"/source,target=/app alpine ash
```

### **Running BootStrap app in a container - Lab**
```
docker run --rm -tdi --mount type=bind,source="$(pwd)"/source,target=/app levep79/alpine-oraclejre8 java -jar -Dspring.profiles.active /app/spring-music.jar

docker run --rm -tdi -p 8082:8080 --mount type=bind,source="$(pwd)"/source,target=/app levep79/alpine-oraclejre8 java -jar -Dspring.profiles.active /app/spring-music.jar

```
### **Networking - Lab**
```
docker network ls
docker run --rm -tdi --name alpine1 alpine ash
docker run --rm -tdi --name alpine2 alpine ash
```
```
docker network create dmz
docker network inspect dmz
docker run -tdi --rm --name network_test --network dmz alpine ash
docker inspect network_test
```

```
docker network alpine-net 
docker run -tdi --rm --name alpine1 --network alpine-net alpine ash
docker run -tdi --rm --name alpine2 --network alpine-net alpine ash
docker run -tdi --rm --name alpine3  alpine ash
docker run -tdi --rm --name alpine4 --network alpine-net alpine ash
docker network connect dmz alpine4
```
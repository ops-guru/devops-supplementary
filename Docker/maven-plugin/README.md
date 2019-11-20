# Docker Maven Plugin


### Build maven container

```
docker build -f Dockerfile-mvn .  -t mvn-docker
```

### Start maven container

```
docker run --rm -itd --name my-mvn -v /var/run/docker.sock:/var/run/docker.sock -v `pwd`/source:/app mvn-docker bash
```

### Download and build Docker Image

```
docker exec -it my-mvn bash
cd /app
git clone https://github.com/arun-gupta/docker-java-sample.git
cd docker-java-sample
mvn package -Pdocker
exit
docker run hellojava
```
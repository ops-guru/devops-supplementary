# Hello Java


### Create + Build
```
docker run --rm -itd --name my-mvn -v `pwd`/source:/app maven:3.6.2-jdk-8-slim sh
docker exec -it my-mvn sh
cd /app
mvn archetype:generate -DgroupId=org.examples.java -DartifactId=helloworld -DinteractiveMode=false
cd helloworld
mvn package
java -cp target/helloworld-1.0-SNAPSHOT.jar org.examples.java.App
exit
```

### Run
```
docker image build -t hello-java:latest .
docker container run hello-java:latest

```
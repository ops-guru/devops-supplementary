# Jenkins

## Environment
```
mkdir ~/jenkins_home
docker build . -t my-jenkins
docker run -itd --name my-jenkins -p 8080:8080 -p 50000:50000 -v ~/jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock my-jenkins 
docker run -itd --name my-mail -p 1025:1025 -p 8025:8025 mailhog/mailhog
```
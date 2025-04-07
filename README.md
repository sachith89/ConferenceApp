# ConferenceApp

A demo app using DropWizard

[![Java CI with Maven](https://github.com/sachith89/ConferenceApp/actions/workflows/maven.yml/badge.svg)](https://github.com/sachith89/ConferenceApp/actions/workflows/maven.yml)

How to start the conference-app application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/conference-app-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

Build the Docker image
---

```bash
    docker build -t conference-app .
```

Setup kubernetes config maps
---

```bash
    kubectl apply -f application-config.yml
```
```bash
    kubectl apply -f application-deployment.yml
```

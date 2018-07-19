# devops-tools-sample-app

## Analyzing the Code

Go to User-> My Account-> Security in SonarQube and generate a token. Use the token in gradle.properties file.

```
#Sonar
systemProp.sonar.host.url=http://c.pncapix.com
systemProp.sonar.login=<token>
```

## Submit to SonarQube

Push the code once using gradle to see if everything works fine.

```
./gradlew sonarqube
```

## References
 - [Why you should not break the build based on the Quality Gate Results](https://blog.sonarsource.com/why-you-shouldnt-use-build-breaker/) 

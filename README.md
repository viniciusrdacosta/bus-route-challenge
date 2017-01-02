[![Build Status](https://circleci.com/gh/viniciusrdacosta/bus-route-challenge.svg?&style=shield&circle-token=f8b0cb0d526340d572bffc3d2739d18e2929c829)](https://circleci.com/gh/viniciusrdacosta/bus-route-challenge/tree/master) [![Coverage Status](https://coveralls.io/repos/github/viniciusrdacosta/bus-route-challenge/badge.svg)](https://coveralls.io/github/viniciusrdacosta/bus-route-challenge) [![codebeat badge](https://codebeat.co/badges/3736d596-c19e-40ca-b559-9267f86891e8)](https://codebeat.co/projects/github-com-viniciusrdacosta-bus-route-challenge) 


## How Run Locally - Developer Mode
 
 1. Clone repo
 2. Run `./gradlew bootRun`
    1. Default file is located under `/src/main/resources/data`
    2. To change the file location change `gradle bootRun` task arguments in `build.gradle`
    3. Argument file name is `routesFilePath`


## Implementation
  ### Relevant Frameworks   
  
 1. Spring-Boot 
    1. spring-boot-devtools
    2. spring-boot-starter-web
    3. spring-boot-starter-actuator


 2. Lombok
 3. Logback
 4. AssertJ
    
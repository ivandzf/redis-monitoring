# Redis Monitoring (Under Development)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/27d0e36a6c534dabb588a0766932a6d6)](https://www.codacy.com/app/ivandzf/redis-monitoring?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ivandzf/redis-monitoring&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/ivandzf/redis-monitoring.svg?branch=master)](https://travis-ci.org/ivandzf/redis-monitoring)

![Home](https://github.com/ivandzf/redis-monitoring/blob/master/img/dashboard.png)

[DEMO](https://redis-monitoring-java-vue.herokuapp.com/)

## Tech Stack
*  Vue.js
*  Springboot 1.5.14
*  Servlet Based
*  Lettuce (Redis Client)
*  Websocket (Realtime Monitoring)

## TODO
*  Memory Graph Monitoring
*  Cluster Monitoring

## Run Development
*  mvn spring-boot:run
*  cd src/main/ui
*  npm install
*  npm run dev

open in your browser -> http://localhost:8080

## Run Production
*  mvn clean package -DskipTests
*  java -jar target/redis-monitoring-1.0-SNAPSHOT.jar

open in your browser -> http://localhost:8080

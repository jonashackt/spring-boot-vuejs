# spring-boot-vuejs
=============================
[![Build Status](https://travis-ci.org/codecentric/cxf-spring-boot-starter-maven-plugin.svg?branch=master)](https://travis-ci.org/codecentric/cxf-spring-boot-starter-maven-plugin)
[![Dependency Status](https://www.versioneye.com/user/projects/5720e321fcd19a004544247d/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5720e321fcd19a004544247d)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/jonashackt/spring-boot-vuejs/blob/master/LICENSE)


## In Search of a new Webfrontend-Framework after 2 Years of absence...

Well I´am not a Frontend developer. I´am more like playing around with Spring Boot, Web- & Microservices & Docker, automating things with Ansible and Docker, Scaling things with Spring Cloud, Docker Compose and Traefik... And the only GUIs I´am building are the "new JS framework in town"-app every two years... :) So the last one was Angular 1 - and it felt, as it was a good choice! I loved the coding experience and after a day of training, I felt able to write awesome Frontends...

But now we´re 2 years later and I heard from afar, that there was a complete rewrite of Angular (2), a new kid in town from Facebook (React) and lot´s of ES201x stuff and dependency managers like bower and Co. So I´am now in the new 2-year-cycle of trying to cope up again - and so glad I found this article: https://medium.com/reverdev/why-we-moved-from-angular-2-to-vue-js-and-why-we-didnt-choose-react-ef807d9f4163

Key points are:
* Angular 2 isn´t the way to go, if you know version 1 (complete re-write, only with Typescript, loss of many of 1´s advantages, Angular 4 is coming)
* React  (facebook´ish problems (licence), need to choose btw. Redux & MObX, harder learning curve, slower coding speed)

![comparison-angular-react-vuejs](https://github.com/jonashackt/spring-boot-vuejs/blob/master/comparison-angular-react-vuejs.png)

So I think, it could be a good idea to invest a day or so into Vue.js. Let´s have a look here!



## Setup Vue.js & Spring Boot

### Prerequisites

##### MacOSX

`brew install node`

`brew install npm`

`npm install --global vue-cli`

## Backend

Go to https://start.spring.io/ and initialize an Spring Boot app with `Web` and `Actuator`. Place the zip´s contents in the backend folder.


## Frontend

```
vue init webpack frontend
```

This will initialize an project sceleton for Vue.JS in /frontend directory - it therefore asks some questions in the cli:

![vuejs-cli-init](https://github.com/jonashackt/spring-boot-vuejs/blob/master/vuejs-cli-init.png)

### tell Webpack to output the dist/ contents to target/

Commonly, node projects will create a dist/ directory for final builds which contains the minified source code of the web app - but we want it all in `/target`. Therefore go to `frontend/config/index.js` and replace the following lines:

```
index: path.resolve(__dirname, '../dist/index.html'),
assetsRoot: path.resolve(__dirname, '../dist'),
```

with

```
index: path.resolve(__dirname, '../target/dist/index.html'),
assetsRoot: path.resolve(__dirname, '../target/dist'),
```


## First App run

```
mvn clean install
```

Run our complete Spring Boot App:

```
mvn --projects backend spring-boot:run
```

Now go to http://localhost:8080/ and have a look at your first Vue.js Spring Boot App:

![localhost-first-run](https://github.com/jonashackt/spring-boot-vuejs/blob/master/localhost-first-run.png)









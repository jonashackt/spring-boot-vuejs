# spring-boot-vuejs
Example project showing how to build a Spring Boot App providing a GUI with Vue.js


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

###### MacOSX El Capitan

`brew install node`
`brew install npm`
`npm install --global vue-cli`

## Frontend

```
vue init webpack frontend
```

This will initialize an project sceleton for Vue.JS in /frontend directory - it therefore asks some questions in the cli:

![vuejs-cli-init](https://github.com/jonashackt/spring-boot-vuejs/blob/master/vuejs-cli-init.png)



cd frontend

#Install all dependencies
npm install

#Run front end app on node server localhost:8080
npm run dev




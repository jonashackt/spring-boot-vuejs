# spring-boot-vuejs

[![Build Status](https://travis-ci.org/jonashackt/spring-boot-vuejs.svg?branch=master)](https://travis-ci.org/jonashackt/spring-boot-vuejs)
[![Coverage Status](https://coveralls.io/repos/github/jonashackt/spring-boot-vuejs/badge.svg?branch=master)](https://coveralls.io/github/jonashackt/spring-boot-vuejs?branch=master)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/jonashackt/spring-boot-vuejs/blob/master/LICENSE)

![localhost-first-run](https://github.com/jonashackt/spring-boot-vuejs/blob/master/localhost-first-run.png)

A live deployment is available on Heroku: https://spring-boot-vuejs.herokuapp.com

There´s also a blog post about this project available here: https://blog.codecentric.de/en/2018/04/spring-boot-vuejs/

## In Search of a new Webfrontend-Framework after 2 Years of absence...

Well I’am not a Frontend developer. I’am more like playing around with Spring Boot, Web- & Microservices & Docker, automating things with Ansible and Docker, Scaling things with Spring Cloud, Docker Compose and Traefik... And the only GUIs I’am building are the "new JS framework in town"-app every two years... :) So the last one was Angular 1 - and it felt, as it was a good choice! I loved the coding experience and after a day of training, I felt able to write awesome Frontends...

But now we’re 2 years later and I heard from afar, that there was a complete rewrite of Angular (2), a new kid in town from Facebook (React) and lots of ES201x stuff and dependency managers like bower and Co. So I’am now in the new 2-year-cycle of trying to cope up again - and so glad I found this article: https://medium.com/reverdev/why-we-moved-from-angular-2-to-vue-js-and-why-we-didnt-choose-react-ef807d9f4163

Key points are:
* Angular 2 isn’t the way to go, if you know version 1 (complete re-write, only with Typescript, loss of many of 1’s advantages, Angular 4 is coming)
* React  (facebookish problems (licence), need to choose btw. Redux & MObX, harder learning curve, slower coding speed)

![comparison-angular-react-vuejs](https://github.com/jonashackt/spring-boot-vuejs/blob/master/comparison-angular-react-vuejs.png)

And the [introduction phrase](https://vuejs.org/v2/guide/index.html) sounds really great:

> Vue (pronounced /vjuː/, like view) is a progressive framework for building user interfaces. Unlike other monolithic frameworks, Vue is designed from the ground up to be incrementally adoptable. The core library is focused on the view layer only, and is very easy to pick up and integrate with other libraries or existing projects. On the other hand, Vue is also perfectly capable of powering sophisticated Single-Page Applications when used in combination with modern tooling and supporting libraries.

So I think, it could be a good idea to invest a day or so into Vue.js. Let’s have a look here!



## Setup Vue.js & Spring Boot

### Prerequisites

#### MacOSX

```
brew install node
npm install --global vue-cli
```

#### Linux

```
sudo apt update
sudo apt install node
npm install --global vue-cli
```

#### Windows

```
choco install npm
npm install --global vue-cli
```

(Oder per Installer von der Website: https://nodejs.org/en/download/)

## Project setup

```
spring-boot-vuejs
├─┬ backend     → backend module with Spring Boot code
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module with Vue.js code
│ ├── src
│ └── pom.xml
└── pom.xml     → Maven parent pom managing both modules
```

## Backend

Go to https://start.spring.io/ and initialize an Spring Boot app with `Web` and `Actuator`. Place the zip’s contents in the backend folder.

Customize pom to copy content from Frontend for serving it later with the embedded Tomcat:

```
<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
    <plugin>
      <artifactId>maven-resources-plugin</artifactId>
      <executions>
        <execution>
          <id>copy Vue.js frontend content</id>
          <phase>generate-resources</phase>
          <goals>
            <goal>copy-resources</goal>
          </goals>
          <configuration>
            <outputDirectory>src/main/resources/public</outputDirectory>
            <overwrite>true</overwrite>
            <resources>
              <resource>
                <directory>${project.parent.basedir}/frontend/target/dist</directory>
                <includes>
                  <include>static/</include>
                  <include>index.html</include>
                </includes>
              </resource>
            </resources>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```


## Frontend

```
vue init webpack frontend
```

This will initialize a project skeleton for Vue.js in /frontend directory - it therefore asks some questions in the cli:

![vuejs-cli-init](https://github.com/jonashackt/spring-boot-vuejs/blob/master/vuejs-cli-init.png)

If you want to learn more about installing Vue.js, head over to the docs: https://vuejs.org/v2/guide/installation.html

### Use frontend-maven-plugin to handle NPM, Node, Bower, Grunt, Gulp, Webpack and so on :)

If you’re a backend dev like me, this Maven plugin here https://github.com/eirslett/frontend-maven-plugin is a great help for you - because, if you know Maven, that’s everything you need! Just add this plugin to the frontend’s `pom.xml`:

```
<build>
    <plugins>
        <plugin>
            <groupId>com.github.eirslett</groupId>
            <artifactId>frontend-maven-plugin</artifactId>
            <version>${frontend-maven-plugin.version}</version>
            <executions>
                <!-- Install our node and npm version to run npm/node scripts-->
                <execution>
                    <id>install node and npm</id>
                    <goals>
                        <goal>install-node-and-npm</goal>
                    </goals>
                    <configuration>
                        <nodeVersion>v9.11.1</nodeVersion>
                    </configuration>
                </execution>
                <!-- Install all project dependencies -->
                <execution>
                    <id>npm install</id>
                    <goals>
                        <goal>npm</goal>
                    </goals>
                    <!-- optional: default phase is "generate-resources" -->
                    <phase>generate-resources</phase>
                    <!-- Optional configuration which provides for running any npm command -->
                    <configuration>
                        <arguments>install</arguments>
                    </configuration>
                </execution>
                <!-- Build and minify static files -->
                <execution>
                    <id>npm run build</id>
                    <goals>
                        <goal>npm</goal>
                    </goals>
                    <configuration>
                        <arguments>run build</arguments>
        </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

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

Now go to http://localhost:8088/ and have a look at your first Vue.js Spring Boot App.



## fast feedback with webpack-dev-server

The webpack-dev-server, which will update and build every change through all the parts of the JavaScript build-chain, is pre-configured in Vue.js out-of-the-box! So the only thing needed to get fast feedback development-cycle is to cd into `frontend` and run:

```
npm run dev
```

That’s it! 


## Browser developer tools extension

Install vue-devtools Browser extension https://github.com/vuejs/vue-devtools and get better feedback, e.g. in Chrome:

![vue-devtools-chrome](https://github.com/jonashackt/spring-boot-vuejs/blob/master/vue-devtools-chrome.png)



## HTTP calls from Vue.js to (Spring Boot) REST backend

Prior to Vue 2.0, there was a build in soultion (vue-resource). But from 2.0 on, 3rd party libraries are necessary. One of them is [Axios](https://github.com/mzabriskie/axios) - also see blog post https://alligator.io/vuejs/rest-api-axios/

```
npm install axios --save
```

Calling a REST service with Axios is simple. Go into the script area of your component, e.g. Hello.vue and add:

```
import axios from 'axios'

data () {
  return {
    response: [],
    errors: []
  }
},

callRestService () {
  axios.get(`api/hello`)
    .then(response => {
      // JSON responses are automatically parsed.
      this.response = response.data
    })
    .catch(e => {
      this.errors.push(e)
    })
}
}
```

In your template area you can now request a service call via calling `callRestService()` method and access `response` data:

```
<button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service</button>

<h3>{{ response }}</h3>
```

### The problem with SOP

Single-Origin Policy (SOP) could be a problem, if we want to develop our app. Because the webpack-dev-server runs on http://localhost:8080 and our Spring Boot REST backend on http://localhost:8088.

We need to use Cross Origin Resource Sharing Protocol (CORS) to handle that (read more background info about CORS here https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS)

#### Enabling Axios CORS support

Create a central Axios configuration file called `http-commons.js`:

```
import axios from 'axios'

export const AXIOS = axios.create({
  baseURL: `http://localhost:8088`,
  headers: {
    'Access-Control-Allow-Origin': 'http://localhost:8080'
  }
})
```

Here we allow requests to the base URL of our Spring Boot App on port 8088 to be accessable from 8080.

Now we could use this configuration inside our Components, e.g. in `Hello.vue`:
```
import {AXIOS} from './http-common'

export default {
  name: 'hello',

  data () {
    return {
      posts: [],
      errors: []
    }
  },
  methods: {
    // Fetches posts when the component is created.
    callRestService () {
      AXIOS.get(`hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.posts = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
```

#### Enabling Spring Boot CORS support

Additionally, we need to configure our Spring Boot backend to answer with the appropriate CORS HTTP Headers in it’s responses (theres a good tutorial here: https://spring.io/guides/gs/rest-service-cors/). Therefore we add the annotation `@CrossOrigin` to our BackendController:

```
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/hello")
public @ResponseBody String sayHello() {
    LOG.info("GET called on /hello resource");
    return HELLO_TEXT;
}
```

Now our Backend will responde CORS-enabled and accepts requests from 8080. But as this only enables CORS on one method, we have to repeatately add this annotation to all of our REST endpoints, which isn’t a nice style. We should use a global solution to allow access with CORS enabled to all of our REST resources. This could be done in the `SpringBootVuejsApplication.class`:

```
// Enable CORS globally
@Bean
public WebMvcConfigurer corsConfigurer() {
  return new WebMvcConfigurerAdapter() {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/api/*").allowedOrigins("http://localhost:8080");
    }
  };
}
```

Now all calls to resources behind `api/` will return the correct CORS headers. 

#### But STOP! Webpack & Vue have something much smarter for us to help us with SOP!

Thanks to my colleague [Daniel](https://www.codecentric.de/team/dre/) who pointed me to the nice proxying feature of Webpack dev-server, we don´t need to configure all the complex CORS stuff anymore!

According to [Vue.js Webpack Template](https://vuejs-templates.github.io/webpack/) the only thing we need to [configure is a Proxy](https://vuejs-templates.github.io/webpack/proxy.html) for our webpack dev-server requests. This could be done easily in the [frontend/config/index.js](https://github.com/jonashackt/spring-boot-vuejs/blob/master/frontend/config/index.js) inside `dev.proxyTable`: 

```
dev: {
    ...
    proxyTable: {
      // proxy all webpack dev-server requests starting with /api to our Spring Boot backend (localhost:8088)
      '/api': {
        target: 'http://localhost:8088',
        changeOrigin: true
      }
    },
```

With this configuration in place, the webpack dev-server uses the [http-proxy-middleware](https://github.com/chimurai/http-proxy-middleware), which is a really handy component, to proxy all frontend-requests from http://localhost:8080 --> http://localhost:8088 - incl. Changing the Origin accordingly.

This is used in the [frontend/build/dev-server.js](https://github.com/jonashackt/spring-boot-vuejs/blob/master/frontend/build/dev-server.js) to configure the proxyMiddleware (you don´t need to change something here!):

```
// proxy api requests
Object.keys(proxyTable).forEach(function (context) {
  var options = proxyTable[context]
  if (typeof options === 'string') {
    options = { target: options }
  }
  app.use(proxyMiddleware(options.filter || context, options))
})
```


## Bootstrap & Vue.js

There’s a nice integration of Bootstrap in Vue.js: https://bootstrap-vue.js.org/

```
npm install bootstrap-vue
```

Now you can use all the pretty Bootstrap stuff with ease like:

```
<b-btn @click="callRestService()">CALL Spring Boot REST backend service</b-btn>
```

instead of

```
<button type="button" class=”btn” @click="callRestService()">CALL Spring Boot REST backend service</button>
```

The docs contain all the possible components: https://bootstrap-vue.js.org/docs/components/alert/

See some elements, when you go to http://localhost:8080/#/bootstrap/ - this should look like this:

![bootstrap-styled-vuejs](https://github.com/jonashackt/spring-boot-vuejs/blob/master/bootstrap-styled-vuejs.png)

A good discussion about various UI component frameworks: http://vuetips.com/bootstrap


## Heroku Deployment

As you may already read, the app is automatically deployed to Heroku on https://spring-boot-vuejs.herokuapp.com/.

The project makes use of the nice Heroku Pipelines feature, where we do get a full Continuous Delivery pipeline with nearly no effort:

![heroku-pipeline](heroku-pipeline.png)

And with the help of super cool `Automatic deploys`, we have our TravisCI build our app after every push to master - and with the checkbox set to `Wait for CI to pass before deploy` - the app gets also automatically deployed to Heroku - but only, if the TravisCI (and Coveralls...) build succeeded:

![heroku-automatic-deploys](heroku-automatic-deploys.png)

You only have to connect your Heroku app to GitHub, activate Automatic deploys and set the named checkbox. That´s everything!


#### Accessing Spring Boot REST backend on Heroku from Vue.js frontend

Frontend needs to know the Port of our Spring Boot backend API, which is [automatically set by Heroku every time, we (re-)start our App](https://stackoverflow.com/a/12023039/4964553).

> You can [try out your Heroku app locally](https://devcenter.heroku.com/articles/heroku-local)! Just create a .env-File with all your Environment variables and run `heroku local`! 

To access the Heroku set port, we need to use relative paths inside our Vue.js application instead of hard-coded hosts and ports! 

All we need to do, is to configure Axios in such a way inside our [frontend/src/components/http-common.js](https://github.com/jonashackt/spring-boot-vuejs/blob/master/frontend/src/components/http-common.js):

```
export const AXIOS = axios.create({
  baseURL: `/api`
})
```



# Links

Nice introdutory video: https://www.youtube.com/watch?v=z6hQqgvGI4Y

Examples: https://vuejs.org/v2/examples/

Easy to use web-based Editor: https://vuejs.org/v2/examples/

http://vuetips.com/

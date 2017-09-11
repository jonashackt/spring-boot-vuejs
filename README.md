spring-boot-vuejs
=============================
[![Build Status](https://travis-ci.org/codecentric/cxf-spring-boot-starter-maven-plugin.svg?branch=master)](https://travis-ci.org/codecentric/cxf-spring-boot-starter-maven-plugin)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/jonashackt/spring-boot-vuejs/blob/master/LICENSE)


## In Search of a new Webfrontend-Framework after 2 Years of absence...

Well I´am not a Frontend developer. I´am more like playing around with Spring Boot, Web- & Microservices & Docker, automating things with Ansible and Docker, Scaling things with Spring Cloud, Docker Compose and Traefik... And the only GUIs I´am building are the "new JS framework in town"-app every two years... :) So the last one was Angular 1 - and it felt, as it was a good choice! I loved the coding experience and after a day of training, I felt able to write awesome Frontends...

But now we´re 2 years later and I heard from afar, that there was a complete rewrite of Angular (2), a new kid in town from Facebook (React) and lot´s of ES201x stuff and dependency managers like bower and Co. So I´am now in the new 2-year-cycle of trying to cope up again - and so glad I found this article: https://medium.com/reverdev/why-we-moved-from-angular-2-to-vue-js-and-why-we-didnt-choose-react-ef807d9f4163

Key points are:
* Angular 2 isn´t the way to go, if you know version 1 (complete re-write, only with Typescript, loss of many of 1´s advantages, Angular 4 is coming)
* React  (facebook´ish problems (licence), need to choose btw. Redux & MObX, harder learning curve, slower coding speed)

![comparison-angular-react-vuejs](https://github.com/jonashackt/spring-boot-vuejs/blob/master/comparison-angular-react-vuejs.png)

And the [introduction phrase](https://vuejs.org/v2/guide/index.html) sounds really great:

> Vue (pronounced /vjuː/, like view) is a progressive framework for building user interfaces. Unlike other monolithic frameworks, Vue is designed from the ground up to be incrementally adoptable. The core library is focused on the view layer only, and is very easy to pick up and integrate with other libraries or existing projects. On the other hand, Vue is also perfectly capable of powering sophisticated Single-Page Applications when used in combination with modern tooling and supporting libraries.

So I think, it could be a good idea to invest a day or so into Vue.js. Let´s have a look here!



## Setup Vue.js & Spring Boot

### Prerequisites

##### MacOSX

`brew install node`

`brew install npm`

`npm install --global vue-cli`

## Project setup

```
spring-boot-vuejs
├── backend  		--> backend module with Spring Boot stuff
│	├── src
│	├── pom.xml
├── frontend		--> frontend module with Vue.js stuff
│	├── pom.xml
├── pom.xml  		--> Maven parent pom with modules
```


## Backend

Go to https://start.spring.io/ and initialize an Spring Boot app with `Web` and `Actuator`. Place the zip´s contents in the backend folder.


## Frontend

```
vue init webpack frontend
```

This will initialize an project sceleton for Vue.JS in /frontend directory - it therefore asks some questions in the cli:

![vuejs-cli-init](https://github.com/jonashackt/spring-boot-vuejs/blob/master/vuejs-cli-init.png)

If you want to learn more about installing Vue.js, head over to the docs: https://vuejs.org/v2/guide/installation.html

### Use frontend-maven-plugin to handle NPM, Node, Bower, Grunt, Gulp, Webpack and so on :)

If you´re a backend dev like me, this Maven plugin here https://github.com/eirslett/frontend-maven-plugin is a great help for you - because, if you know Maven, that´s everything you need! Just add this plugin to the frontend´s `pom.xml`:

```
	<build>
		<plugins>
			<plugin>
				<groupId>com.github.eirslett</groupId>
				<artifactId>frontend-maven-plugin</artifactId>
				<version>1.5</version>
				<executions>
					<!-- Install our node and npm version to run npm/node scripts-->
					<execution>
						<id>install node and npm</id>
						<goals>
							<goal>install-node-and-npm</goal>
						</goals>
						<configuration>
							<nodeVersion>v6.11.3</nodeVersion>
							<npmVersion>5.4.1</npmVersion>
							<nodeDownloadRoot>https://nodejs.org/dist/</nodeDownloadRoot>
							<npmDownloadRoot>http://registry.npmjs.org/npm/-/</npmDownloadRoot>
						</configuration>
					</execution>
					<!-- Set NPM Registry -->
					<execution>
						<id>npm set registry</id>
						<goals>
							<goal>npm</goal>
						</goals>
						<configuration>
							<arguments>config set registry https://registry.npmjs.org</arguments>
						</configuration>
					</execution>
					<!-- Set SSL privilege -->
					<execution>
						<id>npm set non-strict ssl</id>
						<goals>
							<goal>npm</goal>
						</goals>
						<!-- Optional configuration which provides for running any npm command -->
						<configuration>
							<arguments>config set strict-ssl false</arguments>
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

Now go to http://localhost:8080/ and have a look at your first Vue.js Spring Boot App:

![localhost-first-run](https://github.com/jonashackt/spring-boot-vuejs/blob/master/localhost-first-run.png)









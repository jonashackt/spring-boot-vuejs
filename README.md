* frontend and backend are now independent modules
* docker images are built as different aritifacts (not attached)
* added a docker-compose file but I may remove it and use a maven plugin to start the composition before integration tests
* removed all the noise from the readme


usage

prerrequisites
* you need to have docker installed and running
* maven installed

build
* run mvn clean install
* integration tests should run and pass

run
* to run the applications, after a full build of the project, 
* cd e2e-test/src/test/resources/ && docker-compose up



changes:
refactor to follow a pipeline and have independent projects
- addedusage of docker-maven-plugin to build the images
- removed the helper copy thing from the backend
- front is packaged as a war so it may be threated as a dependency and used in the pipeline
- added a docker-compose file that may be removed later whenever I introduce the use of docker-compose-maven-plugin before and after integration or e2e tests. Those should be run in separated maven artifacts or later on in the reactor run
- removed all the heroku and readme manual stuff--- maybe another maven artifact or a terraform thing... donno, have to read whatever those do
- added e2e tests after deploying everything using docker compose
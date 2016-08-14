#my own building process for a http server and eventually a servlet container

## build
$ mvn clean install

### build simple Http server
$ mvn clean install -Dmain.class=net.runnerdave.my_tomcat.simple.http.HttpServer

## run
$ java -jar target/my-tomcat-0.0.1-SNAPSHOT-jar-with-dependencies.jar
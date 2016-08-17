#my own building process for a http server and eventually a servlet container

## build
$ mvn clean install

### build simple Http server
$ mvn clean install -Dmain.class=net.runnerdave.my_tomcat.simple.http.HttpServer

### build simple servlet container
$ mvn clean install -Dmain.class=net.runnerdave.my_tomcat.servlet.container.HttpServer1

### build simple servlet container with Facade for protection
$ mvn clean install -Dmain.class=net.runnerdave.my_tomcat.servlet.container.HttpServer2

## run
$ java -jar target/my-tomcat-0.0.1-SNAPSHOT-jar-with-dependencies.jar
# Robot-Hover-Service

This is a solution to the test https://github.com/lampkicking/java-backend-test.

### Prerequisite

* Java 7/8 http://www.oracle.com/technetwork/java/javase/downloads/index.html
* Tomcat 8.0 http://tomcat.apache.org/
* Maven 3 https://maven.apache.org/
* MongoDB 3 https://www.mongodb.com/

### MongoDB

Since the problem requires every input and output written into a database, please make sure MongoDB is installed.

Once it is installed, please check the parameters specified in 

```
{RobotHoverService}\src\main\resources\applicationcontext.xml
```

and change the relevant parameters in the bean with id "mongoDBWriterImpl".

The default settings are as follows:

* URL - localhost
* Port number - 27017
* Database name - testDB
* Collection name - testCol

Note: the web service also provides a resource which generate the same results excepting writting input and output into database. Therefore, This resource can be used if using MongoDB is not practical.

### Build From Source

Robot Hover Service is build with Apache Maven, that will handle all the necessary dependencies for its execution.

In order to build RobotHoverService, please install Maven first.

Once Maven is installed, check out the source code and use the following command to build the source code:

```
mvn package
```

Once the project is built successfully, the .war file will be generated in the target folder. Place the .war file in Tomcat's (or other similar web containers) "webapps" folder.

A simple GET request can be used to test if the web service is running:

{url}:{port}/{webservice name}/rest/robot/version 

should return web service's version:

```
1.0
```

To generate the output for solving the specified problem, please use a POST request:

```
{url}:{port}/{webservice name}/rest/robot/hover
```

along with the json payload like:

```
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

The output will be something like:

```
{"coords":[1,3],"patches":1}
```

and these input and output will be written the MongoDB.

Alternatively, to generate the output without writing into MongoDB, please use a POST request:

```
{url}:{port}/{webservice name}/rest/robot/hovernodb
```

and the output should be the same as above.

Note: the syntax and the input values in the json payload will be validated in the web service before generating outputs, so error message(s) will be returned if the validator finds something wrong. For example, if the payload does not have "roomSize" field:

```
{
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

the following error message will be return:

```
{"errorCode":412,"message":"Invalid input. 'roomSize' field does not exist."}
```


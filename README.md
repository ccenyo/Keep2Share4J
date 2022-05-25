# Keep2Share4J

Keep2Share4J is an open source java library for keep2share Api, it's basically a wrapper for the API.
you can find all information about the api [here](https://keep2share.github.io/api/) available for developers who want's to know how the api works.

##Prerequisites

This project is written in java 17. you can only use it in a project that uses java 17 and above.
if you want to use it in any environment, make sure you the right JVM installed on the machine.

##Integration tests

you have to define an environment file  in your resource folder``env.properties`` in which you have to put this lines :

| name=value |                                                             
| --- |                                                                     
| access_token=token | 


## How to use it

add the maven repository to your pom.xml

```java
<dependency>
  <groupId>org.github</groupId>
  <artifactId>keep2share4j</artifactId>
  <version>1.0.1</version>
</dependency>
```

##Basic usage


you can create a ``client`` object in different ways

```java
    Keep2ShareClient client = Keep2ShareClient.fromResource("env.properties");
```
or
```java
    Keep2ShareClient client = Keep2ShareClient.fromFile(new File("path"));
```
or
```java
    Keep2ShareClient client = Keep2ShareClient.of("token");
```


Once you have the client, you can call the function you want to use.

```java
        var command = client.upload(new File("src/test/resources/test.txt"));
        var response = command.call();
```

```java
        var command = client.listFolders();
        var response = command.call();
```


## Built With
* [Java SDK 17](https://www.oracle.com/technetwork/java/javase/downloads/jdk17-downloads-2133151.html) -  Java™ Platform
* [Maven](https://maven.apache.org/) - Dependency Management

## Author
* **Cenyo Medewou** - [medewou@gmail.com](mailto:medewou@gmail.com).

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details                                                                        


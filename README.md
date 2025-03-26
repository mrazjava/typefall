# Getting Started

## Development & Logging
Viewing logs while testing in console interferes with the actual console UI. Rather than 
logging to `STD_OUT` during development, it may be more convenient 
to log to a file (rolling file setup in log4j2.xml) and view logs with something like:

```
tail -f logs/application.log
multitail -cS log4jnew logs/application.log
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


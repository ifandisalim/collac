[<img src="https://img.shields.io/travis/playframework/play-java-starter-example.svg"/>](https://travis-ci.org/playframework/play-java-starter-example)

# Collac

## Running


Run this using [sbt](http://www.scala-sbt.org/). 
There is a pre-packaged sbt in the project directory.

```
./sbt run
```

And then go to http://localhost:9000 to see the running web application.

## Environment Variables
This application uses MySQL. Set the values for below variables:
1. DB_URL
2. DB_USER
3. DB_PASSWORD

Example.
DB_URL = jdbc:mysql://localhost:3306/collac?useSSL=false&serverTimezone=UTC

DB_USER = root
DB_PASSWORD = root


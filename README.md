# Demo Financial API

## Table of content
- [Description](#description)
- [Assumptions](#assumptions)
- [Documents](#documents)
- [Architecture and Design](#architecture-and-design)
- [Environment](#environment)
- [Build and Run](#build-and-run)
- [Suggestions](#suggestions)

## Description

This application is an API responsible to store data from financial transactions of customers.
This API contains the following functionalities:
- Create transactions
- Create account
- Find transactions by filter

Source account needs to be set up in application.yml. 
All of the settings of the Application are located in application.yaml.

## Assumptions

- You will need Java 17 version because of use Spring Boot 3.
- We are considering to run application o Intellij.

## Documents

The documents are located in Docs folder in the root folder.

## Architecture and Design

- In this application we choose a Hexagonal Architecture
- Isolate class in layers:
    - Layer for business rules (package domain.adapter.services)
    - Layer for input (read files) classes (package 'application.controller'.) for rest apis
    - Layer for infrastructure classes (package infrastructure) for files and database access and external APIs
    - Layer for domain classes (package domain)

## Environment

This application requires the following requirements to build and run:
- **[Required]** [Java17](https://www.java.com/en/download/help/whatis_java.html) - Programming language version 17
- [IntelliJ](https://www.jetbrains.com/idea/) - Recommended IDE
- **[Required]** [Gradle](https://gradle.org/) - Dependency Management

This application can run in multiple operational systems, we recommend to use Linux or Windows.

## Build and Run

This application  doesn't need any environment variables:

### Build

After clone the repository developer needs to run the following commands:

1. ``` ./gradlew build ``` this command can run the entire build process, if you need any other command please check Maven Documentation.

### Running Application

- Run via Command line:
    - ``` ./gradlew  bootRun ```

## Suggestions
- [Working with multiple versions of java](https://docs.azul.com/core/zulu-openjdk/manage-multiple-zulu-versions/linux)

## Changes
    - It needs to consider some scheduled process to update the status dinamically if won't have the final status during the syncronized  process.

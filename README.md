# Aliaksei Kaspiarovich

[![Build](https://github.com/AlekseiKasperovich/SocialNetwork/actions/workflows/maven.yml/badge.svg)](https://github.com/AlekseiKasperovich/SocialNetwork/actions/workflows/maven.yml)
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=AlekseiKasperovich_SocialNetwork)

## SpringBoot Social Network Project

![image](https://images.theconversation.com/files/198568/original/file-20171211-15358-w51s6s.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=926&fit=clip)

## Description

- [Class Diagram (UML)](https://github.com/AlekseiKasperovich/SocialNetwork/blob/master/info/class%20diagramm/Class%20Diagram.pdf)
- [Entity Relationship Diagram (MySQL)](https://github.com/AlekseiKasperovich/SocialNetwork/blob/master/info/mysql_db/ERD.pdf)
- [Entity Relationship Diagram (PostgreSQL)](https://github.com/AlekseiKasperovich/SocialNetwork/blob/master/info/mysql_db/ERD.pdf)

## [Functionality](https://github.com/AlekseiKasperovich/SocialNetwork/blob/master/info/class%20diagramm/Functionality.pdf)

### Base

- Registration
- Login
- View own profile
- Update/delete profile
- Update photo
- Change password
- Send new password to email, if you forgot your password
- Send reset password link to email, if you forgot your password
- Search for other users
- View other users profiles
- Send/update/delete private/public messages
- View the list of messages
- Add/remove friends
- View friends list
- Accept/decline friendship invitation
- Join/leave communities
- Send/update/delete messages to communities
- Create/update/delete event
- Send/update/delete messages to events
- Add/remove friends to events
- View the list of communities/events
- View messages on communities/events
- Internationalization: English and Russian languages
- Localization

### Admin

- Create/update/remove communities
- Block/unblock user

## Technologies used

### Stack:

- Java 11
- Spring: SpringBoot, MVC, Data JPA, Security, Cloud, Mail, DevTools, Validation
- Maven - tool that can be used for building and managing any Java-based project.
- H2, PostgreSQL - databases.
- Lombok - reducing boilerplate code.
- Orika - is a Java Bean mapping framework that recursively copies data from one object to another.
- JSON Web Token - allows you to decode, verify and generate JWT.
- SpringFox - JSON API documentation for spring based applications.
- Passay - password validation and generation.
- MiniO - image storage
- Redis - image cache
- Thymeleaf is a modern server-side Java template engine for both web and standalone environments.
- Bootstrap - powerful, extensible, and feature-packed frontend toolkit.
- HTML/CSS
- Junit5, Mockito, MockMvc, Testcontainers - testing tools / libraries
- Liquibase - Fast database change. Fluid delivery.

### Environment

- Docker for containerization

## Quick start

### Required:

- Java 11
- Maven
- Docker

### Steps:

```
- git clone https://github.com/AlekseiKasperovich/SocialNetwork.git
- cd SocialNetwork
- mvn clean package
- docker-compose -f docker-compose.yml up -d --build
- Go to http://localhost:8080/swagger-ui/ - read api documentation
- Go to http://localhost:8082/ - test user interface with credentials:
  email - admin@gmail.com; password - Admin1
  email - user@gmail.com; password - User1
  or register and use your profile.
```

## Postman:

You can import API specifications directly into Postman. To import data into Postman, select Import in upper left:

- select "Upload Files"
- choose SocialNetwork\info\postman\postman_backup
- click "Import"

Go to {{baseUrl}}/api/auth/login

- You can login as user with role "Admin":
- body:
  {
  "email": "admin@gmail.com",
  "password": "Admin1"
  }

... and with role "User":

- body:
  {
  "email": "user@gmail.com",
  "password": "User1"
  }

Not secure requests:

- {{baseUrl}}/api/auth/registration
- {{baseUrl}}/api/auth/login
- {{baseUrl}}/api/auth/password/new

If you want to send any other request, then you need to add the Bearer token to the Authorization header.

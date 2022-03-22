# Aliaksei Kaspiarovich 

## SpringBoot Social Network Project

## Functionality
### Base
- Registration
- Login
- View own profile
- Update/delete profile
- Change password
- Send new password to email, if you forgot your password
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
- Spring: SpringBoot, MVC, Data JPA, Security, Mail, DevTools, Validation
- Maven - tool that can be used for building and managing any Java-based project. 
- H2, PostgreSQL - databases.
- Lombok - reducing boilerplate code.
- Orika - is a Java Bean mapping framework that recursively copies data from one object to another.
- JSON Web Token - allows you to decode, verify and generate JWT.
- SpringFox - JSON API documentation for spring based applications.
- Passay - password validation and generation.
### Environment
- Docker for containerization
 
 ## Quick start
 
 ### Required:
 - Java 11
 - Maven 
 - Docker
 
 ### Steps:
 - git clone --branch=final https://git-students.senla.eu/brest-java-ee-training/aliaksei-kaspiarovich.git
 - cd aliaksei-kaspiarovich/SocialNetwork
 - mvn clean package 
 - cd ..
 - cd WebProject
 - mvn clean package 
 - cd ..
 - docker-compose up -d
 - Go to http://localhost:8080/swagger-ui/ - read api documentation

 ## Postman:
 You can import API specifications directly into Postman. To import data into Postman, select Import in upper left:
  - select "Upload Files"
  - choose aliaksei-kaspiarovich\info\postman\postman_backup
  - click "Import"

Go to {{baseUrl}}/api/auth/login
- You can login as user with role "Admin":
 - body:
{
  "email": "admin@gmail.com",
  "password": "Admin1",
  "matchingPassword": "Admin1"
}

... and with role "User":
 - body:
{
  "email": "user@gmail.com",
  "password": "User1",
  "matchingPassword": "User1"
}

Not secure requests:
- {{baseUrl}}/api/auth/registration
- {{baseUrl}}/api/auth/login
- {{baseUrl}}/api/auth/password/new

If you want to send any other request, then you need to add the Bearer token to the Authorization header.

## Spring-Boot-Blog-Application

Hi guys, here is a spring boot blog application 
that contains the RestApi that allows communication between the server and the client,
this application is deployed on AWS and uses MySql to save and retrieve data.

This appication consists of 9 directories, which are

## 1- Config

this package contains the SecurityConfig class that contains the configuration of the following beans

# a- passwordEncoder bean :
this bean is responsible for encrypting the password users enters when registering or logging in and stores the encrypted version in the database.

# b- authenticationManager bean :
this beans authenticates users and ensure the only request/access resources their role is designated to.

# c- securityFilterChain bean :
this bean contains the logic that authorizes http requests and grant permit to get request that contains api/v1/auth/** or api/v1/**





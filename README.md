# Spring-Boot-Blog-Application

Hi guys, here is a spring boot blog application 
that contains the RestAPIs that enables communication between the server and the client,
this application is deployed on AWS, tested with Postman and uses MySql to store and retrieve data.

the functionalities of this blog app allows users to 

- get post, get post by post id, get all post by category id
- comment on post, get comment by commentid, get all comment by post id
- get category

and allows admin to
- create post, get post by post id, get all post by category id, update post by postId, delete post by id
- create comment, get comment by commentid, get all comment by post id, update comment, delete comment
- create category, get category, update category, delete category

This appication consists of 9 directories, which are

## 1- Config

this package contains the SecurityConfig class that contains the configuration of the following beans

- passwordEncoder bean :
this bean is responsible for encrypting the password users enters when registering or logging in and stores the encrypted version in the database.

- authenticationManager bean :
this beans authenticates users and ensure that users only request/access resources their role is designated to.

- securityFilterChain bean :
this bean contains the logic that authorizes http requests and grant permit to GET requests that contains api/v1/auth/** or api/v1/**

## 2- Controller

the controller pacakage contains the RestAPIs methods that contains the logic for the implentation of the functionalities of the blog app.
the controllers are 

- AuthController:
  this contains the POST methods that allows users register/signup or login/signin.
  
- CategoryController:
  this contains the CRUD logic for category
  
- CommentController: 
  this contains the CRUD logic for comment
  
- PostController:
  this contains the CRUD logic for post
  
## 3- Entity

this package defines the Entity classes, their attributes, their naming conventions in the database and the kind of relationship they have with one another.

## 4- Exception

this package handles the implementation of the BlogApiExceptions, ResourceNotFoundExceptions and GlobalExceptionHandler

## 5- Payload

this package contains the DTO <data transfer objects> that is used to communicate between the server and the clients, It's a good practise and it promote security
  
  
  

 
 
  
  





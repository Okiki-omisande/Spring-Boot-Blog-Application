# Spring-Boot-Blog-Application

Hi guys, here is a spring boot blog application 
that contains the RestApi that allows communication between the server and the client,
this application is deployed on AWS and uses MySql to save and retrieve data.

the functionalities of this blog app allows users to 

- create post
- comment on post
- create category
- get post, get post by post id, get all post by category id, update post by postId, delete post by id
- get comment by commentid, get all comment by post id, update comment, delete comment
- get category, update category, delete category

This appication consists of 9 directories, which are

## 1- Config

this package contains the SecurityConfig class that contains the configuration of the following beans

- passwordEncoder bean :
this bean is responsible for encrypting the password users enters when registering or logging in and stores the encrypted version in the database.

- authenticationManager bean :
this beans authenticates users and ensure the only request/access resources their role is designated to.

- securityFilterChain bean :
this bean contains the logic that authorizes http requests and grant permit to get request that contains api/v1/auth/** or api/v1/**

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
  





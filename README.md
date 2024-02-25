# About the Dog Grooming API

I created this API as part of my partner's plan to 
create a home dog grooming business. The project
consists of a ReactJS UI/UX with endpoints from this API. 
---
## Description:
The application currently handles user creation, logging in,
and maintaining a session with JWTs. There's only one other 
related table at the moment: dogs. Users can add dogs with
attributes such as name, age, breed, and bite risk. The dogs 
have a many-to-one relationship with the user by foreign key.
Utilizing Spring Security and JWTs, dogs can only be created
and accessed with the JWT generated upon registering or logging in.
---
## Getting Started
*You must have a PSQL database set up locally.
You can enter your postgres credentials on lines 3 & 4
of application.properties*

To Demo the API, run the DogGroomingApiApplication file.
You can follow the provided list of endpoints as it follows
a typical usage scenario (i.e. register, then login, then add dogs).

### Endpoints:

**Register User**:  
*URL endpoint:*  
/users/register  

*Request body:*  
{  
"username": "dogperson321",  
"firstName": "John",  
"lastName": "Smith",  
"password": "password123",  
"matchPassword": "password123",  
"email": "gmail@hmail.com",  
"phoneNumber": "123-123-4321",   
"role": "USER",  
"_NOTE1": "role can either be "USER" or "ADMIN" but
neither have any effect at the moment until I set up authorities"  
}  

*At this point you'll have a token (the user will be logged in 
after registering). The Tokens are set to expire 24 hours after 
generation (registering and logging in).*

**Login User**:  
*URL endpoint:*  
/users/login

*Request body:*  
{  
"username": "dogperson321",  
"password": "password123"
}  

*To create a dog, you need to add the token to the header.
If you're using postman, you can add the token to the authorization 
header with the option **Bearer Token** selected. Otherwise, 
add a header beginning with the key: Authorization and a
value of "Bearer_" + {token} (where _ is just a blank space).*

**Add dog**:  
*URL endpoint:*  
/users/login

*Request body:*  
{  
"_NOTE1": "userId must match the user created and whose active token
is in the header of this request",  
"userId": 1,  
"name": "Santa's little helper",  
"breed": "simpson",  
"weight": 10,  
"isBiteRisk": false,  
"birthday": "12-17-1989"  
}  

---

### Dependencies:

* Spring Boot Starter Web
* Spring Boot Starter Data JPA
* Spring Boot Starter Test
* Spring Boot Starter Security
* PostgreSQL Driver
* Lombok
* Jakarta Persistence
* Jakarta Validation
* Apache Commons Lang3
* JWT Dependencies
    * JWT Impl
    * JWT Jackson
    * JWT API
## About the Dog Grooming API

I created this API as part of my partner's plan to 
create a home dog grooming business. The project
consists of a ReactJS UI/UX with endpoints from this API. 
---
### Description:
The application currently handles user creation, logging in,
and maintaining a session with JWTs. There's only one other 
related table at the moment: dogs. Users can add dogs with
attributes such as name, age, breed, and bite risk. The dogs 
have a many-to-one relationship with the user by foreign key.
Utilizing Spring Security and JWTs, dogs can only be created
and accessed with the JWT generated upon registering or logging in.

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

### Getting Started
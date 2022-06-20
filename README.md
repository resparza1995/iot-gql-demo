# iot-gql-demo
Demo for the IoS and IoT course.    
Basic implementation of a basic CRUD in Java spring and graphql.  

## Operations
 - Do a query. 
 - Add a new movie   
 - Edit a movie    
 - Delete a movie

## Packages
**Model:** Contains the Entity that is being worked on in the DB and the corresponding DTO.     
**Repository:** Contains the interface to implement the JPA Repository which provides the operations with the DB.    
**Resource:** Here is configured the endpoint /films with the GQL sentences executer.     
**Service:** Contains the service with the methods that implement the operations defined in the schema films.graphql and the class that initializes the GQL service.
initializes the GQL service.     



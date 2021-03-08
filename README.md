# SIR-TP5-API-Kanban
TP2-4 et TP5, Systèmes d'information répartis. <br/> <br/>

##### AUTORS
  * [Bonaventure Gbehe](https://github.com/TodiBG)
  * [Ange Sibomana](https://github.com/angecla)
<br/> 

### Table of contents
1. [About](##ABOUT)
2. [Tools](##TOOLS)
3. [Technologies](##TECHNOLOGIES)
4. [Install](##INSTALL)
5. [Get started](###G)

### ABOUT
***
This application is an API developed during TP2-4 and TP5 of the distributed information system lab.

 Follow [this link](https://docs.google.com/document/d/1H3YU4agteIqMysxSWKxEWEqjpQs0kIVQa3gQQG27CXc/edit)  to see the statement of TP5 lab. This TP5 is based on TP2-4 whose statement is [here](https://docs.google.com/document/d/1IfN_LvfZCZJIu4aNO3_2zpZqAsjObqWRd8Bs4sYtN1I/edit). 
<br> TP2-4 and TP5 can be found in this application. And here is the composition of the application in detail : 
* <b>TP2-4<b/>
    * Writing business classes,
    * Implementation of relational mapping with JPA and Hibernate, connection to the database,
    * Writing DAO (Data Access Objects) which are service classes allowing to populate the database and also to make queries on the database.

* TP5 (API REST) <br>
    * Make TP2-4 a backend (make an API for the Frontend application which will be developed in TP6 and TP7)
    * Set up the REST API
    * Set up API documentation using OpenAPI and Swagger UI. 


### TOOLS
***
 * IDE: 
    * <b>IntelliJ IDEA<b>
      <br>Please click [here](https://www.jetbrains.com/idea/) for more informations about IntelliJ IDEA.
      
     
 * VERSION CONTROL:
    * <b>Git<b>
        <br>Please click [here](https://git-scm.com/) for more informations about Git.
    * <b>Github<b>
      <br>Please click [here](https://github.com/) for more informations about Github.
      

 * PROJET MANAGEMENT TOOL:
    * <b>Apache Maven<b>
      <br>Please click [here](https://maven.apache.org/) for more informations about Maven.

### TECHNOLOGIES   
***
 * java 
 * JPA : [here](https://www.baeldung.com/learn-jpa-hibernate) 
 * Hibernate : [here](https://hibernate.org/)
 * MySQL : [here](https://www.mysql.com/) or , Xampp : [here](https://www.apachefriends.org/fr/index.html)
 * API REST :  [here](https://www.restapitutorial.com/)
 * OpenAPI : [here](https://www.openapis.org/) 
 * Swagger: [here](https://swagger.io/docs/specification/about/)  
 * Postman : [here](https://www.postman.com/)


### INSTALL
***

Requirements :
- IntelliJ IDEA or Eclipse IDE
- Apache Maven
- MySQL


 1. First you must have [Apache Maven](https://maven.apache.org/) insatalled in your computer.  If you dont have Apache Maven then you can get it [here](https://maven.apache.org/).
   
 
 2. Clone the projet or download it from the Github repository.
   
 
 3. Import the projet in your IDE ( IntelliJ IDEA or Eclipse IDE) as Maven project. This can take some minutes while Maven downloads all dependancies in the project. 
  
  
 4. For the application's well running you must have MySQL installed in your computer. If you dont have MySQL then you can get it  [here](https://www.apachefriends.org/fr/index.html) (xampp). In the following instructions we assume that you have MySQL well installed in your computer.
    

 5. In order not to cause you to change anything in the source code of the application, create a new user named `binomes` for MySQL (or PhpMyAdmin if you use Xammp) and give him `binAB01`  as password. Then create a new database named `kanban` for the new user that you newly created and give him all right on this database.

    
 6. To test the application we will help you by populating the database quickly. There is at the root of the project a file named `kanban.sql`. Import the content of this file in `binAB01` database. 
    

 7. You could skip steps 5 and 6. You will follow this step only if you didn't steps 5 and 6. So edit this file `src/main/resources/META-INF/persistence.xml`  by changing the database name, username and the user password.  Don't forget to change the line 8 `<property name="hibernate.hbm2ddl.auto" value="update"/>` to  `<property name="hibernate.hbm2ddl.auto" value="create"/>`.
    

 8. Run this file :  `src/main/java/fr/istic/sir/Main.java`. Then the API is running on port 8080. 
    

 9. Open this URL (`http://localhost:8080/docs/`) in your web browser or click [here](http://localhost:8080/docs/) to see the API's documentation, how to use it.

 10. The end. 


### GET STARTED
***
This part assumes that you have already followed the previous part. 
* Run this file :  `src/main/java/fr/istic/sir/Main.java`. Then the API is running on port 8080.
* Open this URL (`http://localhost:8080/docs/`) in your web browser or click [here](http://localhost:8080/docs/) to see the API's documentation, how to use it.

<br/> <br/> <br/> 

***
* We have reused some code of our teacher from [this repository](https://github.com/barais/tpjpa2020).
* Thanks [Mr Olivier Barais](https://github.com/barais).  


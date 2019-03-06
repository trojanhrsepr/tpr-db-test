##JDBC Basics

JDBC (Java Database Connectivity) is a Java based API to connect to a variety of relational databases. 
Most commonly used in Web Applications hosted by JEE.

Alternatives would be Spring JDBC Template, JPA and Hibernate (Object relational mapping, ORM API)

###Drivers
JDBC Drivers are software libraries communicating between Java application and Database
Types of Drivers:

####JDBC-ODBC Bridge
Communicates through Open Database Connection Bridge from JDBC. Layers would be:
JDBC->Bridge Driver->ODBC Driver->DB
These can talk to any database, but can't be migrated across OS as not purely Java. Also drivers
must be on same computer as Application

####Native API
Has better performance than bridge and communicates through OS specific API. Again not 100% Java, 
and app client should be on same machine as driver. Layers would be:
JDBC->Native API driver->Native API->DB

####All Java/Network Protocol Driver
Communicates through network to middleware server. Server based driver independent from client. 
Layers: JDBC->Type3 Driver->Middleware->DB

####100% Java thin driver
Communicates directly from application to database. 100% Java, no additional layers, driver installed
with application. Needs separate driver for every kind of DB. Layers: JDBC->Thin Driver->DB

###Code
Each instance of ResultSet has an in-memory cursor whose starting position is before first row of data
Procedures with parameters can be called using prepareCall method of CallableStatement
Serializable is implemented in a class means that it can be stored in the local disk as data




# LibAccess - A Role-Based Library Management System

## Overview
- Backend-focused Library Management System built using Java Servlets, JSP, and Hibernate.
- Implements role-based access control and core library operations.

## Featues
- Role-based authentication (Student, Admin, SuperAdmin)
- Session-based login system
- Admin approval workflow by SuperAdmin
- Book management (Add, Update, Delete, Search)
- Book issue and return system
- Automatic fine calculation for late returns
- Students can view:
  - Available books
  - Issued books
  - Pending fines

## Tech-Stack
- Servlets
- Hibernate
- JSP
- PostgreSQL
- Maven
- Apache Tomcat

## Project Structure
- controller/ → Servlets
- dao/ → Database operations
- model/ → Entities
- filter/ → Auth & role filters
- util/ → Hibernate config
- webapp/ → JSP files


## How to Run
- Clone the repository
- Configure PostgreSQL database
- Update hibernate.cfg.xml
- Run:
  - mvn clean install
- Deploy WAR to Tomcat and start server
- Open:
  - http://localhost:8080/LibAccess/

## Author
- Krishna Pratap Singh

# bank
This is our implementation of the bank project. This readme has all the build instructions and the information about the project

## Overview
The frontend for this application is built on Angular js and the backend on Spring Boot. 
The features of this app include
1) creating an account
2) logging in
3) making transactions including deposit and withdraw
4) editing profile

## Build
### Backend
1. It is recommended that you open this project in IntelliJ
2. Download the zip for this projcet, or clone it.(it will get downloaded as bank-master)
3. Open IntelliJ and import this project using Maven as existing source
4. Select all default options and move forward.
5. Do a Maven clean
6. The database for this application is SQLite
7. You will find bank0.db inside the root folder
8. The database is already created and you don't need to create it.
9. We do recommend that you install drivers
10. click on bank0.db and it will show up in the databases tab on right side in your intellij window
11. If you can see user inside schemas > main, its good
12. otherwise click on the datasource properties option(just beside the square button in the database tab)
13. Download drivers is mentioned at the bottom, click that
14. refresh the database tab
15. Now everything is configured
16. To run the application please run src/main/java/com.group2.bank/BankApplication.java
17. You can also run the application from command line(we recommend using IntelliJ however)
18. To run from command line go to the root folder of the project(bank-master) and type "mvn spring-boot:run"
19. Application runs on 8080. So if you have any other applications running on this port, disable them first.


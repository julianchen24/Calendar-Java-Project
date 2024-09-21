# Calendar Java Project
This repository contains a Java-based Calendar Event Management System that allows users to create, view, and manage events in a MySQL database. This system features a user interface built with Java Swing for managing events, including title, description, date, and time.

## Installation
To run the project please ensure you have Java 22.0.X installed and follow the steps below:

1. Clone the repository
``` bash
$ git clone https://github.com/julianchen24/Calendar-Java-Project
$ cd Calendar-Java-Project
```
2. Install XAMPP
```
$ Download and install XAMPP version 8.2.X or later.
$ Once installed, open the XAMPP Control Panel.
```
3. Start Apache and MySQL
```
$ In the XAMPP Control Panel, click Start for both Apache and MySQL modules.
```
4. Configure the MySQL Database
```
$ Go to your browser and visit: http://localhost/phpMyAdmin/
$ Under the Databases tab, create a new database called Calendar
$ In the Calendar database, create a table called Calendar with the following 5 columns:
    - ID(Type: INT, Attributes: UNSIGNED, Set as Auto Increment by checking A_I box)
    - Title (Type: TEXT)
    - Description (Type: TEXT)
    - Date (Type: TEXT)
    - Time (Type: TEXT)
```
5. Set Database Credentials in Code
```
$ Open Database.java file in your project
$ Set MySQL username and password:

private String user = "root"; // Set to your own username
private String pass = ""; // Set to your own password

```
   
### Usage
Run the Calendar using the :

```
java -cp ".;mysql-connector-java-8.0.28.jar" Main
```

## Contributing
All contributions are welcome.


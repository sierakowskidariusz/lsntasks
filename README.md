### Requirements
Project to compile and run requires:
* JDK environment in version at least 1.8.x
* Maven build manager version 3.6.x

### Preparation for running
In the project directory, run the command:

```mvn clean package```

The result of executing the command should be the file ```tasks.jar```

### Running tasks
All commands should be executed in the project directory

#### Syntax for running task Task1

```java -cp tasks.jar pl.sierakowski.lsn.Task1 <arguments>```

Example:

```java -cp tasks.jar pl.sierakowski.lsn.Task1 1 2 3 4 2 3 4 11 -10```

#### Syntax for running task Task2

```java -cp tasks.jar pl.sierakowski.lsn.Task2 <arguments>```

Example:

```java -cp tasks.jar pl.sierakowski.lsn.Task2 10 9 8 -11 34 5 4 3 1```

#### Syntax for running task Task3

```java -cp tasks.jar pl.sierakowski.lsn.Task3 <path to data file>```

Example:

```java -cp tasks.jar pl.sierakowski.lsn.Task3 Task3.txt```

Note: in the project directory for the Task3 task an example file "Task3.txt" has already been created

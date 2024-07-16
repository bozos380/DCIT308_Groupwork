Here's the updated `README.md` file, considering you'll be using SQL Server Management Studio (SSMS):

```markdown
# Pharmacy Management System

This project is a simple Pharmacy Management System developed in Java using Swing for the user interface and SQL Server for the database. The application allows you to add and view drugs, customers, suppliers, and purchase history.

## Features

- Add and view drugs
- Add and view customers
- Add and view suppliers
- View purchase history

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- SQL Server
- SQL Server Management Studio (SSMS)

## Setup

### Database Setup

1. **Install SQL Server:**
   Download and install SQL Server from the [official website](https://www.microsoft.com/en-us/sql-server/sql-server-downloads).

2. **Install SQL Server Management Studio (SSMS):**
   Download and install SSMS from the [official website](https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms).

3. **Create Database:**
   Open SSMS and connect to your SQL Server instance. Then, create a new database named `pharmacy_db`.

   ```sql
   CREATE DATABASE pharmacy_db;
   ```

### Project Setup

1. **Clone the Repository:**

   ```sh
   git clone https://github.com/your-username/pharmacy-management-system.git
   cd pharmacy-management-system
   ```

2. **Open the Project in Your IDE:**
   Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

3. **Configure Database Connection:**
   In `Database.java`, update the `JDBC_URL`, `USERNAME`, and `PASSWORD` constants to match your SQL Server configuration.

   ```java
   private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=pharmacy_db";
   private static final String USERNAME = "your_username";
   private static final String PASSWORD = "your_password";
   ```

4. **Run the Project:**
   Execute the `Main.java` class to run the application.

## Usage

### Adding Drugs

1. Open the application.
2. From the menu bar, select `Drugs -> Add Drug`.
3. Enter the drug details (name, manufacturer, price, and quantity) and click `Add Drug`.

### Viewing Drugs

1. Open the application.
2. From the menu bar, select `Drugs -> View Drugs`.
3. A list of all drugs will be displayed.

### Adding Customers, Suppliers, and Viewing Purchase History

Follow similar steps as above for adding customers, suppliers, and viewing purchase history.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [SQL Server](https://www.microsoft.com/en-us/sql-server) - Relational database management system
- [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/) - GUI widget toolkit for Java
```

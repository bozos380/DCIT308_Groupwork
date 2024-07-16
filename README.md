# Pharmacy Management System

The Pharmacy Management System is a Java-based application designed to manage the inventory, suppliers, and transactions of a pharmacy. This system includes functionality for adding, viewing, and searching for drugs and suppliers, as well as tracking purchase history.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Database Setup](#database-setup)
- [Contributing](#contributing)
- [Authors](#authors)
- [License](#license)

## Features

- **Drugs Management**:
  - Add new drugs
  - View a list of all drugs
  - Search for drugs by name, manufacturer, price, or supplier
  - View purchase history for each drug

- **Suppliers Management**:
  - Add new suppliers
  - View a list of all suppliers

## Installation

1. **Clone the repository**:
   ```sh
   git clone https://github.com/your-username/pharmacy-management-system.git
   cd pharmacy-management-system
   ```

2. **Set up the database**:
   - Ensure you have MSSQL installed and running.
   - Create a database named `PharmacyDB` (see [Database Setup](#database-setup) for more details).

3. **Import the project into your IDE**:
   - Open your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
   - Import the project as a Maven project.

4. **Install dependencies**:
   - The project uses Maven to manage dependencies. Run the following command to download and install them:
     ```sh
     mvn clean install
     ```

5. **Run the application**:
   - Execute the `Main` class from your IDE or use the following Maven command:
     ```sh
     mvn exec:java -Dexec.mainClass="com.example.pharmacy.Main"
     ```

## Usage

- **Navigating the application**:
  - The main interface consists of a menu bar with options to manage drugs and suppliers.
  - Click on "Drugs" to add, view, or search for drugs.
  - Click on "Suppliers" to add or view suppliers.

- **Adding a new drug**:
  - Select "Drugs" > "Add Drug" from the menu bar.
  - Fill in the drug details, including name, manufacturer, price, quantity, and supplier.
  - Click "Add Drug" to save the new drug to the database.

- **Viewing all drugs**:
  - Select "Drugs" > "View Drugs" from the menu bar.
  - A table will display all drugs in the database.

- **Searching for a drug**:
  - Select "Drugs" > "Search Drugs" from the menu bar.
  - Enter search criteria such as drug name, manufacturer, price, or supplier.
  - Click "Search Drug" to display matching results.

- **Adding a new supplier**:
  - Select "Suppliers" > "Add Supplier" from the menu bar.
  - Fill in the supplier details, including name and contact info.
  - Click "Add Supplier" to save the new supplier to the database.

- **Viewing all suppliers**:
  - Select "Suppliers" > "View Suppliers" from the menu bar.
  - A table will display all suppliers in the database.

## Database Setup

1. **Create the database**:
   - Open MSSQL Server Management Studio (SSMS) or any other SQL client.
   - Execute the following SQL script to create the database:
     ```sql
     CREATE DATABASE PharmacyDB;
     ```

2. **Create the required tables**:
   - Switch to the `PharmacyDB` database:
     ```sql
     USE PharmacyDB;
     ```

   - Execute the following SQL script to create the necessary tables:
     ```sql
     CREATE TABLE Suppliers (
       id INT PRIMARY KEY IDENTITY(1,1),
       name VARCHAR(255) NOT NULL,
       contact_info VARCHAR(255) NOT NULL
     );

     CREATE TABLE Drugs (
       id INT PRIMARY KEY IDENTITY(1,1),
       name VARCHAR(255) NOT NULL,
       manufacturer VARCHAR(255) NOT NULL,
       price DECIMAL(10, 2) NOT NULL,
       quantity INT NOT NULL,
       supplier_id INT NOT NULL,
       FOREIGN KEY (supplier_id) REFERENCES Suppliers(id)
     );

     CREATE TABLE Purchases (
       id INT PRIMARY KEY IDENTITY(1,1),
       drug_id INT NOT NULL,
       buyer VARCHAR(255) NOT NULL,
       date_time DATETIME NOT NULL,
       quantity INT NOT NULL,
       FOREIGN KEY (drug_id) REFERENCES Drugs(id)
     );
     ```

## Contributing

1. **Fork the repository**:
   - Click the "Fork" button on the top right of the repository page.

2. **Clone your forked repository**:
   ```sh
   git clone https://github.com/your-username/pharmacy-management-system.git
   cd pharmacy-management-system
   ```

3. **Create a new branch**:
   ```sh
   git checkout -b feature/your-feature-name
   ```

4. **Make your changes**:
   - Implement your feature or bug fix.

5. **Commit your changes**:
   ```sh
   git add .
   git commit -m "Add your commit message"
   ```

6. **Push your changes**:
   ```sh
   git push origin feature/your-feature-name
   ```

7. **Create a Pull Request**:
   - Go to the repository page on GitHub and click "New Pull Request".

## Authors

- **Daniel Okpoti Sowah** - *10957946* - [Daniel Okpoti Sowah](https://github.com/10957946)
- **Josephina Ashong** - *10961638* - [Josephina Ashong](https://github.com/contributor-username)
- **Owusu-Asare Derrick** - *10984555* - [Owusu-Asare Derrick](https://github.com/showmandem)
- **Justice Addo** - *10966864* - [Justice Addo](https://github.com/contributor-username)
- **Adjabeng Maa Abena** - *10960201* - [Adjabeng Maa Abena](https://github.com/Maamedtr)
- **Bozoma Mame Duku** - *10982457* - [Bozoma Mame Duku](https://github.com/bozos380)

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

# Mini Online Shopping Application

A small beginner-friendly project demonstrating the flow:

**Browser → Thymeleaf → Spring Boot Controller → Service → Repository (JDBC) → MySQL**

Maven manages dependencies and builds the application.

## Features
- View products stored in MySQL
- Add a product using a web form
- Uses Thymeleaf templates, Spring Boot, Spring JDBC (`JdbcTemplate`), MySQL Connector/J, and Maven
- Sample products are inserted when the application starts

> This is a learning demo, not a production-ready shopping system. It does not include login, payments, cart/order processing, CSRF/security configuration, or full validation.

## Requirements
- Java 17 or newer
- Maven 3.6+ (or use the Maven wrapper if you add one)
- MySQL Server running locally

## 1. Create the database
Open MySQL Workbench or the MySQL command line and run:

```sql
CREATE DATABASE online_shopping;
```

The app creates the `products` table and inserts sample products at startup.

## 2. Configure your MySQL credentials
The default configuration expects:
- URL: `jdbc:mysql://localhost:3306/online_shopping`
- Username: `root`
- Password: blank

If your MySQL password is not blank, set environment variables before starting the app.

**Windows PowerShell example:**
```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
$env:DB_URL="jdbc:mysql://localhost:3306/online_shopping"
```

Do not commit real database passwords to GitHub.

## 3. Run the application
Open PowerShell in the folder containing `pom.xml`:

```powershell
mvn spring-boot:run
```

Wait until Spring Boot starts, then open:

http://localhost:8080

To build a runnable JAR:

```powershell
mvn clean package
java -jar target/online-shopping-mini-0.0.1-SNAPSHOT.jar
```

## 4. Understand the code
- `controller/ProductController.java`: handles GET `/` and POST `/products`
- `service/ProductService.java`: application/service layer
- `repository/ProductRepository.java`: SQL access through `JdbcTemplate`
- `model/Product.java`: Java object representing a product
- `templates/products.html`: Thymeleaf HTML page
- `static/style.css`: page styling
- `schema.sql`: table definition
- `data.sql`: sample products
- `application.properties`: database connection settings
- `pom.xml`: Maven dependencies and build configuration

## Request flow: add a product
1. User fills the Thymeleaf form and clicks **Add product**.
2. Browser sends POST `/products`.
3. `ProductController` receives the form data.
4. Controller calls `ProductService`.
5. Service calls `ProductRepository`.
6. `JdbcTemplate` uses the MySQL JDBC driver to run a parameterized INSERT query.
7. MySQL stores the product.
8. Controller redirects to `/`, and the product list is loaded again.

## Troubleshooting
- **Access denied:** verify MySQL username/password and `DB_USERNAME` / `DB_PASSWORD`.
- **Communications link failure / connection refused:** ensure MySQL Server is running and the URL/port are correct.
- **Unknown database:** run `CREATE DATABASE online_shopping;`.
- **Port 8080 already in use:** stop the other app or change `server.port` in `application.properties`.
- **`mvn` not recognized:** install Maven or open the project in an IDE with Maven support.

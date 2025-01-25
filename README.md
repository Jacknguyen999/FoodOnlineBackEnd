# FoodOnlineBackEnd

Welcome to the **FoodOnlineBackEnd** repository! This project is the backend system for a food delivery and management platform. It provides APIs for user authentication, food item management, order processing, and more. 

---

## Features

- User Authentication and Authorization (JWT-based)
- Role Management (Admin, Customers, Delivery Personnel)
- Food Item CRUD Operations
- Order Placement and Tracking
- Payment Gateway Integration
- Database Connectivity and Optimization

---

## Tech Stack

- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: MySQL
- **Authentication**: JSON Web Tokens (JWT)
- **Others**: Spring Security, Hibernate, and more

---

## Getting Started

### Prerequisites

Ensure you have the following installed:

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (v11 or later)
- [MySQL](https://dev.mysql.com/downloads/installer/)
- [Maven](https://maven.apache.org/install.html)

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/Jacknguyen999/FoodOnlineBackEnd.git
   cd FoodOnlineBackEnd
   ```

2. Configure the application properties:

   Update the `src/main/resources/application.properties` file with your database and other configuration details:

   ```properties
   server.port=8080
   spring.datasource.url=jdbc:mysql://localhost:3306/food_online_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=your_jwt_secret
   payment.gateway.key=your_payment_gateway_key
   ```

3. Build and run the application:

   ```bash
   mvn spring-boot:run
   ```

   The server will start at `http://localhost:8080`.

---

## Directory Structure

Below is the main structure of the `src` folder:

```
src/
├── main/
   ├── java/
   │   └── com/
   │       └── example/
   │           └── food/
   │               ├── FoodApplication.java
   │               ├── controller/
   │               ├── model/
   │               ├── repository/
   │               ├── request/
   │               ├── response/
   │               └── service/
   └── resources/
       └── application.properties

```

---

## API Documentation

### Base URL

`http://localhost:8080/api`

### Endpoints

#### User Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Log in a user

#### Food Items
- `GET /api/foods` - Get all food items
- `POST /api/foods` - Add a new food item (Admin only)
- `PUT /api/foods/:id` - Update a food item (Admin only)
- `DELETE /api/foods/:id` - Delete a food item (Admin only)

#### Orders
- `POST /api/orders` - Place an order
- `GET /api/orders/:userId` - Get all orders for a specific user
- `PUT /api/orders/:id/status` - Update order status (Admin/Delivery personnel)

#### Payments
- `POST /api/payments` - Process a payment

*(Full documentation can be found in the `/docs` folder or via Swagger UI if integrated.)*

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch:

   ```bash
   git checkout -b feature/your-feature-name
   ```

3. Commit your changes:

   ```bash
   git commit -m "Add your feature"
   ```

4. Push the branch:

   ```bash
   git push origin feature/your-feature-name
   ```

5. Open a pull request

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

For any inquiries, please reach out to:

- **Author**: Jack Nguyen
- **Email**: jacknguyen999@example.com
- **GitHub**: [Jacknguyen999](https://github.com/Jacknguyen999)

---

Thank you for checking out the FoodOnlineBackEnd project! Your feedback and contributions are greatly appreciated!

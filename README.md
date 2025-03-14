# **Article Management System**  

This Java-based **Article Management System** is designed to manage media articles for an online shop. The system allows users to **add, delete, list, and analyze** articles (books and DVDs) while ensuring persistent storage using **Java Object Serialization**.  

## **Features**  
- **Add Articles**: Store books and DVDs with relevant details.  
- **List Articles**: Display stored articles with calculated discounts.  
- **Retrieve Specific Articles**: Fetch details using an article ID.  
- **Delete Articles**: Remove an article by ID.  
- **Statistics**: Count articles, determine mean price, and find the oldest article(s).  
- **Data Persistence**: Articles are stored in a file using **serialization**.  
- **Command-Line Interface**: Users interact through CLI commands.  

---

## **Concepts Learned**  
This project enhanced my understanding of **OOP principles**, **exception handling**, **file serialization**, **interface-based design**, and **command-line applications** in Java. It provided hands-on experience with **data persistence**, **error handling**, and **business logic implementation** in a structured manner. 🚀 

### **Object-Oriented Programming (OOP)**
- **Abstract Classes & Inheritance**  
  - The `Article` class is an **abstract class**, serving as a blueprint for `Book` and `DVD`.  
  - **Encapsulation** is used to keep instance variables private, exposing them through getters and setters.  
  - The `getDiscount()` method is **abstract**, enforcing implementation in subclasses.  

- **Polymorphism**  
  - Methods like `getPrice()` in `Article` behave differently in `Book` and `DVD` due to different discount rules.  

### **Exception Handling**
- Validates **input correctness** (e.g., release year must not be in the future).  
- Throws `IllegalArgumentException` for invalid input like duplicate IDs, incorrect release years, or invalid age ratings.  

### **Interfaces & Dependency Injection**
- `ArticleDAO` defines a **Data Access Object (DAO)** interface for handling storage operations.  
- `SerializedArticleDAO` implements `ArticleDAO`, enabling **flexible storage management**.  
- **Dependency Injection**: `ArticleManagement` depends on `ArticleDAO`, making storage implementation interchangeable.  

### **File Handling & Serialization**
- `SerializedArticleDAO` uses **Java Object Serialization** for **persistent storage**.  
- Handles serialization and deserialization errors, displaying:  
  - `"Error during serialization."`  
  - `"Error during deserialization."`  

### **Command-Line Interface (CLI)**
- The `ArticleCLI` class provides a **CLI-based user interface** to interact with the system.  
- Parses user input, performs operations, and outputs results in the specified format.  
- Implements **error handling** for invalid parameters, incorrect commands, and missing articles.  

### * Data Processing & Formatting**
- Uses `DecimalFormat` to ensure **consistent formatting** of floating-point numbers (e.g., `12.35` instead of `12.345`).  
- Implements **age-based discount calculation** for books and **age-rating-based discounts** for DVDs.  

---

## **How to Run the Program**  

### **Compile & Execute**  
1. **Compile**  
   ```sh
   javac *.java
   ```
2. **Run**  
   ```sh
   java ArticleCLI articles.ser add book 1 "The Art of Java" "Tech Press" 2020 25.99 500
   ```

### **Example Commands**  
- **Add a book:**  
  ```sh
  java ArticleCLI articles.ser add book 1 "Java Basics" "O'Reilly" 2018 20.00 450
  ```
- **Add a DVD:**  
  ```sh
  java ArticleCLI articles.ser add dvd 2 "Inception" "Warner Bros" 2010 15.99 120 12
  ```
- **List all articles:**  
  ```sh
  java ArticleCLI articles.ser list
  ```
- **Delete an article:**  
  ```sh
  java ArticleCLI articles.ser delete 1
  ```
- **Get article count:**  
  ```sh
  java ArticleCLI articles.ser count
  ```

---

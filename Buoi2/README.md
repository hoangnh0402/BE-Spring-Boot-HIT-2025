# Lombok, ORM Hibernate, Spring Data JPA & Spring MVC với Thymeleaf (Kết nối MySQL)

## 🔹 Giới thiệu

### 1. Lombok

Lombok là một thư viện giúp giảm thiểu boilerplate code trong các entity bằng cách tự động sinh code như getter, setter, constructor, v.v.

### 2. ORM & Hibernate

**ORM (Object-Relational Mapping)** là kỹ thuật ánh xạ dữ liệu giữa đối tượng trong lập trình hướng đối tượng và bảng trong cơ sở dữ liệu.

**Hibernate** là một framework ORM phổ biến trong Java, giúp quản lý dữ liệu giữa ứng dụng và cơ sở dữ liệu quan hệ.

### 3. Spring Data JPA

Spring Data JPA giúp đơn giản hóa việc tương tác với cơ sở dữ liệu thông qua Hibernate bằng cách cung cấp các phương thức thao tác dữ liệu tại tầng **Repository**.

Trong kiến trúc **3 tầng**:

- **Controller**: Nhận request từ client.
- **Service**: Chứa logic nghiệp vụ, gọi Repository.
- **Repository**: Tầng giao tiếp với cơ sở dữ liệu thông qua JPA.

### 4. Spring MVC & Thymeleaf

**Spring MVC** là một framework mạnh mẽ để xây dựng ứng dụng web theo mô hình MVC (Model-View-Controller).

**Thymeleaf** là một template engine giúp hiển thị giao diện động trên ứng dụng Spring Boot.

---

## 🔹 Thực hành

### 1. Cài đặt Lombok

Thêm Lombok vào file `pom.xml`:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```

### 2. Cấu hình Hibernate + Spring Data JPA kết nối MySQL

Thêm dependency vào `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
    <scope>runtime</scope>
</dependency>
```

Cấu hình trong `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Tạo Entity & Repository

#### Entity `Department`

```java
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
}
```

#### Entity `Employee`

```java
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
```

#### Repository Layer

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
```

### 4. Service Layer

```java
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
```

### 5. Controller Layer với Thymeleaf

```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping("/employees")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }
}
```

### 6. Giao diện Thymeleaf

Tạo file `src/main/resources/templates/employees.html`:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Danh sách nhân viên</title>
</head>
<body>
    <h2>Danh sách nhân viên</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Email</th>
            <th>Phòng ban</th>
        </tr>
        <tr th:each="employee : ${employees}">
            <td th:text="${employee.id}"></td>
            <td th:text="${employee.name}"></td>
            <td th:text="${employee.email}"></td>
            <td th:text="${employee.department.name}"></td>
        </tr>
    </table>
</body>
</html>
```

---

## 🔹 Kết luận

- **Lombok** giúp loại bỏ các phương thức getter/setter bằng annotation.
- **Hibernate** giúp ánh xạ Java object với database.
- **Spring Data JPA** giúp truy vấn database dễ dàng hơn.
- **Spring MVC** giúp xử lý request và response theo mô hình MVC.
- **Thymeleaf** là template engine giúp hiển thị dữ liệu trên giao diện.
- Chúng ta đã tạo **Entity**, **Repository**, **Service**, **Controller**, và giao diện Thymeleaf cho ứng dụng đơn giản kết nối MySQL.

> 🚀 Bây giờ bạn có thể chạy ứng dụng và kiểm tra giao diện tại `/employees`!


# Spring Boot - JPA Relationship & Thymeleaf

## 1. Relationship trong Spring JPA
Spring Data JPA hỗ trợ các mối quan hệ giữa các entity bằng cách sử dụng các annotation như `@OneToOne`, `@OneToMany`, `@ManyToOne`, và `@ManyToMany`. Dưới đây là chi tiết cách sử dụng từng loại quan hệ.

### 1.1. @OneToOne (Một - Một)
Một mối quan hệ một-một giữa hai entity, ví dụ một `User` có một `UserProfile`.
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile profile;
}

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bio;
    private String avatarUrl;
}
```

### 1.2. @OneToMany & @ManyToOne (Một - Nhiều và Nhiều - Một)
Một `User` có thể có nhiều `Order`, nhưng một `Order` chỉ thuộc về một `User`.
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
}

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderDetails;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
```

### 1.3. @ManyToMany (Nhiều - Nhiều)
Một `Student` có thể tham gia nhiều `Course` và một `Course` có thể có nhiều `Student`.
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
}
```

---

## 2. Thymeleaf trong Spring Boot
Thymeleaf là một template engine mạnh mẽ giúp render HTML trên Spring Boot.

### 2.1. Cấu hình Thymeleaf trong Spring Boot
Spring Boot hỗ trợ Thymeleaf mặc định. Chỉ cần thêm dependency sau vào `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

### 2.2. Sử dụng Thymeleaf trong View
Tạo file `src/main/resources/templates/index.html`:
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Home Page</title>
</head>
<body>
    <h1>Welcome, <span th:text="${name}"></span>!</h1>
</body>
</html>
```

### 2.3. Controller trong Spring Boot
```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Spring Boot");
        return "index"; // Trả về index.html
    }
}
```

### 2.4. Thymeleaf Iteration (Duyệt danh sách)
Khi cần hiển thị danh sách người dùng trên giao diện, ta có thể sử dụng vòng lặp của Thymeleaf:
```html
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <tr th:each="user : ${users}">
        <td th:text="${user.id}"></td>
        <td th:text="${user.name}"></td>
        <td th:text="${user.email}"></td>
    </tr>
</table>
```

### 2.5. Thymeleaf Form
Thymeleaf hỗ trợ binding form dữ liệu với model:
```html
<form th:action="@{/save}" method="post" th:object="${user}">
    <input type="text" th:field="*{name}" placeholder="Enter name">
    <input type="email" th:field="*{email}" placeholder="Enter email">
    <input type="submit" value="Save">
</form>
```

```java
@PostMapping("/save")
public String saveUser(@ModelAttribute User user) {
    userService.save(user);
    return "redirect:/";
}
```

### 2.6. Thymeleaf Conditional (Điều kiện hiển thị)
```html
<p th:if="${user.age > 18}">Bạn đủ tuổi trưởng thành!</p>
<p th:unless="${user.age > 18}">Bạn chưa đủ tuổi trưởng thành.</p>
```

---


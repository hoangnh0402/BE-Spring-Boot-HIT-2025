# Hướng Dẫn Xây Dựng RESTful API Trong Spring Boot

## 1. Giới Thiệu
### 1.1. REST là gì?
REST (**Representational State Transfer**) là một phong cách kiến trúc phần mềm dựa trên giao thức HTTP, được sử dụng để xây dựng các dịch vụ web. REST định nghĩa một tập hợp các nguyên tắc để tạo API linh hoạt, có khả năng mở rộng cao và dễ bảo trì.

# Hướng Dẫn Xây Dựng RESTful API Trong Spring Boot

## 1. Giới Thiệu
### 1.1. REST là gì?
REST (**Representational State Transfer**) là một phong cách kiến trúc phần mềm dựa trên giao thức HTTP, được sử dụng để xây dựng các dịch vụ web. REST định nghĩa một tập hợp các nguyên tắc để tạo API linh hoạt, có khả năng mở rộng cao và dễ bảo trì.

### 1.2. RESTful API là gì?
RESTful API là một API tuân theo các nguyên tắc của REST. Nó sử dụng các phương thức HTTP như **GET, POST, PUT, DELETE** để thực hiện các thao tác CRUD (Create, Read, Update, Delete) trên tài nguyên.

RESTful API là một tiêu chuẩn dùng trong việc thiết kế API cho các ứng dụng web (thiết kế Web services) để tiện cho việc quản lý các resource. Nó chú trọng vào tài nguyên hệ thống (tệp văn bản, ảnh, âm thanh, video, hoặc dữ liệu động…), bao gồm các trạng thái tài nguyên được định dạng và được truyền tải qua HTTP.

### 1.3. Diễn giải các thành phần
- **API (Application Programming Interface)**: là một tập các quy tắc và cơ chế cho phép một ứng dụng hay một thành phần tương tác với ứng dụng hoặc thành phần khác. API có thể trả về dữ liệu dưới dạng JSON hoặc XML.
- **REST (REpresentational State Transfer)**: là một kiểu kiến trúc API sử dụng các phương thức HTTP như GET, POST, DELETE để giao tiếp giữa các hệ thống.
- **RESTful API**: là một tiêu chuẩn thiết kế API cho các ứng dụng web, giúp quản lý các tài nguyên dễ dàng hơn. RESTful API quy định cách sử dụng các phương thức HTTP và cách định dạng URL để quản lý tài nguyên.

### 1.4. RESTful API hoạt động như thế nào?
RESTful API hoạt động chủ yếu dựa trên giao thức HTTP với các phương thức sau:
- **GET** (SELECT): Trả về một hoặc nhiều tài nguyên.
- **POST** (CREATE): Tạo mới một tài nguyên.
- **PUT** (UPDATE): Cập nhật thông tin cho tài nguyên.
- **DELETE** (DELETE): Xóa một tài nguyên.

Những phương thức này tương ứng với CRUD (Create, Read, Update, Delete – Tạo, Đọc, Sửa, Xóa).

## 2. Nguyên Tắc Thiết Kế RESTful API
Một RESTful API tốt nên tuân theo 6 nguyên tắc sau:

1. **Client-Server (Máy khách - Máy chủ)**: Máy khách và máy chủ phải được tách biệt để cải thiện khả năng mở rộng của hệ thống.
2. **Stateless (Không trạng thái)**: Mỗi yêu cầu từ máy khách đến máy chủ phải chứa tất cả thông tin cần thiết để xử lý yêu cầu. Máy chủ không lưu trữ trạng thái của máy khách.
3. **Cacheable (Có thể lưu vào bộ nhớ đệm)**: Các phản hồi từ máy chủ nên có khả năng được lưu vào bộ nhớ đệm để cải thiện hiệu suất.
4. **Layered System (Hệ thống phân lớp)**: API có thể có nhiều lớp trung gian giúp tăng cường bảo mật, cân bằng tải và khả năng mở rộng.
5. **Uniform Interface (Giao diện thống nhất)**: API phải tuân theo một quy ước chung, bao gồm:
   - Sử dụng URI để xác định tài nguyên.
   - Sử dụng các phương thức HTTP chuẩn.
   - Sử dụng định dạng dữ liệu chung (ví dụ: JSON, XML).
   - Hỗ trợ điều hướng tài nguyên thông qua liên kết (HATEOAS - Hypermedia as the Engine of Application State).
6. **Code on Demand (Mã theo yêu cầu) [Không bắt buộc]**: Máy chủ có thể gửi mã thực thi đến máy khách để mở rộng chức năng của API.

## 3. Authentication và Dữ Liệu Trả Về
RESTful API không sử dụng session và cookie, mà sử dụng một **access_token** với mỗi request. Dữ liệu trả về thường có dạng:

```json
{
    "data" : {
        "id": "1",
        "name": "TopDev"
    }
}
```

## 4. HTTP Status Code
Các mã trạng thái phổ biến khi làm việc với RESTful API:
- **200 OK** – Thành công.
- **201 Created** – Tạo thành công một tài nguyên mới.
- **204 No Content** – Xóa thành công tài nguyên.
- **400 Bad Request** – Request không hợp lệ.
- **401 Unauthorized** – Yêu cầu cần xác thực.
- **403 Forbidden** – Bị từ chối quyền truy cập.
- **404 Not Found** – Không tìm thấy tài nguyên.
- **405 Method Not Allowed** – Phương thức không được phép.
- **429 Too Many Requests** – Quá nhiều request trong khoảng thời gian ngắn.

## 5. Nên Sử Dụng Version Cho API
Luôn sử dụng phiên bản cho API để đảm bảo khả năng mở rộng và duy trì hỗ trợ các phiên bản cũ. Ví dụ:
```sh
http://api.example.com/v1/users
```

## 3. Cấu trúc dự án Spring Boot
```
restful-api-springboot/
│-- src/main/java/com/example/demo/
│   │-- controllers/
│   │   ├── UserController.java
│   │-- models/
│   │   ├── User.java
│   │-- services/
│   │   ├── impl/
│   │   │   ├── UserServiceImpl.java
│   │   ├── UserService.java
│   │-- repositories/
│   │   ├── UserRepository.java
│-- src/main/resources/
│   │-- application.properties
│-- pom.xml
```

## 4. Tạo RESTful API với Spring Boot

### 4.1. Tạo Model
```java
package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
```

### 4.2. Tạo Repository
```java
package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
```

### 4.3. Tạo Service Interface
```java
package com.example.demo.services;

import com.example.demo.models.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
}
```

### 4.4. Tạo Service Implementation
```java
package com.example.demo.services.impl;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
```

### 4.5. Tạo Controller
```java
package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
```

## 5. Kiểm Thử API
Sử dụng **Postman** để kiểm thử API:
```sh
-X GET http://localhost:8080/api/users
```


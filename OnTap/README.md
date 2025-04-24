📁 Video bài giảng: [Google Drive Link](https://drive.google.com/drive/folders/1VtIe6RosvZmQ6rSkWAsoMIZp6tx2eDsh?usp=sharing)


# 🔍 Java Backend - Tổng quan lý thuyết

Tài liệu này cung cấp kiến thức tổng quan về một số thành phần cốt lõi thường được sử dụng trong lập trình Java Backend với Spring Boot, bao gồm:

- [1. MapStruct](#1-mapstruct)
- [2. Custom Annotation](#2-custom-annotation)
- [3. Validation](#3-validation)
- [4. Constant](#4-constant)
- [5. Exception Handling](#5-exception-handling)

---

## 1. MapStruct

**MapStruct** là một thư viện ánh xạ (mapping) mã nguồn mở giúp chuyển đổi dữ liệu giữa các lớp Java (thường là từ DTO sang Entity và ngược lại) mà không cần viết tay.

### ✅ Ưu điểm:
- Tốc độ nhanh (biên dịch thành mã Java thực).
- Giảm boilerplate code.
- Tăng tính nhất quán và dễ bảo trì.

### 🔧 Ví dụ:

```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}
```

---

## 2. Custom Annotation

**Custom Annotation** cho phép bạn định nghĩa annotation của riêng mình nhằm phục vụ các mục đích như đánh dấu đặc biệt, xử lý nghiệp vụ tùy chỉnh hoặc hỗ trợ validation.

### 🛠 Cách tạo:

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
}
```

Annotation này có thể được dùng cùng AOP để log thời gian thực thi.

---

## 3. Validation

Validation đảm bảo dữ liệu đầu vào đúng định dạng, hợp lệ trước khi xử lý. Spring Boot tích hợp với Hibernate Validator theo chuẩn **JSR-380 (Bean Validation 2.0)**.

### 🧪 Ví dụ:

```java
public class UserDTO {
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @Email
    private String email;
}
```

Kết hợp với annotation `@Valid` trong controller để kích hoạt kiểm tra:

```java
@PostMapping
public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
    ...
}
```

---

## 4. Constant

Constant là các giá trị không đổi được khai báo nhằm tránh "magic numbers" hoặc "hardcoded strings" trong mã nguồn.

### 📌 Ví dụ:

```java
public class AppConstants {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final int MAX_PAGE_SIZE = 50;
}
```

Việc tách các constant vào một class riêng giúp dễ bảo trì và tái sử dụng.

---

## 5. Exception Handling

**Exception Handling** giúp xử lý lỗi một cách thống nhất và trả về thông báo rõ ràng cho người dùng hoặc API client.

### ✅ Cách tiếp cận trong Spring Boot:

1. Dùng `@ControllerAdvice` kết hợp với `@ExceptionHandler`.
2. Trả về ResponseEntity chứa thông tin lỗi chuẩn hóa.

### 🧵 Ví dụ:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

---

## 📘 Tài liệu tham khảo

- [MapStruct Documentation](https://mapstruct.org/documentation/stable/reference/html/)
- [Hibernate Validator Docs](https://hibernate.org/validator/)
- [Spring Boot Exception Handling](https://www.baeldung.com/exception-handling-for-rest-with-spring)

---

> 🧠 Ghi nhớ: Việc sử dụng đúng các kỹ thuật trên giúp dự án dễ mở rộng, dễ test và chuyên nghiệp hơn trong môi trường phát triển phần mềm thực tế.

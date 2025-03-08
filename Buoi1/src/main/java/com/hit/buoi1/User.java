package com.hit.buoi1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// User không cần tự tạo Phone, Spring sẽ tự Inject vào
@Component
class User {
    private Phone phone;

    // Spring tự động Inject dependency thông qua @Autowired
    @Autowired
    public User(@Qualifier("samsung") Phone phone) {  // Chỉ định rõ bean Samsung
        this.phone = phone;
    }

    public void makeCall() {
        phone.call();
    }
}

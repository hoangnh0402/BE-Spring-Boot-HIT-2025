package com.hit.buoi1;

import org.springframework.stereotype.Component;

@Component
class Samsung implements Phone {
    @Override
    public void call() {
        System.out.println("Gọi điện bằng Samsung!");
    }
}

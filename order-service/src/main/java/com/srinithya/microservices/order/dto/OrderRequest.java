package com.srinithya.microservices.order.dto;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, String skuCode,

                           BigDecimal price, Integer quantity, UserDetails userDetails) {

    public record UserDetails(String email, String firstName, String lastName){}
}

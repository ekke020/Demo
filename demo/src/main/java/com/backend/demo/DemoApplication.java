package com.backend.demo;

import com.backend.demo.services.OrderService;
import com.backend.demo.services.ProductService;
import com.backend.demo.services.UserService;
import com.backend.demo.services.dto.OrderCreationDto;
import com.backend.demo.services.dto.ProductCreationDto;
import com.backend.demo.services.dto.UserCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@PropertySource("classpath:base64.properties")
public class DemoApplication {

    @Autowired
    private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//
//    @Bean
//    public CommandLineRunner demo(OrderService orderService, ProductService productService, UserService userService){
//        return (args) -> {
//            orderService.deleteAll();
//            productService.deleteAll();
//            userService.deleteAll();
//
//            String[] names = {"Adam Andersson", "Berit Bengtsson", "Carl Carlsson"};
//            String[] emails = {"adam@mail.com", "Berit@mail.com", "Carl@mail.com"};
//			String[] passwords = {"12354", "abcde", "test123"};
//            long[] userIds = new long[3];
//            for (int i = 0; i < 3; i++) {
//				UserCreationDto user = new UserCreationDto();
//                user.setName(names[i]);
//                user.setEmail(emails[i]);
//				user.setPassword(passwords[i]);
//                userIds[i] = userService.save(user).getId();
//            }
//            String[] productNames = {"Mango", "Banana", "Pineapple"};
//			String[] base64 = {
//                    env.getProperty("png.mango"),
//                    env.getProperty("png.banana"),
//                    env.getProperty("png.pineapple")
//            };
//            List<Long> productIds = new ArrayList<>();
//            for (int i = 0; i < productNames.length; i++) {
//                ProductCreationDto product = new ProductCreationDto();
//                product.setName(productNames[i]);
//				product.setPrice(BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
//				product.setBase64(base64[i]);
//                productIds.add(productService.save(product).getId());
//            }
//
//            for (int i = 0; i < 3; i++) {
//                OrderCreationDto order = new OrderCreationDto();
//                order.setProductIds(productIds);
//                order.setUserId(userIds[i]);
//                orderService.save(order);
//            }
//
//        };
//    }


}

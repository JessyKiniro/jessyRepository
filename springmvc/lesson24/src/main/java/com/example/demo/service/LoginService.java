package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private static Map<String, String> userMap = new HashMap<>(8);

    static {
        userMap.put("小李", "123456");
        userMap.put("小红", "654321");
    }

    public boolean isCorrect(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        boolean result = false;

        if (userMap.containsKey(userName)) {
            if (userMap.containsValue(password)) {
                result = true;
            }
        }
        return result;
    }
}

package com.example.bfhl_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("message", "API is alive!");
    }

    @PostMapping
    public Map<String, Object> processData(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            List<String> data = (List<String>) request.get("data");

            List<String> oddNumbers = new ArrayList<>();
            List<String> evenNumbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> specialCharacters = new ArrayList<>();

            int sum = 0;
            StringBuilder alphaConcat = new StringBuilder();

            for (String item : data) {
                if (item.matches("\\d+")) {  
                    int num = Integer.parseInt(item);
                    sum += num;
                    if (num % 2 == 0) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                } else if (item.matches("[a-zA-Z]+")) {
                    alphabets.add(item.toUpperCase());
                    alphaConcat.append(item);
                } else { 
                    specialCharacters.add(item);
                }
            }

            StringBuilder concatStr = new StringBuilder();
            char[] reversed = alphaConcat.reverse().toString().toCharArray();
            for (int i = 0; i < reversed.length; i++) {
                concatStr.append(i % 2 == 0 
                        ? Character.toUpperCase(reversed[i]) 
                        : Character.toLowerCase(reversed[i]));
            }
            response.put("is_success", true);
            response.put("user_id", "venkateshwar_rao_29082001");
            response.put("email", "your_email@example.com");
            response.put("roll_number", "YOUR_ROLL_NO");
            response.put("odd_numbers", oddNumbers);
            response.put("even_numbers", evenNumbers);
            response.put("alphabets", alphabets);
            response.put("special_characters", specialCharacters);
            response.put("sum", String.valueOf(sum));
            response.put("concat_string", concatStr.toString());

        } catch (Exception e) {
            response.put("is_success", false);
            response.put("message", "Invalid input");
        }

        return response;
    }
}

package com.example.securitydemo.data;

import com.example.securitydemo.domain.User;
import com.example.securitydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserEntries  implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        List<User> userList = new ArrayList<>();
        User user1 = new User("Arthur Ezeagbo","password","arthur@gmail.com");
        User user2 = new User("Agbo Chijioke","password","agbo@gmail.com");

        userList.add(user1);
        userList.add(user2);

        try {
            userRepository.saveAll(userList);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}

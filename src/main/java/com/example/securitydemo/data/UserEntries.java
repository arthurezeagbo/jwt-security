package com.example.securitydemo.data;

import com.example.securitydemo.domain.User;
import com.example.securitydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserEntries  implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        List<User> userList = new ArrayList<>();
        User user1 = new User("Arthur Ezeagbo",passwordEncoder.encode("password"),"arthur@gmail.com","admin");
        User user2 = new User("Agbo Chijioke",passwordEncoder.encode("password"),"agbo@gmail.com","user");

        userList.add(user1);
        userList.add(user2);

        try {
            userRepository.saveAll(userList);
            System.out.println("User entries added successfully");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Scheduled(cron = "0 59 16 11 12 fri",zone="Africa/Lagos")
    public void myScheduler() throws InterruptedException {
        System.out.println("Starting batch operation--------------------");
        Thread.sleep(2000);
        for (int i = 0; i < 6; i++) {
            Thread.sleep(1000);
            System.out.println("Counting to >>>>>"+ i);

        }
        Thread.sleep(2000);
        System.out.println("Batch completed --------------------------");
    }

}

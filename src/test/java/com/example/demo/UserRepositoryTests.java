package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("Ka.Chun@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Ka");
        user.setLastName("Chun");

        User save = repo.save(user);

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAll(){
        Iterable<User> users =  repo.findAll();

        for(User user : users){
            System.out.println(users.toString());
        }
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<User> userOptional = repo.findById(userId);
        User user = userOptional.get();
        user.setPassword("newpassword");
        repo.save(user);

        User userAfter = repo.findById(userId).get();

        Assertions.assertThat(userAfter.getPassword()).isEqualTo("newpassword");
    }

    @Test
    public void testGet(){
        Integer userId = 1;
        Optional<User> userOptional = repo.findById(userId);

        Assertions.assertThat(userOptional).isPresent();
        System.out.println(userOptional.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 1;
        repo.deleteById(userId);

        Optional<User> userOptional = repo.findById(userId);
        Assertions.assertThat(userOptional).isNotPresent();
    }
}

package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFound {
        Optional<User> user = repo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserNotFound("User with id " + id + " is not found");
    }

    public void delete(Integer id) throws UserNotFound {
        Optional<User> user = repo.findById(id);
        if(user.isPresent()) {
            repo.deleteById(id);
        }else{
            throw new UserNotFound("User with id " + id + " is not found");
        }
    }
}

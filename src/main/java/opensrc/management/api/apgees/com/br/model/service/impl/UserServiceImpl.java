package opensrc.management.api.apgees.com.br.model.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import opensrc.management.api.apgees.com.br.exceptions.UserCreateException;
import opensrc.management.api.apgees.com.br.model.domain.User;
import opensrc.management.api.apgees.com.br.model.repository.UserRepository;
import opensrc.management.api.apgees.com.br.model.service.UserService;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) throws  UserCreateException{
        var users = userRepository.findById(user.getId());
        if(users.isPresent()){
            throw new UserCreateException("User already exist");
        }
        return  userRepository.save(user);
    }

    @Override
    public void  update(User user){
        userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));

        userRepository.delete(user);
    }

    @Override
    public List<User> list(){
        return  userRepository.findAll();
    }

    @Override
    public User findUserById(UUID id){
        return  userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not find."));
    }
}

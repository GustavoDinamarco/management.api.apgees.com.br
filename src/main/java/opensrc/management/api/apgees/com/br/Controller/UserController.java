package opensrc.management.api.apgees.com.br.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import opensrc.management.api.apgees.com.br.exceptions.UserCreateException;
import opensrc.management.api.apgees.com.br.model.domain.User;
import opensrc.management.api.apgees.com.br.model.repository.UserRepository;
import opensrc.management.api.apgees.com.br.model.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User create(@Valid @RequestBody User user)
        throws  UserCreateException{
        return userService.create(user);
    }
    
    @GetMapping()
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> searchById(@PathVariable("id") UUID id) {
        var user = userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        if(!id.equals(user.getId())){
            return  ResponseEntity.badRequest().build();
        }

        try {
            userService.update(user);
            return  ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

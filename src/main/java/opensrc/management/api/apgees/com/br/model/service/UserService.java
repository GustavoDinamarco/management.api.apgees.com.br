package opensrc.management.api.apgees.com.br.model.service;

import java.util.List;
import java.util.UUID;

import opensrc.management.api.apgees.com.br.exceptions.UserCreateException;
import opensrc.management.api.apgees.com.br.model.domain.User;

public interface UserService {
    User create(User user) throws  UserCreateException;
    void update(User user);
    void delete(UUID id);
    List<User>list();
    User findUserById(UUID id);
}

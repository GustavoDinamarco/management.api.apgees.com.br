package opensrc.management.api.apgees.com.br.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import opensrc.management.api.apgees.com.br.model.domain.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    boolean exisexistsBy(UUID id);
}

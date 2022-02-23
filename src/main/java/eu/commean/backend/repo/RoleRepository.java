package eu.commean.backend.repo;

import eu.commean.backend.entity.Role;
import eu.commean.backend.enums.RoleName;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
	Role findByName(RoleName name);

}
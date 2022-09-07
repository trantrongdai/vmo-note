package com.vmo.note.repository;


import com.vmo.note.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleName);

    Boolean existsByName(String name);

    List<Role> findByIdIn(List<Long> ids);
}

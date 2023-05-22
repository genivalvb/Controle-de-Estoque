package com.controle_estoqueapi.Controle.de.Estoque.Repository;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByAuthority(String authority);
}

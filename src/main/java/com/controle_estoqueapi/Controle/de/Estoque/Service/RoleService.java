package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Role;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role queryByAuthority(String authority){
        return roleRepository.findByAuthority(authority).orElseThrow(() -> new EntityNotFoundException("Role não encontrada"));
    }
}

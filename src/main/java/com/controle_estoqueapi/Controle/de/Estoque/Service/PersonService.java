package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.Config.SecurityConfig;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Person;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Role;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ActionDeniedException;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
@Service
public class PersonService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleService roleService;
    //@Autowired
    //BCryptPasswordEncoder b;

    SecurityConfig securityConfig = new SecurityConfig();

    private boolean personExists(String mail) {
        return personRepository.findByMail(mail).orElse(null) != null;
    }

    public Person queryByMail(String mail){
        return personRepository.findByMail(mail).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }

    public List<Person> listPeople() {
        return personRepository.findAll();
    }

    public Person queryPersonById(long idPerson) {
        Person u = personRepository.findById(idPerson).orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));
        return u;
    }

    public Person save(Person person) {
        Person vPerson = validatePerson(person);
        if(vPerson.getIdPerson() == 0 && this.personExists(person.getMail())){
            throw new ActionDeniedException("Email ja existe");
        }
        return personRepository.save(vPerson);
    }

    private Person validatePerson(Person people){
        HashSet<Role> roles = new HashSet<Role>();
        roles.add(roleService.queryByAuthority(people.getRoles().stream().findFirst().orElseThrow(() -> new EntityNotFoundException("Role não encontrada")).getAuthority()));
        people.setRoles(roles);
        people.setPassword(securityConfig.passwordEncoder().encode(people.getPassword()));
        if(people.getName().isEmpty() || people.getName().isBlank()){
            throw new ActionDeniedException("nome vazio");
        }else if(people.getPassword().isEmpty() || people.getPassword().isBlank()){
            throw new ActionDeniedException("senha vazia");
        }else {
            return people;
        }
    }

    public Person alter(String mail, Person person) {

        Person vPerson = queryByMail(mail);

        if(!person.getName().isEmpty() && !person.getName().isBlank()){
            vPerson.setName(person.getName());
        }
        if(person.getPassword() != null && !person.getPassword().isEmpty()){
            vPerson.setPassword(securityConfig.passwordEncoder().encode(person.getPassword()));
        }
        if(!person.getMail().isEmpty() && !person.getMail().isBlank()){
            vPerson.setMail(person.getMail());
        }

        vPerson.setRoles(person.getRoles());

        return personRepository.save(vPerson);
    }

    public Person disablePerson(String mail) {
        Person vPerson = this.queryByMail(mail);
        return this.save(vPerson);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByMail(username).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }
}

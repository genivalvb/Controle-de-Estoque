package com.controle_estoqueapi.Controle.de.Estoque.DTO;

import lombok.Builder;
import net.bytebuddy.implementation.bind.annotation.Default;

public class PersonPublicDTO {

    private long idPerson;
    private String name;
    private String mail;
    private String role;

    public PersonPublicDTO(long idPerson, String name, String mail, String role) {
        this.idPerson = idPerson;
        this.name = name;
        this.mail = mail;
        this.role = role;
    }

   /* public PersonPublicDTO(long idPerson, String name) {
        this.idPerson = idPerson;
        this.name = name;
    }*/

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

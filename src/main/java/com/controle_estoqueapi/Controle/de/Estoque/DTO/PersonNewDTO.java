package com.controle_estoqueapi.Controle.de.Estoque.DTO;

public class PersonNewDTO {

    private Long idPerson;
    private String name = "";
    private String password = "";
    private String mail = "";
    private String role = "";


    public PersonNewDTO(Long idPerson, String name, String password, String mail, String role) {
        this.idPerson = idPerson;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.role = role;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

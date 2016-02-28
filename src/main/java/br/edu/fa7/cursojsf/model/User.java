package br.edu.fa7.cursojsf.model;

import br.edu.fa7.cursojsf.model.converter.CepConverter;
import br.edu.fa7.cursojsf.util.Cep;

import javax.persistence.Convert;
import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {

    private String username;
    private String password;

    @Convert(converter = CepConverter.class)
    private Cep cep;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }
}

package org.francescobasile.tisanoweb1.entity.fortest;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class UtenteTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 30, message = "La lunghezza del testo per il nome deve essere tra {min} e {max}.")
    private String nome;
    @Size(min = 3, max = 30, message = "La lunghezza del testo per il nome deve essere tra {min} e {max}.")
    private String cognome;
    @Version
    private Long version;


    public UtenteTest() {
    }

    public UtenteTest(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}

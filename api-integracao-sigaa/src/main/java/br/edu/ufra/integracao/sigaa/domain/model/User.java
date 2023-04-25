package br.edu.ufra.integracao.sigaa.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="User")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(name = "username", nullable = false)
    @JsonIgnore
    private String username;

    @Column(name = "nr_matricula", nullable = false)
    private String matricula;

    @Column(name = "curso", nullable = false)
    private String curso;

    @Column(name = "nivel", nullable = false)
    private String nivel;

    @Column(name = "ira", nullable = false)
    private Double ira;

    @Column(name = "mc", nullable = false)
    private Double mc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String title) {
        this.matricula = title;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Double getIra() {
        return ira;
    }

    public void setIra(Double ira) {
        this.ira = ira;
    }

    public Double getMc() {
        return mc;
    }

    public void setMc(Double mc) {
        this.mc = mc;
    }
}

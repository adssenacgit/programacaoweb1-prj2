package com.senac.projeto2.entity;

import jakarta.persistence.*;

import java.io.File;

@Entity
@Table(name="participante")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="participante_id")
    private int id;
    @Column(name="participante_nome")
    private String nome;
    @Column(name="participante_email")
    private String email;
    @Column(name="participante_identificacao")
    private String identificacao;
    @Column(name="participante_endereco")
    private String endereco;
    @Column(name="participante_foto_perfil")
    private File foto_participante;
    @Column(name="participante_status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public File getFoto_participante() {
        return foto_participante;
    }

    public void setFoto_participante(File foto_participante) {
        this.foto_participante = foto_participante;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

package com.minsait.teste.usuarioApi.api.dto;

public class UsuarioInputPostDTO {
    private String nome;
    private String email;

    public UsuarioInputPostDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public UsuarioInputPostDTO() {
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
}
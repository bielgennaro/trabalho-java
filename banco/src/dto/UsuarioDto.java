package dto;

import enums.ETipoUsuario;

public class UsuarioDto {

    Integer id;
    String nome;
    String cpf;
    String dataNascimento;
    ETipoUsuario tipoUsuario;

    public UsuarioDto(Integer id, String nome, String cpf, String dataNascimento, ETipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(ETipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

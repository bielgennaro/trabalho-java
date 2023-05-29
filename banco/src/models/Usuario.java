package models;

import dto.UsuarioDto;
import enums.ETipoUsuario;

import java.time.LocalDate;

public class Usuario {

    Integer id;
    String nome;
    LocalDate dataNascimento;
    String cpf;
    ETipoUsuario tipoUsuario;

    public Usuario(Integer id, String nome, LocalDate dataNascimento, String cpf, ETipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {
        super();
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public static Usuario of(UsuarioDto dto) {
        var usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(converterDatas(dto.getDataNascimento()));

        return usuario;
    }

    private static LocalDate converterDatas(String data) {
        return LocalDate.parse(data);
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(ETipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

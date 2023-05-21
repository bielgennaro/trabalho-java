package models;

import dto.UsuarioDto;

import java.time.LocalDate;
import java.util.List;

public class Usuario extends Conta {

    Integer id;
    String nome;
    LocalDate dataNascimento;
    String cpf;
    List<Conta> contas;

    public Usuario(Integer id, Integer agencia, Integer numConta, Float saldo,
                   Float credito, Float limite, Usuario usuario, Integer id1, String nome,
                   LocalDate dataNascimento, String cpf, List<Conta> contas) {
        super(id, agencia, numConta, saldo, credito, limite, usuario);
        this.id = id1;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.contas = contas;
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

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}

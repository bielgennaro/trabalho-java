package dto;

import enums.ETipoUsuario;
import models.Cliente;
import models.Funcionario;

import java.time.LocalDate;

public class UsuarioDto {

    Integer id;
    String nome;
    String cpf;
    String dataNascimento;
    ETipoUsuario tipoUsuario;
    String cargo;
    Integer crm;

    public UsuarioDto(Integer id, String nome, String cpf, String dataNascimento,
                      ETipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioDto(Integer id, String nome, String cpf, String dataNascimento,
                      ETipoUsuario tipoUsuario, String cargo, Integer crm) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
        this.cargo = cargo;
        this.crm = crm;
    }

    public UsuarioDto() {

    }

    public static UsuarioDto convertClienteFrom(Cliente cliente) {
        var clienteDto = new UsuarioDto();
        clienteDto.setNome(cliente.getNome());
        clienteDto.setCpf(cliente.getCpf());
        clienteDto.setDataNascimento(converterDatas(cliente.getDataNascimento()));
        return clienteDto;
    }

    public static UsuarioDto convertFuncionarioFrom(Funcionario funcionario) {
        var funcionarioDto = new UsuarioDto();
        funcionarioDto.setNome(funcionario.getNome());
        funcionarioDto.setCpf(funcionario.getCpf());
        funcionarioDto.setDataNascimento(converterDatas(funcionario.getDataNascimento()));
        funcionarioDto.setCargo(funcionario.getCargo());
        funcionario.setCrm(funcionarioDto.getCrm());
        return funcionarioDto;
    }

    private static String converterDatas(LocalDate data) {
        return String.valueOf(data);
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }
}

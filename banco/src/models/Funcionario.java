package models;

import dto.UsuarioDto;
import enums.ETipoUsuario;

import java.time.LocalDate;

public class Funcionario extends Usuario {

    Integer id;
    String cargo;
    Integer crm;

    public Funcionario(Integer id, String nome, LocalDate dataNascimento, String cpf, ETipoUsuario tipoUsuario,
                       Integer id1, String cargo, Integer crm) {
        super(id, nome, dataNascimento, cpf, tipoUsuario);
        this.id = id1;
        this.cargo = cargo;
        this.crm = crm;
    }

    public Funcionario() {
        super();
    }

    public static Funcionario of(UsuarioDto dto) {
        var funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setDataNascimento(converterDatas(dto.getDataNascimento()));
        funcionario.setCrm(dto.getCrm());
        funcionario.setCargo(dto.getCargo());
        return funcionario;
    }

    private static LocalDate converterDatas(String data) {
        return LocalDate.parse(data);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

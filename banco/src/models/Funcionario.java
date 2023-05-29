package models;

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

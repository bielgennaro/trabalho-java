package models;

import enums.ETipoUsuario;

import java.time.LocalDate;

public class Cliente extends Usuario {

    Integer id;

    public Cliente(Integer id, String nome, LocalDate dataNascimento, String cpf,
                   ETipoUsuario tipoUsuario, Integer id1) {
        super(id, nome, dataNascimento, cpf, tipoUsuario);
        this.id = id1;
    }

    public Cliente() {

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

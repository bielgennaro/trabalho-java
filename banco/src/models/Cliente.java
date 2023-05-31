package models;

import dto.UsuarioDto;
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

    public static Cliente of(UsuarioDto dto) {
        var cliente = new Cliente();
        cliente.setCpf(dto.getCpf());
        cliente.setNome(dto.getNome());
        cliente.setDataNascimento(converterDatas(dto.getDataNascimento()));
        cliente.setTipoUsuario(ETipoUsuario.CLIENTE);
        return cliente;
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
}

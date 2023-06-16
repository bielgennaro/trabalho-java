package models;

import dto.UsuarioDto;
import enums.ETipoUsuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Cliente extends Usuario {

    Integer id;
    List<Conta> contas;

    public Cliente(Integer id, String nome, LocalDate dataNascimento, String cpf,
                   ETipoUsuario tipoUsuario, Integer id1, List<Conta> contas) {
        super(id, nome, dataNascimento, cpf, tipoUsuario);
        this.id = id1;
        this.contas = contas;
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
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}

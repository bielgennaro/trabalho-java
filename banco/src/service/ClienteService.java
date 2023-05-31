package service;

import dto.UsuarioDto;
import models.Cliente;
import models.Usuario;

import java.util.ArrayList;

public class ClienteService implements UsuarioInterface {

    private final UsuarioService usuarioService = new UsuarioService();
    ArrayList<Cliente> clientes = new ArrayList<>();

    public Usuario getUsuariosByCpf(String cpf) {
        var usuario = clientes.stream()
                .filter(u -> u.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .orElse(null);

        return usuario;
    }

    public Usuario getUsuariosById(Integer id) {
        var usuario = clientes.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }

        return usuario;
    }

    public Usuario cadastrarUsuario(UsuarioDto dto) {
        var novoUsuario = Cliente.of(dto);
        novoUsuario.setId(gerarId());
        clientes.add(novoUsuario);
        System.out.println("Usuario criado com sucesso!");
        return novoUsuario;
    }

    private Integer gerarId() {
        var usuarioId = clientes.size();
        return usuarioId + 1;
    }

    @Override
    public void salvar(UsuarioDto usuarioDto) {
        var cliente = Cliente.of(usuarioDto);
        cliente.setId(gerarId());
        clientes.add(cliente);
        System.out.println("Usuario criado com sucesso!");
    }
}

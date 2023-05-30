package service;

import dto.UsuarioDto;
import models.Usuario;

import java.util.ArrayList;

public class ClienteService implements UsuarioInterface {

    ArrayList<Usuario> usuarios = new ArrayList<>();

    public Usuario getUsuariosByCpf(String cpf) {
        var usuario = usuarios.stream()
                .filter(u -> u.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .orElse(null);

        return usuario;
    }

    public Usuario getUsuariosById(Integer id) {
        var usuario = usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }

        return usuario;
    }

    public Usuario cadastrarUsuario(UsuarioDto dto) {
        var novoUsuario = Usuario.of(dto);
        novoUsuario.setId(gerarId());
        usuarios.add(novoUsuario);
        System.out.println("Usuario criado com sucesso!");
        return novoUsuario;
    }

    private Integer gerarId () {
        var usuarioId =  usuarios.size();
        return usuarioId + 1;
    }

    @Override
    public void salvar(UsuarioDto usuarioDto) {
        var usuario = Usuario.of(usuarioDto);

    }
}

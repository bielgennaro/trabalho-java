package service;

import dto.UsuarioDto;
import models.Usuario;

import java.util.ArrayList;

public class UsuarioService {

    ArrayList<Usuario> usuarios = new ArrayList<>();

    public Usuario getUsuariosByCpf(String cpf) {
        var usuario = usuarios.stream()
                .filter(u -> u.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }

        return usuario;
    }

    public Usuario cadastrarUsuario(UsuarioDto dto) {
        var novoUsuario = Usuario.of(dto);
        usuarios.add(novoUsuario);
        System.out.println("Usuario criado com sucesso!");
        return novoUsuario;
    }
}
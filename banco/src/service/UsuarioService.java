package service;

import dto.UsuarioDto;
import models.Usuario;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UsuarioService {

    ArrayList<Usuario> usuarios = new ArrayList<>();

    public Usuario getUsuariosByCpf(String cpf) {
        var usuario = (Usuario) usuarios.stream()
                .filter(u -> u.getCpf().equalsIgnoreCase(cpf))
                .collect(Collectors.toList());

        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }

        return usuario;
    }

    public void cadastrarUsuario(UsuarioDto dto) {
        var usuario = Usuario.of(dto);
    }
}

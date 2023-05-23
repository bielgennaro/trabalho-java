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

    public Usuario logarUsuario(UsuarioDto dto) {
        Object nome = dto.getNome();
        Object cpf = dto.getCpf();

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.getCpf().equals(cpf)) {
                System.out.println("Bem vindo " + nome);
            }else{
                throw new RuntimeException("Credenciais invalidas");
            }
        }
        return null;
    }
}
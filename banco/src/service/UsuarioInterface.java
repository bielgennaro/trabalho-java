package service;

import dto.UsuarioDto;
import models.Usuario;

public interface UsuarioInterface {

    Usuario salvar(UsuarioDto usuarioDto);

    UsuarioDto getUsuarioById(Integer id);

    UsuarioDto getUsuariosByCpf(String cpf);

    void getAll();

}

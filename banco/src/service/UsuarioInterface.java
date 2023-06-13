package service;

import dto.UsuarioDto;

public interface UsuarioInterface {

    void salvar(UsuarioDto usuarioDto);

    UsuarioDto getUsuarioById(Integer id);
}

package service;

import dto.ContaDto;
import enums.ETipoUsuario;
import models.Usuario;

public class ServicoService {

    private ClienteService clienteService;
    private ContaService contaService;
    private UsuarioService usuarioService;
    private UsuarioInterface usuarioInterface;
    private FuncionarioService funcionarioService;

    public ContaDto listarDadosConta(Integer numConta) {
        var conta = contaService.getContaByNumConta(numConta);
        return ContaDto.convertFrom(conta);
    }

    public boolean isFuncionario(Usuario usuario) {
        return usuario.getTipoUsuario().equals(ETipoUsuario.FUNCIONARIO);
    }
}

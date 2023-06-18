package service;

import enums.ETipoUsuario;
import models.Conta;
import models.Usuario;

import java.util.Scanner;

public class UsuarioService {

    private static Usuario usuarioAutenticado;
    private static Conta contaUsuarioAutenticado;
    private ClienteService clienteService;
    private ContaService contaService;
    private FuncionarioService funcionarioService;

    public UsuarioService() {

    }

    public UsuarioService(ClienteService clienteService, ContaService contaService, FuncionarioService funcionarioService) {
        this.clienteService = clienteService;
        this.contaService = contaService;
        this.funcionarioService = funcionarioService;
    }

    public Usuario logarUsuario() {
        var scanner = new Scanner(System.in);

        while (usuarioAutenticado == null && contaUsuarioAutenticado == null) {
            System.out.println("+---------------------------+");
            System.out.println(" Digite sua conta:           ");
            System.out.println("+---------------------------+");
            var conta = scanner.nextInt();

            System.out.println("+---------------------------+");
            System.out.println(" Digite sua senha:           ");
            System.out.println("+---------------------------+");
            var senha = scanner.nextInt();

            contaUsuarioAutenticado = contaService.logarContaAutenticada(conta, senha);
            usuarioAutenticado = autenticarUsuario(contaUsuarioAutenticado);
        }

        System.out.println("Bem vindo " + usuarioAutenticado.getNome());
        return usuarioAutenticado;
    }

    public Usuario getUsuarioAutenticado() {
        if (usuarioAutenticado != null) {
            return usuarioAutenticado;
        }

        return null;
    }

    public boolean isFuncionario(Usuario usuario) {
        return usuario.getTipoUsuario().equals(ETipoUsuario.FUNCIONARIO);
    }

    public void consultarDadosDaConta() {
        var funcionario = isFuncionario(usuarioAutenticado);
        var usuario = funcionario ?
                funcionarioService.getUsuarioById(usuarioAutenticado.getId()) :
                clienteService.getUsuarioById(usuarioAutenticado.getId());

        System.out.println("+---------------------------+");
        System.out.println("nome: " + usuario.getNome());
        System.out.println("cpf: " + usuario.getCpf());

        if (funcionario) {
            System.out.println("cargo: " + usuario.getCargo());
            System.out.println("crm: " + usuario.getCrm());
        }
        System.out.println("+---------------------------+");
    }

    private Usuario autenticarUsuario(Conta conta) {
        if (conta != null) {
            usuarioAutenticado = conta.getUsuario();
            return usuarioAutenticado;
        }

        return null;
    }

    public void deslogarUsuario() {
        if (usuarioAutenticado != null && contaUsuarioAutenticado != null) {
            usuarioAutenticado = null;
            contaUsuarioAutenticado = null;
        }
    }
}
package service;

import enums.ETipoUsuario;
import models.Conta;
import models.Usuario;

import java.util.Scanner;

public class UsuarioService {

    private static Usuario usuarioAutenticado;
    private final ClienteService clienteService = new ClienteService();
    private final ContaService contaService = new ContaService();
    private final FuncionarioService funcionarioService = new FuncionarioService();

    public void logarUsuario() {
        var scanner = new Scanner(System.in);

        while (usuarioAutenticado == null) {
            System.out.println("+---------------------------+");
            System.out.println(" Digite sua conta:           ");
            System.out.println("+---------------------------+");
            var conta = scanner.nextInt();

            System.out.println("+---------------------------+");
            System.out.println(" Digite sua senha:           ");
            System.out.println("+---------------------------+");
            var senha = scanner.nextInt();

            contaService.logarContaAutenticada(conta, senha);
            autenticarUsuario(contaService.getContaAutenticada());
        }

        System.out.println("Bem vindo " + usuarioAutenticado.getNome());
    }

    public void deslogarUsuario() {
        if (usuarioAutenticado != null) {
            usuarioAutenticado = null;
        }
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
        var usuario = isFuncionario(usuarioAutenticado) ?
                funcionarioService.getUsuarioById(usuarioAutenticado.getId()) :
                clienteService.getUsuarioById(usuarioAutenticado.getId());

        System.out.println("+---------------------------+");
        System.out.println("nome: " + usuario.getNome());
        System.out.println("+cpf: " + usuario.getCpf());
        System.out.println("+nome: " + usuario.getNome());
        System.out.println("+cargo: " + usuario.getCargo());
        System.out.println("+crm: " + usuario.getCrm());
        System.out.println("+---------------------------+");
    }

    private void autenticarUsuario(Conta conta) {
        usuarioAutenticado = conta.getUsuario();
    }
}
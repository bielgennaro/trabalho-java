package service;

import dto.UsuarioDto;
import enums.ETipoUsuario;
import models.Usuario;

import java.io.IOException;
import java.util.Scanner;

public class UsuarioService {

    private static Usuario usuarioAutenticado;
    private final ClienteService clienteService = new ClienteService();
    private final ContaService contaService = new ContaService();
    private final FuncionarioService funcionarioService = new FuncionarioService();

    public void logarUsuario() throws IOException {
        var scanner = new Scanner(System.in);

        while (usuarioAutenticado == null) {
            System.out.println("+---------------------------+");
            System.out.println(" Digite seu cpf:             ");
            System.out.println("+---------------------------+");

            var cpf = scanner.next();

            System.out.println("+---------------------------+");
            System.out.println(" Digite sua conta:           ");
            System.out.println("+---------------------------+");

            var conta = scanner.nextInt();

            var usuario = clienteService.getUsuariosByCpf(cpf);
            var contaAutenticada = contaService.getContaByNumConta(conta);

            if (usuario == null || contaAutenticada == null) {
                System.out.println("Credenciais invalidas");
            }

            usuarioAutenticado = usuario;
        }

        System.out.println("Bem vindo " + usuarioAutenticado.getNome());
    }

    public Usuario getUsuarioAutenticado() {
        if (usuarioAutenticado != null) {
            return usuarioAutenticado;
        }

        return null;
    }

    public void cadastrarUsuario() {
        var scanner = new Scanner(System.in);

        System.out.println("+---------------------------+");
        System.out.println(" Digite seu nome:            ");
        System.out.println("+---------------------------+");
        var nome = scanner.next();

        System.out.println("+---------------------------+");
        System.out.println(" Digite seu cpf:             ");
        System.out.println("+---------------------------+");
        var cpf = scanner.next();

        System.out.println("+---------------------------------+");
        System.out.println(" Digite sua data de nascimento:    ");
        System.out.println("+---------------------------------+");
        var dataNascimento = scanner.next();

        System.out.println("+---------------------------+");
        System.out.println("Selecione o tipo de conta    ");
        System.out.println(" 1- Cliente                  ");
        System.out.println(" 2- Funcionario              ");
        System.out.println("+---------------------------+");
        var opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                var clienteDto = new UsuarioDto(1, nome, cpf, dataNascimento, ETipoUsuario.CLIENTE);
                clienteService.salvar(clienteDto);
                break;

            case 2:
                var funcionarioDto = new UsuarioDto(1, nome, cpf, dataNascimento, ETipoUsuario.FUNCIONARIO);
                funcionarioService.salvar(funcionarioDto);
                break;
        }

    }
}
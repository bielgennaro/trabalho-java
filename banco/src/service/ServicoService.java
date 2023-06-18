package service;

import dto.ContaDto;
import dto.UsuarioDto;
import models.Usuario;

import java.util.Scanner;

public class ServicoService {

    private static final Scanner scanner = new Scanner(System.in);
    private ContaService contaService;
    private UsuarioService usuarioService;
    private ClienteService clienteService;
    private FuncionarioService funcionarioService;
    private static Usuario usuarioAutenticado;

    public ServicoService() {}


    public ServicoService(ContaService contaService, UsuarioService usuarioService, ClienteService clienteService, FuncionarioService funcionarioService) {
        this.contaService = contaService;
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
    }

    public void iniciaProjeto() {
        cadastrarContaPadrao();
        while (usuarioAutenticado == null) {
            System.out.println("+---------------------------+");
            System.out.println(" Selecione a opção desejada: ");
            System.out.println(" 1- Logar                    ");
            System.out.println(" 2- Criar nova conta         ");
            System.out.println("+---------------------------+");
            var opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    usuarioAutenticado = usuarioService.logarUsuario();
                    listarOpcoesDeInteracao();
                    break;
                case 2:
                    var usuario = cadastrarUsuario();
                    criarConta(usuario);
                    break;
            }
        }
    }

    private void logarUsuario() {

    }

    private void listarOpcoesDeInteracao() {
        while (usuarioAutenticado != null) {
            System.out.println("+----------------------------+");
            System.out.println(" 1- Consultar dados           ");
            System.out.println(" 2- Realizar operações        ");
            System.out.println("+----------------------------+");
            var opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    usuarioService.consultarDadosDaConta();
                    break;
                case 2:
                    realizarOperacoes();
                    break;
            }
        }
    }

    private Usuario cadastrarUsuario() {
        var dto = new UsuarioDto();

        System.out.println("+---------------------------+");
        System.out.println(" Digite seu nome:            ");
        System.out.println("+---------------------------+");
        dto.setNome(scanner.next());

        System.out.println("+---------------------------+");
        System.out.println(" Digite seu cpf:             ");
        System.out.println("+---------------------------+");
        dto.setCpf(scanner.next());

        System.out.println("+---------------------------------+");
        System.out.println(" Digite sua data de nascimento:    ");
        System.out.println("+---------------------------------+");
        dto.setDataNascimento(scanner.next());

        System.out.println("+---------------------------+");
        System.out.println("Selecione o tipo de conta    ");
        System.out.println(" 1- Cliente                  ");
        System.out.println(" 2- Funcionario              ");
        System.out.println("+---------------------------+");
        var opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                var cliente = clienteService.salvar(dto);
                return cliente;
            case 2:
                var funcionario = funcionarioService.salvar(dto);
                return funcionario;
        }

        return null;
    }

    public void realizarOperacoes() {
        System.out.println("+------------------------------+");
        System.out.println("Quais operações deseja realizar?");
        System.out.println(" 1- Realizar transferências     ");
        System.out.println(" 2- Consultar saldo e limite    ");
        System.out.println(" 3- Solicitar limite de cŕedito ");
        System.out.println(" 4- Realizar pagamentos         ");
        System.out.println(" 5- Criar uma nova conta        ");
        System.out.println(" 6- Encerrar seção              ");
        System.out.println("+------------------------------+");
        var opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                contaService.realizarTransferencias();
                break;
            case 2:
                contaService.ListarSaldoELimiteConta();
                break;
            case 3:
                contaService.inserirNovoLimite();
                break;
            case 4:
                contaService.realizarPagamentos();
                break;
            case 5:
                criarConta(usuarioAutenticado);
                break;
            case 6:
                deslogarUsuario();
                break;
        }
    }

    private void criarConta(Usuario usuario) {
        var dto = new ContaDto();

        System.out.println("+---------------------------+");
        System.out.println(" Digite o numero da agência: ");
        System.out.println("+---------------------------+");
        dto.setAgencia(scanner.nextInt());

        System.out.println("+---------------------------+");
        System.out.println(" Digite o valor da sua renda:");
        System.out.println("+---------------------------+");
        dto.setRenda(Float.parseFloat(scanner.next()));

        System.out.println("+---------------------------+");
        System.out.println(" Digite uma senha de numeros:");
        System.out.println("+---------------------------+");
        dto.setSenha(scanner.nextInt());

        contaService.criarConta(dto, usuario);
    }

    private void deslogarUsuario() {
        if (usuarioAutenticado != null) {
            usuarioService.deslogarUsuario();
            usuarioAutenticado = null;
        }
    }

    private void cadastrarContaPadrao() {
        var usuario = funcionarioService.cadastrarFuncionarioPadrao();
        contaService.cadastrarContaPadrao(usuario);
    }
}
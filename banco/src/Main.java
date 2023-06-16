import service.*;

public class Main {
    public static void main(String[] args) {

        final ContaService contaService = new ContaService();
        final ClienteService clienteService = new ClienteService();
        final FuncionarioService funcionarioService = new FuncionarioService();
        final UsuarioService usuarioService = new UsuarioService(clienteService, contaService, funcionarioService);

        var servicoService = new ServicoService(contaService, usuarioService, clienteService, funcionarioService);

        servicoService.iniciaProjeto();
    }
}
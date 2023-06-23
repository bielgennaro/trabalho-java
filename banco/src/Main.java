import service.*;

public class Main {
    public static void main(String[] args) {

        final ClienteService clienteService = new ClienteService();
        final FuncionarioService funcionarioService = new FuncionarioService();
        final ContaService contaService = new ContaService(clienteService, funcionarioService);
        final UsuarioService usuarioService = new UsuarioService(clienteService, contaService, funcionarioService);

        var servicoService = new ServicoService(contaService, usuarioService, clienteService, funcionarioService);

        servicoService.iniciaProjeto();
    }
}
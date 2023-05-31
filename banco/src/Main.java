import jdbc.Conexao;
import service.ClienteService;
import service.ContaService;
import service.ServicoService;
import service.UsuarioService;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Conexao conexao = new Conexao();

        Locale brasil = new Locale("PT", "BR");
        DateFormat data = DateFormat.getDateInstance(DateFormat.FULL, brasil);
        var contaService = new ContaService();

        var clienteService = new ClienteService();
        var servicoService = new ServicoService();
        var usuarioService = new UsuarioService();

        var scanner = new Scanner(System.in);

        System.out.println("+---------------------------+");
        System.out.println(" Selecione a opção desejada: ");
        System.out.println(" 1- Logar                    ");
        System.out.println(" 2- Criar nova conta         ");
        System.out.println("+---------------------------+");

        var opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                usuarioService.logarUsuario();
                break;

            case 2:
                usuarioService.cadastrarUsuario();
                break;
        }

    }
}
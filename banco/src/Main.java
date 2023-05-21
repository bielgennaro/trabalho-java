import service.ContaService;
import service.ServicoService;
import service.UsuarioService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ContaService contaService;
        UsuarioService usuarioService;
        ServicoService service = null;

        var scanner = new Scanner(System.in);
        var opcao = scanner.nextInt();

        System.out.println("+---------------------------+");
        System.out.println(" Selecione a opção desejada: ");
        System.out.println(" 1- Logar                    ");
        System.out.println(" 2- Criar nova conta         ");
        System.out.println("+---------------------------+");

        switch (opcao) {
            case 1:
                System.out.println("+---------------------------+");
                System.out.println(" Digite seu cpf:             ");
                System.out.println("+---------------------------+");
                var cpf = scanner.next();
                System.out.println("+---------------------------+");
                System.out.println(" Digite sua conta:           ");
                System.out.println("+---------------------------+");
                var conta = scanner.nextInt();
                service.logarUsuario(cpf, conta);
        }


    }
}
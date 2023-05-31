package service;

import dto.UsuarioDto;
import models.Funcionario;

import java.util.ArrayList;
import java.util.Scanner;

public class FuncionarioService implements UsuarioInterface {

    private final UsuarioService usuarioService = new UsuarioService();
    ArrayList<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void salvar(UsuarioDto dto) {
        preencherDadosAdicionais(dto);
        validarAutorizacaoCadastroFuncionario();
        var funcionario = Funcionario.of(dto);
        funcionarios.add(funcionario);
        System.out.println("Funcionario cadastrado com sucesso");
    }

    private UsuarioDto preencherDadosAdicionais(UsuarioDto dto) {
        var scanner = new Scanner(System.in);
        System.out.println("+---------------------------------+");
        System.out.println(" Digite o cargo do funcionario:    ");
        System.out.println("+---------------------------------+");
        dto.setCargo(scanner.next());

        System.out.println("+---------------------------------+");
        System.out.println(" Digite o crm do funcionario:      ");
        System.out.println("+---------------------------------+");
        dto.setCrm(scanner.nextInt());
        return dto;
    }

    private void validarAutorizacaoCadastroFuncionario() {
        var usuario = usuarioService.getUsuarioAutenticado();
        if (!usuarioService.isFuncionario(usuario)) {
            System.out.println("Usuario sem permissao para realizar cadastro de funcionarios");
        }
    }
}

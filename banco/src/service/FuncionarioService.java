package service;

import dto.UsuarioDto;
import enums.ETipoUsuario;
import models.Funcionario;
import models.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class FuncionarioService implements UsuarioInterface {

    private UsuarioService usuarioService;
    ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioService() {

    }

    public FuncionarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Usuario salvar(UsuarioDto dto) {
        preencherDadosAdicionais(dto);
        validarAutorizacaoCadastroFuncionario();
        var funcionario = Funcionario.of(dto);
        funcionario.setId(gerarId());
        funcionarios.add(funcionario);
        System.out.println("Funcionario cadastrado com sucesso");
        return funcionario;
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

    @Override
    public UsuarioDto getUsuarioById(Integer id) {
        var funcionario = funcionarios.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);

        return UsuarioDto.convertFuncionarioFrom(funcionario);
    }

    private void validarAutorizacaoCadastroFuncionario() {
        var usuario = usuarioService.getUsuarioAutenticado();
        if (!usuarioService.isFuncionario(usuario)) {
            System.out.println("Usuario sem permissao para realizar cadastro de funcionarios");
        }
    }

    private Integer gerarId() {
        var funcionario = funcionarios.size();
        return funcionario + 1;
    }

    public Usuario cadastrarFuncionarioPadrao() {
        var funcionario = Funcionario.of(umFuncionario());
        funcionarios.add(funcionario);
        return funcionario;
    }

    private UsuarioDto umFuncionario() {
        var usuario = new UsuarioDto();
        usuario.setId(1);
        usuario.setCargo("Gerente");
        usuario.setNome("ADM");
        usuario.setCpf("108.007.889-40");
        usuario.setCrm(123);
        usuario.setDataNascimento("20-09-2003");
        usuario.setTipoUsuario(ETipoUsuario.FUNCIONARIO);
        return usuario;
    }
}
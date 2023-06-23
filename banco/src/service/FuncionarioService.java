package service;

import dto.UsuarioDto;
import enums.ETipoUsuario;
import models.Conta;
import models.Funcionario;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FuncionarioService implements UsuarioInterface {

    ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private UsuarioService usuarioService;

    public FuncionarioService() {

    }

    public FuncionarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Usuario salvar(UsuarioDto dto) {
        try {
            preencherDadosAdicionais(dto);
            validarAutorizacaoCadastroFuncionario();
            var funcionario = Funcionario.of(dto);
            funcionario.setId(gerarId());
            funcionarios.add(funcionario);
            System.out.println("Funcionario cadastrado com sucesso");
            return funcionario;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuario");
            throw new RuntimeException(e);
        }
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
    public void getAll() {
        funcionarios.forEach(funcionario -> {
            System.out.println("+--------------------------------+");
            System.out.println(" nome:" + funcionario.getNome());
            System.out.println(" cpf:" + funcionario.getCpf());
            System.out.println(" crm:" + funcionario.getCrm());
            System.out.println(" cargo:" + funcionario.getCargo());
            listarContaUsuario(funcionario.getContas());
            System.out.println("+--------------------------------+");
        });
    }

    private void listarContaUsuario(List<Conta> contas) {
        contas.forEach(conta -> {
            System.out.println(" Contas do usuario");
            System.out.println(" conta:" + conta.getNumConta());
        });
    }

    @Override
    public UsuarioDto getUsuariosByCpf(String cpf) {
        var funcionario = funcionarios.stream()
                .filter(u -> u.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .orElse(null);

        return UsuarioDto.convertFuncionarioFrom(funcionario);
    }

    public void cadastrarContas(Usuario usuario, Conta conta) {
        var usuarioById = funcionarios.stream()
                .filter(u -> u.getId().equals(usuario.getId()))
                .findFirst()
                .orElse(null);

        if (usuarioById != null) {
            List<Conta> contas = usuarioById.getContas();
            if (contas == null) {
                contas = new ArrayList<>();
            }
            contas.add(conta);
            usuarioById.setContas(contas);
            funcionarios.set(getIndexConta(usuario.getId()), usuarioById);
        }
    }

    private Integer getIndexConta(Integer id) {
        var indexOptional = IntStream.range(0, funcionarios.size())
                .filter(i -> funcionarios.get(i).getId().equals(id))
                .findFirst();

        return indexOptional.getAsInt();
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
        var conta = new Conta();
        conta.setNumConta(1234);
        List<Conta> contas = new ArrayList<>();
        contas.add(conta);
        funcionario.setContas(contas);
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
package service;

import dto.UsuarioDto;
import models.Cliente;
import models.Conta;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ClienteService implements UsuarioInterface {

    List<Cliente> clientes = new ArrayList<>();
    private UsuarioService usuarioService;

    public ClienteService() {
    }

    public ClienteService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UsuarioDto getUsuariosByCpf(String cpf) {
        var usuario = clientes.stream()
                .filter(u -> u.getCpf().equalsIgnoreCase(cpf))
                .findFirst()
                .orElse(null);

        return UsuarioDto.convertClienteFrom(usuario);
    }

    public Usuario getUsuariosById(Integer id) {
        var usuario = clientes.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }

        return usuario;
    }

    @Override
    public void getAll() {
        clientes.forEach(cliente -> {
            System.out.println("+--------------------------------+");
            System.out.println(" nome:" + cliente.getNome());
            System.out.println(" cpf:" + cliente.getCpf());
            listarContaUsuario(cliente.getContas());
            System.out.println("+--------------------------------+");
        });
    }

    private void listarContaUsuario(List<Conta> contas) {
        System.out.println(" Contas do usuario");
        contas.forEach(conta -> {
            System.out.println(" conta:" + conta.getNumConta());
        });
    }

    private Integer gerarId() {
        var usuarioId = clientes.size();
        return usuarioId + 1;
    }

    @Override
    public Usuario salvar(UsuarioDto usuarioDto) {
        try {
            var cliente = Cliente.of(usuarioDto);
            cliente.setId(gerarId());
            clientes.add(cliente);
            System.out.println("Usuario criado com sucesso!");
            return cliente;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuario");
            throw new RuntimeException(e);
        }
    }

    @Override
    public UsuarioDto getUsuarioById(Integer id) {
        var cliente = clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        return UsuarioDto.convertClienteFrom(cliente);
    }

    public void cadastrarContas(Usuario usuario, Conta conta) {
        var usuarioById = clientes.stream()
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
            clientes.set(getIndexConta(usuario.getId()), usuarioById);
        }
    }

    private Integer getIndexConta(Integer id) {
        var indexOptional = IntStream.range(0, clientes.size())
                .filter(i -> clientes.get(i).getId().equals(id))
                .findFirst();

        return indexOptional.getAsInt();
    }
}

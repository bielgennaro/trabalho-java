package service;

import dto.ContaDto;
import jdbc.Conexao;
import models.Conta;
import models.Usuario;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContaService {

    private static final Scanner scanner = new Scanner(System.in);
    private static Conta contaUsuarioAutenticado;
    ArrayList<Conta> contas = new ArrayList<>();
    ArrayList<Integer> numContas = new ArrayList<>();
    private ClienteService clienteService;

    public ContaService() {

    }

    public ContaService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    public Conta getContaByNumConta(Integer numConta) {
        var conta = contas.stream()
                .filter(c -> c.getNumConta().equals(numConta))
                .findFirst()
                .orElse(null);

        return conta;
    }

    public Conta logarContaAutenticada(Integer numConta, Integer senha) {
        var conta = contas.stream()
                .filter(c -> c.getNumConta().equals(numConta)
                        && c.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (conta == null) {
            System.out.println("Credenciais invalidas");
        }

        contaUsuarioAutenticado = conta;
        return contaUsuarioAutenticado;
    }

    public void ListarSaldoELimiteConta() {
        var contaAutenticada = getContaAutenticada();
        System.out.println("Saldo: " + contaAutenticada.getSaldo());
        System.out.println("Limite disponivel:" + contaAutenticada.getLimiteDisponivel());
        System.out.println("Limite: " + contaAutenticada.getLimite());
    }

    private void validarSaldoConta(Float valor, Conta conta) {
        if (valor > conta.getSaldo()) {
            System.out.println("Saldo insuficiente para realizar a transação");
        }
    }

    private void validarLimiteConta(Float valor, Conta conta) {
        if (valor > conta.getLimiteDisponivel()) {
            System.out.println("Limite insuficiente para realizar a transação");
        }
    }

    public void realizarTransferenciaEntreContas(Float valor, Conta conta) {
        var contaAutenticada = getContaAutenticada();
        validarSaldoConta(valor, contaAutenticada);
        conta.setSaldo(conta.getSaldo() + valor);
        contaAutenticada.setSaldo(contaAutenticada.getSaldo() - valor);
        System.out.println("Transferência realizada com sucesso");
        contas.set(getIndexConta(conta.getId()), conta);
        atualizarContaAutenticada(contaAutenticada);
    }

    public void inserirNovoLimite() {
        var contaAutenticada = getContaAutenticada();
        System.out.println("Seu limite total no momente é de " + contaAutenticada.getLimite());
        System.out.println("Qual valor deseja para adicionar ao seu limite?");
        var novoLimite = scanner.nextFloat();
        contaAutenticada.setLimite(contaAutenticada.getLimite() + novoLimite);
        atualizarContaAutenticada(contaAutenticada);
        System.out.println("Novo Limite adiconado com sucesso");
    }

    public void realizarTransferencias() {
        System.out.println("Digite o numero da conta de quem quer realizar a transferência");
        var conta = getContaByNumConta(scanner.nextInt());
        System.out.println("Digite o valor da transferência");
        var valor = scanner.nextFloat();
        realizarTransferenciaEntreContas(valor, conta);
    }

    private void realizarPagamentosComContaCorrente(Float valor) {
        var contaAutenticada = getContaAutenticada();
        validarSaldoConta(valor, contaAutenticada);
        contaAutenticada.setSaldo(contaAutenticada.getSaldo() - valor);
        atualizarContaAutenticada(contaAutenticada);
    }

    private void realizarPagamentosComLimiteCredito(Float valor) {
        var contaAutenticada = getContaAutenticada();
        validarLimiteConta(valor, contaAutenticada);
        contaAutenticada.setLimiteDisponivel(contaAutenticada.getLimiteDisponivel() - valor);
        atualizarContaAutenticada(contaAutenticada);
    }

    public void realizarPagamentos() {
        System.out.println("Digite o valor do pagamente");
        var valor = scanner.nextFloat();

        System.out.println("Selecione como ira pagar:        ");
        System.out.println(" 1- Saldo conta corrente corrente");
        System.out.println(" 2- Saldo do limite de crédito   ");

        switch (scanner.nextInt()) {
            case 1:
                realizarPagamentosComContaCorrente(valor);
                break;
            case 2:
                realizarPagamentosComLimiteCredito(valor);
                break;
        }

        System.out.println("Pagamento realizado com sucesso");
    }

    public List<Conta> getContasByUsuarioId(Integer id) {
        var usuario = clienteService.getUsuariosById(id);
        return contas.stream()
                .filter(c -> c.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    public Conta criarConta(ContaDto dto, Usuario usuario) {
        dto.setLimite(calcularLimite(dto.getRenda()));
        dto.setNumConta(gerarNumConta());
        var novaConta = Conta.of(dto, usuario);
        novaConta.setId(gerarId());
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso!");
        System.out.println("Parabens o numero da sua conta é " +  novaConta.getNumConta());
        return novaConta;
    }

    private Float calcularLimite(Float renda) {
        return (renda/100)*80;
    }

    private Integer gerarId() {
        var contaId = contas.size();
        return contaId + 1;
    }

    private Integer getIndexConta(Integer id) {
        var indexOptional = IntStream.range(0, contas.size())
                .filter(i -> contas.get(i).getId().equals(id))
                .findFirst();

        return indexOptional.getAsInt();
    }

    private void atualizarContaAutenticada(Conta contaAutenticada) {
        contas.set(getIndexConta(contaAutenticada.getId()), contaAutenticada);
    }

    private Integer gerarNumConta() {
        var random = new Random();
        var numConta = 0;
        while (numContas.contains(numConta)) {
            numConta = random.nextInt(900000) + 100000;
        }

        return numConta;
    }

    public Conta getContaAutenticada() {
        if (contaUsuarioAutenticado != null) {
            return contaUsuarioAutenticado;
        }

        return null;
    }
}
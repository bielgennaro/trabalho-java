package service;

import dto.ContaDto;
import jdbc.Conexao;
import models.Conta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ContaService {

    private static final Scanner scanner = new Scanner(System.in);
    private static Conta contaUsuarioAutenticado;
    ArrayList<Conta> contas = new ArrayList<>();
    private final ClienteService clienteService = new ClienteService();
    private final Conexao conexao = new Conexao();

    public Conta getContaByNumConta(Integer numConta) {
        var conta = contas.stream()
                .filter(c -> c.getNumConta().equals(numConta))
                .findFirst()
                .orElse(null);

        return conta;
    }

    public void logarContaAutenticada(Integer numConta, Integer senha) {
        var conta = contas.stream()
                .filter(c -> c.getNumConta().equals(numConta)
                        && c.getSenha().equals(senha))
                .findFirst()
                .orElse(null);

        if (conta == null) {
            System.out.println("Credenciais invalidas");
        }

        contaUsuarioAutenticado = conta;
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
    }

    public void inserirNovoLimite() {
        var contaAutenticada = getContaAutenticada();
        System.out.println("Seu limite total no momente é de " + contaAutenticada.getLimite());
        System.out.println("Qual valor deseja para adicionar ao seu limite?");
        var novoLimite = scanner.nextFloat();
        contaAutenticada.setLimite(contaAutenticada.getLimite() + novoLimite);
        System.out.println("Novo Limite adiconado com sucesso");
    }

    public void realizarTransferencias() {
        System.out.println("Digite o numero da conta de quem quer realizar a transferência");
        var conta = getContaByNumConta(scanner.nextInt());
        System.out.println("Digite o valor da transferência");
        var valor = scanner.nextFloat();
        realizarTransferenciaEntreContas(valor, conta);
    }

    private void realizarTransferenciasComContaCorrente(Float valor) {
        var contaAutenticada = getContaAutenticada();
        validarSaldoConta(valor, contaAutenticada);
        contaAutenticada.setSaldo(contaAutenticada.getSaldo() - valor);
    }

    private void realizarTransferenciasComLimiteCredito(Float valor) {
        var contaAutenticada = getContaAutenticada();
        validarLimiteConta(valor, contaAutenticada);
        contaAutenticada.setLimiteDisponivel(
                contaAutenticada.getLimiteDisponivel() - valor);
    }

    public void realizarPagamentos() {
        System.out.println("Digite o valor do pagamente");
        var valor = scanner.nextFloat();

        System.out.println("Selecione como ira pagar:        ");
        System.out.println(" 1- Saldo conta corrente corrente");
        System.out.println(" 2- Saldo do limite de crédito   ");

        switch (scanner.nextInt()) {
            case 1:
                realizarTransferenciasComContaCorrente(valor);
                break;
            case 2:
                realizarTransferenciasComLimiteCredito(valor);
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

    public Conta criarConta(ContaDto dto, Float renda) {
        dto.setLimite(calcularLimite(renda));
        var novaConta = Conta.of(dto);
        novaConta.setId(gerarId());
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso!");
        return novaConta;
    }

    private Float calcularLimite(Float renda) {
        return (renda/100)*80;
    }

    private Integer gerarId() {
        var contaId = contas.size();
        return contaId + 1;
    }

    public Conta getContaAutenticada() {
        if (contaUsuarioAutenticado != null) {
            return contaUsuarioAutenticado;
        }

        return null;
    }
}
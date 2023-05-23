package service;

import models.Conta;
import models.Usuario;

public class ServicoService {

    Conta conta = new Conta();
    private UsuarioService usuarioService;
    private ContaService contaService;

    public void logarUsuario(String cpf, Conta saldo) {
        Usuario usuario = usuarioService.getUsuariosByCpf(cpf);
        Conta conta = contaService.getContaByNumConta(saldo.getNumConta());
    }

    public void Pix(Integer conta, Float valor, Integer contaEnviar) {
        var contaAserEnviada = contaService.getContaByNumConta(contaEnviar);
        validarSaldo(valor, contaAserEnviada);
        contaAserEnviada.setSaldo(contaAserEnviada.getSaldo() - valor);
        var contaAserDepositado = contaService.getContaByNumConta(conta);
        contaAserDepositado.setSaldo(contaAserDepositado.getSaldo() + valor);
        System.out.println("Valor enviado com sucesso");
    }


    private void validarSaldo(Float saldo, Conta conta) {
        if (conta.getSaldo() < saldo) {
            throw new RuntimeException("Saldo insuficiente");
        }
    }

    public void Deposito(Float saldo) {

    }
}

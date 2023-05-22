package service;

import models.Conta;

public class ServicoService {

    private UsuarioService usuarioService;
    private ContaService contaService;

    public void logarUsuario(String cpf, Conta conta) {
        usuarioService.getUsuariosByCpf(cpf);
        contaService.getContaByNumConta(conta);
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

    public void Boleto(Integer conta, Float valor, Integer id) {

        36
    }
}

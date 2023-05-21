package models;

import dto.ContaDto;

public class Conta {

    Integer id;
    Integer agencia;
    Integer numConta;
    Float saldo;
    Float credito;
    Float limite;
    Usuario usuario;

    public Conta(Integer id, Integer agencia, Integer numConta, Float saldo, Float credito, Float limite, Usuario usuario) {
        this.id = id;
        this.agencia = agencia;
        this.numConta = numConta;
        this.saldo = saldo;
        this.credito = credito;
        this.limite = limite;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conta() {}

    public static Conta of (ContaDto dto) {
        var conta = new Conta();
        conta.setId(dto.getId());
        conta.setNumConta(dto.getNumConta());
        conta.setLimite(dto.getLimite());
        conta.setUsuario(new Usuario(dto.getUsuarioId()));
        return conta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getNumConta() {
        return numConta;
    }

    public void setNumConta(Integer numConta) {
        this.numConta = numConta;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Float getCredito() {
        return credito;
    }

    public void setCredito(Float credito) {
        this.credito = credito;
    }

    public Float getLimite() {
        return limite;
    }

    public void setLimite(Float limite) {
        this.limite = limite;
    }

}
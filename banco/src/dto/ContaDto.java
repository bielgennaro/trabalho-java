package dto;

public class ContaDto {

    Integer id;
    Integer agencia;
    Integer numConta;
    Float credito;
    Float limite;
    Integer usuarioId;

    public ContaDto(Integer id, Integer agencia, Integer numConta, Float credito, Float limite, Integer usuarioId) {
        this.id = id;
        this.agencia = agencia;
        this.numConta = numConta;
        this.credito = credito;
        this.limite = limite;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public Float getLimite() {
        return limite;
    }

    public void setLimite(Float limite) {
        this.limite = limite;
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

    public Float getCredito() {
        return credito;
    }

    public void setCredito(Float credito) {
        this.credito = credito;
    }
}

package dto;

public class ContaDto {

    Integer id;
    Integer agencia;
    Integer numConta;
    Float credito;

    public ContaDto(Integer id, Integer agencia, Integer numConta, Float credito) {
        this.id = id;
        this.agencia = agencia;
        this.numConta = numConta;
        this.credito = credito;
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

    public Float getCredito() {
        return credito;
    }

    public void setCredito(Float credito) {
        this.credito = credito;
    }
}

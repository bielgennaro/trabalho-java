package enums;

public enum ETipoUsuario {

    FUNCIONARIO("Funcionario"),
    CLIENTE("Cliente");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    ETipoUsuario(String descricao) {
        this.descricao = descricao;
    }
}

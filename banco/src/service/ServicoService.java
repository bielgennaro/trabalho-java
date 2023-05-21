package service;

public class ServicoService {

    private UsuarioService usuarioService;
    private ContaService contaService;

    public void logarUsuario(String cpf, Integer conta) {
        usuarioService.getUsuariosByCpf(cpf);
        contaService.getContaByNumConta(conta);
    }

}

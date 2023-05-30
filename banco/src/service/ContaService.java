package service;

import dto.ContaDto;
import models.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContaService {

    private ClienteService clienteService;
    ArrayList<Conta> contas = new ArrayList<>();

    public Conta getContaByNumConta(Integer numConta) {
        var conta = contas.stream()
                .filter(c -> c.getNumConta().equals(numConta))
                .findFirst()
                .orElse(null);

        return conta;
    }

    public List<Conta> getContasByUsuarioId(Integer id) {
        var usuario = clienteService.getUsuariosById(id);
        return contas.stream()
                .filter(c -> c.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    public Conta criarConta(ContaDto dto) {
        var novaConta = Conta.of(dto);
        novaConta.setId(gerarId());
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso!");
        return novaConta;
    }

    private Integer gerarId () {
        var contaId =  contas.size();
        return contaId + 1;
    }
}

package service;

import dto.ContaDto;
import models.Conta;

import java.util.ArrayList;

public class ContaService {
    ArrayList<Conta> contas = new ArrayList<>();

    public Conta getContaByNumConta(Integer numConta) {
        Conta conta = contas.stream()
                .filter(c -> c.getNumConta().equals(numConta))
                .findFirst()
                .orElse(null);

        if (conta == null) {
            throw new RuntimeException("Esta conta não existe");
        }

        return conta;
    }

    public void criarConta(ContaDto dto) {
        Integer id = dto.getId();
        Integer numeroConta = dto.getNumConta();

        Conta novaConta = new Conta();
        novaConta.setId(id);
        novaConta.setNumConta(numeroConta);

        contas.add(novaConta);

        System.out.println("Nova conta criada com sucesso!");
    }
}

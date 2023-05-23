package service;

import dto.ContaDto;
import models.Conta;

import java.util.ArrayList;

public class ContaService {
    ArrayList<Conta> contas = new ArrayList<>();

    public Conta getContaByNumConta(Integer numConta) {
        var conta = contas.stream()
                .filter(c -> c.getNumConta().equals(numConta))
                .findFirst()
                .orElse(null);

        if (conta == null) {
            throw new RuntimeException("Conta não encontrada");
        }

        return conta;
    }

    public Conta criarConta(ContaDto dto) {
        var novaConta = Conta.of(dto);
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso!");
        return novaConta;
    }

}
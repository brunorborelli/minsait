package com.minsait.teste.Algoritimos.simuladorBanco.service;

import com.minsait.teste.Algoritimos.simuladorBanco.model.Conta;

import java.util.List;

public class BancoService {
    private final List<Conta> contas;

    public BancoService(List<Conta> contas) {
        this.contas = contas;
    }

    public void transferir(Conta origem, Conta destino, double valor) {
        Conta primeira = origem.getId() < destino.getId() ? origem : destino;
        Conta segunda = origem.getId() < destino.getId() ? destino : origem;

        synchronized (primeira) {
            synchronized (segunda) {
                if (origem.sacar(valor)) {
                    destino.depositar(valor);
                    System.out.printf("Transferido %.2f da Conta %d para Conta %d%n", valor, origem.getId(), destino.getId());
                } else {
                    System.out.printf("Falha ao transferir %.2f da Conta %d (saldo insuficiente)%n", valor, origem.getId());
                }
            }
        }
    }

    public double saldoTotal() {
        return contas.stream().mapToDouble(Conta::getSaldo).sum();
    }

    public List<Conta> getContas() {
        return contas;
    }
}
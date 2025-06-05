package com.minsait.teste.Algoritimos.simuladorBanco.threads;

import com.minsait.teste.Algoritimos.simuladorBanco.model.Conta;
import com.minsait.teste.Algoritimos.simuladorBanco.service.BancoService;

import java.util.List;
import java.util.Random;

public class Transferencia implements Runnable {
    private final BancoService banco;
    private final int repeticoes;
    private final Random random = new Random();

    public Transferencia(BancoService banco, int repeticoes) {
        this.banco = banco;
        this.repeticoes = repeticoes;
    }

    @Override
    public void run() {
        List<Conta> contas = banco.getContas();
        int n = contas.size();

        for (int i = 0; i < repeticoes; i++) {
            int origemIndex = random.nextInt(n);
            int destinoIndex;
            do {
                destinoIndex = random.nextInt(n);
            } while (destinoIndex == origemIndex);

            Conta origem = contas.get(origemIndex);
            Conta destino = contas.get(destinoIndex);
            double valor = random.nextDouble(100);

            banco.transferir(origem, destino, valor);
        }
    }
}
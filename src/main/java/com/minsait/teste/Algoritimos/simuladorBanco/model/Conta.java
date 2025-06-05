package com.minsait.teste.Algoritimos.simuladorBanco.model;

public class Conta {
    private final int id;
    private double saldo;

    public Conta(int id, double saldoInicial) {
        this.id = id;
        this.saldo = saldoInicial;
    }

    public int getId() {
        return id;
    }

    public synchronized void depositar(double valor) {
        saldo += valor;
    }

    public synchronized boolean sacar(double valor) {
        if (valor > saldo) return false;
        saldo -= valor;
        return true;
    }

    public synchronized double getSaldo() {
        return saldo;
    }
}
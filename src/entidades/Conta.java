package entidades;

public class Conta {
    private double saldo;

    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void depositar(double valor) {
        saldo += valor;
        System.out.println(Thread.currentThread().getName() + " deposita: " + valor + ", Saldo atual: " + saldo);
    }

    public synchronized boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println(Thread.currentThread().getName() + " saca: " + valor + ", Saldo atual: " + saldo);
            return true;
        }
        return false;
    }

    public double getSaldo() {
        return saldo;
    }
}


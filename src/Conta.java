import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    private double saldo;  
    private Lock lock = new ReentrantLock();  


    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        lock.lock(); 
        try {
            this.saldo += valor;
            System.out.println(Thread.currentThread().getName() + " depositou R$" + valor + ". Saldo atual: R$" + saldo);
        } finally {
            lock.unlock();  
        }
    }

    public void sacar(double valor) {
        lock.lock();  
        try {
            if (this.saldo >= valor) {
                this.saldo -= valor;
                System.out.println(Thread.currentThread().getName() + " sacou R$" + valor + ". Saldo restante: R$" + saldo);
            } else {
                System.out.println("Tentativa de saque falhou por saldo insuficiente. Saldo atual: R$" + saldo);
            }
        } finally {
            lock.unlock();  
        }
    }

    public double getSaldo() {
        return saldo;
    }
}

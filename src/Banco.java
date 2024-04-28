import java.util.concurrent.locks.ReentrantLock;

public class Banco {
   
    private final ReentrantLock lock = new ReentrantLock();

    public void transferencia(Conta origem, Conta destino, double valor) {
        lock.lock();  
        try {
            if (origem.getSaldo() >= valor) {
                origem.sacar(valor);  
                destino.depositar(valor);  
                System.out.println("Transferência de R$" + valor + " realizada de " + origem + " para " + destino);
            } else {
                System.out.println("Saldo insuficiente para realizar a transferência de " + origem + " para " + destino);
            }
        } finally {
            lock.unlock();  
        }
    }
}

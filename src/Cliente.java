public class Cliente extends Thread {
    private Conta conta;  
    private Loja[] lojas;  
    private Banco banco;  
    private static final double[] valoresCompras = {100.0, 200.0};  

    public Cliente(Conta conta, Loja[] lojas, Banco banco) {
        this.conta = conta;
        this.lojas = lojas;
        this.banco = banco;
    }

    @Override
    public void run() {
        int indexLoja = 0;  
        while (conta.getSaldo() > 0) {
            double valorCompra = valoresCompras[(int) (Math.random() * valoresCompras.length)];  
            Loja lojaAtual = lojas[indexLoja]; 


            synchronized (conta) {
                realizarCompra(valorCompra, lojaAtual.getConta());
            }

  
            indexLoja = 1 - indexLoja;

            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void realizarCompra(double valor, Conta contaDestino) {
        if (conta.getSaldo() >= valor) {
            banco.transferencia(conta, contaDestino, valor);
            System.out.println(Thread.currentThread().getName() + " realizou uma compra de R$" + valor + " na loja.");
        } else {
            System.out.println(Thread.currentThread().getName() + " não possui saldo suficiente para realizar a compra.");
        }
    }
}

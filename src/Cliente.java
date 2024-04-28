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
        int lojaIndex = 0;
        while (conta.getSaldo() > 0) {
            double valorCompra = valoresCompras[(int) (Math.random() * valoresCompras.length)];
            realizarCompra(valorCompra, lojas[lojaIndex].getConta());
            lojaIndex = 1 - lojaIndex; // Alterna entre as lojas
        }
    }

    private void realizarCompra(double valor, Conta contaDestino) {
        if (conta.getSaldo() >= valor) {
            banco.transferencia(conta, contaDestino, valor);
            System.out.println(Thread.currentThread().getName() + " realizou uma compra de R$" + valor + ".");
        } else {
            System.out.println(Thread.currentThread().getName() + " não possui saldo suficiente para realizar a compra de R$" + valor + ".");
        }
    }
}

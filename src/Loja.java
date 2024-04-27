public class Loja {
    private Conta conta;  
    private Funcionario[] funcionarios;  
    private Banco banco;  

    public Loja(Conta conta, Funcionario[] funcionarios, Banco banco) {
        this.conta = conta;
        this.funcionarios = funcionarios;
        this.banco = banco;
    }

    public synchronized void pagarSalarios() {
        for (Funcionario funcionario : funcionarios) {
            if (conta.getSaldo() >= Funcionario.SALARIO) {
                banco.transferencia(conta, funcionario.getContaSalario(), Funcionario.SALARIO);
                funcionario.start();  
                System.out.println("Salário pago a " + funcionario.getName());
            } else {
                System.out.println("A loja não possui saldo suficiente para pagar os salários.");
            }
        }
    }

    
    public Conta getConta() {
        return conta;
    }
}


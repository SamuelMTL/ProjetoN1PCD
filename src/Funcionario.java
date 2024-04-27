public class Funcionario extends Thread {
    public static final double SALARIO = 1400.0;
    private final Conta contaSalario;
    private final Conta contaInvestimentos;

    public Funcionario(Conta contaSalario, Conta contaInvestimentos) {
        this.contaSalario = contaSalario;
        this.contaInvestimentos = contaInvestimentos;
    }
    
    public void investir() {
        double valorInvestimento = SALARIO * 0.2;
        contaSalario.transferir(contaInvestimentos, valorInvestimento);
        System.out.println(Thread.currentThread().getName() + " investiu R$" + valorInvestimento + " na conta de investimentos.");
    }

    public Conta getContaSalario() {
        return contaSalario;
    }
}


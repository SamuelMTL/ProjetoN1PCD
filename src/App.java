public class App {
    public static void main(String[] args) {
        Banco bancoCentral = new Banco();

        // Criar contas das lojas
        Conta contaDaLoja1 = new Conta(10000);  // Supondo um saldo inicial para operações
        Conta contaDaLoja2 = new Conta(10000);

        // Criar contas para os funcionários
        Conta contaSalarioFunc1Loja1 = new Conta(0);
        Conta contaSalarioFunc2Loja1 = new Conta(0);
        Conta contaSalarioFunc1Loja2 = new Conta(0);
        Conta contaSalarioFunc2Loja2 = new Conta(0);

        // Criar contas de investimento para os funcionários
        Conta contaInvestimentoFunc1Loja1 = new Conta(0);
        Conta contaInvestimentoFunc2Loja1 = new Conta(0);
        Conta contaInvestimentoFunc1Loja2 = new Conta(0);
        Conta contaInvestimentoFunc2Loja2 = new Conta(0);

        // Criar funcionários
        Funcionario funcionario1Loja1 = new Funcionario(contaSalarioFunc1Loja1, contaInvestimentoFunc1Loja1);
        Funcionario funcionario2Loja1 = new Funcionario(contaSalarioFunc2Loja1, contaInvestimentoFunc2Loja1);
        Funcionario funcionario1Loja2 = new Funcionario(contaSalarioFunc1Loja2, contaInvestimentoFunc1Loja2);
        Funcionario funcionario2Loja2 = new Funcionario(contaSalarioFunc2Loja2, contaInvestimentoFunc2Loja2);

        // Criar lojas
        Loja loja1 = new Loja(contaDaLoja1, new Funcionario[]{funcionario1Loja1, funcionario2Loja1}, bancoCentral);
        Loja loja2 = new Loja(contaDaLoja2, new Funcionario[]{funcionario1Loja2, funcionario2Loja2}, bancoCentral);

        // Criar contas para clientes
        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < clientes.length; i++) {
            Conta contaCliente = new Conta(1000);  
            clientes[i] = new Cliente(contaCliente, new Loja[]{loja1, loja2}, bancoCentral);
        }

        // Iniciar threads dos clientes
        for (Cliente cliente : clientes) {
            cliente.start();
        }

        // Iniciar threads dos funcionários (ainda não recebem pagamento automaticamente)
        funcionario1Loja1.start();
        funcionario2Loja1.start();
        funcionario1Loja2.start();
        funcionario2Loja2.start();

        // Esperar até que todas as threads dos clientes tenham terminado
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                System.err.println("Execução interrompida: " + e.getMessage());
                // Propagação opcional da interrupção para manter o estado consistente
                Thread.currentThread().interrupt();
            }
        }

        // Tentar pagar salários após as compras dos clientes
        loja1.pagarSalarios();
        loja2.pagarSalarios();

        // Exibir saldos finais das contas
        System.out.println("Saldos finais das contas das lojas e funcionários:");
        System.out.println("Conta Loja 1: R$" + contaDaLoja1.getSaldo());
        System.out.println("Conta Loja 2: R$" + contaDaLoja2.getSaldo());
        System.out.println("Conta Funcionário 1 Loja 1: R$" + contaSalarioFunc1Loja1.getSaldo());
        System.out.println("Conta Investimento Funcionário 1 Loja 1: R$" + contaInvestimentoFunc1Loja1.getSaldo());
        System.out.println("Conta de Salário do Funcionário 2 da Loja 1: R$" + contaSalarioFunc2Loja1.getSaldo());
        System.out.println("Conta de Investimento do Funcionário 2 da Loja 1: R$" + contaInvestimentoFunc2Loja1.getSaldo());
        System.out.println("Conta de Salário do Funcionário 1 da Loja 2: R$" + contaSalarioFunc1Loja2.getSaldo());
        System.out.println("Conta de Investimento do Funcionário 1 da Loja 2: R$" + contaInvestimentoFunc1Loja2.getSaldo());
        System.out.println("Conta de Salário do Funcionário 2 da Loja 2: R$" + contaSalarioFunc2Loja2.getSaldo());
        System.out.println("Conta de Investimento do Funcionário 2 da Loja 2: R$" + contaInvestimentoFunc2Loja2.getSaldo());

    }
}
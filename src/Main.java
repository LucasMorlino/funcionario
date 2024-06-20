import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);
        int option;

        //DecimalFormat df = new DecimalFormat("#.###,00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        do {
            // Menu para entrar no sistema e fazer o uso das funções do mesmo

            System.out.println("-----------------------------");
            System.out.println("-------------Menu------------");
            System.out.println(" Digite uma das opções abaixo. (apenas o número)");
            System.out.println(" (1)- Cadastrar um funcionário");
            System.out.println(" (2)- Remover funcionario João");
            System.out.println(" (3)- Imprimir todos os funcionários");
            System.out.println(" (4)- Funcionários receberem aumento de 10%");
            System.out.println(" (5)- Agrupamento dos funcionários em um Map e imprimir os funcionários por função");
            System.out.println(" (6)- Imprimir os funcionários que fazem aniversário no mês 10 e 12");
            System.out.println(" (7)- Imprimir o funcionário com maior idade. (exibindo nome e idade)");
            System.out.println(" (8)- Imprimir lista de funcionários em ordem alfabética");
            System.out.println(" (9)- Imprimir o total dos salários dos funcionários");
            System.out.println(" (10)- Imprimir quanto salários minimos cada funcionário ganha. (considerando: R$1212.00)");
            System.out.println(" (0)- Sair");
            System.out.println(" Digite a opção: ");
            option = Integer.parseInt(input.nextLine());


            switch (option) {

                // Cadastramento de um funcionário por vez, contendo nome, data de nascimento, salario e função do mesmo.
                case 1:
                    System.out.println("Digite o nome do funcionário: ");
                    String nomeInput = input.nextLine();

                    System.out.println("Digite a data de nascimento do funcionário: (obs: informar data nesse padrão - dd/mm/yyyy) ");
                    String dataInput = input.nextLine();

                    LocalDate date = null;
                    try
                    {
                        date = LocalDate.parse(dataInput, formatter);
                    } catch (Exception e) {
                        System.out.println("Formato de data inválido. Por favor, insira a data no formato dd/MM/yyyy.");
                    }

                    System.out.println("Digite o salário do funcionário: ");
                    String valor = input.nextLine();
                    BigDecimal salarioInput = new BigDecimal(valor);

                    System.out.println("Digite a função do funcionário: ");
                    String funcaoInput = input.nextLine();

                    funcionarios.add(new Funcionario(nomeInput, date, salarioInput, funcaoInput));
                    break;

                // Impressão de uma tabela com os dados cadastrados te todos os funcionários
                case 3:

                    String divisor = "------";
                    String result = divisor.repeat(10);

                    int[] widths = {10, 20, 15, 10};
                    String format = "%-" + widths[0] + "s%-" + widths[1] + "s%-" + widths[2] + "s%-" + widths[3] + "s%n";

                    System.out.println(result);
                    // Imprimindo o cabeçalho da tabela
                    System.out.printf(format, "Nome", "Data de Nascimento", "Salário", "Função");
                    // Imprimindo a linha divisória
                    System.out.println(result);
                    // Imprimindo os dados da tabela
                    for (Funcionario value : funcionarios) {

                        LocalDate date2 = value.getDataNasc();
                        String formatDate = date2.format(formatter);

                        NumberFormat formatoBr = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                        String formatSalario = formatoBr.format(value.getSalario());

                        System.out.printf(format, value.getNome(), formatDate, formatSalario, value.getFuncao());
                    }

                    break;

                // Remover o funcionário cujo o nome é João
                case 2:
                    funcionarios.removeIf(funcionarioJ -> Objects.equals(funcionarioJ.getNome(), "João"));
                    break;

                // Adicionando 10% de salário a mais em todos os funcionários cadastrados
                case 4:
                    BigDecimal dezP = new BigDecimal("0.10");
                    for (Funcionario value : funcionarios) {
                        value.setSalario(value.getSalario().add(value.getSalario().multiply(dezP)));
                    }
                    break;

                // Mapeando os funcionárioe por função e imprimindo eles conforme a suas funções
                case 5:
                    Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                            .collect(Collectors.groupingBy(Funcionario::getFuncao));

                    funcionariosPorFuncao.forEach((funcao, lista) -> {
                        System.out.println("------------------");
                        System.out.println("Função: " + funcao);
                        System.out.println("Funcionários: ");
                        lista.forEach(funcionario -> System.out.println(" - " + funcionario.getNome()));
                    });
                    break;

                // Impressão dos funcionários que fazem aniversário nos meses 10 e 12
                case 6:
                    funcionarios.stream()
                            .filter(funcionario -> funcionario.getDataNasc().getMonthValue() == 10 || funcionario.getDataNasc().getMonthValue() == 12 )
                            .forEach(funcionario -> System.out.println(funcionario.getNome() + " faz aniversário no mês " + funcionario.getDataNasc().getMonthValue()));
                    break;

                // Impressão do funcionário com a maior idade
                case 7:
                    Optional<Funcionario> funcionarioMaiorIdade = funcionarios.stream().max(Comparator.comparing(Funcionario::getIdade));
                    funcionarioMaiorIdade.ifPresent(funcionario -> System.out.println("Funcionário com a maior idade: " + funcionario.getNome() + " - " + funcionario.getIdade()));
                    break;

                // Listando os funcionários em ordem alfabética
                case 8:
                    funcionarios.sort(Comparator.comparing(Funcionario::getNome));
                    System.out.println("Lista de funcionários por ordem alfabética:");
                    funcionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
                    break;

                // Totalizando o salário de todos os funcionários
                case 9:
                    BigDecimal total = new BigDecimal("0");
                    NumberFormat formatoBr = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                    for (Funcionario value : funcionarios) {
                        total = total.add(value.getSalario());
                    }
                    String formatSalario = formatoBr.format(total);
                    System.out.println("O total do salario dos funcionarios é: " + formatSalario);
                    break;

                // Impressão de quantos salários minimos cada funcionário ganha
                case 10:
                    BigDecimal salarioMinimo = new BigDecimal("1212.00");
                    funcionarios.forEach(funcionario -> {
                        BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 0, BigDecimal.ROUND_HALF_UP);
                        System.out.println(funcionario.getNome() + " ganha " + quantidadeSalariosMinimos + " salários mínimos.");
                    });
                    break;

                // Sair do sistema
                case 0:
                    System.out.println("Saindo");
                    break;

                // Erro ao digitar alguma opção inexistente
                default:
                    System.out.println("Opção inexistente, tente novamente");
            }
        } while (option != 0);
    }
}
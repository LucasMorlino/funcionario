// Classe contrutora do objeto Funcionario que extende de Pessoa

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNasc, BigDecimal salario, String funcao) {
        super(nome, dataNasc);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getIdade(){
        return LocalDate.now().getYear() - dataNasc.getYear();
    }

    @Override
    public String toString() {
        return  "Nome: " + nome +
                "; Data de Nascimento: " + dataNasc +
                "; Salário: " + salario +
                "; Função: " + funcao;
    }
}

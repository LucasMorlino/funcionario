//Classe construtora do objeto Pessoa

import java.time.LocalDate;

public class Pessoa{
    String nome;
    LocalDate dataNasc;

    //construtor
    public Pessoa( String nome, LocalDate dataNasc){
        this.nome = nome;
        this.dataNasc = dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
}

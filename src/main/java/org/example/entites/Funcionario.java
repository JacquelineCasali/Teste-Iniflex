package org.example.entites;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario  extends  Pessoa{

    private BigDecimal salario;
    private String funcao;

     public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao=funcao;
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s",
                getNome(),
                getDataNascimento().format(formatter),
                String.format(Locale.GERMANY, "%,.2f", salario).replace('.', ','),
                funcao);
    }

}

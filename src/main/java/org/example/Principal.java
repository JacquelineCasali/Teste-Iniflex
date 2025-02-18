package org.example;

import org.example.entites.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        //   3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("407.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        //  3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        funcionarios.forEach(System.out::println);

//        3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
//• informação de data deve ser exibido no formato dd/mm/aaaa;
//• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);

        //  3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(funcionario ->
                funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.10"))));
        System.out.println("Funcionários com Aumento:");
        funcionarios.forEach(System.out::println);

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> agruparPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função.

        System.out.println("\nFuncionários agrupados por função:");
        agruparPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
        });

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.

        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(funcionario -> {
                    int mes = funcionario.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                }).forEach(System.out::println);

//        3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario maiorIdade = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (maiorIdade != null) {
            long idade = ChronoUnit.YEARS.between(maiorIdade.getDataNascimento(), LocalDate.now());
            System.out.println("\nFuncionário com a maior idade:");
            System.out.println("Nome: " + maiorIdade.getNome() + ", Idade: " + idade);
        }


//        3.10 – Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);


//        3.11 – Imprimir o total dos salários dos funcionários.

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " +
                String.format(Locale.GERMANY, "%,.2f", totalSalarios).replace('.', ','));


//        3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n Quantidade de Salário mínimo por funcionário :");
        funcionarios.forEach(funcionario -> {
            BigDecimal multiplos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + multiplos + "  Quantidade de salário mínimo");
        });

    }

}

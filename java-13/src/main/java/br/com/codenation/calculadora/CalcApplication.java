package br.com.codenation.calculadora;

public class CalcApplication {
    public static void main(String[] args) {
        CalculadoraSalario calc = new CalculadoraSalario();
        double salario = 5000;
        System.out.println(calc.calculoINSS(salario));
        System.out.println(calc.calculoIRPF(salario));
        System.out.println(calc.calcularSalarioLiquido(salario));
    }
}

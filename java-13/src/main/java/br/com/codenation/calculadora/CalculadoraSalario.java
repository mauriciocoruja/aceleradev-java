package br.com.codenation.calculadora;


public class CalculadoraSalario {

    public double calculoINSS(double salarioBase) {
        if (salarioBase <= 1500.00) {
            return salarioBase * 0.08;
        }
        if (salarioBase <= 4000.00) {
            return salarioBase * 0.09;
        }
        return salarioBase * 0.11;
    }

    public double calculoIRPF(double salarioMenosINSS) {
        if (salarioMenosINSS <= 3000.00) {
            return 0;
        }
        if (salarioMenosINSS <= 6000.00) {
            return salarioMenosINSS * 0.075;
        }
        return salarioMenosINSS * 0.15;
    }

    public long calcularSalarioLiquido(double salarioBase) {

        double salarioMenosINSS = (salarioBase - calculoINSS(salarioBase));
        double salarioLiquido = salarioMenosINSS - calculoIRPF(salarioMenosINSS);

        if (salarioBase < 1039.00) {
            return 0;
        }
        return Math.round(salarioLiquido);
    }
}

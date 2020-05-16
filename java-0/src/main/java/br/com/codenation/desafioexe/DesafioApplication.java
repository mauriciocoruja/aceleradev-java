package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

    public static List<Integer> fibonacci() {

        List<Integer> fibo = new ArrayList<>();
        int sucessor = 1, antecessor = 0, numeroAtual = 0;

        for (int i = 1; i <= 15; i++) {
            fibo.add(numeroAtual);
            numeroAtual = sucessor;
            sucessor += antecessor;
            antecessor = numeroAtual;
        }
        return fibo;
    }

    public static Boolean isFibonacci(Integer a) {
        return fibonacci().contains(a);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci());
    }
}


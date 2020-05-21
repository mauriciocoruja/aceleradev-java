package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

    public static int average(int[] elementos) {
        int somatoria = 0;

        for (int elemento : elementos) {
            somatoria += elemento;
        }
        return somatoria / elementos.length;
    }

    public static int mode(int[] elementos) {

		int numeroModa = 0, maiorValorDeRepeticoes = 0;

		for (int i = 0; i < elementos.length; i++) {
			int contador = 0;
			for (int j = 0; j < elementos.length; j++) {
				if (elementos[j] == elementos[i]) contador++;
			}
			if (contador > maiorValorDeRepeticoes) {
				maiorValorDeRepeticoes = contador;
				numeroModa = elementos[i];
			}
		}
		return numeroModa;
    }

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int middle = elements.length / 2;
		if (elements.length % 2 == 1) {
			return elements[middle];
		}
		return (elements[middle - 1] + elements[middle]) / 2;
	}

	public static void main(String[] args) {

		int[] elements = {23,25,25,37,40};

		System.out.println(average(elements)); //4
		System.out.println(mode(elements)); //4
		System.out.println(median(elements));


	}
}

package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        return criptoCesar(texto, 3);
    }


    @Override
    public String descriptografar(String texto) {
        return criptoCesar(texto, -3);
    }

    public String criptoCesar(String texto, int chave) {
        String mensagem = texto.toLowerCase();

        if (mensagem.isEmpty()) {
            throw new IllegalArgumentException("Texto nulo ou vazio  não vale");
        }

        StringBuilder textoDecifrado = new StringBuilder();

        int tamanhoTexto = texto.length();

        for (int i = 0; i < tamanhoTexto; i++) {

            int baseASCII = mensagem.charAt(i);

            if (baseASCII >= 97 && baseASCII <= 122) {
                baseASCII += chave;
            }

            textoDecifrado.append((char) (baseASCII));
        }
        return (textoDecifrado.toString());
    }
}



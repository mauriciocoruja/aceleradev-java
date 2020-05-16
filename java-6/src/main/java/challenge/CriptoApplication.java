package challenge;

public class CriptoApplication {
    public static void main(String[] args) {
        CriptografiaCesariana texto = new CriptografiaCesariana();

        String textoCriptografado = "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";
        System.out.println(texto.descriptografar(textoCriptografado));
    }
}

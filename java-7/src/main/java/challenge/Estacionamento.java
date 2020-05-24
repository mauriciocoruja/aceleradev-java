package challenge;

import java.util.ArrayList;

public class Estacionamento {

    private final Integer limiteDeVagas = 10;

    private ArrayList<Carro> vagas = new ArrayList<>();

    public void estacionar(Carro carro) {
        if (carrosEstacionados() < limiteDeVagas) {
            vagas.add(0, carro);
        }

    }

    public int carrosEstacionados() {
        return vagas.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return vagas.contains(carro);
    }
}

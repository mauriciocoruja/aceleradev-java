package challenge;

import java.util.ArrayList;

public class Estacionamento {

    private final Integer IDADE_MINIMA = 18;
    private final Integer LIMITE_DE_VAGAS = 10;
    private final Integer LIMITE_DE_PONTOS = 20;
    private final Integer IDADE_PRIORITARIA = 55;
    private ArrayList<Carro> vagas = new ArrayList<>();

    public void estacionar(Carro carro) {
        validarSePodeEstacionar(carro);

        if (carrosEstacionados() >= LIMITE_DE_VAGAS) {
            Carro resultado = vagas.stream()
                    .filter(vaga -> vaga.getMotorista().getIdade() < IDADE_PRIORITARIA)
                    .findFirst()
                    .orElseThrow(() -> new EstacionamentoException("Todas as vagas estão preenchidas prefencialmente"));
            vagas.remove(resultado);
        }
        vagas.add(carro);
    }

    public int carrosEstacionados() {
        return vagas.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return vagas.contains(carro);
    }
    public void validarSePodeEstacionar(Carro carro) throws EstacionamentoException{
        if (carro.getMotorista() == null) {
            throw new EstacionamentoException("Deve haver um motorista. Não aceitamos carros autonomos");
        }
        if (carro.getMotorista().getIdade() < IDADE_MINIMA) {
            throw new EstacionamentoException("Não permitimos menores de idade");
        }
        if (carro.getMotorista().getPontos() > LIMITE_DE_PONTOS) {
            throw new EstacionamentoException("Não há mais pontos disponiveis");
        }
    }
}

import br.com.codenation.DesafioMeuTimeApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();

        //Inclui Time
        application.incluirTime(1l, "Barcelona", LocalDate.now(), "Vermelho e Azul", "Laranja");
        application.incluirTime(2l, "Real Madrid", LocalDate.now(), "Branco", "Preto");
        System.out.println(application.times);

        //Inclui Jogador
        application.incluirJogador(1l, 1l, "Messi", LocalDate.of(1986, 10, 15), 98, new BigDecimal(1500000));
        application.incluirJogador(2l, 1l, "Pedro", LocalDate.of(1986, 10, 15), 98, new BigDecimal(1500000));
        application.incluirJogador(3l, 1l, "David Villa", LocalDate.of(1986, 10, 15), 98, new BigDecimal(1500000));

        application.definirCapitao(1l);
        application.definirCapitao(2l);
        application.definirCapitao(3l);

        System.out.println(application.jogadores);
        System.out.println(application.times);
    }
}


import br.com.codenation.DesafioMeuTimeApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();

        //Inclui Time
        application.incluirTime(1l, "Barcelona", LocalDate.now(), "Vermelho e Azul", "Laranja");
        System.out.println(application.times);

        //Inclui Jogador
        application.incluirJogador(1l, 1l, "Messi", LocalDate.of(1986, 10, 15), 98, new BigDecimal(1500000));
        application.incluirJogador(2l, 1l, "Pedro", LocalDate.of(1986, 10, 15), 98, new BigDecimal(1500000));
        application.definirCapitao(1l);
        System.out.println(application.jogadores);
        application.definirCapitao(2l);
        System.out.println(application.jogadores);


    }
}


package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.jogador.Jogador;
import br.com.codenation.time.Time;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
    public List<Time> times = new ArrayList<>();
    public List<Jogador> jogadores = new ArrayList<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

        if (times.stream().anyMatch(time -> time.getId().equals(id))) {
            throw new IdentificadorUtilizadoException("Time com esse identificador já existe");
        }

        times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

        Time time = buscarTime(idTime);

        if (jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id))) {
            throw new IdentificadorUtilizadoException("Identificador já utilizado");
        }

        jogadores.add(new Jogador(id, time.getId(), nome, dataNascimento, nivelHabilidade, salario));
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Jogador capitao = this.buscarJogador(idJogador);

        jogadores.stream().filter(Jogador::getCapitao).findFirst().ifPresent(result -> result.setCapitao(false));

        capitao.setCapitao(true);

    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        throw new UnsupportedOperationException();
    }

    public Jogador buscarJogador(Long idJogador) {
        return jogadores.stream()
                .filter(jogador -> jogador.getId().equals(idJogador))
                .findFirst().orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado"));
    }

    public Time buscarTime(Long idTime) {
        return times.stream()
                .filter(x -> x.getId().equals(idTime))
                .findFirst().orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
    }

}

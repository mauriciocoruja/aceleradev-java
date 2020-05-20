package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
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
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
                            String corUniformeSecundario) throws IdentificadorUtilizadoException {

        if (times.stream().anyMatch(time -> time.getId().equals(id))) {
            throw new IdentificadorUtilizadoException("ID de Time já utilizado");
        }

        times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
                               BigDecimal salario) throws IdentificadorUtilizadoException {

        Time time = buscarTimePorId(idTime);

        if (jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id))) {
            throw new IdentificadorUtilizadoException("ID de Jogador já utilizado");
        }

        jogadores.add(new Jogador(id, time.getId(), nome, dataNascimento, nivelHabilidade, salario));
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) throws JogadorNaoEncontradoException {
        Jogador capitao = this.buscarJogadorPorId(idJogador);
        jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(capitao.getIdTime()) && jogador.getCapitao())
                .findAny().ifPresent(result -> result.setCapitao(false));

        capitao.setCapitao(true);
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) throws TimeNaoEncontradoException, CapitaoNaoInformadoException {
        Time time = buscarTimePorId(idTime);
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(time.getId()) && jogador.getCapitao())
                .findAny().orElseThrow(() -> new CapitaoNaoInformadoException("Capitão não informado")).getId();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) throws JogadorNaoEncontradoException {
        return buscarJogadorPorId(idJogador).getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException {
        return buscarTimePorId(idTime).getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException {
        Time time = buscarTimePorId(idTime);

        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(time.getId()))
                .map(Jogador::getId)
                .collect(Collectors.toList());
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) throws TimeNaoEncontradoException {
        Time time = buscarTimePorId(idTime);

        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(time.getId()))
                .max(Comparator.comparing(Jogador::getNivelHabilidade))
                .get()
                .getId();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) throws TimeNaoEncontradoException {
        Time time = buscarTimePorId(idTime);
        return this.jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(time.getId()))
                .min(Comparator.comparing(Jogador::getDataNascimento)
                        .thenComparingLong(Jogador::getId))
                .get()
                .getId();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        return times.stream()
                .map(Time::getId)
                .collect(Collectors.toList());
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) throws TimeNaoEncontradoException {
        Time time = buscarTimePorId(idTime);

        return this.jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(time.getId()))
                .min(Comparator.comparing(Jogador::getSalario).reversed()
                        .thenComparingLong(Jogador::getId))
                .get()
                .getId();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) throws JogadorNaoEncontradoException {
        return  buscarJogadorPorId(idJogador).getSalario();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        return jogadores.stream()
                .sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
                .limit(top)
                .map(Jogador::getId)
                .collect(Collectors.toList());
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time timePrincipal = buscarTimePorId(timeDaCasa);
        Time timeVisitante = buscarTimePorId(timeDeFora);

        if (timePrincipal.getCorUniformePrincipal().equals(timeVisitante.getCorUniformePrincipal())) {
            return timeVisitante.getCorUniformeSecundario();
        }
        return timeVisitante.getCorUniformePrincipal();
    }

    public Jogador buscarJogadorPorId(Long idJogador) throws JogadorNaoEncontradoException {
        return jogadores.stream()
                .filter(jogador -> jogador.getId().equals(idJogador))
                .findFirst().orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado"));
    }

    public Time buscarTimePorId(Long idTime) {
        return times.stream()
                .filter(x -> x.getId().equals(idTime))
                .findFirst().orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado"));
    }

}

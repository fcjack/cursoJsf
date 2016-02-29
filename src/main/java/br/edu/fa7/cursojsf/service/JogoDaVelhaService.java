package br.edu.fa7.cursojsf.service;

import br.edu.fa7.cursojsf.util.Player;
import br.edu.fa7.cursojsf.util.StatusJogo;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Dependent
public class JogoDaVelhaService implements Serializable {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player nextPlayer;
    private Player lastWinner;
    private StatusJogo status;
    private List<String> field;

    private final List<int[]> possiveisCombinacoes = Arrays.asList(
            new int[]{0, 1, 2},
            new int[]{3, 4, 5},
            new int[]{6, 7, 8},
            new int[]{0, 4, 8},
            new int[]{6, 4, 2},
            new int[]{0, 3, 6},
            new int[]{1, 4, 7},
            new int[]{2, 5, 8}
    );

    @PostConstruct
    public void init() {
        player1 = new Player("O");
        player2 = new Player("X");
        status = StatusJogo.READY;
    }

    public void iniciaNovoJogo() {
        if (lastWinner != null) {

            Player nextPlayer;
            if (lastWinner.equals(player1)) {
                nextPlayer = player2;
            } else {
                nextPlayer = player1;
            }

            defineOrder(lastWinner, nextPlayer);
        } else {
            int i = new Random().nextInt(2) + 1;
            if (i == 1) {
                defineOrder(player1, player2);
            } else {
                defineOrder(player2, player1);
            }
        }

        setupField();
        status = StatusJogo.STARTED;
    }

    private void defineOrder(Player currentPlayer, Player nextPlayer) {
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
    }

    private void setupField() {
        field = new ArrayList<String>(9);
        for (int i = 0; i < 9; i++) {
            field.add("");
        }
    }

    public void checkShot(int position) {
        field.set(position, currentPlayer.getCode());
        verifyEndGame();
        if (!status.equals(StatusJogo.FINISHED)) {
            defineOrder(nextPlayer, currentPlayer);
        }
    }

    private void verifyEndGame() {
        for (int[] combinacao : possiveisCombinacoes) {
            String shot = field.get(combinacao[0]);
            if (!"".equals(shot)
                    && shot.equals(field.get(combinacao[1]))
                    && shot.equals(field.get(combinacao[2]))) {
                lastWinner = getVencedor(shot);
                status = StatusJogo.FINISHED;
                return;
            }
        }
        checkDraw();
    }

    private void checkDraw() {
        int qtdChecked = 0;
        for (String s : field) {
            qtdChecked += StringUtils.isEmpty(s) ? 0 : 1;
        }

        if (qtdChecked == 9)
            status = StatusJogo.DRAW;
    }

    private Player getVencedor(String marca) {
        if ("x".equalsIgnoreCase(marca)) {
            return player2;
        } else {
            return player1;
        }
    }

    public List<String> getField() {
        return field;
    }

    public boolean isJogoIniciado() {
        return StatusJogo.STARTED.equals(status);
    }

    public boolean isJogoFinalizado() {
        return StatusJogo.FINISHED.equals(status);
    }

    public boolean isJogoNaoIniciado() {
        return StatusJogo.READY.equals(status);
    }

    public boolean isJogoEmpatado() {
        return StatusJogo.DRAW.equals(status);
    }

    public Player getLastWinner() {
        return lastWinner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}

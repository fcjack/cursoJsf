package br.edu.fa7.cursojsf.service;

import br.edu.fa7.cursojsf.util.Player;
import br.edu.fa7.cursojsf.util.StatusJogo;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.Random;

@Dependent
public class JogoDaVelhaService implements Serializable {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player nextPlayer;
    private Player lastWinner;
    private StatusJogo status;
    private String[][] allField;

    @PostConstruct
    public void init() {
        player1 = new Player("O");
        player2 = new Player("X");
        status = StatusJogo.READY;
        allField = new String[3][3];
        setupField();
    }

    public void startGame() {
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
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                allField[row][column] = "";
    }

    public void checkShot(int row, int column) {
        if (currentPlayer != null) {
            allField[row][column] = currentPlayer.getCode();
            verifyEndGame();
            if (!status.equals(StatusJogo.FINISHED)) {
                defineOrder(nextPlayer, currentPlayer);
            }
        }
    }

    private void verifyEndGame() {
        checkRows();
        checkDiagonals();
        checkColumns();
        checkDraw();
    }

    private void checkRows() {
        for (int row = 0; row < 3; row++) {
            String first = allField[row][0];
            String center = allField[row][1];
            String right = allField[row][2];
            if (!StringUtils.isEmpty(first) && !StringUtils.isEmpty(center) && !StringUtils.isEmpty(right)) {
                if (first.equals(center) && first.equals(right)) {
                    finishGame(allField[row][0]);
                }
            }
        }
    }

    private void checkColumns() {
        if (!status.equals(StatusJogo.FINISHED)) {
            for (int column = 0; column < 3; column++) {
                String first = allField[0][column];
                String center = allField[1][column];
                String right = allField[2][column];
                if (!StringUtils.isEmpty(first) && !StringUtils.isEmpty(center) && !StringUtils.isEmpty(right)) {
                    if (allField[0][column].equals(allField[1][column]) && allField[0][column].equals(allField[2][column])) {
                        finishGame(allField[0][column]);
                    }
                }
            }
        }
    }

    private void checkDiagonals() {
        if (!status.equals(StatusJogo.FINISHED)) {
            String leftUp = allField[0][0];
            String center = allField[1][1];
            String rightDown = allField[2][2];
            String rightUp = allField[0][2];
            String leftDown = allField[2][0];

            if (!StringUtils.isEmpty(leftUp) && !StringUtils.isEmpty(center) && !StringUtils.isEmpty(rightDown)) {
                if (leftUp.equals(center) && leftUp.equals(rightDown)) {
                    finishGame(leftUp);
                }
            }

            if (!StringUtils.isEmpty(rightUp) && !StringUtils.isEmpty(center) && !StringUtils.isEmpty(leftDown)) {
                if (rightUp.equals(center) && rightUp.equals(leftDown)) {
                    finishGame(rightUp);
                }
            }
        }
    }

    private void finishGame(String shot) {
        lastWinner = getWinner(shot);
        status = StatusJogo.FINISHED;
    }

    private void checkDraw() {
        if (!status.equals(StatusJogo.FINISHED)) {
            int qtdChecked = 0;
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (!StringUtils.isEmpty(allField[row][column])) {
                        qtdChecked++;
                    }
                }
            }

            if (qtdChecked == 9) status = StatusJogo.DRAW;
        }
    }

    private Player getWinner(String shot) {
        if ("X".equalsIgnoreCase(shot)) {
            return player2;
        } else {
            return player1;
        }
    }

    public String getField(Long row, Long column) {
        return allField[row.intValue()][column.intValue()];
    }

    public boolean isGameStarted() {
        return StatusJogo.STARTED.equals(status);
    }

    public boolean isGameFinished() {
        return StatusJogo.FINISHED.equals(status);
    }

    public boolean isGameReady() {
        return StatusJogo.READY.equals(status);
    }

    public boolean isGameDraw() {
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

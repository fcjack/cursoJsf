package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.service.JogoDaVelhaService;
import br.edu.fa7.cursojsf.util.Player;
import org.apache.commons.lang3.StringUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class JogoDaVelhaBean implements Serializable {

    @Inject
    protected JogoDaVelhaService service;

    public void newGame() {
        service.iniciaNovoJogo();
    }

    public boolean disableField(Long row, Long column) {
        return !StringUtils.isEmpty(field(row, column)) || service.isGameFinished() || service.isGameReady() || service.isGameDraw();
    }

    public void shot(Long row, Long column) {
        service.checkShot(row.intValue(), column.intValue());
    }

    public String field(Long row, Long column) {
        return service.getField(row, column);
    }

    public boolean isGameStarted() {
        return service.isGameStarted();
    }

    public boolean isGameFinished() {
        return service.isGameFinished();
    }

    public boolean isGameDraw() {
        return service.isGameDraw();
    }

    public Player getCurrentPlayer() {
        return service.getCurrentPlayer();
    }

    public Player getPlayer1() {
        return service.getPlayer1();
    }

    public Player getPlayer2() {
        return service.getPlayer2();
    }


}

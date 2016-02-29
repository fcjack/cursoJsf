package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.service.JogoDaVelhaService;
import br.edu.fa7.cursojsf.util.Player;
import org.apache.commons.lang3.StringUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class JogoDaVelhaBean implements Serializable {

    @Inject
    protected JogoDaVelhaService service;

    public void novoJogo() {
        service.iniciaNovoJogo();
    }

    public boolean desabilitaCampo(String valorCampo) {
        return !StringUtils.isEmpty(valorCampo) || service.isJogoFinalizado() || service.isJogoNaoIniciado() || service.isJogoEmpatado();
    }

    public void marca(int position) {
        service.checkShot(position);
    }

    public List<String> getCampo() {
        return service.getField();
    }

    public boolean isJogoIniciado() {
        return service.isJogoIniciado();
    }

    public boolean isJogoFinalizado() {
        return service.isJogoFinalizado();
    }

    public boolean isJogoEmpatado() {
        return service.isJogoEmpatado();
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

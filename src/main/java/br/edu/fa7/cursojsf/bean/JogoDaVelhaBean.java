package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.service.JogoDaVelhaService;

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

  public void novoJogo(){
    service.iniciaNovoJogo();
  }

  public boolean desabilitaCampo(String valorCampo){
    return !"".equals(valorCampo) || service.isJogoFinalizado() || service.isJogoNaoIniciado() || service.isJogoEmpatado();
  }

  public void marca(int position){
    service.marcaJogada( position );
  }

  public List<String> getCampo(){
    return service.getCampo();
  }
  public boolean isJogoIniciado(){
    return service.isJogoIniciado();
  }
  public boolean isJogoFinalizado(){
    return service.isJogoFinalizado();
  }
  public boolean isJogoEmpatado(){
    return service.isJogoEmpatado();
  }
  public JogoDaVelhaService.Jogador getJogadorDaVez(){
    return service.getJogadorDaVez();
  }
  public JogoDaVelhaService.Jogador getJogador1(){
    return service.getJogador1();
  }
  public JogoDaVelhaService.Jogador getJogador2(){
    return service.getJogador2();
  }


}

package br.edu.fa7.cursojsf.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Dependent
public class JogoDaVelhaService implements Serializable {

  private Jogador jogador1;
  private Jogador jogador2;
  private Jogador jogadorDaVez;
  private Jogador proximoJogador;
  private Jogador ultimoVencedor;
  private StatusJogo status;
  private List<String> campo;

  private final List<int[]> possiveisCombinacoes = Arrays.asList(
    new int[]{0,1,2},
    new int[]{3,4,5},
    new int[]{6,7,8},
    new int[]{0,4,8},
    new int[]{6,4,2},
    new int[]{0,3,6},
    new int[]{1,4,7},
    new int[]{2,5,8}
  );

  @PostConstruct
  public void init(){
    jogador1 = new Jogador("O");
    jogador2 = new Jogador("X");
    status = StatusJogo.NAO_INICIADO;
  }

  public void iniciaNovoJogo() {
    if(ultimoVencedor != null) {
      defineOrdemDeJogada( ultimoVencedor
              , ultimoVencedor == jogador1 ? jogador2 : jogador1 );
    }else{
      int i = new Random().nextInt( 2 ) + 1;
      if( i == 1) {
        defineOrdemDeJogada( jogador1 , jogador2 );
      }else {
        defineOrdemDeJogada( jogador2 , jogador1 );
      }
    }
    montaCampo();
    status = StatusJogo.INICIADO;
  }

  private void defineOrdemDeJogada( Jogador jogadorDaVez , Jogador proximoJogador){
    this.jogadorDaVez = jogadorDaVez;
    this.proximoJogador = proximoJogador;
  }

  private void montaCampo(){
    campo = new ArrayList<String>(9);
    for(int i = 0 ; i < 9 ; i++){
      campo.add("");
    }
  }

  public void marcaJogada(int position){
    campo.set( position ,  jogadorDaVez.getMarca() );
    verificaFimDeJogo();
    if(!status.equals(StatusJogo.FINALIZADO)) {
      defineOrdemDeJogada(proximoJogador, jogadorDaVez);
    }
  }

  private void verificaFimDeJogo(){
    for(int[] combinacao : possiveisCombinacoes){
      String marca = campo.get(combinacao[0]);
      if( !"".equals(marca)
       && marca.equals(campo.get(combinacao[1]))
       && marca.equals(campo.get(combinacao[2])) ){
        ultimoVencedor = getVencedor(marca);
        status = StatusJogo.FINALIZADO;
        return;
      }
    }
    verificaEmpate();
  }

  private void verificaEmpate(){
    int qntPreenchida = 0;
    for(String s : campo){
      qntPreenchida += "".equals(s) ? 0 : 1;
    }
    if(qntPreenchida == 9)
      status = StatusJogo.EMPATE;
  }

  private Jogador getVencedor(String marca){
    if( "x".equalsIgnoreCase( marca ) ){
      return jogador2;
    }else{
      return jogador1;
    }
  }

  public List<String> getCampo(){
    return campo;
  }

  public boolean isJogoIniciado(){
    return StatusJogo.INICIADO.equals(status);
  }
  public boolean isJogoFinalizado(){
    return StatusJogo.FINALIZADO.equals(status);
  }
  public boolean isJogoNaoIniciado(){
    return StatusJogo.NAO_INICIADO.equals(status);
  }
  public boolean isJogoEmpatado() { return StatusJogo.EMPATE.equals(status); }
  public Jogador getUltimoVencedor() { return ultimoVencedor; }
  public Jogador getJogadorDaVez() { return jogadorDaVez;  }
  public Jogador getJogador1() {
    return jogador1;
  }
  public Jogador getJogador2() { return jogador2; }



  public class Jogador{
    private String nome;
    private String marca;

    public Jogador(String marca) {
      this.marca = marca;
    }

    public String getNome() {
      return nome;
    }
    public void setNome(String nome) {
      this.nome = nome;
    }
    public String getMarca() {
      return marca;
    }
    public void setMarca(String marca) {
      this.marca = marca;
    }
  }

  public enum StatusJogo{
    NAO_INICIADO,
    INICIADO,
    EMPATE,
    FINALIZADO;
  }

}

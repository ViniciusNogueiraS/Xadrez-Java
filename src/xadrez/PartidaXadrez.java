package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Dama;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        iniciarPartida();
    }
    
    public PecaXadrez[][] getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for(int i = 0; i < tabuleiro.getLinhas(); i++){
            for(int j = 0; j < tabuleiro.getColunas(); j++){
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return mat;
    }
    
    public boolean[][] movimentosPossiveis(PosicaoXadrez origem){
        Posicao posicao = origem.toPosicaoMatriz();
        validaPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }
    
    public PecaXadrez MovimentaXadrez(PosicaoXadrez origemX, PosicaoXadrez destinoX){
        Posicao origem = origemX.toPosicaoMatriz();
        Posicao destino = destinoX.toPosicaoMatriz();
        validaPosicaoOrigem(origem);
        validaPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);
        return (PecaXadrez)pecaCapturada;
    }
    
    private Peca fazerMovimento(Posicao origem, Posicao destino){
        Peca p = tabuleiro.removerPeca(origem);
        Peca pCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.ColocaPeca(p, destino);
        return pCapturada;
    }
    
    private void validaPosicaoOrigem(Posicao posicao){
        if(!tabuleiro.ExistePeca(posicao)){
            throw new ExcecaoXadrez("Não existe peça na origem!");
        }
        if(!tabuleiro.peca(posicao).existePeloMenosUmMovimento()){
            throw new ExcecaoXadrez("Não existe movimentos possíveis para esta peça!");
        }
    }
    
    private void validaPosicaoDestino(Posicao origem, Posicao destino){
        if(!tabuleiro.peca(origem).movimentoPossivel(destino)){
            throw new ExcecaoXadrez("A peça não pode fazer este movimento!");
        }
    }
    
    private void colocaNovaPeca(char couna, int linha, PecaXadrez peca){
        tabuleiro.ColocaPeca(peca, new PosicaoXadrez(couna, linha).toPosicaoMatriz());
    }
    
    private void iniciarPartida(){        
        colocaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('e', 1, new Dama(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA));

        colocaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
        colocaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
        colocaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
        colocaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
        colocaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA));
        colocaNovaPeca('d', 8, new Dama(tabuleiro, Cor.PRETA));
        colocaNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA));
    }
}

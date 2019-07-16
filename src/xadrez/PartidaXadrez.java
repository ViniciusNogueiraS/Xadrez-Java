package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
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
        colocaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));

        colocaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
    }
}

package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{
    private Cor cor;
    private int quantMovimentos;    

    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public int getQuantMovimentos() {
        return quantMovimentos;
    }
    
    public void addQuantMovimentos(){
        quantMovimentos++;
    }
    
    public void removeQuantMovimentos(){
        quantMovimentos--;
    }
    
    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.toPosicaoTabuleiro(posicao);
    }
    
    protected boolean existePecaInimiga(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p != null && p.cor != cor;
    }
    
}

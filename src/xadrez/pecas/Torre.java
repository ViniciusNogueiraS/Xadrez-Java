package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }
    
    @Override
    public String toString() {
        return "T";
    }
}

package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

    public Torre(Color color, Tabuleiro tabuleiro) {
        super(color, tabuleiro);
    }
    
    @Override
    public String toString() {
        return "T";
    }
}

package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

public class PecaXadrez extends Peca{
    private Color color;

    public PecaXadrez(Color color, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }    
    
}

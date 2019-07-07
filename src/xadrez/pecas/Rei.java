package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

    public Rei(Color color, Tabuleiro tabuleiro) {
        super(color, tabuleiro);
    }

    @Override
    public String toString() {
        return "R";
    }
}

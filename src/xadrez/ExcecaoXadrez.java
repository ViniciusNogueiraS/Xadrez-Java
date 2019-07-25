package xadrez;

import sistemaxadrez.UI;
import tabuleiro.ExcecaoTabuleiro;

public class ExcecaoXadrez extends ExcecaoTabuleiro{

    public ExcecaoXadrez(String message) {
        super(UI.ANSI_RED_BACKGROUND + "→" + UI.ANSI_RESET + " " + message);
    }
}

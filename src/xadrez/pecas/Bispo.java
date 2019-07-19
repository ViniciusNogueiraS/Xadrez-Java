package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez{
    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        Posicao p = new Posicao(0,0);
        
        //DIAGONAL CIMA-ESQUERDA DA PEÇA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
            p.setColuna(p.getColuna() - 1);
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIAGONAL BAIXO-ESQUERDA DA PEÇA
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
            p.setColuna(p.getColuna() - 1);
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIAGONAL BAIXO-DIREITA DA PEÇA
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
            p.setColuna(p.getColuna() + 1);
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIAGONAL CIMA-DIREITA DA PEÇA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
            p.setColuna(p.getColuna() + 1);
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        return mat;
    }
}

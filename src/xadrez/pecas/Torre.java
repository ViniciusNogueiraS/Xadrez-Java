package xadrez.pecas;

import tabuleiro.Posicao;
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

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        Posicao p = new Posicao(0,0);
        
        //ACIMA DA PEÇA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //Á ESQUERDA DA PEÇA
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //ABAIXO DA PEÇA
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //Á DIREITA DA PEÇA
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        return mat;
    }
}









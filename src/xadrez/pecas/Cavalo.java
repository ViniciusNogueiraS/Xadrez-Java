package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{
    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }
    
    @Override
    public String toString() {
        return "C";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        Posicao p = new Posicao(0,0);
        
        //2 ACIMA E 1 Á ESQUERDA
        p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 ACIMA E 1 Á DIREITA
        p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //1 ACIMA E 2 Á ESQUERDA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //1 ACIMA E 2 Á DIREITA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 ABAIXO E 1 Á ESQUERDA
        p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 ABAIXO E 1 Á DIREITA
        p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //1 ABAIXO E 2 Á ESQUERDA
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //1 ABAIXO E 2 Á DIREITA
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        return mat;
    }
}

package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{
    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }
    
    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        Posicao p = new Posicao(0,0);
        
        if(getCor() == Cor.BRANCA){
            //ACIMA DA PEÇA
            p.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            if(getTabuleiro().ExistePosicao(p) && !existePecaInimiga(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            //ACIMA DA PEÇA PRIMEIRO MOVIMENTO
            p.setValores(posicao.getLinha() - 2, posicao.getColuna());
            if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p) && getQuantMovimentos() == 0){
                if(mat[p.getLinha() + 1][p.getColuna()] == true){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
            }
            if(getTabuleiro().ExistePosicao(p) && !existePecaInimiga(p) && getQuantMovimentos() == 0){
                if(mat[p.getLinha() + 1][p.getColuna()] == true){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
            }
        
            //DIAGONAL CIMA-ESQUERDA DA PEÇA
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
        
            //DIAGONAL CIMA-DIREITA DA PEÇA
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }else{
            //ABAIXO DA PEÇA
            p.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            if(getTabuleiro().ExistePosicao(p) && !existePecaInimiga(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            //ABAIXO DA PEÇA PRIMEIRO MOVIMENTO
            p.setValores(posicao.getLinha() + 2, posicao.getColuna());
            if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p) && getQuantMovimentos() == 0){
                if(mat[p.getLinha() - 1][p.getColuna()] == true){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
            }
            if(getTabuleiro().ExistePosicao(p) && !existePecaInimiga(p) && getQuantMovimentos() == 0){
                if(mat[p.getLinha() - 1][p.getColuna()] == true){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
            }
        
            //DIAGONAL BAIXO-ESQUERDA DA PEÇA
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
        
            //DIAGONAL BAIXO-DIREITA DA PEÇA
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }
        return mat;
    }
}

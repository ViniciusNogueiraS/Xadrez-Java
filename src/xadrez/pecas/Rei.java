package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{
    
    private PartidaXadrez partida;
    
    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partida1) {
        super(cor, tabuleiro);
        partida = partida1;
    }
    
    private boolean testeRoque(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getQuantMovimentos() == 0;
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        Posicao p = new Posicao(0,0);
        
        //ACIMA DA PEÇA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIAGONAL CIMA-ESQUERDA DA PEÇA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //Á ESQUERDA DA PEÇA
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIAGONAL BAIXO-ESQUERDA DA PEÇA
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //ABAIXO DA PEÇA
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIAGONAL BAIXO-DIREITA DA PEÇA
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //Á DIREITA DA PEÇA
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIAGONAL CIMA-DIREITA DA PEÇA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().ExistePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        if(getTabuleiro().ExistePosicao(p) && existePecaInimiga(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        //#jogada especial Roque!
        if(getQuantMovimentos() == 0 && !partida.getXeque()){
            Posicao PT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if(testeRoque(PT1)){
                Posicao casa1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao casa2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if(getTabuleiro().peca(casa1) == null && getTabuleiro().peca(casa2) == null){
                    // permitir a jogada especial Roque Menor!
                    mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }
            
            Posicao PT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if(testeRoque(PT2)){
                Posicao casa1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao casa2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao casa3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if(getTabuleiro().peca(casa1) == null && getTabuleiro().peca(casa2) == null && getTabuleiro().peca(casa3) == null){
                    // permitir a jogada especial Roque Maior!
                    mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }
           
        return mat;
    }
}

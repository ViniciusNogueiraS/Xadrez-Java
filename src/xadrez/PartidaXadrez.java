package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Dama;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;
    private int turno;
    private Cor jogadorAtual;
    private boolean xeque;
    private boolean xequeMate;
    
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCA;
        iniciarPartida();
    }
    
    public int getTurno(){
        return turno;
    }
    
    public Cor getJogadorAtual(){
        return jogadorAtual;
    }

    public boolean getXeque() {
        return xeque;
    }

    public boolean getXequeMate() {
        return xequeMate;
    }
    
    public PecaXadrez[][] getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for(int i = 0; i < tabuleiro.getLinhas(); i++){
            for(int j = 0; j < tabuleiro.getColunas(); j++){
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return mat;
    }
    
    public boolean[][] movimentosPossiveis(PosicaoXadrez origem){
        Posicao posicao = origem.toPosicaoMatriz();
        validaPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }
    
    public PecaXadrez MovimentaXadrez(PosicaoXadrez origemX, PosicaoXadrez destinoX){
        Posicao origem = origemX.toPosicaoMatriz();
        Posicao destino = destinoX.toPosicaoMatriz();
        validaPosicaoOrigem(origem);
        validaPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);
        
        if(TesteXeque(jogadorAtual)){
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new ExcecaoXadrez("Você não pode colocar-se em xeque!");
        }
        
        xeque = (TesteXeque(oponente(jogadorAtual))) ? true : false;
        
        if(TesteXequeMate(oponente(jogadorAtual))){
            xequeMate = true;
        }else{
            proximoTurno();
        }
        
        return (PecaXadrez)pecaCapturada;
    }
    
    private Peca fazerMovimento(Posicao origem, Posicao destino){
        PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(origem);
        p.addQuantMovimentos();
        Peca pCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.ColocaPeca(p, destino);
        
        if(pCapturada != null){
            pecasNoTabuleiro.remove(pCapturada);
            pecasCapturadas.add(pCapturada);
        }
        
        //#movimento especial Roque Menor!
        if(p instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            
            PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemT);
            tabuleiro.ColocaPeca(torre, destinoT);
            torre.addQuantMovimentos();
        }
        
        //#movimento especial Roque Maior!
        if(p instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            
            PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemT);
            tabuleiro.ColocaPeca(torre, destinoT);
            torre.addQuantMovimentos();
        }
        
        return pCapturada;
    }
    
    private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturada){
        PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(destino);
        p.removeQuantMovimentos();
        tabuleiro.ColocaPeca(p, origem);
        
        if(capturada != null){
            tabuleiro.ColocaPeca(capturada, destino);
            pecasNoTabuleiro.add(capturada);
            pecasCapturadas.remove(capturada);
        }
        
        //#DESFAZER movimento especial Roque Menor!
        if(p instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            
            PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoT);
            tabuleiro.ColocaPeca(torre, origemT);
            torre.removeQuantMovimentos();
        }
        
        //#DESFAZER movimento especial Roque Maior!
        if(p instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            
            PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoT);
            tabuleiro.ColocaPeca(torre, origemT);
            torre.removeQuantMovimentos();
        }
    }
    
    private Cor oponente(Cor cor){
        return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }
    
    private PecaXadrez rei(Cor cor){
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : lista){
            if(p instanceof Rei){
                return (PecaXadrez)p;
            }
        }
        throw new IllegalStateException("Não existe rei " + cor + " no tabuleiro!");//não é para ocorrer NUNCA!
    }
    
    private boolean TesteXeque(Cor cor){
        Posicao pRei = rei(cor).getPosicaoXadrez().toPosicaoMatriz();
        List<Peca> pecasInimigas = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecasInimigas){
            boolean[][] mat = p.movimentosPossiveis();
            if(mat[pRei.getLinha()][pRei.getColuna()] == true){
                return true;
            }
        }
        return false;
    }
    
    private boolean TesteXequeMate(Cor cor){
        if(!TesteXeque(cor)){
            return false;
        }
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list){
            boolean[][] mat = p.movimentosPossiveis();
            for(int i = 0; i < tabuleiro.getLinhas(); i++){
                for(int j = 0; j < tabuleiro.getColunas(); j++){
                    if(mat[i][j]){
                        Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicaoMatriz();
                        Posicao destino = new Posicao(i, j);
                        Peca capturada = fazerMovimento(origem, destino);
                        boolean testeXeque = TesteXeque(cor);
                        desfazerMovimento(origem, destino, capturada);
                        if(!testeXeque){//este movimento tirou o Rei de xeque!
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private void validaPosicaoOrigem(Posicao posicao){
        if(!tabuleiro.ExistePeca(posicao)){
            throw new ExcecaoXadrez("Não existe peça na origem!");
        }
        if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()){
            throw new ExcecaoXadrez("Está peça não é sua!");
        }
        if(!tabuleiro.peca(posicao).existePeloMenosUmMovimento()){
            throw new ExcecaoXadrez("Não existe movimentos possíveis para esta peça!");
        }
    }
    
    private void validaPosicaoDestino(Posicao origem, Posicao destino){
        if(!tabuleiro.peca(origem).movimentoPossivel(destino)){
            throw new ExcecaoXadrez("A peça não pode fazer este movimento!");
        }
    }
    
    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA; 
    }
    
    private void colocaNovaPeca(char coluna, int linha, PecaXadrez peca){
        tabuleiro.ColocaPeca(peca, new PosicaoXadrez(coluna, linha).toPosicaoMatriz());
        pecasNoTabuleiro.add(peca);
    }
    
    private void iniciarPartida(){        
        colocaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA, this));
        colocaNovaPeca('d', 1, new Dama(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA));
        colocaNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA));

        colocaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
        colocaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
        colocaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
        colocaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
        colocaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
        colocaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA , this));
        colocaNovaPeca('d', 8, new Dama(tabuleiro, Cor.PRETA));
        colocaNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA));
        colocaNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA));
    }
}

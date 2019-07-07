package tabuleiro;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if(linhas < 1 || colunas < 1){
            throw new ExcecaoTabuleiro("É necessário ao menos uma linha e uma coluna!");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
    
    public Peca peca(int linha, int coluna){
        if(!ExistePosicao(linha, coluna)){
            throw new ExcecaoTabuleiro("Esta posição não existe!");
        }
        return pecas[linha][coluna];
    }
    
    public Peca peca(Posicao posicao){
        if(!ExistePosicao(posicao)){
            throw new ExcecaoTabuleiro("Esta posição não existe!");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
    
    public void ColocaPeca(Peca peca, Posicao posicao){
        if(ExistePeca(posicao)){
            throw new ExcecaoTabuleiro("Já existe uma peça nesta posição!");
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }
    
    private boolean ExistePosicao(int linha, int coluna){
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }
    
    public boolean ExistePosicao(Posicao posicao){
        return ExistePosicao(posicao.getLinha(), posicao.getColuna());
    }
    
    public boolean ExistePeca(Posicao posicao){
        if(!ExistePosicao(posicao)){
            throw new ExcecaoTabuleiro("Esta posição não existe!");
        }
        return peca(posicao) != null;
    }
}













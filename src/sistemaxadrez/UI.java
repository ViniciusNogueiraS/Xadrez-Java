package sistemaxadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class UI {
    
    public static PosicaoXadrez lerPosicaoXadrez(Scanner sc){
        try{
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoXadrez(coluna, linha);   
        }catch(RuntimeException e){
            throw new InputMismatchException("Erro ao ler posição de xadrez!");
        }
    }
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    
    public static void limpaTela(){
        for (int i = 0; i < 40; ++i){
            System.out.println();
        }
    }
    
    public static void printPartida(PartidaXadrez partida, List<PecaXadrez> capturadas){
        printTabuleiro(partida.getPecas());
        printPecaCapturada(capturadas);
        System.out.print(ANSI_CYAN_BACKGROUND + "Turno: " + partida.getTurno() + " ║ ");
        if(!partida.getXequeMate()){
            System.out.println("Esperando pelo jogador das peças " + partida.getJogadorAtual() + "S..." + ANSI_RESET);
            if(partida.getXeque()){
                System.out.println(ANSI_RED_BACKGROUND + " ▼ XEQUE ▲ " + ANSI_RESET);
            }
        }else{
            System.out.println(ANSI_RED_BACKGROUND + " ▼▲ XEQUE-MATE ▲▼ " + ANSI_RESET);
            System.out.println("O vencedor é o jogador das peças " + partida.getJogadorAtual() + "S!!!");
        }
    }
    
    public static void printTabuleiro(PecaXadrez[][] pecas){
        System.out.println(ANSI_WHITE_BACKGROUND+"  a b c d e f g h  ");
        for(int i = 0; i < pecas.length; i++){
            System.out.print(ANSI_WHITE_BACKGROUND+(8-i)+ANSI_GREEN_BACKGROUND+" ");
            for(int j = 0; j < pecas.length; j++){
                    printPeca(pecas[i][j], false);
            }
            System.out.print(ANSI_WHITE_BACKGROUND+(8-i));
            System.out.println();
        }
        System.out.println(ANSI_WHITE_BACKGROUND+"  a b c d e f g h  ");
    }
    
    public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis){
        System.out.println(ANSI_WHITE_BACKGROUND+"  a b c d e f g h  ");
        for(int i = 0; i < pecas.length; i++){
            System.out.print(ANSI_WHITE_BACKGROUND+(8-i)+ANSI_GREEN_BACKGROUND+" ");
            for(int j = 0; j < pecas.length; j++){
                printPeca(pecas[i][j], movimentosPossiveis[i][j]);
            }
            System.out.print(ANSI_WHITE_BACKGROUND+(8-i));
            System.out.println();
        }
        System.out.println(ANSI_WHITE_BACKGROUND+"  a b c d e f g h  ");
    }
    
    private static void printPeca(PecaXadrez peca, boolean mp){
        if(mp){
            System.out.print(ANSI_RED);
        }
        System.out.print(ANSI_GREEN_BACKGROUND);
	if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }else{
            if (peca.getCor() == Cor.BRANCA){
                if(mp){
                    System.out.print(ANSI_RED + "\033[1m" + peca + "\033[0m" + ANSI_RESET);
                }else{
                    System.out.print(ANSI_WHITE + "\033[1m" + peca + "\033[0m" + ANSI_RESET);
                }
            }else{
                System.out.print(peca + ANSI_RESET);
            }
        }
        System.out.print(ANSI_GREEN_BACKGROUND + " ");
        
    }
    private static void printPecaCapturada(List<PecaXadrez> capturadas){
        List<PecaXadrez> brancas = capturadas.stream().filter(x -> x.getCor() == Cor.BRANCA).collect(Collectors.toList());
        List<PecaXadrez> pretas = capturadas.stream().filter(x -> x.getCor() == Cor.PRETA).collect(Collectors.toList());
        System.out.println("Peças capturadas:");
        System.out.print(ANSI_GREEN_BACKGROUND + ANSI_WHITE + "Brancas:");
        System.out.println(Arrays.toString(brancas.toArray()) + ANSI_RESET);
        System.out.print(ANSI_GREEN_BACKGROUND + "Pretas:");
        System.out.println(Arrays.toString(pretas.toArray()) + ANSI_RESET);
    }
    
}

package sistemaxadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class SistemaXadrez {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PartidaXadrez partida1 = new PartidaXadrez();
        
        List<PecaXadrez> capturadas = new ArrayList<>();
        
        while(!partida1.getXequeMate()){
            try{
                UI.limpaTela();
                UI.printPartida(partida1, capturadas);
                System.out.println();
                System.out.print(UI.ANSI_YELLOW_BACKGROUND + "Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
                
                boolean[][] movimentosPossiveis = partida1.movimentosPossiveis(origem);
                UI.limpaTela();
                UI.printTabuleiro(partida1.getPecas(), movimentosPossiveis);
                
                System.out.println();
                System.out.print(UI.ANSI_YELLOW_BACKGROUND +"Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pCapturada = partida1.MovimentaXadrez(origem, destino);
                
                if(pCapturada != null){
                    capturadas.add(pCapturada);
                }
                
                if(partida1.getPromovido() != null){
                    System.out.print("Entre com a peça a ser promovida (T/C/B/D): ");
                    String tipo = sc.nextLine();
                    partida1.substituiPecaPromovida(tipo);
                }
                
            }catch(ExcecaoXadrez e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            
        }
        UI.limpaTela();
        UI.printPartida(partida1, capturadas);
    }
}

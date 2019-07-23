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
        
        while(true){
            try{
                UI.limpaTela();
                System.out.println("P = Peão, T = Torre, C = Cavalo, B = Bispo, R = Rei, D = Dama");
                System.out.println();
                UI.printPartida(partida1, capturadas);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
                
                boolean[][] movimentosPossiveis = partida1.movimentosPossiveis(origem);
                UI.limpaTela();
                UI.printTabuleiro(partida1.getPecas(), movimentosPossiveis);
                
                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pCapturada = partida1.MovimentaXadrez(origem, destino);
                
                if(pCapturada != null){
                    capturadas.add(pCapturada);
                }
            }catch(ExcecaoXadrez e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            
        }
    }
}

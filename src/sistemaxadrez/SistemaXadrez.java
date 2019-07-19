package sistemaxadrez;

import java.util.InputMismatchException;
import java.util.Scanner;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class SistemaXadrez {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        PartidaXadrez partida1 = new PartidaXadrez();
        while(true){
            try{
                UI.limpaTela();
                System.out.println("P = Peão, T = Torre, C = Cavalo, B = Bispo, R = Rei, D = Dama");
                System.out.println();
                UI.printTabuleiro(partida1.getPecas());   
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);

                PecaXadrez pCapturada = partida1.MovimentaXadrez(origem, destino);
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

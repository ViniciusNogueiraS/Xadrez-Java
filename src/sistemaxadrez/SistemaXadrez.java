package sistemaxadrez;

import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class SistemaXadrez {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        PartidaXadrez partida1 = new PartidaXadrez();
        while(true){
            UI.printTabuleiro(partida1.getPecas());   
            System.out.println();
            System.out.print("Origem: ");
            PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
            System.out.println();
            System.out.print("Destino: ");
            PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
            
            PecaXadrez pCapturada = partida1.MovimentaXadrez(origem, destino);
        }
    }
}

package sistemaxadrez;

import xadrez.PartidaXadrez;

public class SistemaXadrez {

    public static void main(String[] args) {
        PartidaXadrez partida1 = new PartidaXadrez();
        
        UI.printTabuleiro(partida1.getPecas());
        
        
    }
   
}

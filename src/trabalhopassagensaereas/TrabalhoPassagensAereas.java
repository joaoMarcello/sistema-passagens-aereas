/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopassagensaereas;

import interfaceGrafica.*;
import java.awt.Point;

/**
 *
 * @author JM
 */
public class TrabalhoPassagensAereas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Aviao plane = new Aviao();  //criando o aviao
        
        //adicionado voos no aviao criado
        plane.addVoo("Sao Luis", "Brasilia", 500.00, "30/05/2018", "13:00");
        plane.addVoo("Sao Luis", "Sao Paulo", 800.00, "01/06/2018", "14:00");
        plane.addVoo("Sao Luis","Rio de Janeiro", 900.00, "15/06/2018", "15:00");

        
        GerenciadorClientes gc = new GerenciadorClientes(plane); //criando o gerenciador de clientes
        
        ReservaHandler rh = new ReservaHandler();   //criando o manipulador de reservas
        
        
        MenuPrincipal menu = new MenuPrincipal(gc);
        FrameMethods.setDefaultFrameConfig(new Point(300, 0), menu);
    }
    
}

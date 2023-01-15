/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopassagensaereas;

/**
 *
 * @author JM
 */
public class ReservaPrimClasse extends Reserva {
    
    //Construtor
    ReservaPrimClasse(Assento s, Voo v, Cliente c){
        super(s, v, c); 
    }
    
    /*  Retorna o preco de uma reserva duplicado    */
    @Override
    public double getPreco(){   return voo.getPreco() * 2;  }
    
}

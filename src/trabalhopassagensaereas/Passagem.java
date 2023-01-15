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
public class Passagem {
    private Reserva reserva;
    
    Passagem(Reserva r){
        reserva = r;
    }
    
    public Reserva getReserva(){ return reserva; }
    
    public void setReserva(Reserva novaReserva){ this.reserva = novaReserva; }

    /**
     *
     * Retorna os dados da passagem na forma de String.
     */
    @Override
    public String toString(){
        return reserva.toString();  
    }
    
    public String getFormatedString(){
        return reserva.getFormatedString();
    }
    
    /** Imprime a passagem. */
    public static void printPassagem(Passagem p){
        
    }
}

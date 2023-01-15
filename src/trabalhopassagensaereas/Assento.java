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
public class Assento {
    private char letraAssento;  
    private int numFila;   
    
    //  construtor recebendo o numero da fila e a letra
    public Assento(int numFila, char letraAssento){
        this.letraAssento = Character.toUpperCase(letraAssento);
        this.numFila = numFila;
    }
    
    //construtor recebendo uma string
    public Assento(String s){
        letraAssento = s.toUpperCase().charAt(s.length() - 1); //pegando o ultimo caractere da string e atribuindo a letra do assento
        
        s = s.substring(0, s.length() - 1); //tirando o ultimo caractere da string
        
        try {
            numFila = Integer.parseInt(s); //transformando a string em inteiro
        }
        catch(NumberFormatException exception){ //nao consequiu formar um numero com a string!
            numFila = -1;
        }
    }
    
    //  transforma os dados do assento em string
    public String toString(){
        return "" + numFila + letraAssento;
    }
    
    public String getStringFormated() {
        return "" + numFila + letraAssento;
    }
    
    
    //  retorna a letra do assento
    public char getLetra(){ return letraAssento; }
    
    
    //  retorna o numero da fila do assento
    public int getNumFila(){ return numFila; }
    
    
    // muda o numero da fila
    public void changeFila(int n){
        numFila = n;
    }
    
    
    //  muda a letra do assento
    public void changeLetraAssento(char l){
        letraAssento = l;
    }
}

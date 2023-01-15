/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopassagensaereas;

import java.util.ArrayList;

/**
 *
 * @author JM
 */
public class Cliente {
    private static long currentId = 0;
    /** Representa a chave identificadora do cliente.*/
    private long id;  
    /**Nome do cliente.*/
    private String nome;
    private long telefone, numCartao;
    private ArrayList<Reserva> reservas;
    private ArrayList<Passagem> passagens;
    
    //  Contrutor
    Cliente(String nome, long numCartao, long telefone){
        currentId++; 
        this.id = currentId;
        this.nome = nome;
        this.telefone = telefone;
        this.numCartao = numCartao;
        reservas = new ArrayList<Reserva>();
        passagens = new ArrayList<Passagem>();
    }
    
    // Construtor: s eh uma string com os dados do cliente separados por ;
    Cliente(String s){
        ArrayList<String> lista = UtilityMethods.getSeparatedString(s);
        
        if(lista.size() == 4){
            
            try{
                id = Integer.parseInt(lista.get(0));
            }
            catch(NumberFormatException e){
                 id = -1;   
            }
            nome = lista.get(1);
            
            try{
                telefone = Long.parseLong(lista.get(2));
            }
            catch(NumberFormatException e){
                telefone = -1;
            }
            
            try{
                numCartao = Long.parseLong(lista.get(3));
            }
            catch(NumberFormatException e){
                numCartao = -1;
            }
        }
        else{
            id = -1;
            nome = "error";
            telefone = -1;
            numCartao =-1;
        }
        
        reservas = new ArrayList<Reserva>(); 
        passagens = new ArrayList<Passagem>();
    }
    
    /**
     * Transforma os dados do cliente em string.    
     */
    public String toString(){
        return "Id Cliente: " + id + "\nNome: " + nome.substring(0,30) ;
    }
    
    /** 
     * Retorna os dados do cliente formatados para a gravacao em arquivo.
     */
    public String getStringFormated(){
        return "" + id + ";" + nome + ";" + telefone + ";" + numCartao;
    }
    
    
    /**
     * Adiciona uma reserva na lista de reservas do cliente.    
     */
    public boolean addReserva(Reserva r){
        return reservas.add(r);
    }
    
    
    /**
     * Remove uma reserva da lista de reservas do cliente. 
     */
    public boolean removeReserva(Reserva r){
        return reservas.remove(r); 
    }
    
    /**Adiciona uma passagem na lista de passagens do cliente.
     */
    public boolean addPassagem(Passagem p){
        return passagens.add(p);
    }
    
    /**Remove uma passagem da lista de passagens do cliente.
     */
    public boolean removePassagem(Passagem p){
        return passagens.remove(p);
    }
    
    
    /**
     * Retorna uma reserva com o mesmo numero do id especificado.
     * Se nao encontrar, retorna null.
     */
    public Reserva getReservaPorId(long id){
        for(Reserva r : reservas){  //  percorrendo a lista de reservas
            if(r.getId() == id)
                return r;
        }
        return null;
    }
    
    public static long getLastAddedId(){ return currentId; }
    
    public static void setCurrentId(long id) { currentId = id; }
    
    /**
     * Retorna o id do cliente. 
     */
    public long getId(){    return id;  }
    
    
    /**
     * Retorna o nome do cliente.
     */
    public String getNome(){    return nome;      }
    
    
    /**  Retorna o telefone de contato do cliente.    
     */
    public long getTelefone(){  return telefone;    }
    
    
    /**  Retorna o numero do cartao do cliente.   
     */
    public long getNumCartao(){     return numCartao;    }
    
    
    /**  Retorna true caso o cliente possui alguma reserva ou false caso contrario.   
     */
    public boolean possuiReservas(){
        for(Reserva r : reservas){
            if(!r.jaFoiComprada())
                return true;
        }
        
        return false;
    }
    
    
    /**  Imprime todas as reservas do cliente.    
     */
    public void showReservas(){
        System.out.println();
        for(Reserva r : reservas){
            System.out.println(r.toString());
        }
        System.out.println();
    }
    
    public ArrayList<Reserva> getListaReservas(){ return reservas; }
    
    
    public ArrayList<Passagem> getListaPassagens(){ return passagens; }
}

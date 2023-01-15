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
public class Reserva {
    private static long currentId = 0;
    private long id;
    private Assento s;
    private Cliente cliente;
    protected Voo voo;
    private boolean foiComprada = false;
    
    //  Construtor
    Reserva(Assento assento, Voo v, Cliente cliente){
        currentId++;
        id = currentId;
        this.s = assento;
        this.voo = v;
        this.cliente = cliente;
    }
    
    
    /*  Transforma os dados da reserva em string    */
    public String toString(){
        return "ID: " + id + "   Assento:" + s.toString() 
                + (this instanceof ReservaPrimClasse ? "    Prim. Classe" : "    Classe Economica")
                + "     Voo Numero " + voo.getId() + ", " + voo.getDestino() + (foiComprada ? "    COMPRADA" : "");
    }
    
    public String getFormatedString(){
        return "" + cliente.getId() + ";" + id + ";" + voo.getId() + ";" + s.getNumFila() + s.getLetra() +
                ";" + foiComprada;
    }
    
    public static long getLastAddedId(){ return currentId; }
    
    
    public static void setCurrentId(long current){ currentId = current; }
    
    
    /**   Compra uma reserva e gera uma passagem. A passagem
     * eh adicionada na lista de passagens do cliente.
     */
    public boolean comprar(){
        Passagem p;
        
        if(!jaFoiComprada()){  //se reserva ainda nao foi comprada
            this.foiComprada = true;  //comprando a reserva
            p = new Passagem(this); //criando a nova passagem
            
            if(getCliente().addPassagem(p)) //adicionando a passagem na lista de passagem do cliente
                return true;
            else    //se nao conseguiu adicionar a passagem na lista
                cancelarCompra();  //cancelando a compra da reserva
        }
        
        return false;
    }
    
    public void cancelarCompra() {
        this.foiComprada = false;
    }
    
    
    public boolean jaFoiComprada(){ return this.foiComprada; }
    
    
    /*  Retorna o numero da reserva */
    public long getId(){return id;}
    
    public void setId(long id){ this.id = id; }
    
    
    /*  Retorna o assento da reserva */
    public Assento getAssento(){ return s; }
    
    
    /*  Muda o assento reservado */
    public void changeAssento(Assento s){
        this.s = s;
    }
    
    
    /*  Retorna o cliente */
    public Cliente getCliente(){    return cliente; }
    
    
    /*  Retorna o voo ao qual a reserva esta associada   */
    public Voo getVoo(){    return voo; }
    
    
    /*  Muda o voo */
    public void setVoo(Voo v){  voo = v;    }
    
    
    /*  Retorna o preco do voo  */
    public double getPreco(){   return voo.getPreco();  }
    
    
    /*  Muda o assento reservado */
    public void changeAssento(int numFila, char letraAssento){
        this.s.changeFila(numFila);
        this.s.changeLetraAssento(letraAssento);
    }
    
}

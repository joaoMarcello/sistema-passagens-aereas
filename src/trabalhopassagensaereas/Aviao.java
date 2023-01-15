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
public class Aviao {
    private final int NUM_FILAS = 20;
    private final int NUM_COLUNAS = 6;
    private final int NUM_FILAS_PRIM_CLASSE = 5;
    private final int MAX_VAGAS_PRIM_CLASSE = NUM_FILAS_PRIM_CLASSE * NUM_COLUNAS;
    private final int MAX_VAGAS_ECONOM = NUM_FILAS * NUM_COLUNAS - MAX_VAGAS_PRIM_CLASSE;
    private ArrayList<Voo> voos;
    
    
    //construtor
    Aviao(){
        voos = new ArrayList<Voo>();
    }
    
    //  retorna um voo que possui o id igual ao id especificado ou null caso nao encontre
    public Voo getVooPorId(long id){
        for(Voo v : voos)
            if(v.getId() == id)
                return v;
        return null;
    }
    
    
    public Voo getVooPorIndice(int i){
        return voos.get(i);
    }
    
    
    //  retorna a lista de voos do aviao
    public ArrayList<Voo> getListaVoos(){   return voos;    }
    
    
    //  adiciona um voo na lista de voos do aviao
    public boolean addVoo(String origem, String destino, double preco, String data, String horario){
        Voo v = new Voo(origem, destino, this, preco, data, horario); //criando o novo voo
        return voos.add(v);     //adicionando na lista de voos
    }

    
    
    /*  Retorna o numero de filas do aviao  */    
    public int getNumFilas(){ return NUM_FILAS; }
    
    
    /*  Retorna o numero de filas da primeira classe    */
    public int getNumFilasPrimClasse(){ return NUM_FILAS_PRIM_CLASSE; }

    
    /*  Retorna o numero de colunas do aviao    */
    public int getNumColunas(){ return NUM_COLUNAS; }
    

    /* Retorna o numero maximo de vagas da primeira classe*/
    public int getMaxVagasPrimClasse(){ return MAX_VAGAS_PRIM_CLASSE; }
    
    
    /* Retorna o numero maximo de vagas da classe economica */
    public int getMaxVagasClasseEconom(){ return MAX_VAGAS_ECONOM; }
    
    
    /*  Checa se um assento eh valido para este aviao   */
    public boolean assentoEhValido(Assento s){
        return s.getNumFila() - 1 < NUM_FILAS && s.getNumFila() - 1 >= 0
                && s.getLetra() >= 'A' && s.getLetra() <= 'A' + NUM_COLUNAS - 1;
    }
    
    
    /* Recebe um assento e retorna true caso o assento seja da primeira classe ou false caso contrario */
    public boolean assentoEhPrimClasse(Assento s){
        return s.getNumFila() - 1 < NUM_FILAS_PRIM_CLASSE && s.getNumFila() - 1 >= 0 
                && s.getLetra() >= 'A' && s.getLetra() <= 'A' + NUM_COLUNAS - 1;
    }
    
    
}

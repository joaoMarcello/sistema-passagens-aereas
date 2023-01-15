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
public class Voo {
    private static long currentId = 0;
    private String destino;
    private String origem;
    private long id;
    private Aviao plane;
    private boolean[][] mapaAssentos;
    private int vagasPrimClasse;
    private int vagasClasseEconom;
    private double preco;
    private String horario;
    private String data;
    
    
    //Construtor
    Voo(String origem, String destino, Aviao plane, double preco, String data, String horario){
        currentId++;
        this.origem = origem;
        this.destino = destino;
        this.plane = plane;
        this.id = currentId;
        this.preco = preco;
        this.horario = horario;
        this.data = data;
        vagasClasseEconom = plane.getMaxVagasClasseEconom();
        vagasPrimClasse = plane.getMaxVagasPrimClasse();
        
        mapaAssentos = new boolean[plane.getNumFilas()][plane.getNumColunas()];
    }
    
    /*  Transforma os dados do voo em string    */
    public String toString(){
        return "" + id + "     " + destino;
    }
    
    
    /*  Retorna o destino do voo    */
    public String getDestino(){ return destino; }
    
    public String getOrigem(){return origem;}
    
    
    /*  Retorna o numero do voo (ID) */
    public long getId(){   return id;     }
    
    
    /*  Retorna o aviao a quem pertence este voo */
    public Aviao getAviao(){   return plane;   }
    
    
    /*  Retorna o mapa de assentos do voo   */
    public boolean[][] getMapAssentos(){   return mapaAssentos;    }
    
    
    /*  Altera o valor do preco */
    public void setPreco(double novoPreco){   preco = novoPreco;  } 
    
    
    /*  Retorna o preco do voo  */
    public double getPreco(){   return preco;   }
    
    
    /*  Retorna o numero de vagas na primeira classe    */
    public int numVagasPrimClasse(){    return vagasPrimClasse;  }
    
    
    /*  Retorna o numero de vagas na classe economica   */
    public int numVagasClasseEconom(){  return vagasClasseEconom;   }
    
    
    /*  Altera o horario    */
    public void setHorario(String novoHorario){ horario = novoHorario;  }
    
    
    /*  Retorna o horario   */
    public String getHorario(){ return horario; }
    
    
    /*  Retorna a data do voo*/
    public String getData(){    return data;    }
    
    
    /*  Altera a data do voo*/
    public void setData(String novaData){   data = novaData;    }  
    
    
    /*  Diz se a primeira classe esta cheia */
    public boolean primClasseEstaCheia(){   return vagasPrimClasse <= 0;    }
    
    
    /*  Diz se a classe economica esta cheia    */
    public boolean classeEconomEstaCheia(){ return vagasClasseEconom <= 0;    }
    
    
    /*  Diz se o voo esta lotado    */
    public boolean estaLotado(){    return vagasPrimClasse + vagasClasseEconom <= 0;    }
    
    
    
    /* Incrementa em uma unidade o numero de vagas da primeira classe*/
    private boolean addVagaNaPrimClasse(){
        if(vagasPrimClasse + 1 <= plane.getMaxVagasPrimClasse()){
            vagasPrimClasse ++;
            return true;
        }
        return false;
    }
    
    
    /* Subtrai em uma unidade o numero da vagas da primeira classe */
    private boolean subtractVagaPrimClasse(){
        if(vagasPrimClasse - 1 >= 0){
            vagasPrimClasse --;
            return true;
        }
        return false;
    }
    
    
    /* Incrementa em uma unidade o numero de vagas da classe economica */
    private boolean addVagaClasseEconom(){
        if(vagasClasseEconom + 1 <= plane.getMaxVagasClasseEconom()){
            vagasClasseEconom ++;
            return true;
        }
        return false;
    }
    
    
    /* Subtrai uma unidade do numero de vagas da classe economica */
    private boolean subtractVagaClasseEconom(){
        if(vagasClasseEconom - 1 >= 0){
            vagasClasseEconom --;
            return true;
        }
        return false;
    }
    
    
    /* Recebe um assento e retorna true se o assento esta ocupado ou false caso contrario*/
    public boolean assentoEstaOcupado(Assento s){
        if(plane.assentoEhValido(s))
                return mapaAssentos[s.getNumFila() - 1][s.getLetra() - 'A'];
       
        return false;
    }
    
    
    /* Reserva um assento se este nao estiver ocupado */
    public boolean reservarAssento(Assento s){
        if(!assentoEstaOcupado(s)){
            mapaAssentos[s.getNumFila() - 1][s.getLetra() - 'A' ] = true;
            
            if(plane.assentoEhPrimClasse(s))
                subtractVagaPrimClasse();
            else
                subtractVagaClasseEconom();
            return true;
        }
        return false;
    }
    
    
    /*  Cancela a reserva de um  assento    */
    public boolean cancelaReservaAssento(Assento s ){
        if(assentoEstaOcupado(s)){
            mapaAssentos[s.getNumFila() - 1][s.getLetra() - 'A'] = false;
            
            if(plane.assentoEhPrimClasse(s))
                addVagaNaPrimClasse();
            else
                addVagaClasseEconom();
            
            return true;
        }
        return false;
    }
    
    
    /*  Mostra os assentos disponiveis no aviao */
    public void printMapaAssentos(){
        System.out.print("\n    ");
        
        //imprimindo os caracteres A, B, C...
        for(int i = 0; i < plane.getNumColunas(); i++)
            System.out.print(" " + (char)('A' + i) + " " + (i == plane.getNumColunas()/2 - 1 ? "  " : ""));
        
        System.out.println();
        
        
        for(int i = 0; i < mapaAssentos.length; i++){
            
            if(i == plane.getNumFilasPrimClasse())  //deixa uma linha em branco entre a prim. classe e a classe economica
                System.out.println();
            
            System.out.print(i + 1 + (i + 1 < 10 ? " " : "") +  ": ");  //imprimindo o numero da linha
            
            //imprimindo os assentos. Se estiver ocupado imprime 'X'. Senao imprime '-'
            for(int j = 0; j < mapaAssentos[i].length; j++){ 
                
                System.out.print(mapaAssentos[i][j] ? " X " : " - " ); 
                
                if(j == plane.getNumColunas()/2-1)  //deixa um espaco em branco na metade das colunas
                    System.out.print("  ");
            }
            
            //mostrando algumas informacoes adicionais
            if(i == 0)
                System.out.print("    Voo " + id);
            else if(i == 1)
                System.out.print("    Destino: " + destino);
            else if(i == plane.getNumFilas() - 3)
                System.out.print("   Num. vagas Prim. Classe: " + vagasPrimClasse);
            else if(i == plane.getNumFilas() - 2)
                System.out.print("   Num. vagas Classe Econom.: " + vagasClasseEconom);
            else if(i == plane.getNumFilas() - 1)
                System.out.print("   Total Vagas Livres: " + (vagasClasseEconom + vagasPrimClasse));

            System.out.print("\n");
        }
        
        System.out.print("\n");
    }
    
    
    public String getMapaAssentos(){
        String s = "     ";
        
        //imprimindo os caracteres A, B, C...
        for(int i = 0; i < plane.getNumColunas(); i++)
            s = s + (" " + (char)('a' + i) + "" + (i == plane.getNumColunas()/2 - 1 ? "  " : ""));
        
       s = s + "\n";
        
        
        for(int i = 0; i < mapaAssentos.length; i++){
            
            if(i == plane.getNumFilasPrimClasse())  //deixa uma linha em branco entre a prim. classe e a classe economica
                s = s + "\n";
            
            s = s + (i + 1 + (i + 1 < 10 ? " " : "") +  ": ");  //imprimindo o numero da linha
            
            //imprimindo os assentos. Se estiver ocupado imprime 'X'. Senao imprime '-'
            for(int j = 0; j < mapaAssentos[i].length; j++){ 
                
                s = s + (mapaAssentos[i][j] ? " x " : " - " ); 
                
                if(j == plane.getNumColunas()/2-1)  //deixa um espaco em branco na metade das colunas
                    s = s + ("  ");
            }
            
           s = s + ("\n");
        }
        
        s = s + ("\n");
        
        return s;
    }
}

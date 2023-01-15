/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopassagensaereas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author JM
 */
public class GerenciadorClientes {
    private ArrayList<Cliente> clientes;
    private final String clienteFileName = "clientes.txt";
    private final String reservaFileName = "reservas.txt";
    private Aviao aviao;
    
    //  Construtor
    public GerenciadorClientes(Aviao aviao){
        this.aviao = aviao;
        clientes = new ArrayList<Cliente>();    //inicializando a lista de clientes
        this.loadClienteData();  //carregando os dados dos clientes a partir de um arquivo de texto
        this.loadReservaClienteData(aviao);  //carregando as reservas dos clientes a partir de um arquivo de texto
    }
    
    public Aviao getAviao(){return aviao;}
    
    /*  Adiciona um cliente na lista de clientes    */
    public Cliente cadastrar(String nome, long numCartao, long telefone){
        Cliente c = new Cliente(nome,numCartao, telefone);  //criando o novo cliente
        
        if(clientes.add(c)) //adiciona o cliente na lista
            return c;
        return null;
    }
    
    
    /*  Adiciona um cliente na lista de clientes    */
    public boolean cadastrar(Cliente c){
        return clientes.add(c);
    }
    
    
    /*  Retorna um cliente com id especificado. Caso nao encontre retorna null  */
    public Cliente getClientePorId(long id){
        for(Cliente c : clientes)   //percorrendo a lista de clientes
            if(c.getId() == id)
                return c;
        
        return null;
    }
    
    public ArrayList<Cliente> getListaClientes() {return clientes; }
    
    //Carrega os dados dos clientes a partir de um arquivo de texto
    public  void loadClienteData(){
        Scanner input;
        Cliente c;
        
        try {
            input = new Scanner(Paths.get(clienteFileName));  //abrindo o arquivo para leitura
        } catch (IOException ex) {  //se nao conseguiu abrir o arquivo
            return; 
        }
        
        long currentId = 0;
        try{
            currentId = Long.parseLong(input.nextLine());  //pegando o id do ultimo cliente adicionado(primeira linha do arquivo) 
        }
        //caso nao conseguiu converter pra long ou arquivo estiver vazio...
        catch(NumberFormatException | NoSuchElementException e){  
            currentId = 0;
        }
        
        Cliente.setCurrentId(currentId);  //mudando o currentId da classe Cliente
        
        while(input.hasNext()){  //enquanto houver dados no arquivo
            c = new Cliente(input.nextLine()); //criando o novo cliente a partir de uma string
            clientes.add(c);  //adicionando o novo cliente na lista de clientes
        }
        
        if(input != null)
            input.close(); //fechando o arquivo
        
    }
    
    
    //Salva os dados dos clientes em um arquivo de texto
    public void saveClienteData() throws FormatterClosedException, SecurityException, FileNotFoundException{
        
        try(Formatter output = new Formatter(clienteFileName)){ //abrindo o arquivo para gravacao (pode gerar SecurityException)
            
            //colocando o id do ultimo cliente adicionado no arquivo
            output.format("" + Cliente.getLastAddedId() + "\n"); //pode gerar FormatterClosedException
            
            for(Cliente c : clientes){  //percorrendo a lista de clientes
                try{
                    output.format(c.getStringFormated() + "\n"); //transformando o cliente em string e 
                                                            //tenta adicionar no arquivo (pode gerar FormatterClosedException)
                }
                catch(FormatterClosedException e){  //se nao conseguiu escrever no arquivo...
                    throw e;
                }
            }
            
           if(output != null)  //se conseguiu abrir arquivo...
               output.close();  //fecha o arquivo
                
        }
        catch(SecurityException e ){  //nao pode gravar no arquivo
            throw e;
        }
        catch(FileNotFoundException e){  //nao conseguiu abrir o arquivo
           throw e;
        }
        catch(FormatterClosedException e){
            throw e;
        }
    }
    
    //Salva os dados das reservas do cliente
    public void saveReservaClienteData() throws SecurityException, FileNotFoundException, FormatterClosedException {
        try{
            //pode gerar SecurityException e FileNotFoundException
            Formatter output = new Formatter(reservaFileName);
            
            //adicionando o id do ultimo cliente cadastrado no arquivo
            output.format("" + Reserva.getLastAddedId() + "\n"); //pode gerar FormatterClosedException
            
            for(Cliente c : clientes){  //percorrendo a lista de clientes
                for(Reserva r : c.getListaReservas()){  //percorrendo a lista de reservas do cliente
                    //colocando a reserva formatada no arquivo de texto
                    output.format(r.getFormatedString() + "\n"); //pode gerar FormatterClosedException
                }
            }
            
            if(output != null)
                output.close(); //fechando o arquivo
        }
        catch(SecurityException e){ //acesso negado ao abrir o arquivo
            throw e;
        }
        catch(FileNotFoundException e){ //nao encontrou o arquivo
            throw e;
        }
        catch(FormatterClosedException e){ //nao conseguiu inserir um dado no arquivo
            throw e;
        }
    }
    
    //Carrega as reservas a partir de um arquivo de texto e atribui ao cliente correspondente
    public void loadReservaClienteData(Aviao aviao){
        Scanner input;
        Reserva r;
        Cliente c;
        ReservaHandler rh = new ReservaHandler();
        
        try {
            //abrindo o arquivo para leitura
            input = new Scanner(Paths.get(reservaFileName)); //pode gerar IOException
            
            long currentId = 0;
            
            try{
                currentId = Long.parseLong(input.nextLine());  //parseLong: pode gerar NumberFormatException 
                                                               //input.nextLine: pode gerar NoSuchElementException
            }
            catch(NumberFormatException | NoSuchElementException e){ //tratando as excecoes
                currentId = 0;
            }
            
            Reserva.setCurrentId(currentId);
            
            while(input.hasNext()){  //enquanto houver dados...
                r = rh.reservarByString(input.nextLine(), aviao, this); //faz a reserva no voo correspondente
            }
            
            if(input != null)  
                input.close(); //fechando arquivo
            
        } catch (IOException ex) {
            return;
        }
    }
    
}

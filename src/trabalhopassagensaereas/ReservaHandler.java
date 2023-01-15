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
public class ReservaHandler {
    private String messageError;
    
    
    /*  Reserva um assento no voo se este nao estiver ocupado   */
    public Reserva reservar(Cliente cliente,  Assento s, Voo voo, boolean reservarNaPrimClasse){
        
        if(voo.getAviao().assentoEhValido(s)){ //checando se o assento eh valido
                
                //checando se classe do assento eh compativel com a classe escolhida
                if( (reservarNaPrimClasse && voo.getAviao().assentoEhPrimClasse(s)) 
                    || (!reservarNaPrimClasse && !voo.getAviao().assentoEhPrimClasse(s))){

                    if(voo.reservarAssento(s)){  //checando se conseguiu reservar o assento no voo

                        Reserva r ;

                        if(reservarNaPrimClasse) //checa se eh pra reservar na primeira classe
                            r = new ReservaPrimClasse(s, voo, cliente);  //se sim, r recebe uma reserva da primeira classe
                        else
                            r = new ReservaClasseEconom(s, voo, cliente);    //senao, r recebe uma reserva da classe economica


                        if(cliente.addReserva(r)){  //adicionando a reserva na lista de reservas do cliente
                            return r;   //  retorna a reserva caso consiga adicionar na lista
                        }
                        else{   //senao, cancela o assento que tinha reservado
                            messageError = "houve um problema ao adicionar a reserva.";
                            voo.cancelaReservaAssento(s);
                        }
                    }
                    else{   //caso nao consegue reservar o assento, significa que ele ja esta ocupado
                        messageError = "o assento " + s.toString() +" ja esta ocupado.";
                    }
                }
                else{   //o assento nao se encaixa na classe escolhida
                    messageError = "o assento informado nao se encaixa na classe escolhida.";
                }
        }
        else{   //o assento informado nao eh valido
            messageError = "o assento " + s.toString() + " nao eh valido para este aviao.";
        }
        return null;
    }
    
    
    /*  Cancela a reserva de um assento */
    public boolean cancelarReserva(Reserva reserva){
        if(reserva == null){    //checando se a reserva existe
            messageError = "a reserva nao existe.";
            return false;
        }
        
        if(reserva.jaFoiComprada()){
            messageError = "a reserva ja foi comprada.";
            return false;
        }
        
        Voo voo = reserva.getVoo();
        Cliente cliente = reserva.getCliente(); 
        
        
        if(voo.cancelaReservaAssento(reserva.getAssento())){ //removendo a reserva de assento do voo
            return cliente.removeReserva(reserva);  //removendo a reserva da lista de reserva do cliente
        }
        
        messageError = "houve um problema ao cancelar.";
        return false;   //retorna false caso algo deh errado
    }
    
    
    /*  Remarca uma reserva */
    public Reserva remarcarReserva(Reserva reserva, Assento novoAssento){
        if(reserva == null){    // checando se a reserva existe
            messageError = "a reserva nao existe.";
            return null;
        }
        
        if(reserva.jaFoiComprada()){
            messageError = "a reserva ja foi comprada.";
            return null;
        }
        
        Voo voo = reserva.getVoo();
        Cliente cliente = reserva.getCliente();
        
        if(voo.getAviao().assentoEhValido(novoAssento)){   //checando se o assento eh valido
            if(!voo.assentoEstaOcupado(novoAssento)){    //checando se o assento nao estah ocupado

                if( voo.cancelaReservaAssento(reserva.getAssento())){   //cancelando a reserva do assento

                    if(voo.reservarAssento(novoAssento)){   //  reserva o novo assento
                        cliente.removeReserva(reserva);    // removendo a reserva da lista de reservas do cliente

                        Reserva nova;

                        if( !voo.getAviao().assentoEhPrimClasse(novoAssento) )  //checando se voo eh da classe econom.
                            nova = new ReservaClasseEconom(novoAssento, voo, cliente); //criando a nova reserva na classe econom.
                        else                                                //voo eh da prim. classe
                            nova = new ReservaPrimClasse(novoAssento, voo, cliente);   //criando a nova reserva na primeira classe

                        cliente.addReserva(nova);   //adicionando a nova reserva na lista de reserva do cliente
                        return nova;
                    }
                    else{
                        messageError = "nao foi possivel reservar o novo assento.";
                    }
                }
                else
                    messageError = "nao foi possivel cancelar a reserva.";

            }
            else{
                messageError = "o assento " + novoAssento.toString() + " ja esta ocupado.";
            }
        }
        else{
            messageError = "o assento " + novoAssento.toString() + " nao eh valido para este aviao.";
        }
        return null;
    }
    
    
    /*  Imprime um cartao de embarque ficticio  */
    public boolean printCartaoEmbarque(Reserva r){
        
        if(r == null){  //checando se a reserva existe
            messageError = "a reserva nao existe.";
            return false;
        }
        
        //imprimindo os dados da reserva
        System.out.println("\nReserva concluida!\nNúmero da Reserva: " + r.getId());
        System.out.println("Voo número: " + r.getVoo().getId() 
                + "    Destino: " + r.getVoo().getDestino());
        System.out.println("Data: " + r.getVoo().getData() + "      Horario do voo: " + r.getVoo().getHorario());
        System.out.println(r.getCliente().getNome());
        
        return true;
    }
    
    
    /*  Retorna a mensagem de erro  */
    public String getMessageError(){
        return messageError;
    }
    
    
    //Reserva um assento a partir de uma string tirada de um arquivo de texto
    public Reserva reservarByString(String s, Aviao aviao, GerenciadorClientes gc){
        Reserva r = null;
        long id;
        ArrayList<String> lista = UtilityMethods.getSeparatedString(s); //separando a string
        long lastId = Reserva.getLastAddedId(); //pegando o id da ultima reserva construida
        
        if(lista.size() == 5 ){
            
            try{
                Assento assento = new Assento(lista.get(3)); //gerando o assento
                
                //pegando o id do voo
                id = Long.parseLong(lista.get(2)); //pode gerar NumberFormatException
                Voo voo = aviao.getVooPorId(id); //procurando voo por id e atribuindo a variavel voo caso ache
                
                if(voo == null){
                    messageError = "o voo nao existe.";
                    return null;
                }
                
                //pegando o id do cliente
                id = Long.parseLong(lista.get(0)); //pode gerar NumberFormatException
                Cliente cliente = gc.getClientePorId(id); //procurando o cliente por id e atribuindo a variavel cliente
                
                if(cliente == null){
                    messageError = "nao foi encontrado um cliente para o id informado.";
                    return null;
                }
                
                //reservando o assento
                r = this.reservar(cliente, assento, voo, aviao.assentoEhPrimClasse(assento)); 
                
                Reserva.setCurrentId(lastId);
                
                if(r != null){
                    //pegando o id da reserva da lista de string
                    id = Long.parseLong(lista.get(1)); //pode gerar NumberFormatException
                    r.setId(id);
                    
                    if(lista.get(4).equals("true")){ //se o arquivo diz que a reserva ja foi comprada
                        r.comprar();  //compra a reserva
                    }
                    
                }
            }
            catch(NumberFormatException e){
                Reserva.setCurrentId(lastId);
                messageError = "houve um problema ao gerar a reserva.";
                r = null;
            }
        }
        else{
            messageError = "a string informada nao esta no formato adequado.";
        }
        
        return r;
    }

}

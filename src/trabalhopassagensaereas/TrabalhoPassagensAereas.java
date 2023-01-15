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
        
        /*Scanner input = new Scanner(System.in);
        
        int  optClasse;
        long idR;
        long numVoo;
        boolean continueLoop;
        Voo voo;
        Reserva re;
        Cliente cliente;
        String posicaoAssento;
        Assento assento;
        
        int option = -1;    //opcao do menu principal
        
        while(option != 4){ //condicao de saida do loop
            
            System.out.println("\n");   //  mostrando o menu principal
            System.out.println("+-------------------------+");
            System.out.println("|     MENU PRINCIPAL      |");
            System.out.println("+-------------------------+");
            System.out.println("| 1- Cadastrar cliente    |");
            System.out.println("| 2- Fazer Login cliente  |");
            System.out.println("| 3- Mostrar assentos     |");
            System.out.println("| 4- Sair                 |");
            System.out.println("+-------------------------+");

            System.out.println(" Informe a sua opcao:");
            option = input.nextInt();   //lendo a opcao do usuario
            input.nextLine();   //limpando o input
            
            switch(option){
                case 1:     //cadastrar cliente
                    
                    //lendo os dados do cliente
                    System.out.println("Informe o nome:");
                    String nome = input.nextLine(); 
                    System.out.println("Informe o telefone de contato:");
                    long telefone = input.nextLong();
                    System.out.println("Informe o numero do cartao:");
                    long numCartao = input.nextLong();
                    
                    Cliente c = new Cliente(nome, numCartao, telefone); //criando o novo cliente

                    if( gc.cadastrar(c) )   //tenta cadastrar
                        System.out.println("\nCliente cadastrado com sucesso! \nId == " + c.getId());
                    else{
                        UtilityMethods.printBox("Ocorreu um problema ao cadastar!!!");
                    }
                    break;
                    
                    
                case 2:     //Fazendo o login do cliente
                    
                    System.out.println("Informe o id do cliente:");
                    long id = input.nextLong();
                    
                    cliente = gc.getClientePorId(id);   //procurando um cliente com o id especificado
                    
                    if(cliente != null){    //se encontrou o cliente...
                        
                        int optionCliente = -1; //opcao para o menu do cliente
                        
                        System.out.println("\n\n BEM VINDO " + cliente.getNome().toUpperCase() + "!");
                        
                        while(optionCliente != 8){     //condicao de saida do loop 
                            
                            //mostrando o menu do cliente
                            System.out.println("+------------------------+");
                            System.out.println("|      MENU CLIENTE      |");
                            System.out.println("+------------------------+");
                            System.out.println("| 1- Consultar Reservas  |");
                            System.out.println("| 2- Mostrar Assentos    |");
                            System.out.println("| 3- Fazer Reserva       |");
                            System.out.println("| 4- Remarcar Reserva    |");
                            System.out.println("| 5- Cancelar Reserva    |");
                            System.out.println("| 6- Comprar Reserva     |");
                            System.out.println("| 7- Mostrar voos        |");
                            System.out.println("| 8- Sair                |");
                            System.out.println("+------------------------+");
                            
                            System.out.println(" Informe a sua opcao:");    
                            optionCliente = input.nextInt();    //lendo a opcao do usuario
                            
                            switch(optionCliente){
                                case 1: //consultar reservas
                                    
                                    if(cliente.possuiReservas()) //checando se a lista de reservas do cliente nao esta vazia
                                       cliente.showReservas();
                                    else{
                                        UtilityMethods.printBox("Voce nao possui reservas.");
                                    }
                                    
                                    break;
                                    
                                case 2: //Mostrar assentos disponiveis de um voo
                                    
                                    continueLoop = true;
                                    
                                    do{
                                        try{    
                                            System.out.println("Por favor, informe o numero do voo:");
                                            numVoo = input.nextLong();  //lendo o numero do voo
                                            
                                            voo = plane.getVooPorId(numVoo);    //procurando um voo com o numero igual ao numero lido
                                    
                                            if(voo != null) //se encontrou o voo
                                                voo.printMapaAssentos(); //mostra os assentos 
                                            else
                                                UtilityMethods.printBox("Desculpe, este voo não existe.");

                                            continueLoop = false;   //entrada bem sucedida: fim do loop
                                        }
                                        catch(InputMismatchException e){    //usuario nao digitou um numero para o id
                                            input.nextLine();   //descarta a entrada pro usuario tentar de novo
                                            UtilityMethods.printBox("Voce deve informar um numero!");
                                        }
                                    }while(continueLoop);
                                    
                                    
                                    break;
                                
                                case 3: //Fazer uma reserva
                                    System.out.println("Por favor, informe o numero do seu voo:");
                                    numVoo = input.nextLong();  //lendo o numero do voo
                                    
                                    voo =  plane.getVooPorId(numVoo);   //procurando um voo com este id
                                    
                                    if(voo == null){    //checando se nao voo existe
                                        UtilityMethods.printBox("Desculpe, este voo não existe.");
                                        break;
                                    }
                                    
                                    if(voo.estaLotado()){   //checando se o voo ja esta cheio
                                        UtilityMethods.printBox("Desculpe, o voo esta lotado");
                                        break;
                                    }
                                    
                                    do{     //escolhendo a classe da reserva (primeira classe ou classe economica)
                                        System.out.println("\nVoce deseja qual tipo de assento?");
                                        System.out.println("1- Primeira Classe        2- Classe Economica");
                                        optClasse = input.nextInt();
                                    }while(optClasse != 1 && optClasse != 2);   //enquanto nao digitar 1 ou 2
                                    
                                    
                                    if(optClasse == 1){ //checando se a classe escolhida foi a primeira classe
                                        if(voo.primClasseEstaCheia()){  //se a primeira classe esta cheia
                                            UtilityMethods.printBox("Desculpe, a primeira classe esta cheia");
                                            break;
                                        }
                                    }
                                    else if(voo.classeEconomEstaCheia()){   //se a classe economica esta cheia
                                        UtilityMethods.printBox("Desculpe, a classe economica esta cheia.");
                                        break;
                                    }
                                    
                                    
                                    voo.printMapaAssentos();   //mostrando os assentos

                                    
                                    System.out.println("Informe o numero da fila e a letra do assento:");
                                    posicaoAssento = input.next();
                                    assento = new Assento(posicaoAssento);
                                    
                                    
                                    Reserva r = rh.reservar(cliente, assento, voo, optClasse == 1);   //tentando fazer a reserva
                                    
                                    if(r != null){  //se conseguiu reservar...
                                        rh.printCartaoEmbarque(r); //...imprime o cartao de embarque
                                    }
                                    else    //senao, mostra uma mensagem de erro
                                        UtilityMethods.printBox("Desculpe, " + rh.getMessageError());  
                                    break;
                               
                                case 4:  //Remarcar uma reserva
                                    
                                    if(!cliente.possuiReservas()){  //o cliente nao possui reservas
                                        UtilityMethods.printBox("Voce nao possui reservas!");
                                        break;
                                    }
                                    
                                    System.out.println("Informe o id da reserva a ser remarcada:");
                                    idR = input.nextLong();
                                    
                                    re = cliente.getReservaPorId(idR);  //procurando uma reserva com aquele id nas reservas do cliente
                                    
                                    if(re != null){ //se achou alguma reserva com o id especificado
                                        
                                        System.out.println(re.toString());
                                        System.out.println("Informe o numero da fila e a letra do assento: ");
                                        posicaoAssento = input.next();  //lendo a posicao do assento
                                        assento = new Assento(posicaoAssento);  //criando o assento
                                        
                                        re = rh.remarcarReserva(re, assento); //tentando remarcar a reserva 
                                        
                                        if(re != null){ //se conseguiu remarcar...
                                            rh.printCartaoEmbarque(re); //...imprime o cartao de embarque
                                        }
                                        else    //senao, mostra uma mensagem de erro
                                            UtilityMethods.printBox("Desculpe, " + rh.getMessageError());
                                    }
                                    else{   //se nao achou a reserva com o id especificado
                                        UtilityMethods.printBox("Reserva nao encontrada!");
                                    }
                                    break;
                               
                                case 5: //Cancela uma reserva
                                    
                                    if(!cliente.possuiReservas()){  //o cliente nao possui reservas
                                        UtilityMethods.printBox("Voce nao possui reservas!");
                                        break;
                                    }
                                    
                                    System.out.println("Informe o id da reserva a ser cancelada:");
                                    idR = input.nextLong();
                                    
                                    re = cliente.getReservaPorId(idR);  //procurando uma reserva com aquele id nas reservas do cliente
                                    
                                    if(re != null){ //se achou a reserva com o id especificado
                                        
                                        if( rh.cancelarReserva(re)){   //cancela a reserva
                                            UtilityMethods.printBox("Reserva cancelada!");
                                            System.out.println("\n " + re.toString());
                                        }
                                        else    //se nao conseguiu cancelar a reserva
                                            UtilityMethods.printBox("Desculpe, " + rh.getMessageError());
                                    }
                                    else{   //nao encontrou a reserva com o id especificado
                                        UtilityMethods.printBox("Reserva nao encontrada!");
                                    }
                                    break;
                                
                                //comprando uma passagem
                                case 6:
                                    if(!cliente.possuiReservas())
                                        UtilityMethods.printBox("Voce nao possui reservas.");
                                    else{
                                        System.out.println("Informe o id da reserva a ser comprada:");
                                        idR = input.nextLong();
                                        
                                        re = cliente.getReservaPorId(idR);
                                        
                                        if(re != null){
                                            if(!re.jaFoiComprada()){
                                                System.out.println( String.format("\n\nValor: %.2f", re.getPreco()));
                                                
                                                if(re.comprar())
                                                     UtilityMethods.printBox("Compra efetuada");
                                                else
                                                     UtilityMethods.printBox("Houve um problema ao efetuar a compra.");
                                            }
                                            else
                                                UtilityMethods.printBox("A reserva informada ja foi comprada.");
                                        }
                                        else
                                            UtilityMethods.printBox("Reserva nao encontrada.");
                                    }
                                    break;
                                    
                                case 7: //Imprime todos os voos 
                                    System.out.println("\n\n+-----------------------------------+");
                                    System.out.println(String.format("| %-10s | %-20s |", "Numero", "Destino"));
                                    System.out.println("+-----------------------------------+");
                                    for(Voo v : plane.getListaVoos()){
                                        System.out.println(String.format("| %-10d | %-20s |", v.getId(), v.getDestino()));
                                    }
                                    System.out.println("+-----------------------------------+\n");
                                    break;
                               
                                    
                                case 8:    //  Saindo do menu do cliente   
                                    break;
                                    
                                default:
                                    UtilityMethods.printBox("Opcao invalida!!!");
                            }
                        }
                    }
                    else{   //nao encontrou nenhum cliente com o id especificado
                        UtilityMethods.printBox("Cliente nao encontrado!");
                    }

                    break;
                    
                 
                case 3: //Mostra os assentos disponiveis de um voo 
                    
                    continueLoop = true;
                                    
                    do{
                        try{  
                            System.out.println("Por favor, informe o numero do voo:");
                            numVoo = input.nextLong();

                            voo = plane.getVooPorId(numVoo);    //procurando um voo com id igual ao id especificado

                            if(voo != null) //se encontrou...
                                voo.printMapaAssentos();    //mostra os assentos
                            else
                                UtilityMethods.printBox("Desculpe, este voo não existe.");

                            continueLoop = false;   //entrada bem sucedida: fim do loop
                        }
                        catch(InputMismatchException e){    //usuario nao digitou um numero
                            input.nextLine();   //descarta a entrada pro usuario tentar de novo
                            UtilityMethods.printBox("Voce deve informar um numero!");
                        }
                    }while(continueLoop);
                    
                    break;
                
                //Fim do programa
                case 4: 
                    try {
                        gc.saveClienteData();
                    } catch (FormatterClosedException ex) {
                        UtilityMethods.printBox("Houve um problema ao tentar salvar um dado.");
                    } catch (SecurityException ex) {
                        UtilityMethods.printBox("Acesso negado.");
                    } catch (FileNotFoundException ex) {
                        UtilityMethods.printBox("O arquivo nao foi encontrado.");
                    }
            
                    try {
                        gc.saveReservaClienteData();
                    } catch (SecurityException ex) {
                        UtilityMethods.printBox("Houve um problema ao tentar salvar um dado.");
                    } catch (FileNotFoundException ex) {
                        UtilityMethods.printBox("Acesso negado.");
                    } catch (FormatterClosedException ex) {
                        UtilityMethods.printBox("O arquivo para salvar as reservas dos clientes nao foi encontrado.");
                    }
                    
                    UtilityMethods.printBox("Programa Encerrado.");
                    break;
                    
                default:    //opcao invalida
                    UtilityMethods.printBox("Opcao Invalida!!!");
            }
        }*/
    }
    
}

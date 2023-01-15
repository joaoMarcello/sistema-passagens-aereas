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
 * @see getStringFormated#Cliente
 */
public class UtilityMethods {
    
    /*Recebe uma string separada por ';' e retorna um arraylist de
     *String que contem as palavras separadas.
    */
    public static ArrayList<String> getSeparatedString(String s){
        ArrayList<String> lista = new ArrayList<String>();
        
        while(true){
            boolean find = false;
            
            for(int i = 0; i < s.length(); i++){ //percorrendo a string caractere por caractere
                if(s.charAt(i) == ';'){   //se encontrou algum ponto e virgula
                    lista.add(s.substring(0, i));  //adiciona a substring no array
                    s = s.substring(i + 1);  //corta a string ate o ponto onde achou o ';'
                    find = true; //sinaliza que achou
                    break;
                }
            }
            
            if(!find){  //se nao achou nenhum ponto e virgula...
                lista.add(s);
                break;  //sai do loop;
            }
        }
        
        return lista;
    }
    
    /**  Imprime uma string numa caixa.   */
    
    public static void printBox(String s){
        String text = "";

        for(int i = 0; i < s.length() + 6; i++)
            text = text.concat(i == 0 || i == s.length() + 6 - 1 ? "+" : "-");
        
        System.out.print("\n\n" + text + "\n|  " + s + "  |" + "\n" + text + "\n\n");
    }
}

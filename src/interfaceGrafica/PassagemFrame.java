/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import javax.swing.*;
import java.awt.*;
import trabalhopassagensaereas.*;

/**
 *
 * @author JM
 */
public class PassagemFrame extends JFrame {
     JTextField nomeJTextField;
     JTextField vooJTextField;
     JTextField dataJTextField;
     JTextField assentoJTextField;
     JTextField origemJTextField;
     JTextField destinoJTextField;
     JTextField horaEmbarqueJTextField;
     JTextField idJTextField;
     
     Passagem passagem;
     
     GridBagLayout l;
     GridBagConstraints com;
     
     public PassagemFrame(Passagem p){
         super("Passagem");
         l = new GridBagLayout();
         com = new GridBagConstraints();
         setLayout(l);
         this.passagem = p;
         
         nomeJTextField = new JTextField(30);
         nomeJTextField.setText(p.getReserva().getCliente().getNome().toUpperCase());
         nomeJTextField.setEnabled(false);
         nomeJTextField.setDisabledTextColor(Color.BLACK);
         
         vooJTextField = new JTextField(10);
         vooJTextField.setText( String.format( "%d", p.getReserva().getVoo().getId()));
         vooJTextField.setEnabled(false);
         vooJTextField.setDisabledTextColor(Color.BLACK);
         
         dataJTextField = new JTextField(10);
         dataJTextField.setText(p.getReserva().getVoo().getData());
         dataJTextField.setEnabled(false);
         dataJTextField.setDisabledTextColor(Color.BLACK);
         
         assentoJTextField = new JTextField(5);
         assentoJTextField.setText(p.getReserva().getAssento().toString());
         assentoJTextField.setEnabled(false);
         assentoJTextField.setDisabledTextColor(Color.BLACK);
         
         origemJTextField = new JTextField(15);
         origemJTextField.setText(p.getReserva().getVoo().getOrigem());
         origemJTextField.setEnabled(false);
         origemJTextField.setDisabledTextColor(Color.BLACK);
         
         destinoJTextField = new JTextField(15);
         destinoJTextField.setText(p.getReserva().getVoo().getDestino());
         destinoJTextField.setEnabled(false);
         destinoJTextField.setDisabledTextColor(Color.BLACK);
         
         horaEmbarqueJTextField = new JTextField(10);
         horaEmbarqueJTextField.setText(p.getReserva().getVoo().getHorario());
         horaEmbarqueJTextField.setEnabled(false);
         horaEmbarqueJTextField.setDisabledTextColor(Color.BLACK);
         
         idJTextField = new JTextField(10);
         idJTextField.setEnabled(false);
         idJTextField.setDisabledTextColor(Color.BLACK);
         
         JLabel classeJLabel = new JLabel( 
            passagem.getReserva() instanceof ReservaPrimClasse ? "PRIMEIRA CLASSE" : "ECONOMICA" );
         JLabel companyJLabel = new JLabel("VOA BRASIL AIR LINE");
         JLabel nomeJLabel = new JLabel("NOME DO PASSAGEIRO");
         JLabel vooJLabel = new JLabel("VOO");
         JLabel dataJLabel = new JLabel("DATA");
         JLabel assentoJLabel = new JLabel("ASSENTO");
         JLabel deJLabel = new JLabel("DE: ");
         JLabel paraJLabel = new JLabel("PARA: ");
         JLabel horaEmbarqueJLabel = new JLabel("HORA DO EMBARQUE");
         
         addComponent(Box.createHorizontalStrut(20), 0,0, 1, 7);
         addComponent(companyJLabel, 0,1,2,1);
         addComponent(classeJLabel, 0,8,1,1);
         
         addComponent(nomeJLabel, 1,1,2,1);
         addComponent(nomeJTextField, 2,1,2,1);
         
         addComponent(Box.createHorizontalStrut(20), 0,3,1,7);
         
         addComponent(vooJLabel, 1,4,1,1);
         addComponent(vooJTextField, 2,4,1,1);
         
         addComponent(Box.createHorizontalStrut(20), 0,5,1,7);
         
         addComponent(dataJLabel, 1,6,1,1);
         addComponent(dataJTextField, 2,6,1,1);
         
         addComponent(Box.createHorizontalStrut(20), 0,7,1,7);
         
         addComponent(assentoJLabel, 1,8,1,1);
         addComponent(assentoJTextField, 2,8,1,1);
         
         addComponent(Box.createHorizontalStrut(20), 0,9,1,7);
         
         addComponent(Box.createVerticalStrut(20), 3,0,2,1);
         
         addComponent(deJLabel, 4,1,1,1);
         addComponent(origemJTextField, 4,2,1,1);
         
         addComponent(paraJLabel, 5,1,1,1);
         addComponent(destinoJTextField, 5,2,1,1);
         
         addComponent(horaEmbarqueJLabel, 4,6,1,1);
         addComponent(horaEmbarqueJTextField, 5,6,1,1);
         
         addComponent(Box.createVerticalStrut(20), 6,0,3,1);
     }
     
     private void addComponent(Component c, int linha, int coluna, int comprimento, int altura){
        com.gridx = coluna;
        com.gridy = linha;
        com.gridwidth = comprimento;
        com.gridheight = altura;
        l.setConstraints(c, com);
        add(c);
    }
     
    public Dimension getPreferredSize(){
        return new Dimension(800,500);
    }
}

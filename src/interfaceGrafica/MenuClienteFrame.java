/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import trabalhopassagensaereas.*;
/**
 *
 * @author JM
 */

public class MenuClienteFrame extends JFrame implements ActionListener {
    private JButton verReservasButton;
    private JButton fazerReservaButton;
    private JButton voltarMenuPrincipalButton;
    private JButton mostrarPassagensButton;
    
    private Cliente cliente;
    private Aviao aviao;
    
    private FazerReservaFrame fazerReservaFrame;
    private ReservaMenuFrame reservaMenuFrame;
    private MostrarPassagensFrame mostrarPassagensFrame;
    
    public MenuClienteFrame(Cliente cliente, Aviao aviao){
        super("Menu Cliente");
        this.cliente = cliente;
        this.aviao = aviao;
        
        Font fontPadrao = FrameMethods.getFontPadraoToButtons();
        
        verReservasButton = new JButton("Ver Reservas");
        verReservasButton.addActionListener(this);
        verReservasButton.setFont(fontPadrao);
        verReservasButton.setToolTipText("Clique aqui para ver suas reservas");
        
        fazerReservaButton = new JButton("Fazer Reserva");
        fazerReservaButton.addActionListener(this);
        fazerReservaButton.setFont(fontPadrao);
        fazerReservaButton.setToolTipText("Clique aqui para fazer uma reserva");
        
        mostrarPassagensButton = new JButton("Mostrar Passagens");
        mostrarPassagensButton.addActionListener(this);
        mostrarPassagensButton.setFont(fontPadrao);
        mostrarPassagensButton.setToolTipText("Clique aqui para ver todas as passagens compradas");
        
        voltarMenuPrincipalButton = new JButton("Voltar pro Menu Principal");
        voltarMenuPrincipalButton.addActionListener(this);
        voltarMenuPrincipalButton.setFont(fontPadrao);
        voltarMenuPrincipalButton.setToolTipText("Clique aqui para voltar ao menu principal");
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(FrameMethods.getColorPadrao());
        
        JLabel nomeLabel = new JLabel( String.format("Bem vindo, %s", cliente.getNome().toUpperCase() ));
        nomeLabel.setForeground(Color.WHITE);
        nomeLabel.setFont(new Font("MV Boli", 0, 25));

        
        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nomeLabel);
        vBox.add(Box.createVerticalStrut(10));
        
        topPanel.add(vBox);
        
        add(topPanel, BorderLayout.NORTH);
        
        
        
        JPanel downPanel = new JPanel();
        GridBagLayout l1 = new GridBagLayout();
        GridBagConstraints com = new GridBagConstraints();
        downPanel.setLayout(l1);
        
        com.weightx = 10;
        addComponent(downPanel, Box.createVerticalStrut(20), l1, com, 0,0,1,1);
        addComponent(downPanel, Box.createVerticalStrut(20), l1, com, 2,1,1,1);
        addComponent(downPanel, Box.createVerticalStrut(20), l1, com, 4,1,1,1);
        
        addComponent(downPanel, Box.createHorizontalStrut(20), l1, com, 1,0,1,1);
        addComponent(downPanel, Box.createHorizontalStrut(20), l1, com, 1,2,1,1);
        addComponent(downPanel, Box.createHorizontalStrut(20), l1, com, 1,4,1,1);
        
        com.anchor = GridBagConstraints.WEST;
        com.fill = GridBagConstraints.HORIZONTAL;
        com.weightx = 1;
        addComponent(downPanel, verReservasButton, l1, com, 1,1,1,1);
        addComponent(downPanel, this.fazerReservaButton, l1, com, 3,1,1,1);
        addComponent(downPanel, this.mostrarPassagensButton, l1, com, 1,3,1,1);
        addComponent(downPanel, this.voltarMenuPrincipalButton, l1, com, 3,3,1,1);
        
        add(downPanel, BorderLayout.SOUTH);
        
    }
    
    private void addComponent(Container container, Component c, GridBagLayout l, GridBagConstraints com, 
    int linha, int coluna, int width, int height ){
        com.gridx = coluna;
        com.gridy = linha;
        com.gridwidth = width;
        com.gridheight = height;
        l.setConstraints(c, com);
        container.add(c);
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800,500);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.voltarMenuPrincipalButton){
            this.dispose();
        }
        else if(ae.getSource() == this.fazerReservaButton){
            fazerReservaFrame = new FazerReservaFrame(cliente, aviao);
            FrameMethods.setDefaultFrameConfig(this.getLocation(), fazerReservaFrame);
            
            fazerReservaFrame.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosed(WindowEvent ev){
                        MenuClienteFrame.this.setEnabled(true);
                        MenuClienteFrame.this.requestFocus();
                    }
                }
            );
            
            setEnabled(false);
        }
        else if(ae.getSource() == this.verReservasButton){
            
            if(!cliente.possuiReservas()){
                JOptionPane.showMessageDialog(this, "Voce nao possui reservas!");
                return;
            }
            
            reservaMenuFrame = new ReservaMenuFrame(cliente);
            FrameMethods.setDefaultFrameConfig(this.getLocation(), reservaMenuFrame);
            reservaMenuFrame.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosed(WindowEvent ev){
                        MenuClienteFrame.this.setEnabled(true);
                        MenuClienteFrame.this.requestFocus();
                    }
                }
            );
            setEnabled(false);
        }
        else if(ae.getSource() == mostrarPassagensButton){
            if(cliente.getListaPassagens().isEmpty()){
                JOptionPane.showMessageDialog(this, "Voce nao realizou nenhuma compra de passagens!");
                return;
            }
            
            mostrarPassagensFrame = new MostrarPassagensFrame(cliente);
            FrameMethods.setDefaultFrameConfig(getLocation(), mostrarPassagensFrame);
            mostrarPassagensFrame.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosed(WindowEvent ev){
                        MenuClienteFrame.this.setEnabled(true);
                        MenuClienteFrame.this.requestFocus();
                    }
                }
            );
            setEnabled(false);
        }
    }
}

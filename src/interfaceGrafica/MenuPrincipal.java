/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.FormatterClosedException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import trabalhopassagensaereas.*;

/**
 *
 * @author JM
 */
public class MenuPrincipal extends JFrame implements ActionListener {
    private JButton cadastrarButton;
    private JButton loginButton;
    private JButton sairButton;
    private static GerenciadorClientes gc;
    
    private CadastroClienteFrame cadastroFrame;
    private LoginClienteFrame loginFrame;
    
    public MenuPrincipal(GerenciadorClientes gc){
        super("Menu Principal");
        this.gc = gc;
        
        Font buttonFont = FrameMethods.getFontPadraoToButtons();
        
        cadastrarButton = new JButton("Cadastrar Cliente");
        cadastrarButton.addActionListener(this);
        cadastrarButton.setFont(buttonFont);
        cadastrarButton.setToolTipText("Clique aqui para realizar o cadastro de um cliente");
        
        loginButton = new JButton("Logar Cliente");
        loginButton.addActionListener(this);
        loginButton.setFont(buttonFont);
        loginButton.setToolTipText("Clique aqui para realizar o login de um cliente");
        
        sairButton = new JButton("Sair");
        sairButton.setFont(buttonFont);
        sairButton.setToolTipText("Clique aqui para sair do programa");
        sairButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    int opt = JOptionPane.showConfirmDialog(MenuPrincipal.this, 
                            "Deseja realmente sair?", "Confirmar saida", JOptionPane.YES_NO_OPTION);
                    if(opt == JOptionPane.YES_OPTION){
                        try {
                            gc.saveClienteData();
                        } catch (FormatterClosedException ex) {
                            JOptionPane.showMessageDialog(MenuPrincipal.this, "Houve um problema ao gravar os dados.");
                        } catch (SecurityException ex) {
                            JOptionPane.showMessageDialog(MenuPrincipal.this, "Acesso negado.");
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(MenuPrincipal.this, "O arquivo nao foi encontrado.");
                        }
                        
                        try {
                            gc.saveReservaClienteData();
                        } catch (FormatterClosedException ex) {
                            JOptionPane.showMessageDialog(MenuPrincipal.this, "Houve um problema ao gravar os dados.");
                        } catch (SecurityException ex) {
                            JOptionPane.showMessageDialog(MenuPrincipal.this, "Acesso negado.");
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(MenuPrincipal.this, "O arquivo nao foi encontrado.");
                        }
                        
                        MenuPrincipal.this.dispose();
                        System.exit(0);
                    }
                }
            }
        );
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(FrameMethods.getColorPadrao());
        Box vb = Box.createVerticalBox();
        JLabel companyLabel = new JLabel("VOA BRASIL");
        companyLabel.setFont(FrameMethods.getFontPadraoToTitles());
        companyLabel.setForeground(Color.WHITE);
        
        JLabel company2Label = new JLabel("Air line");
        company2Label.setFont(FrameMethods.getFontPadraoToSubtitles());
        company2Label.setForeground(Color.WHITE);
        
        vb.add(Box.createVerticalStrut(20));
        vb.add(companyLabel);
        vb.add(company2Label);
        vb.add(Box.createVerticalStrut(20));
        
        topPanel.add(vb);
        
        add(topPanel, BorderLayout.NORTH);
        
        
        JPanel downPanel = new JPanel();
        Box hb = Box.createHorizontalBox();
        
        hb.add(Box.createHorizontalStrut(30));
        hb.add(this.cadastrarButton);
        hb.add(Box.createHorizontalStrut(30));
        hb.add(this.loginButton);
        hb.add(Box.createHorizontalStrut(30));
        hb.add(this.sairButton);
        hb.add(Box.createHorizontalStrut(20));
        
        downPanel.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        downPanel.add(Box.createVerticalStrut(100), BorderLayout.SOUTH);
        downPanel.add(hb);
        
        add(downPanel, BorderLayout.SOUTH);
        
        
    }
    
    private class WindowHandler extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent we) {
            if(we.getSource() == cadastroFrame){
                setEnabled(true);
                cadastroFrame.dispose();
                requestFocus();
            }
            
        }

        @Override
        public void windowClosed(WindowEvent we) {
            if(we.getSource() == cadastroFrame){
                setEnabled(true);
                cadastroFrame.dispose();
                requestFocus();
            }
            else if(we.getSource() == loginFrame){
                setEnabled(true);
                requestFocus();
            }
            
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == cadastrarButton){
            cadastroFrame = new CadastroClienteFrame(gc);
            FrameMethods.setDefaultFrameConfig(this.getLocation(), cadastroFrame);
            cadastroFrame.addWindowListener(new WindowHandler());
            this.setEnabled(false);
        }
        else if(ae.getSource() == loginButton){
            if(gc.getListaClientes().isEmpty()){
                JOptionPane.showMessageDialog(this, "Nenhum cliente cadastrado!");
                return;
            }
            
            loginFrame = new LoginClienteFrame(gc);
            FrameMethods.setDefaultFrameConfig(getLocation(), loginFrame);
            loginFrame.addWindowListener(new WindowHandler());
            this.setEnabled(false);
        }
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800, 500);
    }
    
    public static void saveAllData2(){
        try {
            gc.saveClienteData();
        } catch (FormatterClosedException ex) {

        } catch (SecurityException ex) {

        } catch (FileNotFoundException ex) {

        }

        try {
            gc.saveReservaClienteData();
        } catch (FormatterClosedException ex) {

        } catch (SecurityException ex) {

        } catch (FileNotFoundException ex) {

        }
        
        Aviao plane = gc.getAviao();
        gc = new GerenciadorClientes(plane);
    }


}

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import trabalhopassagensaereas.*;

/**
 *
 * @author JM
 */
public class FazerReservaFrame extends JFrame implements ActionListener, ItemListener{
    JComboBox vooComboBox;
    
    JRadioButton primClasseRadioButton;
    JRadioButton classeEconomRadioButton;
    
    JTextField assentoTextField;
    
    JTextField vooTextField;
    JTextField destinoTextField;
    JTextField horarioTextField;
    JTextField dataTextField;
    
    JButton mostrarAssentosButton;
    JButton confirmButton;
    JButton cancelButton;
    
    Cliente cliente;
    Aviao aviao;
    ReservaHandler rh;
    
    MapaAssentosFrame mapaFrame;
    
    public FazerReservaFrame(Cliente cliente, Aviao aviao){
        super("Reservar Assento");
        this.cliente = cliente;
        this.aviao = aviao;
        rh = new ReservaHandler();
        
        Font fontPadrao = FrameMethods.getFontPadraoToButtons();
        
        JLabel vooLabel = new JLabel("Voo: ");
        JLabel classeLabel = new JLabel("Classe: ");
        JLabel assentoLabel = new JLabel("Assento: ");
        JLabel numVooLabel = new JLabel("Numero do Voo: ");
        JLabel destinoLabel = new JLabel("Destino: ");
        JLabel horarioLabel = new JLabel("Horario: ");
        JLabel dataLabel = new JLabel("Data: ");
        
        assentoTextField = new JTextField(5);
        
        vooTextField = new JTextField(15);
        vooTextField.setEnabled(false);
        vooTextField.setDisabledTextColor(Color.BLACK);
        
        destinoTextField = new JTextField(15);
        destinoTextField.setEnabled(false);
        destinoTextField.setDisabledTextColor(Color.BLACK);
        
        horarioTextField = new JTextField(15);
        horarioTextField.setEnabled(false);
        horarioTextField.setDisabledTextColor(Color.BLACK);
        
        dataTextField = new JTextField(15);
        dataTextField.setEnabled(false);
        dataTextField.setDisabledTextColor(Color.BLACK);
        
        
        mostrarAssentosButton = new JButton("Mostrar Assentos"); 
        mostrarAssentosButton.addActionListener(this);
        mostrarAssentosButton.setFont(fontPadrao);
        mostrarAssentosButton.setToolTipText("Clique para ver o mapa de assentos");
                
        confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(this);
        confirmButton.setFont(fontPadrao);
        confirmButton.setToolTipText("Clique para concluir a reserva");
        
        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(this);
        cancelButton.setFont(fontPadrao);
        cancelButton.setToolTipText("Clique para cancelar");
        
        String[] vooString = new String[aviao.getListaVoos().size()];
        
        for(int i = 0; i < vooString.length; i++ ){
            vooString[i] = String.format("%d  %s", 
                aviao.getListaVoos().get(i).getId(), aviao.getListaVoos().get(i).getDestino());
        }
        
        vooComboBox = new JComboBox(vooString);
        vooComboBox.setSelectedIndex(0);
        vooComboBox.addItemListener(this);
        
        ButtonGroup group = new ButtonGroup();
        
        primClasseRadioButton = new JRadioButton("Prim. Classe", true);
        group.add(primClasseRadioButton);
        
        classeEconomRadioButton = new JRadioButton("Classe Econom.", false);
        group.add(classeEconomRadioButton);
        
        
        vooComboBox.setSelectedIndex(0);
        Voo voo = aviao.getVooPorIndice(0);
        vooTextField.setText(String.format("%s",voo.getId()));
        destinoTextField.setText(voo.getDestino());
        horarioTextField.setText(voo.getHorario());
        dataTextField.setText(voo.getData());
        
        JLabel vooJLabel = new JLabel("Voo:    ");
        JLabel classeJLabel = new JLabel("Classe:    ");
        JLabel assentoJLabel = new JLabel("Assento:    ");
        JLabel numVooJLabel = new JLabel("Numero do Voo: ");
        JLabel destinoJLabel = new JLabel("Destino: ");
        JLabel horarioJLabel = new JLabel("Horario: ");
        JLabel dataJLabel = new JLabel("Data: ");
        
        
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(FrameMethods.getColorPadrao());
        
        JLabel reservarJLabel = new JLabel("FACA UMA RESERVA");
        reservarJLabel.setFont(FrameMethods.getFontPadraoToTitles());
        reservarJLabel.setForeground(Color.WHITE);
        
        JLabel instrucaoJLabel = new JLabel("Escolha o voo, classe e assento. Depois clique no botao confirmar.");
        instrucaoJLabel.setFont(FrameMethods.getFontPadraoToSubtitles());
        instrucaoJLabel.setForeground(Color.WHITE);
        
        Box vb = Box.createVerticalBox();
        vb.add(Box.createVerticalStrut(20));
        vb.add(reservarJLabel);
        vb.add(instrucaoJLabel);
        vb.add(Box.createVerticalStrut(10));
        
        topPanel.add(vb);
        add(topPanel, BorderLayout.NORTH);
        
        
        
        JPanel leftPanel = new JPanel();
        GridBagLayout l1 = new GridBagLayout();
        GridBagConstraints com = new GridBagConstraints();
        leftPanel.setLayout(l1);
        
        addComponent(leftPanel, Box.createHorizontalStrut(20), l1, com, 0,0,1,5);
        com.weighty = 10;
        addComponent(leftPanel, Box.createVerticalStrut(20), l1, com, 0,1,5,1);
        addComponent(leftPanel, Box.createVerticalStrut(20), l1, com, 5,1,1,1);
        
        com.anchor  = GridBagConstraints.WEST;
        com.weighty = 5;
        addComponent(leftPanel, vooJLabel, l1, com, 1,1,1,1);
        addComponent(leftPanel, classeJLabel, l1, com, 2,1,1,1);
        addComponent(leftPanel, assentoJLabel, l1, com, 3,1,1,1);
        
        com.anchor  = GridBagConstraints.WEST;
        addComponent(leftPanel, vooComboBox, l1, com, 1,2,2,1);
        addComponent(leftPanel, this.primClasseRadioButton, l1, com, 2,2,1,1);
        addComponent(leftPanel, this.classeEconomRadioButton, l1, com, 2,3,1,1);
        addComponent(leftPanel, assentoTextField, l1, com, 3,2,1,1);
        
        addComponent(leftPanel, this.mostrarAssentosButton, l1, com, 4,2,2,1);
        
        add(leftPanel, BorderLayout.WEST);
        
        
        
        JPanel rightPanel = new JPanel();
        GridBagLayout l2 = new GridBagLayout();
        rightPanel.setLayout(l2);
        
        addComponent(rightPanel, Box.createVerticalStrut(20), l2, com, 0,0,4,1);
        addComponent(rightPanel, Box.createVerticalStrut(20), l2, com, 5,0,1,1);
        addComponent(rightPanel, Box.createVerticalStrut(20), l2, com, 7,0,1,1);
        addComponent(rightPanel, Box.createHorizontalStrut(20), l2, com, 1,3,1,8);
        addComponent(rightPanel, Box.createHorizontalStrut(20), l2, com, 6,1,2,1);
        
        addComponent(rightPanel, numVooJLabel, l2, com, 1,0,1,1);
        addComponent(rightPanel, destinoLabel, l2, com, 2,0,1,1);
        addComponent(rightPanel, horarioJLabel, l2, com, 3,0,1,1);
        addComponent(rightPanel, dataJLabel, l2, com, 4,0,1,1);
        
        addComponent(rightPanel, vooTextField, l2, com, 1,1,2,1);
        addComponent(rightPanel, destinoTextField, l2, com, 2,1,2,1);
        addComponent(rightPanel, horarioTextField, l2, com, 3,1,2,1);
        addComponent(rightPanel, dataTextField, l2, com, 4,1,2,1);
        
        addComponent(rightPanel, this.confirmButton, l2, com, 6,0,1,1);
        
        com.anchor = GridBagConstraints.EAST;
        addComponent(rightPanel, this.cancelButton, l2, com, 6,2,1,1);
        
        add(rightPanel, BorderLayout.EAST);
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
    public void actionPerformed(ActionEvent ev){
        if(ev.getSource() == cancelButton){
            this.dispose();
        }
        else if(ev.getSource() == confirmButton){
            if(assentoTextField.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Preencha o campo assento!");
            }
            else{
                Assento s = new Assento(assentoTextField.getText());
                
                if(s.getNumFila() < 0){
                    JOptionPane.showMessageDialog(this, "Assento invalido!");
                    return;
                }
                
                Reserva r = rh.reservar(cliente, s, aviao.getVooPorIndice(vooComboBox.getSelectedIndex()), 
                    primClasseRadioButton.isSelected());
                
                if(r != null){
                    JOptionPane.showMessageDialog(this, "Reserva concluida!\nId : " + r.getId());
                    dispose();
                }
                else{
                   JOptionPane.showMessageDialog(this, "Desculpe, " + rh.getMessageError()); 
                }
                
            }
        }
        else if(ev.getSource() == this.mostrarAssentosButton){
            mapaFrame = new MapaAssentosFrame(aviao.getVooPorIndice(vooComboBox.getSelectedIndex()) );
            FrameMethods.setDefaultFrameConfig(this.getLocation(), mapaFrame);
            mapaFrame.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent ev){
                        mapaFrame.setAssento("");
                        mapaFrame.dispose();
                    }
                    
                    @Override
                    public void windowClosed(WindowEvent ev){
                        FazerReservaFrame.this.assentoTextField.setText( mapaFrame.getAssento() );
                        FazerReservaFrame.this.setEnabled(true);
                        FazerReservaFrame.this.requestFocus();
                        mapaFrame.dispose();
                    }
                }
            );
            
            this.setEnabled(false);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ev) {
        if(ev.getSource() == this.vooComboBox){
            Voo voo = aviao.getVooPorIndice( vooComboBox.getSelectedIndex() );
            vooTextField.setText(String.format("%s",voo.getId()));
            destinoTextField.setText(voo.getDestino());
            horarioTextField.setText(voo.getHorario());
            dataTextField.setText(voo.getData());
        }
    }
    
    
    public Dimension getPreferredSize(){
        return new Dimension(800,500);
    }
}

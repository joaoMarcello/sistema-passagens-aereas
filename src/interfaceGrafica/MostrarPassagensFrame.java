/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionEvent;
import trabalhopassagensaereas.*;

/**
 *
 * @author JM
 */
public class MostrarPassagensFrame extends JFrame implements ActionListener {
    private JList listaPassagens;
    private JButton mostrarDetalhesButton;
    
    private GridBagLayout l;
    private GridBagConstraints com;
    
    private Cliente cliente;
    
    private PassagemFrame passagemFrame;
    
    public MostrarPassagensFrame(Cliente cliente){
        super("Menu Passagens");
        //l = new GridBagLayout();
        //com = new GridBagConstraints();
        //setLayout(l);
        this.cliente = cliente;
        
        String[] s = new String[cliente.getListaPassagens().size()];
        
        for(int i =0; i < s.length; i++){
            Passagem p = cliente.getListaPassagens().get(i);
            s[i] = String.format("Num. Voo:   %d    %s    %s", p.getReserva().getVoo().getId(), 
                p.getReserva().getVoo().getDestino(), p.getReserva().getAssento() );
        }
        
        listaPassagens = new JList(s);
        listaPassagens.setFixedCellWidth(300);
        listaPassagens.setFixedCellHeight(25);
        listaPassagens.setSelectedIndex(0);
        
        JScrollPane scroll = new JScrollPane(listaPassagens);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        mostrarDetalhesButton = new JButton("Mostrar Passagem");
        mostrarDetalhesButton.addActionListener(this);
        mostrarDetalhesButton.setFont(FrameMethods.getFontPadraoToButtons());
        mostrarDetalhesButton.setToolTipText("Clique para ver detalhes da passagem");
        
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(FrameMethods.getColorPadrao());
        JLabel suasPassagensLabel = new JLabel("SUAS PASSAGENS");
        suasPassagensLabel.setFont(FrameMethods.getFontPadraoToTitles());
        suasPassagensLabel.setForeground(Color.WHITE);
        
        JLabel suasPassagensLabel2 = new JLabel("Escolha uma passagem da lista abaixo");
        suasPassagensLabel2.setFont(FrameMethods.getFontPadraoToSubtitles());
        suasPassagensLabel2.setForeground(Color.WHITE);
        
        Box bv = Box.createVerticalBox();
        bv.add(Box.createVerticalStrut(20));
        bv.add(suasPassagensLabel);
        bv.add(suasPassagensLabel2);
        bv.add(Box.createVerticalStrut(20));
        
        topPanel.add(bv);
        
        add(topPanel, BorderLayout.NORTH);
        
        
        JPanel leftPanel = new JPanel();
        GridBagLayout l1 = new GridBagLayout();
        com = new GridBagConstraints();
        leftPanel.setLayout(l1);
        
        com.weightx = 1;
        addComponent(leftPanel, Box.createVerticalStrut(20), l1, com, 0,0,2,1);
        addComponent(leftPanel, Box.createVerticalStrut(20), l1, com, 2,1,2,1);
        addComponent(leftPanel, Box.createHorizontalStrut(20), l1, com, 1,0,1,2);
        
        com.fill = GridBagConstraints.BOTH;
        com.weighty = 50;
        com.weightx = 50;
        addComponent(leftPanel, scroll, l1, com, 1,1,1,1);
        
        add(leftPanel, BorderLayout.WEST);
        
        
        JPanel rightPanel = new JPanel();
        Box bh = Box.createVerticalBox();
        
        bh.add(Box.createVerticalStrut(100));
        bh.add(this.mostrarDetalhesButton);
       
        
        rightPanel.add(bh);
        
        add(rightPanel);
         
        /*com.fill = GridBagConstraints.HORIZONTAL;
        com.weightx = 10;
        addComponent(this, Box.createHorizontalStrut(20), l, com, 1, 0, 1, 3);
        addComponent(this, Box.createHorizontalStrut(20), l, com, 2, 4, 1, 1);
        
        com.weighty = 1000;
        addComponent(this, Box.createHorizontalStrut(20), l, com, 1, 2, 1, 2);

        
        com.fill = GridBagConstraints.VERTICAL;
        com.weighty = 10;
        addComponent(this, Box.createVerticalStrut(20), l, com, 1, 3, 2, 1);
        addComponent(this, Box.createVerticalStrut(20), l, com, 0, 0, 5, 1);
        addComponent(this, Box.createVerticalStrut(20), l, com, 3, 0, 5, 1);
        
        com.fill = GridBagConstraints.BOTH;
        com.weightx = 30;
        com.weighty = 50;
        addComponent(this, scroll, l, com, 2, 1, 1, 1);
        
        com.weighty = 10;
        addComponent(this, new JLabel("Escolha uma das passagens abaixo:"), l, com, 1, 1, 1, 1);
        
        com.fill = GridBagConstraints.HORIZONTAL;
        com.weightx = 1;
        com.weighty = 1;
        addComponent(this, mostrarDetalhesButton, l, com, 2, 3, 1, 1);*/
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
    
    public Dimension getPreferredSize(){
        return new Dimension(800,500);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.mostrarDetalhesButton){
            Passagem p = cliente.getListaPassagens().get(listaPassagens.getSelectedIndex());
            this.passagemFrame = new PassagemFrame(p);
            FrameMethods.setDefaultFrameConfig(getLocation(),passagemFrame);
            passagemFrame.addWindowListener(
                new WindowAdapter(){
                    public void windowClosed(WindowEvent ev){
                        MostrarPassagensFrame.this.setEnabled(true);
                        MostrarPassagensFrame.this.requestFocus();
                    }
                }
            );
            this.setEnabled(false);
        }
    }
}

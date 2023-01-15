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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import trabalhopassagensaereas.*;

/**
 *
 * @author JM
 */
public class ReservaMenuFrame extends JFrame implements ActionListener, ListSelectionListener {
    private JList reservasList;
    
    private JTextField idTextField;
    private JTextField numVooTextField;
    private JTextField destinoTextField;
    private JTextField dataTextField;
    private JTextField horarioTextField;
    private JTextField assentoTextField;
    private JTextField classeTextField;
    
    private JButton remarcarButton;
    private JButton voltarButton;
    private JButton cancelarButton;
    private JButton comprarButton;
    
    private Cliente cliente;
    private ReservaHandler rh;
    private ArrayList<Reserva> listaReservas;
    
    private MapaAssentosFrame mapaAssentosFrame;
    
    public ReservaMenuFrame(Cliente cliente){
        super("Menu Reserva");
        this.cliente = cliente;
        rh = new ReservaHandler();
        
        Font fontPadrao = FrameMethods.getFontPadraoToButtons();
        
        JLabel escolhaReservaLabel = new JLabel("Escolha uma das reservas abaixo:");
        JLabel idLabel = new JLabel("Id: ");
        JLabel numVooLabel = new JLabel("Numero do Voo: ");
        JLabel destinoLabel = new JLabel("Destino: ");
        JLabel horarioLabel = new JLabel("Horario: ");
        JLabel dataLabel = new JLabel("Data Voo: ");
        JLabel assentoLabel = new JLabel("Assento: ");
        JLabel classeLabel = new JLabel("Classe: ");
        
        idTextField = new JTextField(10);
        numVooTextField = new JTextField(10);
        destinoTextField = new JTextField(10);
        dataTextField = new JTextField(10);
        horarioTextField = new JTextField(10);
        assentoTextField = new JTextField(10);
        classeTextField = new JTextField(10);
        
        remarcarButton = new JButton("Remarcar\n Reserva");
        remarcarButton.addActionListener(this);
        remarcarButton.setFont(fontPadrao);
        remarcarButton.setToolTipText("Clique para remarcar a reserva selecionada");
        
        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(this);
        voltarButton.setFont(fontPadrao);
        voltarButton.setToolTipText("Clique aqui para voltar ao menu do cliente");
        
        cancelarButton = new JButton("Cancelar\n Reserva");
        cancelarButton.addActionListener(this);
        cancelarButton.setFont(fontPadrao);
        cancelarButton.setToolTipText("Clique aqui para cancelar a reserva selecionada");
        
        comprarButton = new JButton("Comprar\n Reserva");
        comprarButton.addActionListener(this);
        comprarButton.setFont(fontPadrao);
        comprarButton.setToolTipText("Clique aqui para comprar a reserva selecionada");
        
        listaReservas = new ArrayList<Reserva>();
        
        for(Reserva r : cliente.getListaReservas()){
            if(!r.jaFoiComprada())
                listaReservas.add(r);
        }
        
        String[] reservas = new String[listaReservas.size()];
        
        for(int i =0; i < listaReservas.size(); i++){
            Reserva r = listaReservas.get(i);
            reservas[i] = String.format("%d  %s  %s", r.getId(), r.getAssento().toString(), r.getVoo().getDestino() );
        }
        
        reservasList = new JList(reservas);
        reservasList.setFixedCellHeight(12);
        reservasList.setFixedCellWidth(10);
        reservasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reservasList.addListSelectionListener(this);
        reservasList.setSelectedIndex(0);
        
        JScrollPane scroll = new JScrollPane(reservasList);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(FrameMethods.getColorPadrao());
        JLabel reservaLabel = new JLabel("SUAS RESERVAS");
        reservaLabel.setFont(FrameMethods.getFontPadraoToTitles());
        reservaLabel.setForeground(Color.WHITE);
        
        Box vb = Box.createVerticalBox();
        vb.add(Box.createVerticalStrut(20));
        vb.add(reservaLabel);
        vb.add(Box.createVerticalStrut(20));
        
        topPanel.add(vb);
        
        add(topPanel, BorderLayout.NORTH);
        
        
        
        JPanel downPanel = new JPanel();
        Box hb = Box.createHorizontalBox();
        
        hb.add(Box.createHorizontalStrut(20));
        hb.add(this.remarcarButton);
        hb.add(Box.createHorizontalStrut(20));
        hb.add(this.cancelarButton);
        hb.add(Box.createHorizontalStrut(20));
        hb.add(this.comprarButton);
        hb.add(Box.createHorizontalStrut(20));
        hb.add(this.voltarButton);
        hb.add(Box.createHorizontalStrut(20));
        
        downPanel.add(hb, BorderLayout.CENTER);
        downPanel.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        downPanel.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
        
        add(downPanel, BorderLayout.SOUTH);
        
        
        
        JPanel leftPanel = new JPanel();
        GridBagLayout l1 = new GridBagLayout();
        GridBagConstraints com = new GridBagConstraints();
        leftPanel.setLayout(l1);
        
        addComponent(leftPanel, Box.createVerticalStrut(20), l1, com, 0,0,2,1);
        addComponent(leftPanel, Box.createHorizontalStrut(20), l1, com, 1,0,1,2);
        addComponent(leftPanel, Box.createVerticalStrut(20), l1, com, 3,1,1,1);
        
        com.weighty = 3;
        addComponent(leftPanel, escolhaReservaLabel, l1, com, 1,1,1,1);
        
        com.fill = GridBagConstraints.BOTH;
        com.weighty = 10;
        addComponent(leftPanel, scroll, l1, com, 2,1,1,1);
        
        add(leftPanel, BorderLayout.WEST);
        
        
        
        
        JPanel rightPanel = new JPanel();
        GridBagLayout l2 = new GridBagLayout();
        rightPanel.setLayout(l2);
        com.weightx = 0;
        com.weighty = 0;
        com.fill = GridBagConstraints.NONE;
        
        com.weighty = 10;
        addComponent(rightPanel, Box.createVerticalStrut(20), l2, com, 0,0,1,1);
        addComponent(rightPanel, Box.createVerticalStrut(20), l2, com, 8,0,1,1);
        addComponent(rightPanel, Box.createHorizontalStrut(40), l2, com, 0,2,1,9);
        
        com.weighty = 3;
        com.anchor = GridBagConstraints.WEST;
        addComponent(rightPanel, idLabel, l2, com, 1,0,1,1);
        addComponent(rightPanel, numVooLabel, l2, com, 2,0,1,1);
        addComponent(rightPanel, assentoLabel, l2, com, 3,0,1,1);
        addComponent(rightPanel, destinoLabel, l2, com, 4,0,1,1);
        addComponent(rightPanel, horarioLabel, l2, com, 5,0,1,1);
        addComponent(rightPanel, dataLabel, l2, com, 6,0,1,1);
        addComponent(rightPanel, classeLabel, l2, com, 7,0,1,1);
        
        addComponent(rightPanel, idTextField, l2, com, 1,1,1,1);
        addComponent(rightPanel, numVooTextField, l2, com, 2,1,1,1);
        addComponent(rightPanel, assentoTextField, l2, com, 3,1,1,1);
        addComponent(rightPanel, destinoTextField, l2, com, 4,1,1,1);
        addComponent(rightPanel, horarioTextField, l2, com, 5,1,1,1);
        addComponent(rightPanel, dataTextField, l2, com, 6,1,1,1);
        addComponent(rightPanel, classeTextField, l2, com, 7,1,1,1);
        
        add(rightPanel, BorderLayout.CENTER);
        /*JPanel topPanel = new JPanel();
        GridBagLayout layout1 = new GridBagLayout();
        GridBagConstraints com = new GridBagConstraints();
        topPanel.setLayout(layout1);
        
        addComponent(topPanel, escolhaReservaLabel, layout1, com, 0,0,1,1 );
        addComponent(topPanel, escolhaReservaLabel, layout1, com, 1,0,1,5 );
        
        com.fill = GridBagConstraints.BOTH;
        com.weighty = 1000;
        addComponent(topPanel, scroll, layout1, com, 1,0,1,5 );

        com.fill = GridBagConstraints.NONE;
        com.weighty = 1;
        com.weightx = 10;
        addComponent(topPanel, Box.createHorizontalStrut(15), layout1, com, 0,1,1,6 );
        
        com.weightx = 1;
        com.fill = GridBagConstraints.HORIZONTAL;
        addComponent(topPanel, idLabel, layout1, com, 0,2,1,1 );
        
        com.weightx = 3;
        addComponent(topPanel, idTextField, layout1, com, 0,3,1,1 );
        
        addComponent(topPanel, numVooLabel, layout1, com, 1,2,1,1 );
        
        com.weightx = 10;
        addComponent(topPanel, numVooTextField, layout1, com, 1,3,1,1 );
        
        addComponent(topPanel, assentoLabel, layout1, com, 2,2,1,1 );
        
        com.weightx = 10;
        addComponent(topPanel, assentoTextField, layout1, com, 2,3,1,1 );
        
        addComponent(topPanel, destinoLabel, layout1, com, 3,2,1,1 );
        
        com.weightx = 10;
        addComponent(topPanel, destinoTextField, layout1, com, 3,3,1,1 );
        
        addComponent(topPanel, horarioLabel, layout1, com, 4,2,1,1 );
        
        com.weightx = 10;
        addComponent(topPanel, horarioTextField, layout1, com, 4,3,1,1 );
        
        addComponent(topPanel, dataLabel, layout1, com, 5,2,1,1 );
        
        com.weightx = 10;
        addComponent(topPanel, dataTextField, layout1, com, 5,3,1,1 );
        
        com.weightx = 10;
        addComponent(topPanel, classeLabel, layout1, com, 6,2,1,1 );
        
        addComponent(topPanel, classeTextField, layout1, com, 6,3,1,1 );
        
        add(topPanel,BorderLayout.NORTH);
        
        
        
        
        JPanel downPanel = new JPanel();
        GridBagLayout layout2 = new GridBagLayout();
        downPanel.setLayout(layout2);
        
        com.fill = GridBagConstraints.HORIZONTAL;
        addComponent(downPanel, Box.createHorizontalStrut(20), layout2, com, 0,0,1,1 );
        addComponent(downPanel, remarcarButton, layout2, com, 0,1,1,1 );
        addComponent(downPanel, Box.createHorizontalStrut(20), layout2, com, 0,2,1,1 );
        addComponent(downPanel, cancelarButton, layout2, com, 0,3,1,1 );
        addComponent(downPanel, Box.createHorizontalStrut(20), layout2, com, 0,4,1,1 );
        addComponent(downPanel, comprarButton, layout2, com, 0,5,1,1 );
        addComponent(downPanel, Box.createHorizontalStrut(20), layout2, com, 0,6,1,1 );
        addComponent(downPanel, voltarButton, layout2, com, 0,7,1,1 );
        addComponent(downPanel, Box.createHorizontalStrut(20), layout2, com, 0,8,1,1 );
        addComponent(downPanel, Box.createVerticalStrut(30), layout2, com, 1,0,1,1 );
        
        add(downPanel, BorderLayout.SOUTH);*/
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
        if(ae.getSource() == this.voltarButton){
            this.dispose();
        }
        else if(ae.getSource() == this.cancelarButton){
            int opt = JOptionPane.showConfirmDialog(this, "Deseja mesmo cancelar essa reserva?", "Confirmar Cancelamento"
                    , JOptionPane.YES_NO_OPTION);
            
            if(opt == JOptionPane.YES_OPTION){
                Reserva reserva = listaReservas.get( reservasList.getSelectedIndex() );

                if( rh.cancelarReserva(reserva) )
                    JOptionPane.showMessageDialog(this, "Reserva Cancelada!");
                else
                    JOptionPane.showMessageDialog(this, "Desculpe, " + rh.getMessageError());
                this.dispose();
            }
        }
        else if(ae.getSource() == this.comprarButton){
            Reserva reserva = listaReservas.get( reservasList.getSelectedIndex() );
            
            int opt = JOptionPane.showConfirmDialog(this, String.format("Deseja mesmo comprar essa reserva?\nPreco: %.2f", reserva.getPreco())
                , "Confirmar compra", JOptionPane.YES_NO_OPTION);
            
            if(opt == JOptionPane.YES_OPTION){
                if(!reserva.jaFoiComprada()){
                    reserva.comprar();
                    JOptionPane.showMessageDialog(this, "Compra concluida.");
                    
                    Passagem p = cliente.getListaPassagens().get(cliente.getListaPassagens().size() - 1);
                    PassagemFrame pf = new PassagemFrame(p);
                    FrameMethods.setDefaultFrameConfig(getLocation(), pf);
                    pf.addWindowListener(
                        new WindowAdapter(){
                            public void windowClosed(WindowEvent ev){
                                ReservaMenuFrame.this.dispose();
                            }
                        }
                    );
                    this.setEnabled(false);
                    
                    //this.dispose();
                }
                else{
                   JOptionPane.showMessageDialog(this, "A reserva ja foi comprada."); 
                }
            }
        }
        else if(ae.getSource() == this.remarcarButton){
            Reserva reserva = listaReservas.get( reservasList.getSelectedIndex() );
            
            mapaAssentosFrame = new MapaAssentosFrame(reserva.getVoo());
            FrameMethods.setDefaultFrameConfig(this.getLocation(), mapaAssentosFrame);
            mapaAssentosFrame.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent ev){
                        mapaAssentosFrame.setAssento("");
                        mapaAssentosFrame.dispose();
                        ReservaMenuFrame.this.setEnabled(true);
                        ReservaMenuFrame.this.requestFocus();
                    }
                    
                    @Override
                    public void windowClosed(WindowEvent ev){
                        if(mapaAssentosFrame.getAssento().equals(""))
                            return;
                        
                        Assento s = new Assento(mapaAssentosFrame.getAssento());
                        
                        Reserva nova =  rh.remarcarReserva(reserva, s) ;
                        
                        if(nova != null){
                            JOptionPane.showMessageDialog(ReservaMenuFrame.this, "Reserva Atualizada!\nNovo id: " + nova.getId());
                            ReservaMenuFrame.this.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(ReservaMenuFrame.this, "Desculpe, " + rh.getMessageError());
                        }
                        
                        ReservaMenuFrame.this.setEnabled(true);
                        ReservaMenuFrame.this.requestFocus();
                    }
                    
                }
            );
        }
        
    }


    @Override
    public void valueChanged(ListSelectionEvent ev) {
        if(ev.getSource() == this.reservasList){
            Reserva r = listaReservas.get( reservasList.getSelectedIndex() );
            
            idTextField.setText("" + r.getId());
            numVooTextField.setText("" + r.getVoo().getId());
            destinoTextField.setText(r.getVoo().getDestino());
            horarioTextField.setText(r.getVoo().getHorario());
            assentoTextField.setText(r.getAssento().toString());
            dataTextField.setText(r.getVoo().getData());
            classeTextField.setText(r.getVoo().getAviao().assentoEhPrimClasse(r.getAssento()) 
                ? "Primeira classe" : "Classe Economica");
        }
    }
}

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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import trabalhopassagensaereas.*;

/**
 *
 * @author JM
 */
public class LoginClienteFrame extends JFrame implements ActionListener, ListSelectionListener {
    JTextField idTextField1;
    JTextField idTextField2;
    
    ArrayList<Cliente> listaClientes;
    JList list;
    
    JTextField nomeTextField;
    JTextField telefoneTextField;
    JTextField numCartaoTextField;
    
    JButton logarButton;
    JButton cancelButton;
    
    String[] nomeCliente;
    Cliente cliente;
    GerenciadorClientes gc;
    
    MenuClienteFrame menuCliente;
            
    public LoginClienteFrame(GerenciadorClientes gc){
        super("Login Cliente");
        cliente = null;
        this.gc = gc;
        
        Font fontPadrao = FrameMethods.getFontPadraoToButtons();
        
        idTextField1 = new JTextField(15);
        idTextField1.addActionListener(this);
        
        idTextField2 = new JTextField(20);
        idTextField2.setEnabled(false);
        idTextField2.setDisabledTextColor(Color.BLACK);
        
        nomeTextField = new JTextField(20);
        nomeTextField.setEnabled(false);
        nomeTextField.setDisabledTextColor(Color.BLACK);
        
        telefoneTextField = new JTextField(20);
        telefoneTextField.setEnabled(false);
        telefoneTextField.setDisabledTextColor(Color.BLACK);
        
        numCartaoTextField = new JTextField(20);
        numCartaoTextField.setEnabled(false);
        numCartaoTextField.setDisabledTextColor(Color.BLACK);
        
        logarButton = new JButton("Logar");
        logarButton.addActionListener(this);
        logarButton.setFont(fontPadrao);
        logarButton.setToolTipText("Clique para realizar o login");
        
        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(this);
        cancelButton.setFont(fontPadrao);
        cancelButton.setToolTipText("Clique para cancelar");
        
        JLabel idJLabel = new JLabel("ID: "); 
        JLabel nomeJLabel = new JLabel("Nome: ");
        JLabel telefoneJLabel = new JLabel("Telefone: ");
        JLabel numCartaoJLabel = new JLabel("Numero do Cartao: ");
        
        listaClientes = gc.getListaClientes();
        
        nomeCliente = new String[listaClientes.size()];
        
        for(int i=0; i < nomeCliente.length; i++){
            nomeCliente[i] = listaClientes.get(i).getNome();
        }
        
        list = new JList(nomeCliente);
        list.setVisibleRowCount(8);
        list.setDragEnabled(true);
        list.setFixedCellWidth(10);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(FrameMethods.getColorPadrao());
        GridBagLayout l1 = new GridBagLayout();
        GridBagConstraints com = new GridBagConstraints();
        topPanel.setLayout(l1);
        
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(FrameMethods.getFontPadraoToTitles());
        loginLabel.setForeground(Color.WHITE);
        
        JLabel instrucaoLabel = new JLabel("Selecione um cliente da lista ou informe um ID");
        instrucaoLabel.setFont(FrameMethods.getFontPadraoToSubtitles());
        instrucaoLabel.setForeground(Color.WHITE);
        
        addComponent(topPanel, Box.createVerticalStrut(20), l1, com, 0,1,1,1);
        addComponent(topPanel, Box.createVerticalStrut(20), l1, com, 0,1,1,1);
        
        addComponent(topPanel, loginLabel, l1, com, 0,1,1,1);
        addComponent(topPanel, instrucaoLabel, l1, com, 1,1,1,1);
        add(topPanel, BorderLayout.NORTH);
        
        
        JPanel leftPanel = new JPanel();
        GridBagLayout l2 = new GridBagLayout();
        leftPanel.setLayout(l2);
        
        addComponent(leftPanel, Box.createVerticalStrut(20), l2, com, 0,0,1,1);
        addComponent(leftPanel, Box.createHorizontalStrut(20), l2, com, 1,0,1,3);
        addComponent(leftPanel, Box.createVerticalStrut(20), l2, com, 3,1,1,1);
        
        
        addComponent(leftPanel, idTextField1, l2, com, 1,2,1,1);
        com.weighty = 3;
        addComponent(leftPanel, new JLabel("Informe um ID: "), l2, com, 1,1,1,1);
        
        com.fill = GridBagConstraints.BOTH;
        com.weighty = 10;
        addComponent(leftPanel, scroll, l2, com, 2,1,2,1);
        
        add(leftPanel, BorderLayout.WEST);
        
        
        JPanel rightPanel = new JPanel();
        GridBagLayout l3 = new GridBagLayout();
        rightPanel.setLayout(l3);
        com.fill = GridBagConstraints.NONE;
        com.weightx = 0;
        com.weighty = 10;
        //com.anchor = GridBagConstraints.WEST;
        addComponent(rightPanel, Box.createHorizontalStrut(20), l3, com, 0,0,5,8);
        addComponent(rightPanel, Box.createVerticalStrut(20), l3, com, 0,1,1,1);
        addComponent(rightPanel, Box.createVerticalStrut(20), l3, com, 5,1,1,1);
        addComponent(rightPanel, Box.createVerticalStrut(20), l3, com, 7,1,1,1);
        addComponent(rightPanel, Box.createHorizontalStrut(10), l3, com, 5,2,1,1);
        com.weightx = 10;
        addComponent(rightPanel, Box.createHorizontalStrut(20), l3, com, 1,4,1,1);
        
        com.weightx = 0;
        com.weighty = 3;
        com.anchor = GridBagConstraints.EAST;
        addComponent(rightPanel, idJLabel, l3, com, 1,1,1,1);
        addComponent(rightPanel, nomeJLabel, l3, com, 2,1,1,1);
        addComponent(rightPanel, telefoneJLabel, l3, com, 3,1,1,1);
        addComponent(rightPanel, numCartaoJLabel, l3, com, 4,1,1,1);
        
        com.weightx = 1;
        addComponent(rightPanel, idTextField2, l3, com, 1,2,2,1);
        addComponent(rightPanel, nomeTextField, l3, com, 2,2,2,1);
        addComponent(rightPanel, telefoneTextField, l3, com, 3,2,2,1);
        addComponent(rightPanel, numCartaoTextField, l3, com, 4,2,2,1);
        
        addComponent(rightPanel, logarButton, l3, com, 5,1,1,1);
        addComponent(rightPanel, cancelButton, l3, com, 5,3,1,1);
        
        add(rightPanel,BorderLayout.EAST);
        
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800,500);
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
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == idTextField1){
            try{
                cliente = null;
                
                long id = Long.parseLong(idTextField1.getText());
                
                cliente = gc.getClientePorId(id);
                
                if(cliente != null){
                    idTextField2.setText( String.format("%d", cliente.getId() ) );
                    nomeTextField.setText(cliente.getNome());
                    telefoneTextField.setText( String.format("%d", cliente.getTelefone()));
                    numCartaoTextField.setText( String.format("%d", cliente.getNumCartao()));
                }
                else{
                    idTextField2.setText( "" );
                    nomeTextField.setText("");
                    telefoneTextField.setText( "");
                    numCartaoTextField.setText("");
                    
                    JOptionPane.showMessageDialog(this, "Cliente nao encontrado");
                }
            }
            catch(NumberFormatException e){
                idTextField2.setText( "" );
                nomeTextField.setText("");
                telefoneTextField.setText( "");
                numCartaoTextField.setText("");
                JOptionPane.showMessageDialog(this, "Id invalido");
            }
        }
        else if(ae.getSource() == cancelButton){
            this.dispose();
        }
        else if(ae.getSource() == logarButton){
            if(cliente == null){
                JOptionPane.showMessageDialog(this, "Nenhum cliente foi selecionado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else{
                menuCliente = new MenuClienteFrame(cliente, gc.getAviao());
                menuCliente.setSize(menuCliente.getPreferredSize());
                menuCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                menuCliente.setLocation(this.getLocation());
                menuCliente.setVisible(true);
                menuCliente.addWindowListener(new WindowHandler());
                
                this.setVisible(false);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent ev) {
        cliente = listaClientes.get(list.getSelectedIndex() );
        
        idTextField2.setText( String.format("%d", cliente.getId() ) );
        nomeTextField.setText(cliente.getNome());
        telefoneTextField.setText( String.format("%d", cliente.getTelefone()));
        numCartaoTextField.setText( String.format("%d", cliente.getNumCartao()));
    }
    
    private class WindowHandler extends WindowAdapter{

        @Override
        public void windowClosed(WindowEvent we) {
            LoginClienteFrame.this.dispose();
        }
    }
}

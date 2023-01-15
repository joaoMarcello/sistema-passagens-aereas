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
import javax.swing.*;
import trabalhopassagensaereas.*;
/**
 *
 * @author JM
 */
public class CadastroClienteFrame extends JFrame implements ActionListener {
    private JTextField nomeTextField;
    private JTextField telefoneTextField;
    private JTextField numCartaoTextField;
    
    private JCheckBox termosCheckBox;
    
    private JButton confirmButton;
    private JButton cancelButton;
    
    private GerenciadorClientes gc;
    
    private GridBagLayout gbLayout;
    private GridBagConstraints com ;
            
    public CadastroClienteFrame(GerenciadorClientes gc){
        super("Cadastro de Cliente");
        
        gbLayout = new GridBagLayout();
        com = new GridBagConstraints();
        //setLayout(gbLayout);
        
        this.gc = gc;
        
        Font fontPadrao = FrameMethods.getFontPadraoToButtons();
        
        nomeTextField = new JTextField(30);
        telefoneTextField = new JTextField(30);
        numCartaoTextField = new JTextField(30);
        
        confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(this);
        confirmButton.setFont(fontPadrao);
        confirmButton.setToolTipText("Clique para cadastrar o cliente");
        
        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(this);
        cancelButton.setFont(fontPadrao);
        cancelButton.setToolTipText("Clique para voltar ao menu principal");
        
        termosCheckBox = new JCheckBox("Declaro que li os termos de compromisso.");
        
        JLabel nomeJLabel = new JLabel("Nome Completo: *");
        JLabel telefoneJLabel = new JLabel("Telefone para contato: *");
        JLabel numCartaoJLabel = new JLabel("Número do Cartao: *");
        
        
        JPanel topPanel = new JPanel();
        GridBagLayout l1 = new GridBagLayout();
        GridBagConstraints com = new GridBagConstraints();
        topPanel.setLayout(l1);
        topPanel.setBackground(FrameMethods.getColorPadrao());
        
        addComponent(topPanel, Box.createVerticalStrut(20), l1, com, 0, 0, 1,1 );
        addComponent(topPanel, Box.createHorizontalStrut(5), l1, com, 1, 0, 1,3 );
        addComponent(topPanel, Box.createVerticalStrut(20), l1, com, 2, 1, 1,1 );
        
        JLabel cadastroLabel = new JLabel("CADASTRO");
        cadastroLabel.setFont(FrameMethods.getFontPadraoToTitles());
        cadastroLabel.setForeground(Color.WHITE);
        
        JLabel preencherLabel = new JLabel("Preencha os dados a seguir");
        preencherLabel.setFont(FrameMethods.getFontPadraoToSubtitles());
        preencherLabel.setForeground(Color.WHITE);
        
        addComponent(topPanel, cadastroLabel, l1, com, 1, 1, 1,1 );
        addComponent(topPanel, preencherLabel, l1, com, 3, 1, 1,1 );
        
        add(topPanel, BorderLayout.NORTH);
        
        
        JPanel downPanel = new JPanel();
        GridBagLayout l2 = new GridBagLayout();
        downPanel.setLayout(l2);
        
        com.anchor = GridBagConstraints.WEST;
        com.weighty = 1;
        addComponent(downPanel, Box.createVerticalStrut(20), l2, com, 0, 0, 1, 1 );
        addComponent(downPanel, Box.createHorizontalStrut(10), l2, com, 1, 0, 1,9 );
        
        addComponent(downPanel, nomeJLabel, l2, com, 1, 1, 1,1 );
        addComponent(downPanel, nomeTextField, l2, com, 2, 1, 1,1 );
        
        addComponent(downPanel, telefoneJLabel, l2, com, 3, 1, 1,1 );
        addComponent(downPanel, telefoneTextField, l2, com, 4, 1, 1,1 );
        
        addComponent(downPanel, numCartaoJLabel, l2, com, 5, 1, 1,1 );
        addComponent(downPanel, numCartaoTextField, l2, com, 6, 1, 1,1 );
        
        addComponent(downPanel, termosCheckBox, l2, com, 7, 1, 1,1 );
        
        addComponent(downPanel, confirmButton, l2, com, 8, 1, 1,1 );
        addComponent(downPanel, Box.createHorizontalStrut(20), l2, com, 8, 2, 1,1 );
        addComponent(downPanel, cancelButton, l2, com, 8, 3, 1,1 );
        
        addComponent(downPanel, Box.createVerticalStrut(20), l2, com, 9, 1, 1,1 );
        
        add(downPanel, BorderLayout.CENTER);
    }
    
    private void addComponent(Component c, int linha, int coluna, int comprimento, int altura){
        com.gridx = coluna;
        com.gridy = linha;
        com.gridwidth = comprimento;
        com.gridheight = altura;
        gbLayout.setConstraints(c, com);
        add(c);
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
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == confirmButton){
            if(nomeTextField.getText().equals("") || telefoneTextField.getText().equals("") || numCartaoTextField.equals("")){
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            }
            else if(!this.termosCheckBox.isSelected()){
                JOptionPane.showMessageDialog(this, "Confirmar termo de compromisso!!");
            }
            else{
                long telefone, numCartao;

                try{
                    telefone = Long.parseLong(telefoneTextField.getText());
                }
                catch(NumberFormatException exc){
                    JOptionPane.showMessageDialog(this, "O tefefone informado nao eh valido!", 
                        "Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try{
                    numCartao = Long.parseLong(numCartaoTextField.getText());
                }
                catch(NumberFormatException exc){
                    JOptionPane.showMessageDialog(this, "O numero do cartao informado nao eh valido!", 
                        "Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                
                Cliente cliente = gc.cadastrar(nomeTextField.getText(), numCartao, telefone);

                if(cliente != null){
                    JOptionPane.showMessageDialog(this, 
                        String.format("Cliente cadastrado com sucesso!\nO seu id eh %d", cliente.getId()));
                    
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Desculpe, houve um problema ao cadastrar o cliente!");
                }

            }
        }
        else if(e.getSource() == cancelButton){
            this.dispose();
        }
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800,500); //500,200
    }
}

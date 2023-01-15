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
import trabalhopassagensaereas.*;

/**
 *
 * @author JM
 */
public class MapaAssentosFrame extends JFrame implements ActionListener {
    private JTextArea mapaTextArea;
    private JTextField assentoTextField;
    
    private JButton confirmButton;
    private JButton cancelButton;
    
    private Voo voo;
    private GridBagLayout layout;
    private GridBagConstraints com;
    
    public MapaAssentosFrame(Voo voo){
        super("Mapa de Assentos");
        this.voo = voo;
        
        layout = new GridBagLayout();
        com = new GridBagConstraints();
        setLayout(layout);
        
        mapaTextArea = new JTextArea(voo.getMapaAssentos());
        mapaTextArea.setColumns(15);
        mapaTextArea.setRows(24);
        mapaTextArea.setEnabled(false);
        mapaTextArea.setDisabledTextColor(Color.BLACK);
        
        JScrollPane scroll = new JScrollPane(mapaTextArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        assentoTextField = new JTextField(10);
        
        confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(this);
        confirmButton.setFont(FrameMethods.getFontPadraoToButtons());
        confirmButton.setToolTipText("Clique aqui para confirmar");
        
        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(this);
        cancelButton.setFont(FrameMethods.getFontPadraoToButtons());
        cancelButton.setToolTipText("Clique aqui para cancelar");
        
        addComponent(Box.createVerticalStrut(30), 0, 0, 7, 1);
        addComponent(Box.createHorizontalStrut(30), 0, 1, 1, 4);
        addComponent(scroll, 1, 1, 1, 3);
        addComponent(Box.createHorizontalStrut(30), 1, 2, 1, 3);
        
        addComponent(new JLabel("Escolha o assento: "), 1, 3, 1, 1);
        addComponent(assentoTextField, 1, 4, 2, 1);
        addComponent(Box.createVerticalStrut(30), 2, 3, 3, 1);
        
        addComponent(confirmButton, 3, 3, 1, 1);
        addComponent(Box.createHorizontalStrut(30), 3, 4, 1, 1);
        addComponent(cancelButton, 3, 5, 1, 1);
        
        addComponent(Box.createHorizontalStrut(30), 1, 6, 1, 3);
    }

    private void addComponent(Component c, int linha, int coluna, int comprimento, int altura){
        com.gridx = coluna;
        com.gridy = linha;
        com.gridwidth = comprimento;
        com.gridheight = altura;
        layout.setConstraints(c, com);
        add(c);
    }
    
    public String getAssento(){ return assentoTextField.getText();}
    
    public void setAssento(String assento){
        this.assentoTextField.setText(assento);
    }
    
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800,500);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == cancelButton){
            assentoTextField.setText("");
            dispose();
        }
        else if(ae.getSource() == this.confirmButton){
            if(assentoTextField.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Por favor, preencha o campo assento!");
            else
                dispose();
        }
    }
}

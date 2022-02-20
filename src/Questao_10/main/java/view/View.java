package view;

import Controller.SentimentoController;
import Factory.EManagerFactory;
import Models.Sentimento;
import Models.UsuariosDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.List;

public class View extends JFrame{
    private JTextField textValue;
    private JButton botaoFeliz;
    private JButton botaoTriste;
    private JPanel panel1;
    private JButton salvarButton;
    private JButton voltarButton;

    private SentimentoController sc;

    public View(String msg, SentimentoController sc) {
        super(msg);

        this.sc = sc;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                EManagerFactory.getEm().close();
            }
        });

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        setSize(new Dimension(960, 460));
        setLocationRelativeTo(null);
        setVisible(true);

        botaoFeliz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textValue.setText(textValue.getText() + ":-)");
            }
        });

        botaoTriste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textValue.setText(textValue.getText() + ":-(");
            }
        });
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login("Login").setVisible(true);
                dispose();
            }
        });
    }

    private void salvar() {
        if (!this.textValue.getText().equals("")) {
            int count = 0;
            String texto = this.textValue.getText();
            StringBuilder aux = new StringBuilder("");
            String status;

            for(int i=0; i<texto.length(); i++){
                if(aux.indexOf(":-)") != -1){
                    aux.setLength(0);
                    count++;
                    i--;
                }
                else if(aux.indexOf(":-(") != -1){
                    aux.setLength(0);
                    count--;
                    i--;
                }
                else{
                    aux.append(texto.charAt(i));
                }
            }
            if(aux.indexOf(":-)") != -1) count++;
            else if(aux.indexOf(":-(") != -1) count--;

            if(count > 0) status = "Divertido";
            else if(count < 0) status = "Chateado";
            else status = "Neutro";

            Sentimento sentimento = new Sentimento(this.textValue.getText(), status, this.sc.getUsuario());

            this.sc.salvar(sentimento);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso! Status: "+ status);
            this.textValue.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Mensagem deve ser informada.");
        }
    }



}

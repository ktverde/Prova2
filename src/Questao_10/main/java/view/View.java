package view;

import Controller.SentimentoController;
import Models.Sentimento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{
    private JTextField textValue;
    private JButton botaoFeliz;
    private JButton botaoTriste;
    private JPanel panel1;
    private JButton salvarButton;

    private SentimentoController controller;
    public View(String msg) {
        super(msg);
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        this.setPreferredSize(new Dimension(width, height));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(500, 1000);
        this.setContentPane(panel1);
        this.pack();

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

            }
        });
    }

    private void salvar() {
        if (!textValue.getText().equals("")) {
            Sentimento sentimento = new Sentimento(textValue.getText());

            this.controller.salvar(sentimento);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            this.textValue.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Mensagem deve ser informada.");
        }
    }



}

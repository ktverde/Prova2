package view;

import Controller.UsuariosController;
import Models.UsuariosDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Cadastrar extends JFrame{
    private JTextField nomeText;
    private JTextField usuarioText;
    private JTextField senhaText;
    private JButton salvarButton;
    private JPanel panel1;
    private JButton voltarButton;

    private UsuariosController uc;
    public Cadastrar(String title, UsuariosController uc){
        super(title);

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        this.uc = uc;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        setSize(new Dimension(480,520));
        setLocationRelativeTo(null);
        setVisible(true);


        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nomeText.getText().equals("") && !usuarioText.getText().equals("") && !senhaText.getText().equals("")){
                    String nome = nomeText.getText();
                    String usuario = usuarioText.getText();
                    String senha = senhaText.getText();
                    cadastrar(new UsuariosDB(nome, usuario, senha));
                    showMessageDialog(null, "Usuário Salvo com sucesso!","Informação",INFORMATION_MESSAGE);
                    dispose();
                }
                else{
                    nomeText.setText("");
                    usuarioText.setText("");
                    senhaText.setText("");
                    showMessageDialog(null, "Não deixe campos em branco!","Informação",INFORMATION_MESSAGE);
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void cadastrar(UsuariosDB usuario){
        uc.salvar(usuario);
    }
}

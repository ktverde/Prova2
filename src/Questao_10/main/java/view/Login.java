package view;

import Controller.UsuariosController;
import Models.UsuariosDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Login extends JFrame {
    private JPanel panel1;
    private JTextField LoginUser;
    private JPasswordField PasswordUser;
    private JButton LoginButton;
    private JLabel User;
    private JLabel Password;
    private JButton cadastrarButton;
    private JLabel Image;

    private UsuariosController uc = new UsuariosController();
    public Login(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        setSize(new Dimension(680,820));

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UsuariosDB> lista = listarTodos();
                String user = LoginUser.getText();
                String senha = new String(PasswordUser.getPassword());

                lista.forEach(i -> {
                    if(user.equals(i.getUsuario()) && senha.equals(i.getSenha())){
                        View menu = new View("Risograma");
                        menu.setVisible(true);
                        setVisible(false);
                        dispose();
                    }
                });
                LoginUser.setText("");
                PasswordUser.setText("");
                showMessageDialog(null, "Usuário não Encontrado!","Informação",INFORMATION_MESSAGE);

            }
        });
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc.getEm().close();
                Cadastrar cadastrarMenu = new Cadastrar("Cadastrar-se");
                cadastrarMenu.setVisible(true);
            }
        });
    }

    public List<UsuariosDB> listarTodos(){
        return uc.listarTodos();
    }

}

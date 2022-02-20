package view;

import Controller.SentimentoController;
import Controller.UsuariosController;
import Factory.EManagerFactory;
import Models.UsuariosDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Login extends JFrame {
    private JPanel panel1;
    private JTextField LoginUser;
    private JPasswordField PasswordUser;
    private JButton LoginButton;
    private JButton cadastrarButton;
    private JLabel Image;

    private UsuariosController uc = new UsuariosController();
    private SentimentoController sc = new SentimentoController();

    public Login(String title){
        super(title);
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
        setSize(new Dimension(480,420));
        setLocationRelativeTo(null);
        setVisible(true);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UsuariosDB> lista = listarTodos();
                String user = LoginUser.getText();
                String senha = new String(PasswordUser.getPassword());

                lista.forEach(i -> {
                    if (user.equals(i.getUsuario()) && senha.equals(i.getSenha())) {
                        sc.setUsuario(i);
                        View menu = new View("Risograma", sc);
                        setVisible(false);
                        dispose();
                    }
                });
                if (isVisible()){
                    LoginUser.setText("");
                    PasswordUser.setText("");
                    showMessageDialog(null, "Usuário não Encontrado!", "Informação", INFORMATION_MESSAGE);
                }

            }
        });
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cadastrar("Cadastrar-se", uc);
            }
        });
    }

    public List<UsuariosDB> listarTodos(){
        return uc.listarTodos();
    }

}

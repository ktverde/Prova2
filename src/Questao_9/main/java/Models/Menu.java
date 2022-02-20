package Models;

import Exceptions.EntradaInvalidaException;

import Models.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu
{
    public void Menu(){
        Scanner teclado = new Scanner(System.in);
        Usuario usuario = new Usuario();
        int opcao = -1;

        System.out.println("""
                ===================================
                |  Bem vindo a Lojinha do Paulo!! |
                ===================================""");
        do{
            System.out.println("""
             
             1) Cadastrar ofertas!
             2) Atualizar uma oferta pelo Id
             3) Excluir uma oferta pelo Id
             4) Listar 3 produtos

             0) Sair""");
            try {
                opcao = teclado.nextInt();
                switch (opcao) {
                    case 1:
                        usuario.salva();
                        break;
                    case 2:
                        usuario.atualiza();
                        break;
                    case 3:
                        usuario.deleta();
                        break;
                    case 4:
                        usuario.listar3();
                        break;
                    case 0:
                        System.out.println("""
                         *_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*
                               Obrigado por comprar conosco!! :)
                         *_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*""");
                        break;
                    default:
                        throw new EntradaInvalidaException("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n ERRO: Digite uma entrada válida!\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                }

            } catch (EntradaInvalidaException | InputMismatchException e) {
                System.out.println(e.getMessage());
                teclado.nextLine();
            }
        }while(opcao != 0);
    }
}

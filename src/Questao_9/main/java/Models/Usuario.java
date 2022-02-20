package Models;

import Controller.ProdutoController;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Usuario
{
    ProdutoController pc = new ProdutoController();

    public Produto cadastra(){
        Scanner teclado = new Scanner(System.in, StandardCharsets.UTF_8);
        teclado.useLocale(Locale.US);
        String nome, descricao;
        double valor, desconto;
        while(true) {
            try {
                System.out.println("Digite o nome da oferta: ");
                nome = teclado.nextLine();
                System.out.println("Digite a descrição da oferta: ");
                descricao = teclado.nextLine();
                System.out.println("Digite o valor da oferta: ");
                valor = teclado.nextDouble();
                System.out.println("Digite o desconto que a sua oferta proporciona: ");
                desconto = teclado.nextDouble();

                break;
            }catch(InputMismatchException e){
                System.out.println("Digite uma entrada válida");
                teclado.nextLine();
            }
        }

        return new Produto(nome, descricao, desconto, valor);

    }

    private int coletaId() {
        Scanner teclado = new Scanner(System.in, StandardCharsets.UTF_8);
        teclado.useLocale(Locale.US);
        int id;
        while(true) {
            try {
                id = teclado.nextInt();
                teclado.nextLine();
                break;
            }catch(InputMismatchException e){
                System.out.println("Digite uma entrada válida");
                teclado.nextLine();
            }
        }
        return id;
    }

    public void salva() {
        pc.salvar(cadastra());
        for(Produto p: pc.generate3Offers()){
            pc.salvar(p);
        }
    }
    public void atualiza(){
        System.out.println("Digite o id da oferta que deseja atualizar: ");
        int id = coletaId();

        if(pc.buscarId(id).size() == 0) {
            System.out.println("O id não existe, deseja cadastrar uma nova oferta? (S/N) ");
            String opcao =  new Scanner(System.in).nextLine();
            if (opcao.charAt(0) == 's' || opcao.charAt(0) == 'S'){
                salva();
            }
        }
        else{
            System.out.println("Id encontrado, deseja alterar essa oferta? (S/N) ");
            String opcao = new Scanner(System.in).nextLine();
            if (opcao.charAt(0) == 's' || opcao.charAt(0) == 'S'){
                pc.alterar(cadastra(), id);
            }
        }
    }

    public void deleta(){
        System.out.println("Digite o id da oferta que deseja excluir: ");
        int id = coletaId();

        if(pc.buscarId(id).size() == 0) System.out.println("Esse id já foi excluído do nosso sistema.");
        else{
            System.out.println("Id encontrado, deseja excluir essa oferta? (S/N) ");
            String opcao = new Scanner(System.in).nextLine();
            if (opcao.charAt(0) == 's' || opcao.charAt(0) == 'S'){
                pc.deletar(id);
            }
        }
    }

    public void listar3(){
        Scanner teclado = new Scanner(System.in);
        List<Produto> listaProdutos = new ArrayList<>();
        List<String> listaPalavrasPesquisa = new ArrayList<>();
        int count = 0;

        System.out.println("Digite o nome que deseja procurar: ");
        String nome = teclado.nextLine().toUpperCase();

        Scanner nomePesquisar = new Scanner(nome);
        nomePesquisar.useDelimiter(" ");

        while(nomePesquisar.hasNext())
            listaPalavrasPesquisa.add(nomePesquisar.next());

        nomePesquisar.close();

        List<Produto> produtos = pc.listar();
        for (Produto p:produtos) {
            if(count == 3) break;

            Scanner nomeProduto = new Scanner(p.getNome().toUpperCase());
            nomeProduto.useDelimiter(" ");
            while(nomeProduto.hasNext()){
                for (String s:listaPalavrasPesquisa) {
                    if(nomeProduto.next().equals(s)) {
                        listaProdutos.add(p);
                        count++;
                    }
                }
            }
            nomeProduto.close();
        }
        if(count == 0) System.out.println("Nenhum produto com esse nome encontrado no nosso estoque! \n");
        listaProdutos.forEach(System.out::println);

    }
}

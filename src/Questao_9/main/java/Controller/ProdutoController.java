package Controller;

import DAO.ProdutoDAO;
import Factory.ConnectionFactory;
import Models.Produto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class ProdutoController {
    private ProdutoDAO produtoDAO;
    private static int count = 0;

    public ProdutoController(){
        try{
            Connection connection = new ConnectionFactory().recuperarConexao();
            produtoDAO = new ProdutoDAO(connection);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deletar(Integer id) {
        produtoDAO.deletar(id);
        System.out.println("Deletando produto");
    }

    public void salvar(Produto produto) {
        produtoDAO.salvar(produto);
        System.out.println("Salvando produto");
    }

    public List<Produto> listar() {
        List<Produto> produtos;
        produtos = produtoDAO.listar();
        return produtos;
    }
    public List<Produto> buscarId(int id) {
        List<Produto> produtos;
        produtos = produtoDAO.buscarId(id);
        return produtos;
    }

    public void alterar(Produto produto, int id) {
        this.produtoDAO.alterar(produto, id);
        System.out.println("Alterando produto");
    }

    public List<Produto> generate3Offers(){
        List<Produto> lista = new ArrayList<>();
        int linhaNumero = 0;
        int aux = 0;
        try {
            Scanner sc = new Scanner(new File("ofertas.csv"), StandardCharsets.UTF_8);

            if(count == 4)  count = 0;
            while(sc.hasNextLine()) {
                String linha = sc.nextLine();
                if(count*3 == linhaNumero && aux != 3) {
                    Scanner linhaScanner = new Scanner(linha);
                    linhaScanner.useLocale(Locale.US);
                    linhaScanner.useDelimiter(",");

                    Produto p = new Produto(linhaScanner);
                    lista.add(p);
                    linhaScanner.close();
                    aux++;
                }
                if(aux == 0) linhaNumero++;
                else if(aux == 3) break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        count++;

        return lista;
    }
}


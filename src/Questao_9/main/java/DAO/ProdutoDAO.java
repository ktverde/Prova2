package DAO;
import Models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) {

        String sql = "INSERT INTO PRODUTOS (NOME, DESCRICAO, VALOR, DESCONTO, DATAINICIO) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setDouble(3, produto.getValor());
            pstm.setDouble(4, produto.getDesconto());
            pstm.setDate(5, produto.getDataInicio());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTOS";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            fillList(produtos, pstm);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return produtos;
    }

    public List<Produto> buscarId(int id) {
        List<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT * FROM PRODUTOS WHERE id = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();

            fillList(produtos, pstm);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return produtos;
    }

    public void deletar(Integer id) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTOS WHERE ID = ?")) {
            stm.setInt(1, id);
            stm.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void alterar(Produto p, int id) {
        try (PreparedStatement stm = connection
                .prepareStatement("UPDATE PRODUTOS P SET P.NOME = ?, P.DESCRICAO = ?, P.DESCONTO = ?, P.VALOR = ? WHERE ID = ?")) {
            stm.setString(1, p.getNome());
            stm.setString(2, p.getDescricao());
            stm.setDouble(3, p.getDesconto());
            stm.setDouble(4, p.getValor());
            stm.setInt(5, id);
            stm.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void fillList(List<Produto> produtos, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                produtos.add(produto);
            }
        }
    }
}

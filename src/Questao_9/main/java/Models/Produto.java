package Models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Produto{

    private Integer id;
    private String nome;
    private String descricao;
    private double desconto, valor;
    private LocalDate dataInicio = LocalDate.now();

    public Produto() {
    }
    public Produto(Scanner oferta){
        this.nome = oferta.next();
        this.descricao = oferta.next();
        this.desconto = oferta.nextDouble();
        this.dataInicio = LocalDate.parse(oferta.next());
        this.valor = oferta.nextDouble();
    }

    public Produto(String nome, String descricao, double desconto, double valor) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.desconto = desconto;
    }

    public Produto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getValor(){ return this.valor; }
    public double getDesconto(){ return this.desconto; }
    public Date getDataInicio() { return Date.valueOf(dataInicio); }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDesconto(double desconto) { this.desconto = desconto; }
    public void setValor(double valor) { this.valor = valor; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    @Override
    public String toString() {
        return String.format("Id: %d, %s - %s", this.id, this.nome, this.descricao);
    }
}

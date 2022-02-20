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
    public Produto(Scanner ofertaCSV){
        this.nome = ofertaCSV.next();
        this.descricao = ofertaCSV.next();
        this.desconto = ofertaCSV.nextDouble();
        this.dataInicio = LocalDate.parse(ofertaCSV.next());
        this.valor = ofertaCSV.nextDouble();
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

    @Override
    public String toString() {
        return String.format("Id: %d, %s - %s", this.id, this.nome, this.descricao);
    }
}

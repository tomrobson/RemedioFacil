package br.iesb.medanvisa.model;


public class Remedio {
    private String codigoBarra;
    private String principioAtivo;
    private String produto;
    private Double precoFabrica;
    private Double precoMercado;

    public Remedio(String codigoBarra, String principioAtivo, String produto, Double precoFabrica, Double precoMercado) {
        this.codigoBarra = codigoBarra;
        this.principioAtivo = principioAtivo;
        this.produto = produto;
        this.precoFabrica = precoFabrica;
        this.precoMercado = precoMercado;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public String getProduto() {
        return produto;
    }

    public Double getPrecoFabrica() {
        return precoFabrica;
    }

    public Double getPrecoMercado() {
        return precoMercado;
    }
}

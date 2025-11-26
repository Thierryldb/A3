package ecommerce2;

public class Produto {

	    
	    private String codigo;
	    private String nome;
	    private String categoria;
	    private double preco;
	    private int quantidade;
	    private double avaliacao;
	    private int popularidade;

	   
	    public Produto(String codigo, String nome, String categoria, double preco, int quantidade, double avaliacao, int popularidade) {
	        this.codigo = codigo;
	        this.nome = nome;
	        this.categoria = categoria;
	        this.preco = preco;
	        this.quantidade = quantidade;
	        this.avaliacao = avaliacao;
	        this.popularidade = popularidade;
	    }

	  
	    public String getCodigo() {
	        return codigo;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public String getCategoria() {
	        return categoria;
	    }

	    public double getPreco() {
	        return preco;
	    }

	    public int getQuantidade() {
	        return quantidade;
	    }

	    public double getAvaliacao() {
	        return avaliacao;
	    }

	    public int getPopularidade() {
	        return popularidade;
	    }

	   
	    public void setQuantidade(int novaQuantidade) {
	        if (novaQuantidade >= 0) {
	            this.quantidade = novaQuantidade;
	        } else {
	            System.out.println("Erro: Quantidade não pode ser negativa.");
	        }
	    }

	    public String toString() {
	        return "Produto [" +
	                "Código: '" + codigo + '\'' +
	                ", Nome: '" + nome + '\'' +
	                ", Categoria: '" + categoria + '\'' +
	                ", Preço: R$ " + preco +
	                ", Qtd: " + quantidade +
	                ", Avaliação: " + avaliacao + "☆" +
	                ", Popularidade: " + popularidade +
	                ']';
	    }
	}

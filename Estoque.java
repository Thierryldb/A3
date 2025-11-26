package ecommerce2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {

    private Map<String, Produto> mapaProdutos; 
    private List<Produto> listaProdutos;       

    public Estoque() {
        this.mapaProdutos = new HashMap<>();
        this.listaProdutos = new ArrayList<>();
    }

    public boolean adicionarProduto(Produto produto) {
        if (mapaProdutos.containsKey(produto.getCodigo())) {
            System.out.println("Erro: Produto já existe.");
            return false;
        }
        mapaProdutos.put(produto.getCodigo(), produto);
        listaProdutos.add(produto);
        System.out.println("Produto cadastrado (Memória).");
        return true;
    }

    public Produto buscarPorCodigo(String codigo) {
        return mapaProdutos.get(codigo); 
    }
    
    public List<Produto> getTodosProdutos() {
        return listaProdutos;
    }

    public boolean atualizarQuantidade(String codigo, int novaQtd) {
        Produto p = buscarPorCodigo(codigo);
        if (p != null) {
            p.setQuantidade(novaQtd);
            return true;
        }
        return false;
    }

  
    
    public List<Produto> buscarPorNome(String termo) {
        List<Produto> encontrados = new ArrayList<>();
        for (Produto p : listaProdutos) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        List<Produto> encontrados = new ArrayList<>();
        for (Produto p : listaProdutos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    

    public List<Produto> getProdutosOrdenados(String criterio, String direcao) {
     
        List<Produto> listaOrdenada = new ArrayList<>(this.listaProdutos);
        
        
        boolean isDesc = direcao.equalsIgnoreCase("DESC");

        Comparator<Produto> comparador;

        switch (criterio.toLowerCase()) {
            case "popularidade":
                comparador = Comparator.comparingInt(Produto::getPopularidade);
                break;
            case "quantidade":
                comparador = Comparator.comparingInt(Produto::getQuantidade);
                break;
            case "preco":
              
                return ordenarPorPrecoBubbleSort(listaOrdenada, direcao);
            case "avaliacao":
                comparador = Comparator.comparingDouble(Produto::getAvaliacao);
                break;
            default:
                return listaOrdenada;
        }

      
        if (isDesc) {
            comparador = comparador.reversed();
        }

        listaOrdenada.sort(comparador);
        return listaOrdenada;
    }

   
    private List<Produto> ordenarPorPrecoBubbleSort(List<Produto> lista, String direcao) {
        int n = lista.size();
        boolean trocou;
        boolean isAsc = direcao.equalsIgnoreCase("ASC");

        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                
                boolean deveTrocar;
                
               
                if (isAsc) {
                    
                    deveTrocar = lista.get(j).getPreco() > lista.get(j + 1).getPreco();
                } else {
                    
                    deveTrocar = lista.get(j).getPreco() < lista.get(j + 1).getPreco();
                }

                if (deveTrocar) {
                    Produto temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
        return lista;
    }
}
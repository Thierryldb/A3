package ecommerce2;

import java.util.List;
import java.util.Scanner;

public class SistemaGerenciamento {

    private static Estoque estoque = new Estoque();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        
        boolean executando = true;
        
        while (executando) {
            exibirMenu();
            
            int opcao = lerOpcao();

            switch (opcao) {
                case 1: cadastrarProduto();
                break;
                
                case 2: listarTodosProdutos(); 
                break;
                
                case 3: atualizarEstoqueProduto();
                break;
                
                case 4: menuPesquisarProduto(); 
                break;
                
                case 5: menuOrdenarProduto(); 
                break; 
                
                case 0: 
                    executando = false; 
                    System.out.println("Saindo..."); 
                    break;
                    
                default: System.out.println("Opção inválida.");
            }
            
            if (executando && opcao != 5) { 
                pressionarEnterParaContinuar();
            }
        }
        
        scan.close();
    }

    private static void exibirMenu() {
        System.out.println("\n    MENU PRINCIPAL    ");
        System.out.println("1: Cadastrar novo produto");
        System.out.println("2: Listar todos os produtos");
        System.out.println("3: Atualizar estoque");
        System.out.println("4: Pesquisar produto");
        System.out.println("5: Ordenar e listar");
        System.out.println("0: Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            String s = scan.nextLine();
            
            if (s.trim().isEmpty()) return -1;
            return Integer.parseInt(s);
        } catch (Exception e) { return -1; }
    }

    

    private static void cadastrarProduto() {
        System.out.println("\n    Cadastro    ");
        try {
            System.out.print("Código: "); String codigo = scan.nextLine();
            System.out.print("Nome: "); String nome = scan.nextLine();
            System.out.print("Categoria: "); String categoria = scan.nextLine();
            System.out.print("Preço: "); double preco = Double.parseDouble(scan.nextLine());
            System.out.print("Quantidade: "); int qtd = Integer.parseInt(scan.nextLine());
            System.out.print("Avaliação (0-5): "); double aval = Double.parseDouble(scan.nextLine());
            System.out.print("Popularidade: "); int pop = Integer.parseInt(scan.nextLine());

            Produto p = new Produto(codigo, nome, categoria, preco, qtd, aval, pop);
            estoque.adicionarProduto(p);
        } catch (Exception e) { System.out.println("Erro: Digite números válidos."); }
    }

    private static void listarTodosProdutos() {
        exibirLista(estoque.getTodosProdutos(), "Lista Completa");
    }

    private static void atualizarEstoqueProduto() {
        System.out.print("Código: "); String cod = scan.nextLine();
        System.out.print("Nova Qtd: "); 
        
        try {
            int qtd = Integer.parseInt(scan.nextLine());
            
            if (estoque.atualizarQuantidade(cod, qtd)) System.out.println("Sucesso!");
            else System.out.println("Produto não encontrado.");
        } catch(Exception e) { System.out.println("Erro numérico."); }
    }

    private static void menuPesquisarProduto() {
        System.out.println("1. Código | 2. Nome | 3. Categoria");
        
        int op = lerOpcao();
        String t;
        
        if(op == 1) {
            System.out.print("Código: "); t = scan.nextLine();
            Produto p = estoque.buscarPorCodigo(t);
            
            if(p!=null) System.out.println(p); else System.out.println("Não achou.");
        } else if(op == 2) {
            System.out.print("Nome: "); t = scan.nextLine();
            exibirLista(estoque.buscarPorNome(t), "Busca Nome");
        } else if(op == 3) {
            System.out.print("Categoria: "); t = scan.nextLine();
            exibirLista(estoque.buscarPorCategoria(t), "Busca Categoria");
        }
    }


    private static void menuOrdenarProduto() {
        boolean escolhendoCriterio = true;

        
        while (escolhendoCriterio) {
            System.out.println("      ESCOLHA O CRITÉRIO      ");
            System.out.println("1: Popularidade");
            System.out.println("2: Quantidade");
            System.out.println("3: Preço");
            System.out.println("4: Avaliação");
            System.out.println("0: Voltar ao Menu Principal");
            System.out.print("Opção: ");

            int crit = lerOpcao();

            if (crit == 0) {
                escolhendoCriterio = false;
                break;
            }

            String sCrit = "";
            switch(crit) {
                case 1: sCrit = "popularidade";
                break;
                
                case 2: sCrit = "quantidade";
                break;
                
                case 3: sCrit = "preco";
                break;
                
                case 4: sCrit = "avaliacao";
                break;
                
                default: 
                    System.out.println("Critério inválido."); 
                    continue; 
            }

            boolean escolhendoDirecao = true;

            
            while (escolhendoDirecao) {
                System.out.println("\n   Ordenando por: " + sCrit.toUpperCase() + "    ");
                System.out.println("1: Crescente ");
                System.out.println("2: Decrescente ");
                System.out.println("0: Voltar e escolher outro Critério");
                System.out.print("Opção: ");
                
                int dir = lerOpcao();

                if (dir == 0) {
                    escolhendoDirecao = false;
                    break; 
                }

                if (dir != 1 && dir != 2) {
                    System.out.println("Opção inválida. Digite 1 ou 2.");
                    continue;
                }

                String sDir = (dir == 2) ? "DESC" : "ASC";

               
                exibirLista(estoque.getProdutosOrdenados(sCrit, sDir), 
                            "Ordenado por " + sCrit + " (" + sDir + ")");
                
                pressionarEnterParaContinuar();
                
            }
        }
    }

    private static void exibirLista(List<Produto> l, String t) {
        System.out.println("    " + t + "    ");
        if(l==null || l.isEmpty()) System.out.println("Vazio.");
        else for(Produto p : l) System.out.println(p);
    }

    private static void pressionarEnterParaContinuar() {
        System.out.println("[Pressione Enter...]"); scan.nextLine();
    }
}
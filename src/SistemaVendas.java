import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaVendas {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Produto> produtos = new ArrayList<>();
    private static Perfil perfilAtivo = null;

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> cadastrarPerfil();
                case 4 -> realizarPagamento();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n--- SISTEMA DE VENDAS ---");
        if (perfilAtivo != null) System.out.println(perfilAtivo);
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Visualizar Lista de Produtos");
        System.out.println("3. Cadastrar Perfil");
        System.out.println("4. Pagar Produtos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = lerDouble();

        produtos.add(new Produto(nome, preco));
        System.out.println("Produto adicionado com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println((i + 1) + ". " + produtos.get(i));
            }
        }
    }

    private static void cadastrarPerfil() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        perfilAtivo = new Perfil(nome, email);
        System.out.println("Perfil cadastrado com sucesso!");
    }

    private static void realizarPagamento() {
        if (produtos.isEmpty()) {
            System.out.println("Erro: Não há produtos na lista para pagar.");
            return;
        }

        double total = 0;
        for (Produto p : produtos) {
            total += p.getPreco();
        }

        System.out.println("\n--- CHECKOUT ---");
        System.out.printf("Total da compra: R$ %.2f\n", total);
        if (perfilAtivo == null) {
            System.out.println("Aviso: Pagamento realizado como 'Visitante'.");
        } else {
            System.out.println("Pagamento vinculado ao perfil: " + perfilAtivo);
        }

        System.out.println("Processando pagamento... Sucesso!");
        produtos.clear(); // Limpa a lista após o pagamento
    }

    // Métodos auxiliares para evitar erros de leitura do Scanner
    private static int lerInteiro() {
        try {
            int n = Integer.parseInt(scanner.nextLine());
            return n;
        } catch (Exception e) {
            return -1;
        }
    }

    private static double lerDouble() {
        try {
            double d = Double.parseDouble(scanner.nextLine());
            return d;
        } catch (Exception e) {
            return 0.0;
        }
    }
}
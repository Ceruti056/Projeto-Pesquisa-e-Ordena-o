import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import ordenacao.Item;
import ordenacao.VetorRan;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static VetorRan vetor = new VetorRan();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Criar vetor com números aleatórios");
            System.out.println("2 - Carregar vetor de arquivo (.txt)");
            System.out.println("3 - Exibir maior e menor valor");
            System.out.println("4 - Calcular a moda do vetor");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    vetor.criarVetorAleatorio();
                    break;
                case 2:
                   //carregarDeArquivo();
                    break;
                case 3:
                    //encontrarMaiorMenor();
                    break;
                case 4:
                    vetor.calcularModa();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void gerarArquivo() {
        try {
            System.out.print("Digite a quantidade de números inteiros a serem gerados: ");
            int quantidade = Integer.parseInt(sc.nextLine());

            if (quantidade <= 0) {
                System.out.println("Erro: A quantidade deve ser um número positivo!");
                return;
            }

            System.out.print("Digite o nome do arquivo (ex: dados_teste.txt): ");
            String nomeArquivo = sc.nextLine().trim();

            if (nomeArquivo.isEmpty()) {
                System.out.println("Erro: O nome do arquivo não pode estar vazio!");
                return;
            }

            if (!nomeArquivo.toLowerCase().endsWith(".txt")) {
                nomeArquivo += ".txt";
            }

            vetor = new Item[quantidade];

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (int i = 0; i < quantidade; i++) {
                    int numero = random.nextInt(1000) + 1;
                    vetor[i] = new Item();
                    vetor[i].setChave(numero);
                    writer.write(String.valueOf(numero));
                    writer.newLine();
                }
            }

            System.out.println("Arquivo " + nomeArquivo + " criado com sucesso!");

        } catch (NumberFormatException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void lerExibirArquivo() {
        System.out.print("Digite o nome do arquivo a ser lido: ");
        String nomeArquivo = sc.nextLine().trim();

        if (!nomeArquivo.toLowerCase().endsWith(".txt")) {
            nomeArquivo += ".txt";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String[] linhas = reader.lines().toArray(String[]::new);
            vetor = new Item[linhas.length];
            for (int i = 0; i < linhas.length; i++) {
                vetor[i] = new Item();
                vetor[i].setChave(Integer.parseInt(linhas[i]));
            }

            System.out.println("\nNúmeros carregados:");
            for (Item n : vetor) {
                System.out.print(n.getChave() + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}

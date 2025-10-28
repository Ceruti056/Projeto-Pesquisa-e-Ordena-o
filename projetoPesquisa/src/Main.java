import java.util.Scanner;
import ordenacao_novo.Pesquisa;
import ordenacao_novo.VetorRan;
/**
 * Trabalho de Pesquisa e Ordenação
 * Autores: Gabriel Araujo Ceruti Castro, Heitor Lopes Trindade Fadlalah
 * Professora: Renata Laranja Leite
/**
/**
 * Classe principal do programa.
 * 
 * Responsável por:
 * - Exibir o menu principal e controlar o fluxo da aplicação.
 * - Chamar métodos das classes auxiliares (VetorRan e Pesquisa).
 * - Permitir a execução de diferentes operações: criação de vetor,
 *   leitura de arquivo, análise estatística e testes de desempenho.
 * 
 * O projeto foi desenvolvido com modularidade e tratamento de erros
 * para garantir boa escalabilidade mesmo com vetores grandes.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        // Loop principal do menu
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Criar arquivo com vetor de números aleatórios");
            System.out.println("2 - Exibir arquivo lido");
            System.out.println("3 - Exibir maior e menor valor");
            System.out.println("4 - Calcular a moda do vetor");
            System.out.println("5 - Pesquisas (Sequencial, Binária, Hashing)");
            System.out.println("6 - Comparar desempenho das pesquisas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // Tratamento de erro de entrada inválida
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
                continue;
            }

            // Estrutura de decisão para cada funcionalidade
            switch (opcao) {
                case 1:
                    VetorRan.gerarArquivoVetorRandomico();
                    break;
                case 2:
                    VetorRan.lerExibirArquivo();
                    break;
                case 3:
                    VetorRan.encontrarMaiorMenor();
                    break;
                case 4:
                    VetorRan.calcularModa();
                    break;
                case 5:
                    menuPesquisas();
                    break;
                case 6:
                    compararDesempenho();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }

        } while (opcao != 0); // repete até o usuário escolher sair

        sc.close();
    }

    /**
     * Exibe um submenu com as opções de pesquisa e executa a busca escolhida.
     * Permite ao usuário comparar diferentes algoritmos de busca em um vetor.
     */
    private static void menuPesquisas() {
        Scanner sc = new Scanner(System.in);
        int[] numeros = VetorRan.getVetorComoArray();
        int tipo = -1;
        boolean encontrado = false;

        if (numeros == null) {
            System.out.println("Crie ou carregue o vetor primeiro!");
            return;
        }

        System.out.print("Digite o valor que deseja buscar: ");
        int x = sc.nextInt();

        System.out.println("\n===== TIPOS DE PESQUISA =====");
        System.out.println("1 - Pesquisa Sequencial Simples");
        System.out.println("2 - Pesquisa Sequencial Otimizada (vetor ordenado)");
        System.out.println("3 - Pesquisa Binária (vetor ordenado)");
        System.out.println("4 - Pesquisa com Hashing");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha: ");
        tipo = sc.nextInt();


        switch (tipo) {
            case 1:
                encontrado = Pesquisa.pesquisaSeq(x, numeros);
                break;
            case 2:
                VetorRan.quickSort();
                numeros = VetorRan.getVetorComoArray(); 
                encontrado = Pesquisa.pesquisaSeqMelhorada(x, numeros);
                break;
            case 3:
                VetorRan.quickSort();
                numeros = VetorRan.getVetorComoArray();
                encontrado = Pesquisa.pesquisaBinaria(x, numeros);
                break;
            case 4:
                encontrado = Pesquisa.pesquisaHashing(x, numeros);
                break;
            case 0:
                System.out.println("Retornando ao menu principal...");
                return;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        // Exibe o resultado da busca
        if (encontrado)
            System.out.println("Elemento encontrado!");
        else{
            System.out.println("Elemento não encontrado!");
        }
    }

    /**
     * Compara o tempo de execução entre as pesquisas Sequencial e Binária.
     * Mede o desempenho usando System.nanoTime() para precisão em nanossegundos.
     */
    private static void compararDesempenho() {
        int[] numeros = VetorRan.getVetorComoArray();
        Scanner sc = new Scanner(System.in);
        if (numeros == null) {
            System.out.println("Crie ou carregue o vetor primeiro!");
            return;
        }

        try {
            System.out.print("Digite o valor que deseja buscar: ");
            int x = Integer.parseInt(sc.nextLine());

        // --- Pesquisa Sequencial ---
        long inicioSeq = System.nanoTime();
        boolean achouSeq = Pesquisa.pesquisaSeq(x, numeros);
        long fimSeq = System.nanoTime();

        // --- Pesquisa Binária ---
        VetorRan.quickSort(); // vetor precisa estar ordenado
        long inicioBin = System.nanoTime();
        boolean achouBin = Pesquisa.pesquisaBinaria(x, numeros);
        long fimBin = System.nanoTime();

        long tempoSeq = fimSeq - inicioSeq;
        long tempoBin = fimBin - inicioBin;

        // Exibição dos resultados
        System.out.println("\n===== COMPARATIVO DE DESEMPENHO =====");
        System.out.println("Sequencial: " + tempoSeq + " ns (" + (achouSeq ? "encontrado" : "não encontrado") + ")");
        System.out.println("Binária: " + tempoBin + " ns (" + (achouBin ? "encontrado" : "não encontrado") + ")");

        if (tempoSeq < tempoBin)
            System.out.println("Pesquisa Sequencial foi mais rápida.");
        else if (tempoBin < tempoSeq){
            System.out.println("Pesquisa Binária foi mais rápida.");
        }
        else{
            System.out.println("Ambas tiveram tempos iguais.");
        }
        } catch (NumberFormatException e) {
            System.out.println("Erro. Apenas números inteiros são aceitos: " + e.getMessage());
        }
    }
}

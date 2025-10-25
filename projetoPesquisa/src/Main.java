import java.util.Scanner;

import ordenacao.VetorRan;
import pesquisa_seq.Pesquisa;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Criar arquivo com vetor de números aleatórios");
            System.out.println("2 - Exibir arquivo lido");
            System.out.println("3 - Exibir maior e menor valor");
            System.out.println("4 - Calcular a moda do vetor");
            System.out.println("5 - Pesquisas (Sequencial, Binária, Hashing)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

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
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void menuPesquisas() {
        Scanner sc = new Scanner(System.in);
        int[] numeros = VetorRan.getVetorComoArray(); // método que retorna o vetor como int[]
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
        System.out.println("0 - Retornar ao menu principal");
        System.out.print("Escolha: ");
        int tipo = sc.nextInt();

        switch (tipo) {
            case 1:
                encontrado = Pesquisa.pesquisaSeq(x, numeros);
                break;
            case 2:
                VetorRan.ordenarVetor();
                encontrado = Pesquisa.pesquisaSeqMelhorada(x, numeros);
                break;
            case 3:
                VetorRan.ordenarVetor();
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
        }

        if (encontrado) {
            System.out.println("Elemento encontrado!");
        } else {
            System.out.println("Elemento não encontrado!");
        }
    }
}

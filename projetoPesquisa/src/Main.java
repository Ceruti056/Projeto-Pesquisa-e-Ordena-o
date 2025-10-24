import java.util.Scanner;
import ordenacao.VetorRan;

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
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
        sc.close();
    }

    
}

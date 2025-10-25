package ordenacao;

import java.io.*;
import java.util.*;

public class VetorRan {
    static Scanner sc = new Scanner(System.in);
    static Item[] vetor;
    static Random random = new Random();

    public static void gerarArquivoVetorRandomico() {
        
        try {
            int quantidade;
            // Solicitar a quantidade de números
            System.out.print("Digite a quantidade de números inteiros a serem gerados: ");
            // quantidade = sc.nextInt();
            // sc.nextLine();
            quantidade = Integer.parseInt(sc.nextLine());

            if (quantidade <= 0) {
                System.out.println("Erro: A quantidade deve ser um número positivo!");
                return;
            }

            vetor = new Item[quantidade];

            if (quantidade <= 0) {
                System.out.println("Erro: A quantidade deve ser um número positivo!");
                return;
            }

            if (quantidade <= 0) {
                System.out.println("Erro: A quantidade deve ser um número positivo!");
                return;
            }

            // Solicitar o nome do arquivo
            System.out.print("Digite o nome do arquivo (ex: dados_teste.txt): ");
            String nomeArquivo = sc.nextLine().trim();

            // Validar o nome do arquivo
            if (nomeArquivo.isEmpty()) {
                System.out.println("Erro: O nome do arquivo não pode estar vazio!");
                return;
            }

            // Garantir que o nome do arquivo termine com .txt
            if (!nomeArquivo.toLowerCase().endsWith(".txt")) {
                nomeArquivo += ".txt";
            }

         

            // Gerar números aleatórios e escrever no arquivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (int i = 0; i < quantidade; i++) {
                    int numero = random.nextInt(10000) + 1;
                    vetor[i] = new Item(numero);
                    //vetor[i].setChave(numero);
                    writer.write(String.valueOf(numero));
                    writer.newLine();
                }
            }

            System.out.println("Arquivo " + nomeArquivo + " criado com sucesso!");

        } catch (NumberFormatException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void lerExibirArquivo() {
        String nomeArquivo;
        // Solicitar o nome do arquivo
        System.out.print("Digite o nome do arquivo a ser lido: ");
        nomeArquivo = sc.nextLine().trim();
        //sc.nextLine().trim();
        if (!nomeArquivo.toLowerCase().endsWith(".txt")) {
            nomeArquivo += ".txt";
        }

        // Ler o arquivo e carregar os números no vetor
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String[] linhas = reader.lines().toArray(String[]::new);
            vetor = new Item[linhas.length];
            for (int i = 0; i < linhas.length; i++) {
                vetor[i] = new Item(i);
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

    public static void quickSort() {
        if (vetor == null) { 
            System.out.println("Carregue os dados primeiro!"); 
            return; 
        }
        ordena(0, vetor.length - 1);
        //exibirResultado("QuickSort");
    }

    public static void ordena(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = vetor[(i + j) / 2].getChave();
        do {
            while (vetor[i].getChave() < pivo) i++;
            while (vetor[j].getChave() > pivo) j--;
            if (i <= j) {
                Item temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        if (esq < j) ordena(esq, j);
        if (i < dir) ordena(i, dir);
    }

    public static void encontrarMaiorMenor() {
        Item maior = vetor[vetor.length - 1];
        Item menor = vetor[vetor.length - 1];
        
        if (vetorVazio()){
            return;
        }

        for (Item valor : vetor) {
            if (valor.getChave() > maior.getChave())
                maior = valor;
            if (valor.getChave() < menor.getChave())
                menor = valor;
        }

        System.out.println("Maior valor: " + maior.getChave());
        System.out.println("Menor valor: " + menor.getChave());
    }

    public static void calcularModa() {
        Item moda = vetor[0];
        int maxFreq = 0;

        if (vetorVazio()){
            return;
        }

        Map<Integer, Integer> frequencia = new HashMap<>();

        for (Item valor : vetor) {
            frequencia.put(valor.getChave(), frequencia.getOrDefault(valor.getChave(), 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : frequencia.entrySet()) {
            if (entry.getValue() > maxFreq) {
                moda = new Item(entry.getKey());
                maxFreq = entry.getValue();
            }
        }
        
        if (maxFreq <= 1) {
            System.out.println("Não há moda (nenhum número se repete no vetor).");
        } else {
            System.out.println("Moda: " + moda + " (repetido " + maxFreq + " vezes)");
        }
    }

    private static boolean vetorVazio() {
        if (vetor == null || vetor.length == 0) {
            System.out.println("Vetor não foi criado ou está vazio.");
            return true;
        }
        return false;
    }
    
    public static int[] getVetorComoArray() {
        if (vetor == null) {
            System.out.println("Vetor não foi criado ou carregado!");
            return null;
        }

        int[] numeros = new int[vetor.length];
        for (int i = 0; i < vetor.length; i++) {
            numeros[i] = vetor[i].getChave();
        }
        return numeros;
    }

    public static void ordenarVetor() {
        if (vetor == null || vetor.length == 0) {
            System.out.println("Vetor não foi criado ou está vazio.");
            return;
        }

        quickSort();
        System.out.println("Vetor ordenado com sucesso!");
    }
}
package ordenacao_novo;

import java.io.*;
import java.util.*;
/**
 * Trabalho de Pesquisa e Ordenação
 * Autores: Gabriel Araujo Ceruti Castro, Heitor Lopes Trindade Fadlalah
 * Professora: Renata Laranja Leite
/**
 * Classe responsável pela criação, leitura, ordenação e análise de vetores numéricos.
 * Contém métodos para gerar arquivos, carregar dados e aplicar estatísticas básicas.
 */
public class VetorRan {
    static Scanner sc = new Scanner(System.in);
    static Item[] vetor;
    static Random random = new Random();

    /**
     * Gera um vetor de números aleatórios e salva em arquivo .txt
     */
    public static void gerarArquivoVetorRandomico() {
        int quantidade;
        try {
            System.out.print("Digite a quantidade de números inteiros a serem gerados: ");
            quantidade = Integer.parseInt(sc.nextLine());

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

            // Gera números aleatórios entre 1 e 10000 e grava no arquivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (int i = 0; i < quantidade; i++) {
                    int numero = random.nextInt(10000) + 1;
                    vetor[i] = new Item(numero);
                    writer.write(String.valueOf(numero));
                    writer.newLine();
                }
            }

            System.out.println("Arquivo " + nomeArquivo + " criado com sucesso!");

        } catch (NumberFormatException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Lê um arquivo texto contendo números inteiros e armazena em um vetor de objetos Item.
     */
    public static void lerExibirArquivo() {
        System.out.print("Digite o nome do arquivo a ser lido: ");
        String nomeArquivo = sc.nextLine().trim();
        
        if (!nomeArquivo.toLowerCase().endsWith(".txt")) {
            nomeArquivo += ".txt";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String[] linhas = reader.lines().toArray(String[]::new);
            vetor = new Item[linhas.length];
            for (int i = 0; i < linhas.length; i++) {
                vetor[i] = new Item(Integer.parseInt(linhas[i]));
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

    /**
     * Ordena o vetor de forma crescente usando o algoritmo QuickSort.
     */
    public static void quickSort() {
        if (vetor == null) {
            System.out.println("Carregue os dados primeiro!");
            return;
        }
        ordena(0, vetor.length - 1);
        System.out.println("Vetor ordenado com sucesso!");
    }

    /**
     * Método auxiliar do QuickSort: faz a partição e chamadas recursivas.
     */
    public static void ordena(int esq, int dir) {
        int pivo, i = esq, j = dir;
        Item temp;
        pivo = vetor[(i+j)/2].getChave();

        do {
            while (vetor[i].getChave() < pivo) i++;
            while (vetor[j].getChave() > pivo) j--;
            if (i <= j) {
            temp = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = temp;
            i++;
            j--;
            }
        } while (i <= j);
        if (esq < j) {
            ordena(esq, j);
        }
        if (dir > i) {
            ordena(i, dir);
        }
    }




    /**
     * Encontra e exibe o maior e o menor valor do vetor.
     */
    public static void encontrarMaiorMenor() {
        Item maior = vetor[0];
        Item menor = vetor[0];

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

    /**
     * Calcula a moda (valor mais frequente) do vetor.
     */
    public static void calcularModa() {
        Map<Integer, Integer> frequencia = new HashMap<>();
        Item moda = vetor[0];
        int maxFreq = 0;

        if (vetorVazio()){
            return;
        }
        
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
            System.out.println("Moda: " + moda.getChave() + " (repetido " + maxFreq + " vezes)");
        }
    }

    /**
     * Verifica se o vetor está vazio ou não foi criado.
     */
    private static boolean vetorVazio() {
        if (vetor == null || vetor.length == 0) {
            System.out.println("Vetor não foi criado ou está vazio.");
            return true;
        }
        return false;
    }

    /**
     * Retorna o vetor de objetos como um vetor simples de inteiros.
     */
    public static  int[] getVetorComoArray() {
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
}

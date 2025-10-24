package pesquisa;

import java.util.LinkedList;
import java.util.Scanner;

import ordenacao.Item;
import ordenacao.VetorRan;


public class PesquisaSequencial {

    public static void menuPesquisas() {
        Scanner sc = new Scanner(System.in);
        Item vetorItem = new Item(0);
        System.out.print("Informe o valor a ser buscado: ");
        int valor = sc.nextInt();

        System.out.println("\n1 - Sequencial Simples");
        System.out.println("2 - Sequencial Otimizada");
        System.out.println("3 - Binária");
        System.out.println("4 - Hashing");
        System.out.print("Escolha o tipo de pesquisa: ");
        int tipo = sc.nextInt();

        boolean encontrado = false;
        switch (tipo) {
            case 1:
                encontrado = pesquisaSequencial(valor, vetorItem.getChave());
                break;
            case 2:
                VetorRan.quickSort();
                encontrado = pesquisaSequencialMelhorada(valor, vetorItem.getChave());
                break;
            case 3:
                VetorRan.quickSort();
                encontrado = pesquisaBinaria(valor, vetorItem.getChave());
                break;
            case 4:
                encontrado = pesquisaHashing(valor, vetorItem.getChave());
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (encontrado)
            System.out.println("Valor encontrado!");
        else
            System.out.println("Valor não encontrado!");
    }

    private static void compararDesempenho() {
        int[] numeros = vetor.getVetorInt();
        if (numeros == null || numeros.length == 0) {
            System.out.println("Crie ou carregue um vetor primeiro!");
            return;
        }

        System.out.print("Informe o valor a ser buscado: ");
        int valor = sc.nextInt();

        long inicio1 = System.nanoTime();
        Pesquisa.pesquisaSequencial(valor, numeros);
        long fim1 = System.nanoTime();

        vetor.quickSort();
        long inicio2 = System.nanoTime();
        Pesquisa.pesquisaBinaria(valor, numeros);
        long fim2 = System.nanoTime();

        System.out.println("\nTempo da pesquisa sequencial: " + (fim1 - inicio1) + " ns");
        System.out.println("Tempo da pesquisa binária:    " + (fim2 - inicio2) + " ns");

        if ((fim1 - inicio1) > (fim2 - inicio2))
            System.out.println("Binária foi mais eficiente!");
        else
            System.out.println("Sequencial foi mais eficiente!");
    }
    
    static Scanner sc = new Scanner(System.in);
    static Item[] vetor;
    public static boolean pesquisaSequencial(int x, Item[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            if (x == vetor[i].getChave())
                return true;
        }
        return false;
    }

    public static boolean pesquisaSequencialMelhorada(int x, int numeros[]) {
        for (int i = 0; i < numeros.length && x >= numeros[i]; i++) {
            if (x == numeros[i])
                return true;
        }
        return false;
    }

    public static boolean pesquisaBinaria(int x, int numeros[]) {
        int inicio = 0;
        int fim = numeros.length - 1;
        int meio;
        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            if (x == numeros[meio])
                return true;
            if (x < numeros[meio])
                fim = meio - 1;
            else
                inicio = meio + 1;
        }
        return false;
    }

    public static boolean pesquisaSequencial(String x, String palavras[]) {
        for (int i = 0; i < palavras.length; i++) {
            if (x.equalsIgnoreCase(palavras[i]))
                return true;
        }
        return false;
    }

    public static boolean pesquisaHashing(int x, int numeros[]) {
        int M = numeros.length;
        LinkedList<Integer>[] tabela = new LinkedList[M];
        for (int i = 0; i < M; i++)
            tabela[i] = new LinkedList<>();
        for (int valor : numeros) {
            int pos = valor % M;
            tabela[pos].add(valor);
        }
        int pos = x % M;
        return tabela[pos].contains(x);
    }

   
}

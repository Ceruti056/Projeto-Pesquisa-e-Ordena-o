package ordenacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VetorRan {
    final static int tam = 5;
    static Item[] vetor;


    public void criarVetorAleatorio() {
        vetor = new Item[tam];
        Random random = new Random();

        for (int i = 0; i < tam; i++) {
            vetor[i] = new Item(random.nextInt(100)); // números aleatórios de 0 a 99
        }
    }

    private static void quickSort() {
        if (vetor == null) { System.out.println("Carregue os dados primeiro!"); return; }
        ordena(0, vetor.length - 1);
        //exibirResultado("QuickSort");
    }

    private static void ordena(int esq, int dir) {
        vetor = new Item[tam];
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

    public void encontrarMaiorMenor() {
        if (vetorVazio())
            return;

        Item maior = vetor[0];
        Item menor = vetor[0];

        for (Item valor : vetor) {
            if (valor.getChave() > maior.getChave())
                maior = valor;
            if (valor.getChave() < menor.getChave())
                menor = valor;
        }

        System.out.println("Maior valor: " + maior);
        System.out.println("Menor valor: " + menor);
    }

     

    public void calcularModa() {
        if (vetorVazio())
            return;

        Map<Integer, Integer> frequencia = new HashMap<>();

        for (Item valor : vetor) {
            frequencia.put(valor.getChave(), frequencia.getOrDefault(valor.getChave(), 0) + 1);
        }

        Item moda = vetor[0];
        int maxFreq = 0;

        for (Map.Entry<Integer, Integer> entry : frequencia.entrySet()) {
            if (entry.getValue() > maxFreq) {
                moda = new Item(entry.getKey());
                maxFreq = entry.getValue();
            }
        }

        System.out.println("Moda: " + moda.getChave() + " (repetido " + maxFreq + " vezes)");
    }

    private static boolean vetorVazio() {
        if (vetor == null || vetor.length == 0) {
            System.out.println("Vetor não foi criado ou está vazio.");
            return true;
        }
        return false;
    }

    public void exibirVetor() {
        System.out.print("Vetor: ");
        for (Item v : vetor) {
            System.out.print(v.getChave() + " ");
        }
        System.out.println();
    }
}

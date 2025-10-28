package ordenacao_novo;

import java.util.LinkedList;
/**
 * Trabalho de Pesquisa e Ordenação
 * Autores: Gabriel Araujo Ceruti Castro, Heitor Lopes Trindade Fadlalah
 * Professora: Renata Laranja Leite
/**
/**
 * Classe responsável por implementar diferentes algoritmos de pesquisa.
 * Inclui versões sequenciais, binária e hashing.
 */
public class Pesquisa {

    /**
     * Pesquisa Sequencial Simples - percorre o vetor comparando cada elemento.
     */
    public static boolean pesquisaSeq(int x, int numeros[]) {
        for (int i = 0; i < numeros.length; i++) {
            if (x == numeros[i])
                return true;
        }
        return false;
    }

    /**
     * Pesquisa Sequencial Melhorada - interrompe quando o valor no vetor
     * ultrapassa o elemento buscado (apenas se o vetor estiver ordenado).
     */
    public static boolean pesquisaSeqMelhorada(int x, int numeros[]) {
        VetorRan.quickSort(); // Ordena o vetor antes da busca
        for (int i = 0; i < numeros.length && x >= numeros[i]; i++) {
            if (x == numeros[i])
                return true;
        }
        return false;
    }

    /**
     * Pesquisa Binária - algoritmo eficiente de busca em vetores ordenados.
     */
    public static boolean pesquisaBinaria(int x, int numeros[]) {
        //VetorRan.quickSort(); // Ordena o vetor antes da busca
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

    /**
     * Pesquisa Sequencial para Strings - comparação ignorando maiúsculas/minúsculas.
     */
    public static boolean pesquisaSequencial(String x, String palavras[]) {
        for (String palavra : palavras) {
            if (x.equalsIgnoreCase(palavra)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pesquisa com Hashing - cria uma tabela hash simples para acelerar a busca.
     */
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

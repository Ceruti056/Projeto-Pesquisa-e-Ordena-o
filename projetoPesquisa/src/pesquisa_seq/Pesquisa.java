package pesquisa_seq;

import java.util.InputMismatchException;
import java.util.LinkedList;

public class Pesquisa {

    public static boolean pesquisaSeq(int x, int numeros[]) {
        try {
            for (int i = 0; i < numeros.length; i++) {
                if (x == numeros[i])
                    return true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        }
        return false;
    }

    public static boolean pesquisaSeqMelhorada(int x, int numeros[]) {
        try {
            for (int i = 0; i < numeros.length && x >= numeros[i]; i++) {
                if (x == numeros[i])
                    return true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        }
        return false;
    }

    public static boolean pesquisaBinaria(int x, int numeros[]) {
        int inicio = 0;
        int fim = numeros.length - 1;
        int meio;
        try {
            while (inicio <= fim) {
                meio = (inicio + fim) / 2;
                if (x == numeros[meio])
                    return true;
                if (x < numeros[meio])
                    fim = meio - 1;
                else
                    inicio = meio + 1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        }
        return false;
    }

    public static boolean pesquisaSequencial(String x, String palavras[]) {
        try {
            for (int i = 0; i < palavras.length; i++) {
                if (x.equalsIgnoreCase(palavras[i]))
                    return true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        }
        return false;
    }

    public static boolean pesquisaHashing(int x, int numeros[]) {
        int M = numeros.length;
        int pos1, pos2;
        try {
            LinkedList<Integer>[] tabela = new LinkedList[M];
            for (int i = 0; i < M; i++)
                tabela[i] = new LinkedList<>();
            for (int valor : numeros) {
                pos1 = valor % M;
                tabela[pos1].add(valor);
            }
            pos2 = x % M;
            return tabela[pos2].contains(x);
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        }
        return false;
    }
}

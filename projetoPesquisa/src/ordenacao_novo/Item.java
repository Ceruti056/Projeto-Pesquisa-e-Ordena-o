package ordenacao_novo;
/**
 * Trabalho de Pesquisa e Ordenação
 * Autores: Gabriel Araujo Ceruti Castro, Heitor Lopes Trindade Fadlalah
 * Professora: Renata Laranja Leite
/**
/**
 * Classe Item representa um elemento do vetor com uma chave inteira.
 * É usada para encapsular o valor e permitir futuras extensões (ex: adicionar mais atributos).
 */
public class Item {
    private int chave;

    public Item(int chave) {
        this.chave = chave;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }
}

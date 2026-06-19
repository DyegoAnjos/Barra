package main;

import heap.Heap;
import heap.TipoHeap;

public class Barra {
    private Heap subBarras = new Heap<Barra, Integer>(TipoHeap.MIN, (barra)-> barra.getTamanho());
    private int tamanho;

    public Barra(int tamanho){
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int compareTo(Barra outro){
        return this.tamanho - outro.getTamanho();
    }

    public int soldarBarra(Barra barra){
        subBarras.inserir(barra);
        tamanho+=barra.getTamanho();

        return tamanho;
    }


    @Override
    public String toString() {
        if (subBarras.quantidade() == 0) {
            return "" + this.tamanho;
        }

        Heap<Barra, Integer> heapTemporario = new Heap<Barra, Integer>(TipoHeap.MIN, (b) -> b.getTamanho());

        String retorno = "";
        int qtdOriginal = subBarras.quantidade();

        for (int i = 0; i < qtdOriginal; i++) {
            Barra barraIndice = (Barra) subBarras.remover();

            if (barraIndice != null) {
                if (i == 0) {
                    retorno += barraIndice;
                }
                else {
                    retorno += "-" + barraIndice;
                }
                heapTemporario.inserir(barraIndice);
            }
        }

        while (heapTemporario.quantidade() > 0) {
            subBarras.inserir(heapTemporario.remover());
        }

        return retorno;
    }
}

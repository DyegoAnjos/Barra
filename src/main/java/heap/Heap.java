package heap;

import java.util.function.Function;

public class Heap<T, K extends Comparable<K>> implements IHeap<T> {

    private static final int TAM_MIN = 15;

    private int quantidade;
    private final TipoHeap tipo;
    private T[] v;
    private final Function<T, K> prioridade;

    public Heap(TipoHeap tipo) {
        this(tipo, (o) -> (K) o);
    }

    public Heap(TipoHeap tipo, Function<T, K> prioridade) {
        this.tipo = tipo;
        this.prioridade = prioridade;
        v = (T[]) new Object[TAM_MIN];
    }

    @Override
    public boolean inserir(T e) {
        if (quantidade == v.length)
            try {
                aumentaVetor();
            } catch(OutOfMemoryError ex) {
                return false;
            }

        int pos = quantidade;
        int pai = (pos-1) / 2;

        quantidade++;

        K p = prioridade.apply(e);

        if (tipo == TipoHeap.MAX) {
            while (pos > 0 && prioridade.apply(v[pai]).compareTo(p) < 0) {
                v[pos] = v[pai];
                pos = pai;
                pai = (pos-1) / 2;
            }
        }
        else {
            while (pos > 0 && prioridade.apply(v[pai]).compareTo(p) > 0) {
                v[pos] = v[pai];
                pos = pai;
                pai = (pos-1) / 2;
            }
        }

        v[pos] = e;

        return true;
    }

    @Override
    public T remover() {
        if (quantidade == 0)
            return null;

        quantidade--;

        T old = v[0];

        v[0] = v[quantidade];

        v[quantidade] = null;

        if (tipo == TipoHeap.MAX)
            heapfyMAX();
        else
            heapfyMIN();

        if (quantidade > TAM_MIN && quantidade <= v.length/4)
            try {
                diminuiVetor();
            } catch(OutOfMemoryError ex) {
            }

        return old;
    }

    @Override
    public T prioridade() {
        return quantidade != 0 ? v[0] : null;
    }

    @Override
    public boolean pertence(T e) {
        for (int i = 0; i < quantidade; i++)
			if (v[i].equals(e))
				return true;
			
		return false;
    }

    @Override
    public int quantidade() {
        return quantidade;
    }

    @Override
    public boolean estaVazio() {
        return quantidade == 0;
    }

    @Override
    public void removerTodos() {
        quantidade = 0;
        v = (T[]) new Object[TAM_MIN];
    }

    private void heapfyMAX() {
        int maior, esq, dir, raiz = 0;

        while (true) {
            esq = 2 * raiz + 1;
            dir = esq + 1;
            maior = raiz;

            if (esq < quantidade &&
                prioridade.apply(v[esq]).compareTo(prioridade.apply(v[maior])) > 0)
                maior = esq;

            if (dir < quantidade &&
                prioridade.apply(v[dir]).compareTo(prioridade.apply(v[maior])) > 0)
                maior = dir;

            if (maior != raiz) {
                T aux = v[raiz];
                v[raiz] = v[maior];
                v[maior] = aux;
                raiz = maior; // desce na subárvore de maior
            }
            else
                break;
        }
    }

    private void heapfyMIN() {
        int menor, esq, dir, raiz = 0;

        while (true) {
            esq = 2 * raiz + 1;
            dir = esq + 1;
            menor = raiz;

            if (esq < quantidade &&
                prioridade.apply(v[esq]).compareTo(prioridade.apply(v[menor])) < 0)
                menor = esq;

            if (dir < quantidade &&
                prioridade.apply(v[dir]).compareTo(prioridade.apply(v[menor])) < 0)
                menor = dir;

            if (menor != raiz) {
                T aux = v[raiz];
                v[raiz] = v[menor];
                v[menor] = aux;
                raiz = menor;
            }
            else
                break;
        }
    }

    private void aumentaVetor() {
        int novoTam = 2*v.length + 1;
        T[] novo = (T[]) new Object[novoTam];

        System.arraycopy(v, 0, novo, 0, quantidade);

        v = novo;
    }

    private void diminuiVetor() {
        int novoTam = (v.length-1)/2;

        if (novoTam < TAM_MIN)
            novoTam = TAM_MIN;

        T[] novo = (T[]) new Object[novoTam];

        System.arraycopy(v, 0, novo, 0, quantidade);

        v = novo;
    }
}

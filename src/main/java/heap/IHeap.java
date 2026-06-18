package heap;

public interface IHeap<T> {

    /**
     * Insere o item e no heap
     *
     * @param e Item a ser inserido no heap
     * @return  Verdadeiro se a inserção foi bem sucedida; falso, caso contrário
     */
    boolean inserir(T e);

    /**
     * Remove o item de maior prioridade do heap e o retorna
     * @return Item de maior prioridade
     */
    T remover();

    /**
     * Retorna o item de maior prioridade, sem removê-lo do heap
     * @return Item de maior prioridade
     */
    T prioridade();

    /**
     * Verifica se o item e pertence ao heap
     * @param e Item a ser procura no heap
     * @return Verdadeiro se e pertence ao heap; falso, caso contrário
     */
    boolean pertence(T e);

    /**
     * Retortna a quantidade de itens d heap
     * @return Quantidade de itens do heap
     */
    int quantidade();

    /**
     * Verifica se o heap está vazio
     * @return Verdadeiro se heap está vazio; falso, caso contrário
     */
    boolean estaVazio();

    /**
     * Remove todos os itens do heap
     */
    void removerTodos();
}

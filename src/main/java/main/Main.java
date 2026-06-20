package main;

import heap.Heap;
import heap.TipoHeap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Forneça o nome do arquivo TXT de entrada");
            return;
        }

        String fileName = args[0];

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int qtdTestes = scanner.nextInt();
            for (int i = 0; i < qtdTestes; i++){
                Heap heapBarras = new Heap<Barra, Integer>(TipoHeap.MIN,(barra)-> barra.getTamanho() );
                int qtdBarras = scanner.nextInt();
                int qtdSoldas = scanner.nextInt();
                for (int j = 0; j < qtdBarras; j++){
                    int tamanhoBarra = scanner.nextInt();
                    Barra barra = new Barra(tamanhoBarra);
                    heapBarras.inserir(barra);

                }
                while (heapBarras.quantidade() > 1){
                    Barra novaBarra = new Barra(0);
                    if(heapBarras.quantidade() < qtdSoldas){
                        while (!heapBarras.estaVazio()){
                            Barra barraSoldar = (Barra) heapBarras.remover();
                            novaBarra.soldarBarra(barraSoldar);
                            System.out.print(barraSoldar + " ");
                        }
                    }
                    else
                        for ( int j = 0; j < qtdSoldas; j++){
                            Barra barraSoldar = (Barra) heapBarras.remover();
                            novaBarra.soldarBarra(barraSoldar);
                            System.out.print(barraSoldar + " ");
                        }
                    System.out.println();
                    heapBarras.inserir(novaBarra);

                }
                System.out.println();
                heapBarras.removerTodos();
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}

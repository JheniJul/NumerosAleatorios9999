/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license */

package com.mycompany.aula12;

/*@author sonha */
import java.util.Random;

class No {
    int valor;
    No proximo;
    No anterior;

    public No(int valor) {
        this.valor = valor;
        this.proximo = null;
        this.anterior = null;
    }
}

class ListaDupla {
    No inicio;
    No fim;

    public ListaDupla() {
        this.inicio = null;
        this.fim = null;
    }

    public void inserirNaPosicaoCorreta(int valor) {
        No novoNo = new No(valor);

        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
        } else if (valor <= inicio.valor) {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        } else if (valor >= fim.valor) {
            fim.proximo = novoNo;
            novoNo.anterior = fim;
            fim = novoNo;
        } else {
            No atual = inicio;
            while (atual != null && valor > atual.valor) {
                atual = atual.proximo;
            }

            novoNo.proximo = atual;
            novoNo.anterior = atual.anterior;
            atual.anterior.proximo = novoNo;
            atual.anterior = novoNo;
        }
    }

    public void removerPrimos() {
        No atual = inicio;

        while (atual != null) {
            if (ehPrimo(atual.valor)) {
                if (atual == inicio) {
                    inicio = atual.proximo;
                    if (inicio != null) {
                        inicio.anterior = null;
                    }
                } else if (atual == fim) {
                    fim = atual.anterior;
                    if (fim != null) {
                        fim.proximo = null;
                    }
                } else {
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                }
            }

            atual = atual.proximo;
        }
    }

    private boolean ehPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void imprimirCrescente() {
        No atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor);
            if (atual.proximo != null) {
                System.out.print(", ");
            }
            atual = atual.proximo;
        }
        System.out.println(); // Adiciona uma quebra de linha no final
    }

    public void imprimirDecrescente() {
        No atual = fim;
        while (atual != null) {
            System.out.print(atual.valor);
            if (atual.anterior != null) {
                System.out.print(", ");
            }
            atual = atual.anterior;
        }
        System.out.println(); // Adiciona uma quebra de linha no final
    }
}

public class Aula12 {

    public static void main(String[] args) {
        // Definindo o tamanho do vetor
        int tamanhoDoVetor = 1000;

        // Criando o vetor para armazenar os números aleatórios
        int[] vetor = new int[tamanhoDoVetor];

        // Criando a lista dupla encadeada
        ListaDupla lista = new ListaDupla();

        // Gerando e armazenando os números aleatórios no vetor
        gerarNumeros(vetor);

        // Inserindo os números na lista em ordem crescente
        for (int i = 0; i < tamanhoDoVetor; i++) {
            lista.inserirNaPosicaoCorreta(vetor[i]);
        }

        // Exibindo os números gerados na ordem de geração
        System.out.println("Números aleatórios gerados:");
        for (int i = 0; i < tamanhoDoVetor; i++) {
            System.out.print(vetor[i]);
            if (i < tamanhoDoVetor - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n\n----- EM ORDEM -----"); 

        // Exibindo os números inseridos na lista em ordem crescente
        System.out.println("Ordem Crescente:");
        lista.imprimirCrescente();
        
        // Exibindo a lista em ordem decrescente
        System.out.println("\nOrdem Decrescente:");
        lista.imprimirDecrescente();
    
        // Removendo os números primos da lista
        lista.removerPrimos();
        System.out.println("\n----- EM ORDEM APOS REMOCAO DE PRIMOS -----");
        // Exibindo a lista após a remoção dos números primos
        System.out.println("Ordem Crescente:");
        lista.imprimirCrescente();

        System.out.println("\nOrdem Decrescente:");
        lista.imprimirDecrescente();
        }

    private static void gerarNumeros(int[] vetor) {
        Random random = new Random();

        for (int i = 0; i < vetor.length; i++) {
            // Gerando números aleatórios no intervalo de -9999 a 9999
            vetor[i] = random.nextInt(19999) - 9999;
        }
    }
}

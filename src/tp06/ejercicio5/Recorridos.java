package tp06.ejercicio5;

import tp02.ejercicio2.*;
import tp02.ejercicio3.ColaGenerica;
import tp06.ejercicio3.*;

public class Recorridos<T> {
    public ListaEnlazadaGenerica<Vertice<T>> dfs(Grafo<T> grafo){
        boolean[] marcas = new boolean[grafo.listaDeVertices().tamanio()+1];
        ListaEnlazadaGenerica<Vertice<T>> lista  = new ListaEnlazadaGenerica<Vertice<T>>();

        for (int i = 1; i < marcas.length; i++){
            if (!marcas[i]){
                dfsRecursivo(i,grafo,lista,marcas);
            }
        }

        return lista;
    }

    private void dfsRecursivo(int pos, Grafo<T> grafo, ListaEnlazadaGenerica<Vertice<T>> lista, boolean[] marcas){
        marcas[pos] = true;
        Vertice<T> v = grafo.listaDeVertices().elemento(pos);
        lista.agregarFinal(v);
        ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(v);
        adyacentes.comenzar();
        while(!adyacentes.fin()){
            int prox = adyacentes.proximo().verticeDestino().getPosicion();
            if(!marcas[prox]){
                dfsRecursivo(prox,grafo,lista,marcas);
            }
        }
    }

    public ListaEnlazadaGenerica<Vertice<T>> bfs(Grafo<T> grafo){
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
        ListaEnlazadaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();

        for(int i = 1; i < marca.length; i++){
            if(!marca[i]){
                bfsIterativo(i,grafo,lista,marca);
            }
        }

        return lista;
    }

    private void bfsIterativo(int pos, Grafo<T> grafo, ListaEnlazadaGenerica<Vertice<T>> lista, boolean[] marca){
        ListaGenerica<Arista<T>> adyacentes = null;
        ColaGenerica<Vertice<T>> q = new ColaGenerica<Vertice<T>>();

        q.encolar(grafo.listaDeVertices().elemento(pos));
        marca[pos] = true;

        while(!q.esVacia()){
            Vertice<T> v = q.desencolar();
            adyacentes = grafo.listaDeAdyacentes(v); //se crea una lista con los adyacentes del vertice act
            adyacentes.comenzar();
            while(!adyacentes.fin()){
                Arista<T> a = adyacentes.proximo();
                int prox = a.verticeDestino().getPosicion();
                if(!marca[prox]){
                    Vertice<T> w = a.verticeDestino();
                    marca[prox] = true;
                    q.encolar(w);
                }
            }
        }
    }
}

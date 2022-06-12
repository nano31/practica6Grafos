package ejerciciosTeoria;

import tp02.ejercicio2.*;
import tp06.ejercicio3.*;

public class Recorridos{
    public ListaGenerica<ListaGenerica<Vertice<T>>> dfsConCosto (Grafo<T> grafo){
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
        ListaGenerica<Vertice<T>> lis = null;
        ListaGenerica<ListaGenerica<Vertice<T>>> recorridos =
                    new ListaGenerica<ListaGenerica<Vertice<T>>>();

        int costo = 0;
        for (int i = 1; i < marca.length; i++) {
            lis = new ListaEnlazadaGenerica<Vertice<T>>();
            lis.agregarFinal(grafo.listaDeVertices().elemento(i));
            marca[i] = true;
            dfsConCosto(i,grafo,lis,marca,costo,recorridos);
            marca[i] = false;
        }
        return recorridos;
    }

    private void dfsConCosto(int i, Grafo<T> grafo, ListaGenerica<Vertice<T>> lis,
                             boolean[] marca, int costo, ListaGenerica<ListaGenerica<Vertice<T>>> recorridos){
        Vertice<T> vDestino = null; int p = 0, j = 0;
        Vertice<T> v = grafo.listaDeVertices().elemento(i);
        ListaGenerica<T> ady = grafo.listaDeAdyacentes(v);
        ady.comenzar();
        while(!ady.fin()){
            Arista<T> arista = ady.proximo();
            j = arista.verticeDestino().getPosicion();
            if (!marca[j]) {
                p = arista.peso();
                if(costo + p < 10){
                    vDestino = arista.verticeDestino();
                    lis.agregarFinal(vDestino);
                    marca[j] = true;
                    if(costo+p == 10){
                        recorridos.agregarFinal(lis.clonar());
                    }else{
                        dfsConCosto(j, grafo, lis, marca, costo+p, recorridos);
                    }
                    lis.eliminar(vDestino);
                    marca[j] = false;
                }
            }
        }
    }
}
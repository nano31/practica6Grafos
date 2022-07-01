package ejerciciosTeoria.costoTotalCamino;

import tp02.ejercicio2.*;
import tp06.ejercicio3.*;

public class RecorridosTeoria {
    public ListaGenerica<ListaGenerica<Vertice>> dfsConCosto(Grafo<T> grafo){
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
        ListaGenerica<Vertice<T>> lista = null;
        ListaGenerica<ListaGenerica<Vertice<T>>> recorridos =
                    new ListaGenerica<ListaGenerica<Vertice<T>>>();

        int costo = 0;
        for(int i = 1; i < marca.length; i++){
            lista = new ListaGenericaEnlazada<Vertice<T>>();
            lista.agregarFinal(grafo.listaDeVertices().elemento(i));
            marca[i] = true;
            dfsConCostoRecursivo(i,grafo,lista,marca,costo,recorridos);
            marca[i] = false;
        }

        return recorridos;
    }

    private void dfsConCostoRecursivo(int pos,Grafo<T> grafo,ListaGenerica<T>lista,boolean[] marca,int costo,ListaGenerica<Vertice<T>>recorridos){
        Vertice<T> vDestino = null; int p = 0, j = 0;
        Vertice<T> v = grafo.listaDeVertices().elemento(pos);
        ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);
        ady.comenzar();
        while(!ady.fin()){
            Arista<T> arista = ady.proximo();
            j = arista.verticeDestino().getPosicion();
            if(!marca[j]){
                p = arista.peso();
                if((costo + p) < 10 ){
                    vDestino = arista.verticeDestino();
                    lista.agregarFinal(vDestino);
                    marca[j] = true;
                    if((costo + p) == 10){
                        recorridos.agregarFinal(lista.clonar());
                    }else{
                        dfsConCostoRecursivo(j,grafo,lista,marca,costo+p,recorridos);
                    }
                    lista.eliminarEn(j);
                    marca[j] = false;
                }
            }
        }
    }

}

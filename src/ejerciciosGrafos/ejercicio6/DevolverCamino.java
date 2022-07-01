package ejerciciosGrafos.ejercicio6;

import tp02.*;
import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp06.*;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.Vertice;
import tp06.ejercicio3.Arista;

//se debe devolver el camino desde origen a destino, donde las aristas tomandas, no pueden superar un valor
//distancia maxima pasado como parametro

public class DevolverCamino {
    private Grafo<String> grafo;

    public Vertice<String> buscarOrigen(String origen){
        ListaGenerica<Vertice<String>> vertices = this.grafo.listaDeVertices();
        vertices.comenzar();
        while(!vertices.fin()){
            Vertice<String> vertice = vertices.proximo();
            if(vertice.dato().equals(origen)){
                return vertice;
            }
        }
        return null;
    }

    public ListaGenerica<String> devolverCamino(String origen, String destino, int distanciaMaxima){
        ListaEnlazadaGenerica<String> lista = null;
        ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
        boolean[] marca = new boolean[this.grafo.listaDeVertices().tamanio()];
        boolean encontre = false;
        Vertice<String> vOrigen = buscarOrigen(origen);
        int pos = vOrigen.getPosicion();
        lista.agregarFinal(vOrigen.dato());
        marca[pos] = true;
        dfs(pos,this.grafo,lista,camino,destino,marca,distanciaMaxima,encontre);
        return camino;
    }

    private void dfs(int i,Grafo<String> grafo, ListaEnlazadaGenerica<String> lista, ListaEnlazadaGenerica<String>camino,
                     String destino, boolean[] marca, int dMax,boolean encontre){
        Vertice<String> vDest = null; int peso;
        Vertice<String> origen = grafo.listaDeVertices().elemento(i);
        ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(origen);
        ady.comenzar();
        while(!ady.fin() && !encontre){
            Arista<String> arista = ady.proximo();
            int j = arista.verticeDestino().getPosicion();
            vDest = arista.verticeDestino();
            peso = arista.peso();
            if(peso <= dMax){
                lista.agregarFinal(vDest.dato());
                marca[j] = true;
                if(grafo.listaDeVertices().elemento(i).dato().equals(destino)){
                        lista.clonar(lista,camino);
                        encontre = true;
                }else{
                    lista.eliminarEn(lista.tamanio());
                    marca[i] = false;
                }
            }else{
                dfs(j,grafo,lista,camino,destino,marca,dMax,encontre);
            }
        }
       
    }
}

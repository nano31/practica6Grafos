package ejerciciosGrafos.ejercicio6;

import tp02.ejercicio2.*;
import tp06.ejercicio3.*;

public class VisitaOslo {
    private Grafo<String> mapaCiudades;

    public VisitaOslo(Grafo<String> mapaCiudades){
        this.mapaCiudades = mapaCiudades;
    }


    public void buscarVerticeInicial(String ciudad1,Grafo<String> grafo, boolean[] marca, ListaEnlazadaGenerica<Vertice<String>> lis){
        for(int i = 1; i < marca.length; i++){
            marca[i] = false;
        }

        ListaGenerica<Vertice<String>> vInicial = mapaCiudades.listaDeVertices();
        vInicial.comenzar(); //se busca el vertice en el cual inicia el recorrido

        Vertice<String> v;
        boolean encontrado = false;
        int pos = -1;

        while(!vInicial.fin()){
            v = vInicial.proximo();
            if(v.dato() == ciudad1){
                encontrado = true;
                pos = v.getPosicion();
                lis.agregarFinal(v);
            }
        }
        if(pos != -1){
            marca[pos] = true;
        }
    }

    public ListaGenerica<Vertice<String>>paseoEnBici(Grafo<String> grafo,String destino,int maxTiempo,ListaGenerica<Vertice<String>> lugaresRestringidos){
        ListaEnlazadaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
        ListaEnlazadaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
        String ciudad1 = "gol"; //por poner algo

        buscarVerticeInicial(ciudad1, grafo, marca, lista);

        int tiempo = 0;

        return camino;
    }

    private void dfsPaseoEnBici(){

    }
}

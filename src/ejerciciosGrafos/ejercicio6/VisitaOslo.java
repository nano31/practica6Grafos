package ejerciciosGrafos.ejercicio6;

import tp02.ejercicio2.*;
import tp06.ejercicio3.*;

public class VisitaOslo {
    private Grafo<String> mapaCiudades;

    public VisitaOslo(Grafo<String> mapaCiudades){
        this.mapaCiudades = mapaCiudades;
    }


    public void buscarVerticeInicial(String ciudad1,Grafo<String> grafo, String destino, int maxTiempo, ListaGenerica<String> restringidos){
        ListaEnlazadaGenerica<String> lis = new ListaEnlazadaGenerica<String>;
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
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
                lis.agregarFinal(v.dato());
            }
        }
        if(pos != -1){
            marca[pos] = true;
        }
    }

    public ListaGenerica<String> paseoEnBici(Grafo<String> grafo,String destino,int maxTiempo,ListaGenerica<String> lugaresRestringidos){
        ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
        ListaEnlazadaGenerica<String> lista = new ListaEnlazadaGenerica<String>();
        String ciudad1 = "gol"; //por poner algo

        buscarVerticeInicial(ciudad1, grafo, destino, maxTiempo, lugaresRestringidos);

        return camino;
    }
}

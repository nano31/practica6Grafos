package ejerciciosGrafos.ejercicio6;

import tp02.ejercicio2.*;
import tp06.ejercicio3.*;

public class VisitaOslo {
    private Grafo<String> mapaCiudades;

    public VisitaOslo(Grafo<String> mapaCiudades){
        this.mapaCiudades = mapaCiudades;
    }


    public int buscarVerticeInicial(String lugarInicial,Grafo<String> grafo,boolean[] marca,ListaGenerica<String> restringidos){
        ListaEnlazadaGenerica<String> lis = new ListaEnlazadaGenerica<String>();
        for(int i = 1; i < marca.length; i++){
            marca[i] = false;
        }

        ListaGenerica<Vertice<String>> vertices = mapaCiudades.listaDeVertices();
        vertices.comenzar(); //se busca el vertice en el cual inicia el recorrido

        Vertice<String> vInicio;
        boolean encontrado = false;
        int pos = -1;

        while((!vertices.fin())&&(!encontrado)){
            vInicio = vertices.proximo();
            if((vInicio.dato() == lugarInicial)&&(!restringidos.incluye(lugarInicial))){
                encontrado = true;
                pos = vInicio.getPosicion();
                lis.agregarFinal(vInicio.dato());
            }
        }
        if(pos != -1){
            marca[pos] = true;
        }
        return pos;
    }

    public ListaEnlazadaGenerica<Vertice<String>> paseoEnBici(Grafo<String> grafo,String destino,int maxTiempo,ListaGenerica<String> lugaresRestringidos){
        ListaEnlazadaGenerica<Vertice<String>> camino = new ListaEnlazadaGenerica<Vertice<String>>();
        ListaEnlazadaGenerica<Vertice<String>> lista = new ListaEnlazadaGenerica<Vertice<String>>();
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
        String lugarInicial = "Ingresar un nombre"; //por poner algo

        int tiempoMax = 120;
        int posVerticeInicial = buscarVerticeInicial(lugarInicial,grafo,marca,lugaresRestringidos); //hay que controlar que el vertice inicial no
        //sea un lugar restringido?
        int costoTiempo = 0;
        buscarCamino(posVerticeInicial,grafo,lista,tiempoMax,lugaresRestringidos,costoTiempo,camino,marca,destino);

        return camino;
    }

    public void buscarCamino(int posIni,Grafo<String> grafo,ListaEnlazadaGenerica<Vertice<String>> lista,
                             int tMax,ListaGenerica<String> restringidos, int costoTiempo,
                             ListaEnlazadaGenerica<Vertice<String>> camino, boolean[] marca,String destino){
        int j = 0;
        Vertice<String> inicio = grafo.listaDeVertices().elemento(posIni);
        ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(inicio);
        Vertice<String> vertice = null;
        ady.comenzar();
        while((!ady.fin())&&(!vertice.dato().equals(destino))){
            Arista<String> arista = ady.proximo();
            j = arista.verticeDestino().getPosicion();
            if(!marca[j]){
                costoTiempo += arista.peso();
                vertice = arista.verticeDestino();
                lista.agregarFinal(vertice);
                marca[j] = true;
                if((costoTiempo <= tMax)&&(!restringidos.incluye(vertice.dato()))){
                    camino.clonar(lista,camino);
                }
                lista.eliminarEn(j);
                marca[j] = false;
            }
            buscarCamino(j,grafo,lista,tMax,restringidos,costoTiempo,camino,marca,destino);
        }
    }

}

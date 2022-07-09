/*se tiene un grafo llamado mapa que es variable de instancia de la clase Recorridos.
Se debe ir desde un origen a un destino solamente pasando por aristas que no tengan peso, es decir,
que su peso sea cero.*/
public class Recorridos{
    Grafo<String> mapa;

    public Recorridos(Grafo<String> mapa){
        this.mapa = mapa;
    }

    public ListaGenerica<String> caminoAristasCero(Grafo<String> grafo, String origen, String destino){
        ListaGenerica<String> lista = null;
        ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica();
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
        boolean llegue = false;

        boolean inicio = false;
        int posInicio;
        ListaGenerica<Vertice<String>> vertices = grafo.listaDeVertices;
        vertices.comenzar();
        while(!vertices.fin() && !inicio){
            Vertice<String> vertice = vertices.proximo();
            if(vertice.dato().equals(origen)){
                inicio = true;
                posInicio = vertice.posicion();
                marca[posInicio] = true;
                lista.agregarInicio(vertice.dato);
            }
        }

        buscarCaminoRecursivo(posInicio, grafo, marca, destino, lista, camino, llegue);

        return camino;
    }

    public void buscarCaminoRecursivo(int i, Grafo<String> grafo, boolean[] marca, string destino, ListaGenerica<String> lista
        ListaEnlazadaGenerica<String> camino, boolean llegue){
            Vertice<String> vDestino = null; int j, peso;
            Arista<String> arista;
            ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(grafo.listaDeVertices().elemento(i));
            ady.comenzar();
            while(!ady.fin()){
                arista = ady.proximo();
                vDestino = arista.verticeDestino();
                peso = arista.peso();
                j = vDestino.posicion();
                if(peso == 0 && !llegue){
                    lista.agregarInicio(vDestino.dato());
                    marca[j] = true;
                    if(vDestino.dato().equals(destino)){
                        llegue = true;
                        camino.agregarInicio(lista.clonar());
                    }else{
                        buscarCaminoRecursivo(j,grafo,marca,destino,lista,camino,llegue);
                    }
                    lista.eliminarEn(lista.tamanio());
                    marca[j] = false;
                }
            }
                            }

}
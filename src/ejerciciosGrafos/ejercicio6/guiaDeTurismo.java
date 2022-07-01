//buscar en el grafo y devolver todos los caminos que vayan desde un origen a un destino
//transportando una cierta cantidad maxima de elementos.
//ejemplo si una arista tiene peso 20, esto quiere decir que se pueden transportar
//hasta 20 elementos.
public class Repaso{

    public ListaGenerica<ListaGenerica<String>> devolverRecorridos(Grafo<String> grafo,
    String origen, String destino){
        ListaGenerica<ListaGenerica<String>> lista = null;
        ListaEnlazadaGenerica<ListaEnlazadaGenerica<String>> recorridos =
            new ListaEnlazadaGenerica<ListaEnlazadaGenerica<String>>();
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
        
        ListaGenerica<String> vertices = grafo.listaDeVertices();
        vertices.comenzar();
        boolean encontre = false;
        while(!vertices.fin() && !encontre){
            Vertice<String> vertice = vertices.proximo();
            if(vertice.dato().equals(origen)){
                encontre = true;
                int pos = vertice.getPosicion();
            }
        }
        lista.agregarFinal(vertice.dato());
        marca[pos] = true;
        encontre = false;
        dfs(pos,grafo,lista,recorridos,marca,destino,cantMax,encontre);
        
        return recorridos;
    }

    private void dfs(int i,Grafo<String> grafo, ListaGenerica<ListaGenerica<String>> lista, 
    ListaGenerica<ListaGenerica<String>>recorridos, 
    boolean[]marca, String destino, int cantMax, boolean encontre){
        Vertice<string> origen = grafo.listaDeVertices().elemento(i);
        Vertice<String> vDest = null; Arista<String> arista; int peso;
        ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(origen);
        ady.comenzar();

        while(!ady.fin()&&!encontre){
            arista = ady.proximo();
            vDest = arista.verticeDestino();
            peso = arista.peso();
            int j = arista.verticeDestino().getPosicion();
            if(peso >= cantMax){
                lista.agregarFinal(vDest.dato());
                marca[j] = true;
                if(vDest.dato().equals(destino)){
                    recorridos.agregarFinal(lista.clonar());
                    encontre = true;
                }else{
                    dfs(j,grafo,lisata,recorridos,marca,destino,cantMax,encontre);
                }
                lista.eliminarEn(lista.tamanio());
                marca[j] = false;
            }
        }
    }
}
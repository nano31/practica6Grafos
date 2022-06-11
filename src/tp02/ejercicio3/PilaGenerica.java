package tp02.ejercicio3;

import tp02.ejercicio2.ListaEnlazadaGenerica;

public class PilaGenerica<T> {
    private int tam;

    ListaEnlazadaGenerica<T> lista = new ListaEnlazadaGenerica<>();

    public void apilar(T elem){
        lista.agregarFinal(elem);
    }

    public T desapilar(){
        int pos = lista.tamanio();
        T elem;
        elem = lista.elemento(pos);
        lista.eliminarEn(pos);

        return elem;
    }

    public boolean esVacia(){
        return lista.esVacia();
    }
}

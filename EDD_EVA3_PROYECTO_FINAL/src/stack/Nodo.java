package stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian Abraham Parra Pérez
 * # Control: C18550333
 */
public class Nodo {

    private int valor; //atributo de tipo entero para almacenar el valor
    private Nodo siguiente; //Será el enlace
    private Nodo previo; //Tipo nodo para enlace anterior

    public Nodo() { //Constructor
        siguiente = null; //Siguiente se inicializa a nulo
        previo = null; //previo se inicializa a nulo
    }

    public Nodo(int valor) { //constructor que reciba el valor
        this.valor = valor; //Referencia de valor
        siguiente = null; //Referencia inicializada a nula
        previo = null; //Referencia previa inicializada a nula
    }

    public int getValor() { //Método que obtenga el valor
        return valor; //Regresa el valor
    }

    public void setValor(int valor) { //Método que regrese el valor
        this.valor = valor; //Referencia al valor
    }

    public Nodo getSiguiente() { //Método que obtenga la referencia siguiente
        return siguiente; //Regresa la referencia siguiente
    }

    public void setSiguiente(Nodo siguiente) { //Método que regrese la referencia siguiente
        this.siguiente = siguiente; //Referencia a siguiente
    }

    public Nodo getPrevio() { //Se obtiene la referencia anterior
        return previo; //Se regresa la referencia previa 
    }

    public void setPrevio(Nodo previo) { //Muestra la referencia previa
        this.previo = previo; //Referencia previa
    }

}

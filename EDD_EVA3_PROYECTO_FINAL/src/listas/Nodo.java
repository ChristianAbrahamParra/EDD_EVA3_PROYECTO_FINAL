package listas;

import java.io.Serializable;

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
public class Nodo<T> implements Serializable{
    //Atributo para almacenar datos
    private T dato; //atributo de tipo entero para almacenar el dato
    //Atributo para crear la lista (referencias)
    private Nodo sig; //enlace
    
    public Nodo(){ //Constructor
        sig = null; //la referencia debe ser inicializada a nulo
    }

    public Nodo(T dato) { //constructor que reciba el dato
        this.dato = dato; //Referencia de dato
        this.sig = null; //Referencia inicializada a nulla
    }

    public T getDato() { //Método que obtenga el dato
        return dato; //Regresa el dato
    }

    public void setDato(T dato) { //Método que regrese el dato
        this.dato = dato; //Referencia al dato
    }

    public Nodo getSig() { //Método que obtenga la referencia siguiente
        return sig; //Regresa la referencia siguiente
    }

    public void setSig(Nodo sig) { //Método que regrese la referencia siguiente
        this.sig = sig; //Referencia a siguiente
    }



}

//inicio es una referencia tipo nodo
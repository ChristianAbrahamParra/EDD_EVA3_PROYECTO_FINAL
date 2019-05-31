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
    
    private int valor; //Valor de tipo entero
    private Nodo izq; //Referencia izquierda de tipo Nodo
    private Nodo der; //Referencia derecha de tipo Nodo

    public Nodo() { //Constructor
    
        izq = null; //La referencia izquierda se inicializa en nulo
        der = null; //La referencia derecha se inicializa en nulo
    
    }

    public Nodo(int iVal) { //Método con parámetro
        this.valor = iVal; //Referencia al valor
        this.izq = izq; //Referencia se iguala a la izquierda
        this.der = der; //Referencia se iguala a la derecha
    }

    public int getValor() { //Método que obtenga el valor
        return valor; //Regresa el valor
    }

    public void setValor(int valor) { //Método que regrese el valor
        this.valor = valor; //Referencia al valor
    }

    public Nodo getIzq() { //Método que obtiene la izquierda
        return izq; //Regresa referencia izquierda
    }

    public void setIzq(Nodo izq) { //Método que muestre la izquierda
        this.izq = izq; //Referencia izquierda
    }

    public Nodo getDer() { //Se obtiene la derecha
        return der; //Regresa la diferencia derecha
    }

    public void setDer(Nodo der) { //Se obtiene la derecha
        this.der = der; //Referencia se iguala a la derecha
    }
    
    
}

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
public class ArbolBinario {

    private Nodo root; //Se declara la raíz de tipo Nodo

    public ArbolBinario() { //Constructor
        root = null; //La raíz se inicializa en null
    }

    public void addNodo(Nodo nNuevo) { //Método para agregar un nodo
        if (root == null) { //Árbol vacío
            root = nNuevo; //La raíz es igual al nodo nuevo
        } else { //Proceso recursivo
            addRecursivo(root, nNuevo); //Se manda a llamar el método recursivo con la raíz y el nodo nuevo
        }
    }

    //Nodo actual, valor a insertar

    private void addRecursivo(Nodo nActual, Nodo nNuevo) { //Método recursivo que hará todo el trabajo
        //Izquierda
        if (nNuevo.getValor() < nActual.getValor()) { //Si el nuevo valor es menor al valor actual

            if (nActual.getIzq() != null) { //Si el valor actual hacia la izquierda es diferente de nulo
                addRecursivo(nActual.getIzq(), nNuevo); //Método recursivo para obtener el valor actual y el nodo nuevo
            } else { //Si no
                nActual.setIzq(nNuevo); //Lanza el valor nuevo de la izquierda
            }
        } else if (nNuevo.getValor() > nActual.getValor()) { //Si el valor obtenido del nuevo nodo es mayor al valor actual
            if (nNuevo.getValor() > nActual.getValor()) { //Si nuevamente es mayor
                //Posición ocupada, seguimos avanzando
                if (nActual.getDer() != null) { //Si el valor de la derecha es diferente de nulo
                    addRecursivo(nActual.getDer(), nNuevo); //Método recursivo para el valor actual y el nuevo
                } else { //Si no
                    nActual.setDer(nNuevo); //El valor actual es enviado a la derecha
                }
            } else { //IGUAL --> MENSAJE AL USUARIO
                System.out.println("El valor ya existe en el árbol"); //Imprime mensaje para avisar que el árbol ya existe
                //LANZAR UNA EXCEPCIÒN
                 throw new RuntimeException("El valor ya existe en el árbol"); //Excepción para informar que el valor ya existe en el árbol
            }
        }
    }

    public void imprimirInOrder() { //Método para imprimir InOrder
        InOrder(root); //Se anda a llamar al método InOrder y se le otorga como parámetro la raíz
    }
    
    public void imprimirPostOrder(){ //Método para imprimir PostOrder
        PostOrder(root); //Se manda a llamar al método PostOrder y se le otorga como parámetro la raíz
    }
    
     public void imprimirPreOrder() { //Método para imprimir PreOrder
        PreOrder(root); //Se mmanda a llamar al método PreOrder y se le otorga como parámetro la raíz
    }

    private void InOrder(Nodo nActual) { //Método InOrder
        
         //RECORRER IZQUIERDA, LEER EL VALOR RECORRER DERECHA
         

        if (nActual != null) { //Si el valor actual es diferente de nulo
            InOrder(nActual.getIzq()); //Se obtiene a la izquierdda el valor
            System.out.print("[" + nActual.getValor() + "]"); //Se imprime el valor
            InOrder(nActual.getDer()); //Se obtiene a la derecha el valor
        }
    }
    
    private void PreOrder(Nodo nActual) { //Método PreOrder
        //RECORRER IZQUIERDA, LEER EL VALOR RECORRER DERECHA
        if (nActual != null) { //Si el valor actual es diferente de nulo
            System.out.print("[" + nActual.getValor() + "]"); //Imprimir el valor actual
            PreOrder(nActual.getIzq()); //Método Preorder, se obtiene hacia la izquierda
            PreOrder(nActual.getDer()); //Método PreOrder, se obtiene hacia la derecha
        }
    }
    
    private void PostOrder(Nodo nActual) { //Método PostOrder
        //RECORRER IZQUIERDA, LEER EL VALOR RECORRER DERECHA
        if (nActual != null) { //Si el valor actual es diferente de nulo
            PostOrder(nActual.getIzq()); //Método PostOrder, se obtiene el valor hacia la izquierda
            PostOrder(nActual.getDer()); //Método PostOrder, se obtiene el valor hacia la derecha
            System.out.print("[" + nActual.getValor() + "]"); //Se imprime el valor actual
        }
    }

}

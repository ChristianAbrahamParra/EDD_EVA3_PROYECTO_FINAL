
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import listas.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Christian Abraham Parra Pérez # Control: C18550333
 */
public class BaseDatos implements Serializable {

    private static BaseDatos instancia;

    public static BaseDatos getInstancia() {
        return instancia == null ? new BaseDatos() : instancia;
    }

    private Lista<Alumno> alumnos;
    private Lista<Asistencia> asistencias;
    private Lista<Calificacion> calificaciones;

    public BaseDatos() {
        alumnos = new Lista();
        asistencias = new Lista();
        calificaciones = new Lista();
        instancia = this;
    }

    public void agregarAlumno(Alumno a) {
        listas.Nodo<Alumno> tmp = alumnos.buscar(a);
        if (tmp == null) {
            alumnos.agregarNodo(a);
        } else {
            tmp.setDato(a);
        }
    }

    public void eliminarAlumno(Alumno a) {
        alumnos.borrarNodo(alumnos.buscar(a));
    }

    public Alumno buscarAlumno(String numControl) {
        Object tmp = alumnos.buscar(new Alumno(numControl));
        if (tmp != null) {
            return (Alumno) alumnos.buscar(new Alumno(numControl)).getDato();
        }
        return null;
    }

    public Alumno buscarAlumnoNombre(String nombre) {
        Alumno tmp = new Alumno("");
        tmp.setNombre(nombre);
        listas.Nodo<Alumno> nodo = alumnos.buscar(tmp, (o1, o2) -> {
            return o1.getNombre().toLowerCase().startsWith(o2.getNombre().toLowerCase()) ? 0 : -1;
        });
        if (nodo != null) {
            return (Alumno) nodo.getDato();
        }
        return null;
    }

    public void agregarAsistencia(Asistencia a) {
        listas.Nodo<Asistencia> tmp = asistencias.buscar(a);
        if (tmp == null) {
            asistencias.agregarNodo(a);
        } else {
            tmp.getDato().setPresente(a.isPresente());
        }
    }

    public void agregarCalificacion(Calificacion c) {
        listas.Nodo<Calificacion> tmp = calificaciones.buscar(c);
        if (tmp == null) {
            calificaciones.agregarNodo(c);
        } else {
            tmp.getDato().setCalificacion(c.getCalificacion());
        }
        calificaciones.agregarNodo(c);
    }

    public DefaultComboBoxModel<String> alumnosToDCM() {
        DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>();
        for (int i = 0; i < alumnos.longitud(); i++) {
            dcm.addElement(alumnos.getValor(i).getDato().getNoControl());
        }
        return dcm;
    }

    public DefaultTableModel alumnosToDTM(String busqueda) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        dtm.addColumn("NO CONTROL");
        dtm.addColumn("NOMBRE");
        dtm.addColumn("APELLIDO PATERNO");
        dtm.addColumn("APELLIDO MATERNO");
        dtm.addColumn("PROMEDIO");
        Alumno[] iTemp = new Alumno[alumnos.longitud()];
        for (int i = 0; i < iTemp.length; i++) {
            iTemp[i] = alumnos.getValor(i).getDato();
        }

        for (int i = 0; i < iTemp.length; i++) {
            for (int j = 0; j < iTemp.length; j++) {
                if (iTemp[i].getNoControl().compareTo(iTemp[j].getNoControl()) == 1) {
                    Alumno aux = iTemp[i];
                    iTemp[i] = iTemp[j];
                    iTemp[j] = aux;
                }
            }
        }
        alumnos = new Lista<>();
        for (Alumno a : iTemp) {
            alumnos.agregarNodo(a);
        }
        Lista<Alumno> tmpLista = new Lista<>();
        if (busqueda.length() > 0) {
            tmpLista.agregarNodo(buscarAlumno(busqueda));
            tmpLista.agregarNodo(buscarAlumnoNombre(busqueda));
        } else {
            tmpLista = alumnos;
        }
        for (int i = 0; i < tmpLista.longitud(); i++) {
            Alumno a = tmpLista.getValor(i).getDato();
            Object[] datos = new Object[5];
            datos[0] = a;
            datos[1] = a.getNombre();
            datos[2] = a.getApPaterno();
            datos[3] = a.getApMaterno();
            datos[4] = getPromedio(a);
            dtm.addRow(datos);
        }
        return dtm;
    }

    public DefaultTableModel alumnosToDTM() {
        return alumnosToDTM("");
    }

    public double getPromedio(Alumno a) {
        double prom = 0;
        int n = 0;
        for (int i = 0; i < calificaciones.longitud(); i++) {
            Calificacion c = calificaciones.getValor(i).getDato();
            if (c.getAlumno().equals(a)) {
                n++;
                prom += c.getCalificacion();
            }
        }
        return prom == 0 ? 0 : prom / n;
    }

    public DefaultTableModel asistenciaToDTM(String noControl) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM");
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        dtm.addColumn("NO CONTROL");
        Lista<Asistencia> fechas = new Lista<>();
        for (int i = 0; i < asistencias.longitud(); i++) {
            Asistencia a = asistencias.getValor(i).getDato();
            if (a.getAlumno().getNoControl().equals(noControl)) {
                if (fechas.buscar(a) == null) {
                    dtm.addColumn(f.format(a.getFecha()));
                    fechas.agregarNodo(a);
                }
            }
        }
        if (fechas.estaVacia()) {
            return new DefaultTableModel();
        }
        Asistencia a = fechas.getValor(0).getDato();
        String[] datos = new String[4];
        datos[0] = a.getAlumno().getNoControl();
        dtm.addRow(datos);
        for (int i = 0; i < fechas.longitud(); i++) {
            a = fechas.getValor(i).getDato();
            dtm.setValueAt(a.isPresente() ? "p" : "np", 0, i + 1);
        }
        return dtm;
    }

    public DefaultTableModel calificacionesToDTM(String noControl) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        dtm.addColumn("NO CONTROL");
        Lista<Calificacion> cal = new Lista<>();
        for (int i = 0; i < calificaciones.longitud(); i++) {
            Calificacion c = calificaciones.getValor(i).getDato();
            if (c.getAlumno().getNoControl().equals(noControl)) {
                if (cal.buscar(c) == null) {
                    dtm.addColumn(c.getUnidad());
                    cal.agregarNodo(c);
                }
            }
        }
        if (cal.estaVacia()) {
            return new DefaultTableModel();
        }
        Calificacion tmp = cal.getValor(0).getDato();
        String[] datos = new String[4];
        datos[0] = tmp.getAlumno().getNoControl();
        dtm.addRow(datos);
        for (int i = 0; i < cal.longitud(); i++) {
            tmp = cal.getValor(i).getDato();
            dtm.setValueAt(tmp.getCalificacion(), 0, i + 1);
        }
        return dtm;
    }

    public void guardar(String archivo) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(archivo));
            stream.writeObject(this);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static BaseDatos cargar(String archivo) {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(archivo));
            instancia = (BaseDatos) stream.readObject();
            return instancia;
        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new BaseDatos();
    }
}

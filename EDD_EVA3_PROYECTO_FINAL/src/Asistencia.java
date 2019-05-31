
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
public class Asistencia implements Serializable{

    private Alumno alumno;
    private Date fecha;
    private boolean presente;

    public Asistencia() {
    }

    public Asistencia(Alumno alumno, Date fecha, boolean presente) {
        this.alumno = alumno;
        this.fecha = fecha;
        this.presente = presente;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.alumno);
        hash = 79 * hash + Objects.hashCode(this.fecha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asistencia other = (Asistencia) obj;
        if (!Objects.equals(this.alumno, other.alumno)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }
    
}

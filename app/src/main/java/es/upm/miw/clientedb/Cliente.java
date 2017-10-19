package es.upm.miw.clientedb;

import android.os.Parcel;
import android.os.Parcelable;

public class Cliente implements Parcelable {

    private int id;
    private String nombre;
    private String dni;
    private int telefono;
    private String email;
    private boolean verificado;

    /**
     * Constructor
     *
     * @param id        Identificador
     * @param nombre    Nombre
     * @param dni       DNI
     * @param telefono  Teléfono
     * @param email     E-mail
     * @param verificado    Check
     */
    public Cliente(int id, String nombre, String dni, int telefono, String email, boolean verificado) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.verificado = verificado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", verificado=" + verificado +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.dni);
        dest.writeInt(this.telefono);
        dest.writeString(this.email);
        dest.writeByte(this.verificado ? (byte) 1 : (byte) 0);
    }

    protected Cliente(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.dni = in.readString();
        this.telefono = in.readInt();
        this.email = in.readString();
        this.verificado = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Cliente> CREATOR = new Parcelable.Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel source) {
            return new Cliente(source);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };
}

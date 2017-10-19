package es.upm.miw.clientedb;

import android.provider.BaseColumns;

final public class ClienteContract {

    private ClienteContract() {}

    public static abstract class tablaCliente implements BaseColumns {

        static final String TABLE_NAME        = "clientes";

        static final String COL_NAME_ID       = _ID;
        static final String COL_NAME_NOMBRE   = "nombre";
        static final String COL_NAME_DNI      = "dni";
        static final String COL_NAME_TLF      = "telefono";
        static final String COL_NAME_EMAIL    = "email";
        static final String COL_NAME_CHECK    = "check";
    }

}

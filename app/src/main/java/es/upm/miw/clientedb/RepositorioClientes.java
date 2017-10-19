package es.upm.miw.clientedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static es.upm.miw.clientedb.ClienteContract.tablaCliente;

public class RepositorioClientes extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DB_NAME = tablaCliente.TABLE_NAME + ".db";

    // Número de version
    private static final int DB_VERSION = 1;

    /**
     * Constructor
     *
     * @param contexto Context
     */
    public RepositorioClientes(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaCliente.TABLE_NAME + " ("
                + tablaCliente.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaCliente.COL_NAME_NOMBRE  + " TEXT, "
                + tablaCliente.COL_NAME_DNI     + " TEXT, "
                + tablaCliente.COL_NAME_TLF     + " INTEGER, "
                + tablaCliente.COL_NAME_EMAIL   + " TEXT, "
                + tablaCliente.COL_NAME_CHECK   + " INTEGER)";
        db.execSQL(consultaSQL);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " + tablaCliente.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    /**
     * Devuelve el n&uacute;mero de entidades en la tabla
     *
     * @return N&uacute;mero de entidades
     */
    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, tablaCliente.TABLE_NAME);
    }


    /**
     * A&ntilde;ade una nueva entidad a la tabla
     *
     * @param nombre      Nombre del cliente
     * @param dni         DNI del cliente
     * @param telefono    Tlf. del cliente
     * @param email       Correo electr&oacute;nico del cliente
     * @param verificado  &iquest;est&aacute; la verificado el email?
     * @return Identificador del cliente insertado (-1 si no se ha insertado)
     */
    public long add(String nombre, String dni, int telefono,
                     String email, boolean verificado) {

        // Obtiene la DB en modo escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // Mapa de valores: parejas nombreColumna:valor
        ContentValues valores = new ContentValues();
        valores.put(tablaCliente.COL_NAME_NOMBRE, nombre);
        valores.put(tablaCliente.COL_NAME_DNI, dni);
        valores.put(tablaCliente.COL_NAME_TLF, telefono);
        valores.put(tablaCliente.COL_NAME_EMAIL, email);
        valores.put(tablaCliente.COL_NAME_CHECK, (verificado) ? 1 : 0);

        // Realiza la inserción
        return db.insert(tablaCliente.TABLE_NAME, null, valores);
    }
}

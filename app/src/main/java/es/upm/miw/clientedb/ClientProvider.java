package es.upm.miw.clientedb;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by chinegua on 23/10/17.
 */

public class ClientProvider extends ContentProvider{



    private static final String AUTHORITY = ClientProvider.class.getPackage().getName() + ".provider";
    private static final String ENTITY = "clientes";

    // Definición de la ruta al contenido
    private static final String uri = "content://" + AUTHORITY + "/" + ENTITY;
    private static final Uri CONTENT_URI = Uri.parse(uri);

    // Tipos de URI accesibles
    private static final int ID_URI_USUARIOS_ALL  = 1;
    private static final int ID_URI_USUARIO_UNICO = 2;
    private static final int ID_URI_USUARIO_NOMBRE = 3;
    private static final UriMatcher uriMatcher;


    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ENTITY, ID_URI_USUARIOS_ALL);
        uriMatcher.addURI(AUTHORITY, ENTITY + "/#", ID_URI_USUARIO_UNICO);
        uriMatcher.addURI(AUTHORITY, ENTITY + "/*", ID_URI_USUARIO_NOMBRE);
    }

    private RepositorioClientes repositorioClientes;

    @Override
    public boolean onCreate() {

        repositorioClientes = new RepositorioClientes(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String where = "";
        switch (uriMatcher.match(uri)) {
            case ID_URI_USUARIO_UNICO:  // URI termina en /#
                where = ClienteContract.tablaCliente.COL_NAME_ID + " = ?";
                selectionArgs = new String[1];
                selectionArgs[0] = uri.getLastPathSegment();
                break;
            case ID_URI_USUARIO_NOMBRE: // URI termina en /*
                where = ClienteContract.tablaCliente.COL_NAME_NOMBRE + " LIKE ?";
                selectionArgs = new String[1];
                selectionArgs[0] = uri.getLastPathSegment() + '%';
                break;
            case ID_URI_USUARIOS_ALL:
                break;
        }


        SQLiteDatabase db = repositorioClientes.getReadableDatabase();
        Cursor cursor = db.query(
                ClienteContract.tablaCliente.TABLE_NAME,
                projection,
                where,
                selectionArgs,
                null, null, sortOrder);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case ID_URI_USUARIOS_ALL:
                return "vnd.android.cursor.dir/vnd.miw.clientes";    // lista de entidades
            case ID_URI_USUARIO_NOMBRE:
            case ID_URI_USUARIO_UNICO:
                return "vnd.android.cursor.item/vnd.miw.cliente";   // entidad única
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}

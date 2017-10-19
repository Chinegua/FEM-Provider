package es.upm.miw.clientedb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    static final String LOG_TAG = "MiW";

    RepositorioClientes db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new RepositorioClientes(getApplicationContext());
        long numElementos = db.count();
        Log.i(LOG_TAG, "Número elementos = " + String.valueOf(numElementos));
    }
}

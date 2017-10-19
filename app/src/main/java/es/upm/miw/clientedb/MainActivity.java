package es.upm.miw.clientedb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    static final String LOG_TAG = "MiW";
    static final String KEY_CLIENTE = "MiW_clave_Cliente";

    RepositorioClientes db;
    ArrayList<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new RepositorioClientes(getApplicationContext());
        long numElementos = db.count();
        Log.i(LOG_TAG, "Número elementos = " + String.valueOf(numElementos));

        // Inserta un cliente generado aleatoriamente
        String str = String.valueOf(Math.round(1000 * Math.random()) % 1000);
        long id = db.add("Cl" + str, str + str, Integer.valueOf(str), str + "@xyz.com", (Integer.valueOf(str) % 2 == 0));
        Log.i(LOG_TAG, "Número cliente = " + String.valueOf(id));

        clientes = db.getAll();
        Log.i(LOG_TAG, "Clientes = " + clientes);

        ListView lvClientes = (ListView) findViewById(R.id.lvListadoClientes);
        lvClientes.setAdapter(
                new ClienteAdapter(
                        getApplicationContext(),
                        clientes
                ));

        // Listener del listado de clientes
        lvClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) ((LinearLayout) view).getChildAt(0); // obtiene tv con id del cliente
                long numCliente = Integer.valueOf(tvId.getText().toString());
                Log.d(LOG_TAG, "PRINCIPAL pos= " + String.valueOf(position) + ", numCliente=" + String.valueOf(numCliente));

                Intent intent = new Intent(MainActivity.this, ActividadMostrarCliente.class);
                intent.putExtra(KEY_CLIENTE, clientes.get(position));
                startActivity(intent);
            }
        });

    }
}

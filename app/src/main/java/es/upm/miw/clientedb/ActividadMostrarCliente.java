package es.upm.miw.clientedb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ActividadMostrarCliente extends Activity {

    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView tvId, tvAttrNombre, tvAttrDNI, tvAttrTlf, tvAttrEmail;
        ImageView ivAttrCheck;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_mostrar_cliente);

        contexto = getApplicationContext();

        // Recuperar entidad
        Cliente cliente = getIntent().getParcelableExtra(MainActivity.KEY_CLIENTE);
        Log.i(MainActivity.LOG_TAG, cliente.toString());

        // Identificar vistas
        tvId            = (TextView)  findViewById(R.id.tvMostrarEntidadId);
        tvAttrNombre    = (TextView)  findViewById(R.id.tvMostrarEntidadNombre);
        tvAttrDNI       = (TextView)  findViewById(R.id.tvMostrarEntidadDNI);
        tvAttrTlf       = (TextView)  findViewById(R.id.tvMostrarEntidadTlf);
        tvAttrEmail     = (TextView)  findViewById(R.id.tvMostrarEntidadEmail);
        ivAttrCheck     = (ImageView) findViewById(R.id.ivMostrarEntidadCheck);

        // Asignar valores a las vistas
        tvId.setText(String.format("%2d", cliente.getId()));
        tvAttrNombre.setText(cliente.getNombre());
        tvAttrDNI.setText(cliente.getDni());
        tvAttrTlf.setText(Integer.toString(cliente.getTelefono()));
        tvAttrEmail.setText(cliente.getEmail());
        ivAttrCheck.setImageResource(cliente.isVerificado()
                ? android.R.drawable.checkbox_on_background
                : android.R.drawable.checkbox_off_background);
    }
}

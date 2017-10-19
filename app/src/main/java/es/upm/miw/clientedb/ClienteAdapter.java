package es.upm.miw.clientedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClienteAdapter extends ArrayAdapter {

    private Context _contexto;
    private ArrayList<Cliente> _clientes;

    /**
     * Constructor
     * @param contexto   Contexto
     * @param clientes  Datos a representar
     */
    public ClienteAdapter(Context contexto, ArrayList<Cliente> clientes) {
        super(contexto, R.layout.layout_listado_clientes, clientes);
        this._contexto = contexto;
        this._clientes = clientes;
        setNotifyOnChange(true);
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // obtener o generar vista
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) _contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listado_clientes, null);
        }

        Cliente cliente = _clientes.get(position);
        if (cliente != null) {
            TextView tvId = convertView.findViewById(R.id.tvListadoClienteId);
            tvId.setText(Long.toString(cliente.getId()));

            TextView tvNombre = (TextView) convertView.findViewById(R.id.tvListadoClienteNombre);
            tvNombre.setText(cliente.getNombre());

            TextView tvDorsal = (TextView) convertView.findViewById(R.id.tvListadoClienteDNI);
            tvDorsal.setText(cliente.getDni());

            ImageView ivActivo = (ImageView) convertView.findViewById(R.id.ivListadoClienteVerificado);
            ivActivo.setImageResource(
                    cliente.isVerificado()
                    ? android.R.drawable.checkbox_on_background
                    : android.R.drawable.checkbox_off_background
            );
        }

        return convertView;
    }


}

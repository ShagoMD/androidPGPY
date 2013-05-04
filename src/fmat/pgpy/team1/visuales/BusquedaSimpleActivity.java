package fmat.pgpy.team1.visuales;



import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import fmat.pgpy.team1.R;
import fmat.pgpy.team1.controladores.ControladorPDIs;
import fmat.pgpy.team1.dominio.Posicion;
import fmat.pgpy.team1.interfaces.RespuestaInterface;

/**
 * 
 * Nombre: Santiago Martin
 * Fecha: 5 Febrero 2013
 * Descripcion: Activity donde se obtiene un parametro de busqueda
 * para buscar entre los PDI cercanos
 */
public class BusquedaSimpleActivity extends Activity implements RespuestaInterface {
	
	private static SimpleARBrowserActivity arBrowser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("onCreate BusquedaSimpleActivity");		
		
		Intent intent = getIntent();
		String fraseBuscar = intent.getStringExtra("clave");				
		TextView textView = new TextView(this);
		textView.setTextSize(20);
		textView.setText("Buscando: " + fraseBuscar);
		setContentView(textView);		
		realizaBusquedaSimple();
	}
	
	public void realizaBusquedaSimple(){
		Posicion posicion = new Posicion();
		posicion.setLatitud(SimpleARBrowserActivity.getLatitudActual());
		posicion.setLongitud(SimpleARBrowserActivity.getLongitudActual());
		double distanciaMax = 15;
		Intent intent = getIntent();
		String fraseBuscar = intent.getStringExtra("clave");
		ControladorPDIs.getInstance().filtrarPDIsPorNombre(posicion, distanciaMax, fraseBuscar, arBrowser);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_busqueda_simple, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static void setARBrowserBusquedaSimple(SimpleARBrowserActivity browserActivity){
		System.out.println("setARBrowserBusquedaSimple");
		arBrowser = browserActivity;
	}
	
	@Override
	public void mostrarMensaje(String mensaje) {
		Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void procesarRespuestaServidor(JSONObject jObject) {
		// TODO Auto-generated method stub
		
	}

}

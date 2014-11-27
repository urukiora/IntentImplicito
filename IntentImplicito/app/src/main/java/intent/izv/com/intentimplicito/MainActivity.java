package intent.izv.com.intentimplicito;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends Activity {

    private EditText et;
    private Button bt;
    private String ruta, texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);
        bt = (Button)findViewById(R.id.button);

        Intent i = getIntent();
        Uri datos = i.getData();
        ruta = datos.getPath();

        String  linea;
        try{
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            while ((linea = br.readLine()) != null) {
                et.append(linea);
            }
        }catch(Exception e){
            e.printStackTrace();
        }



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            guardar(v);
            }
        });


    }

    public void guardar(View v){

        texto = et.getText().toString();
        File f = new File(ruta);

        FileWriter fw;
        try {
            fw = new FileWriter(f);
            fw.write(texto);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package yeberc.blogspot.bdsqlite01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText nom, tel;
Button btnins,btnlis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom =(EditText)findViewById(R.id.nombre);
        tel =(EditText)findViewById(R.id.telef);
        btnins=(Button)findViewById(R.id.ins);
        btnlis=(Button)findViewById(R.id.lis);
        btnins.setOnClickListener(this);
        btnlis.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Bdsqlite bdsqlite=new Bdsqlite(this);
        switch (view.getId()){
            case R.id.ins:{

                //Toast.makeText(this," Registro ",Toast.LENGTH_LONG).show();
                long res= 0;

                try {
                    String no=nom.getText().toString();
                    String te=tel.getText().toString();
                    bdsqlite.Abrir();
                    res = bdsqlite.Insertar(no,te);
                    bdsqlite.Cerrar();
                    if(res>0){
                        Toast.makeText(this,"Registro correcto",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this," NO Registro ",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                break;}
            case R.id.lis: {

                try {
                    String res;
                    bdsqlite.Abrir();
                    res = bdsqlite.Listar();
                    Toast.makeText(this,res,Toast.LENGTH_LONG).show();
                    bdsqlite.Cerrar();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }
}

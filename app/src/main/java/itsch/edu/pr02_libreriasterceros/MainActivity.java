package itsch.edu.pr02_libreriasterceros;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.util.ArrayList;

import modelos.Cuentas;

public class MainActivity extends AppCompatActivity {

    EditText txUser, txPwd;
    Button btnRegister, btnLogin;

    //Crear lista
    ArrayList<Cuentas> lCuentas=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarControles();
        cargarEventos();
    }

    private void cargarControles() {
        txUser=findViewById(R.id.editText);
        txPwd=findViewById(R.id.editText2);
        btnLogin=findViewById(R.id.button);
        btnRegister=findViewById(R.id.button2);
    }

    private void cargarEventos() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        if(!txUser.getText().toString().isEmpty() &&
                !txPwd.getText().toString().isEmpty()){
            if(lCuentas.size()>0){
                boolean valido=false;
                String us=txUser.getText().toString();
                String pw=txPwd.getText().toString();
                for (int i = 0; i < lCuentas.size(); i++) {
                    if(lCuentas.get(i).user.equalsIgnoreCase(us) &&
                        lCuentas.get(i).pwd.equals(pw)){
                        valido=true;
                        break;
                    }
                }
                if (valido){
                    miToast("Bienvenido");
                }else{
                    miToast("Error en los datos");
                }
            }
        }
    }

    private void registrar() {
        if(!txUser.getText().toString().isEmpty() &&
            !txPwd.getText().toString().isEmpty()){
            Cuentas miCuenta=new Cuentas();
            miCuenta.user=txUser.getText().toString();
            miCuenta.pwd=txPwd.getText().toString();
            lCuentas.add(miCuenta);
            miToast("Registro Exitoso");
            txUser.setText("");
            txPwd.setText("");
        }


    }

    public void miToast(String mnsj){
        SuperActivityToast.create(MainActivity.this, new Style(),Style.TYPE_PROGRESS_CIRCLE)
                //.setButtonText("Deshacer")
                //.setButtonIconResource(R.drawable.ic_launcher_foreground)
                //.setOnButtonClickListener("Texto", null,null)
                //.setProgressBarColor(Color.BLUE)
                .setText(mnsj)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_INDIGO))
                .setAnimations(Style.ANIMATIONS_FLY)
                .show();
    }


}

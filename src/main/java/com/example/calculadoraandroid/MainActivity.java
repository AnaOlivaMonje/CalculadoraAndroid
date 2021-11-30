package com.example.calculadoraandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

enum Operacion {SUMA, RESTA, MULTIPLICACION, DIVISION, PORCENTAJE};

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button multiplicar, dividir, restar, sumar, coma,
            btCero, btUno, btDos, btTres, btCuatro, btCinco, btSeis, btSiete, btOcho, btNueve,
            btResultado, btBorrar, btComa, btPorcentaje;

    private TextView titulo;

    private EditText pantalla;


    String primerOperando = "";
    String segundoOperando  = "";

    double res = 0;

    Operacion operacion;

    int estado = 1;

    boolean decimal=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instancias();
        acciones();

    }
    private void inicializarVariables(){
        estado = 1;
        decimal=false;
        primerOperando = "";
        segundoOperando = "";
        pantalla.setText("");
    }
    private Operacion GetOperacion(Button b)
    {
        if (b == sumar)
            return Operacion.SUMA;

        if (b == restar)
            return Operacion.RESTA;

        if (b == multiplicar)
            return Operacion.MULTIPLICACION;

        if (b == dividir)
            return Operacion.DIVISION;

        if (b == btPorcentaje)
            return Operacion.PORCENTAJE;

        return Operacion.SUMA;
    }

    private void acciones(){
        btCero.setOnClickListener(this);
        btUno.setOnClickListener(this);
        btDos.setOnClickListener(this);
        btTres.setOnClickListener(this);
        btCuatro.setOnClickListener(this);
        btCinco.setOnClickListener(this);
        btSeis.setOnClickListener(this);
        btSiete.setOnClickListener(this);
        btOcho.setOnClickListener(this);
        btNueve.setOnClickListener(this);
        btComa.setOnClickListener(this);
        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        btResultado.setOnClickListener(this);
        btBorrar.setOnClickListener(this);
        btPorcentaje.setOnClickListener(this);
    }


    private void instancias() {
        sumar = findViewById(R.id.suma);
        restar = findViewById(R.id.resta);
        multiplicar = findViewById(R.id.multiplicacion);
        dividir = findViewById(R.id.division);
        btResultado = findViewById(R.id.resultado);
        btBorrar = findViewById(R.id.borrar);
        btPorcentaje = findViewById(R.id.porcentaje);

        btCero = findViewById(R.id.cero);
        btUno = findViewById(R.id.uno);
        btDos = findViewById(R.id.dos);
        btTres = findViewById(R.id.tres);
        btCuatro = findViewById(R.id.cuatro);
        btCinco = findViewById(R.id.cinco);
        btSeis = findViewById(R.id.seis);
        btSiete = findViewById(R.id.siete);
        btOcho = findViewById(R.id.ocho);
        btNueve = findViewById(R.id.nueve);
        btComa = findViewById(R.id.coma);

        pantalla = (EditText) findViewById(R.id.pantalla);


    }

    @Override
    public void onClick(View view) {

        Button b = (Button) findViewById(view.getId());


        if ((b.getId() == R.id.cero) ||
                (b.getId() == R.id.uno) ||
                (b.getId() == R.id.dos) ||
                (b.getId() == R.id.tres) ||
                (b.getId() == R.id.cuatro) ||
                (b.getId() == R.id.cinco) ||
                (b.getId() == R.id.seis) ||
                (b.getId() == R.id.siete) ||
                (b.getId() == R.id.ocho) ||
                (b.getId() == R.id.nueve))
        {
            if (estado == 1) {
                primerOperando = primerOperando + b.getText();
                pantalla.setText(primerOperando);
            } else {
                segundoOperando = segundoOperando + b.getText();
                pantalla.setText(segundoOperando);
            }


        }else if ((b.getId() == R.id.suma) ||
                (b.getId() == R.id.resta) ||
                (b.getId() == R.id.multiplicacion) ||
                (b.getId() == R.id.division) ||
                (b.getId() == R.id.porcentaje))
        {
            if (estado == 1)
            {
                operacion = GetOperacion((Button) b);
                estado = 2;
                decimal=false;
                pantalla.setText(pantalla.getText() + " " + b.getText());

            }
        }


        else if (b.getId() == R.id.coma){
            if (estado == 1) {
                if (!primerOperando.contains(".")) {
                    primerOperando = primerOperando + b.getText();
                }
            } else {
                if (!segundoOperando.contains(".")) {
                    segundoOperando = segundoOperando + b.getText();
                }
            }
        }

        else if (b.getId() == R.id.resultado){

            if (estado==2){
                switch (operacion){
                    case SUMA:
                        res =  Double.parseDouble(primerOperando) +  Double.parseDouble(segundoOperando);

                        break;

                    case RESTA:
                        res = Double.parseDouble(primerOperando) -  Double.parseDouble(segundoOperando);

                        break;

                    case MULTIPLICACION:
                        res = Double.parseDouble(primerOperando) * Double.parseDouble(segundoOperando);

                        break;

                    case DIVISION:
                        res = Double.parseDouble(primerOperando) / Double.parseDouble(segundoOperando);

                        break;

                    case PORCENTAJE:
                        res = Double.parseDouble(primerOperando) * Double.parseDouble(segundoOperando)/100.0;

                        break;

                }

                if (res - Math.floor(res) == 0)
                {
                    pantalla.setText(Integer.toString((int)res));
                }
                else {
                    pantalla.setText(Double.toString(res));
                }
                estado =1;
                primerOperando = "";
                segundoOperando = "";
                decimal = false;

            }

        }else if (b.getId() == R.id.borrar){
            inicializarVariables();
        }

    }


    }





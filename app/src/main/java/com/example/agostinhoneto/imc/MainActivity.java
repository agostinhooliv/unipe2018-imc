package com.example.agostinhoneto.imc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText textoPeso;
    EditText textoAltura;
    Button botaCalcular;
    TextView textoSaida;
    Double imc = 0.0;
    DecimalFormat dec = new DecimalFormat("#0.00");
    String nome_pessoa = "";

    private void inputDialog(String resultado)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nome:");


        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nome_pessoa = input.getText().toString();
                System.out.println(nome_pessoa);

                if (imc < 18.5) {
                    textoSaida.setText("Sr(a) " + nome_pessoa + ", seu imc é: " +dec.format(imc).toString() + "\tAbaixo do peso");
                } else if (imc == 18.6 || imc <= 24.9) {
                    textoSaida.setText("Sr(a) " + nome_pessoa + ", seu imc é: " +dec.format(imc).toString() + "\tPeso Ideal");
                } else if (imc == 25.0 || imc <= 29.9) {
                    textoSaida.setText("Sr(a) " + nome_pessoa + ", seu imc é: " +dec.format(imc).toString() + "\tLevemente acima do peso");
                } else if (imc == 30.0 || imc <= 34.9) {
                    textoSaida.setText("Sr(a) " + nome_pessoa + ", seu imc é: " +dec.format(imc).toString() + "\tObesididade Grau I");
                } else if (imc == 35.0 || imc <= 39.9) {
                    textoSaida.setText("Sr(a) " + nome_pessoa + ", seu imc é: " +dec.format(imc).toString() + "\tObesididade Grau II (Severa)");
                } else if (imc >= 40.0) {
                    textoSaida.setText("Sr(a) " + nome_pessoa + ", seu imc é: " +dec.format(imc).toString() + "\tObesididade Grau III (Mórbida)");
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoPeso = (EditText) findViewById(R.id.pesoText);
        textoAltura = (EditText) findViewById(R.id.alturaText);
        botaCalcular = (Button) findViewById(R.id.botaoCalcular);
        textoSaida = (TextView) findViewById(R.id.saidaTexto);


        botaCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((textoAltura == null || textoAltura.getText().toString().equals("")) || (textoPeso == null || textoPeso.getText().toString().equals(""))){
                    Toast toast = Toast.makeText(getApplicationContext(), "Por favor digitar um valor", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    inputDialog(String.valueOf(imc));
                    imc = Double.parseDouble(textoPeso.getText().toString()) / (Double.parseDouble(textoAltura.getText().toString()) * Double.parseDouble(textoAltura.getText().toString()));
                }
            }
        });


    }
}

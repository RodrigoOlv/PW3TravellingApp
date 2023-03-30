package com.example.apptravelerbackpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class FormActivity extends AppCompatActivity {

    private EditText edtPlace;
    private Spinner spnType;
    private RadioButton rdMonth;
    private RadioButton rdSemester;
    private RadioButton rdYear;
    private CheckBox chkAccept;
    private Button btnShare;

    private String activeRadioTime;

    private String acceptance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        edtPlace = findViewById(R.id.edtPlace);
        spnType = findViewById(R.id.spnType);
        rdMonth = findViewById(R.id.rdMonth);
        rdSemester = findViewById(R.id.rdSemester);
        rdYear = findViewById(R.id.rdYear);
        chkAccept = findViewById(R.id.chkAccept);
        btnShare = findViewById(R.id.btnShare);

        rdMonth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Snackbar.make(findViewById(android.R.id.content), "Você informou que esteve neste local em até um mês", Snackbar.LENGTH_SHORT).show();

                    activeRadioTime = "Até um mês";
                }
            }
        });

        rdSemester.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Snackbar.make(findViewById(android.R.id.content), "Você informou que esteve neste local neste semestre", Snackbar.LENGTH_SHORT).show();

                    activeRadioTime = "Até seis meses";
                }
            }
        });

        rdYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Snackbar.make(findViewById(android.R.id.content), "Você informou que esteve neste local há pelo menos um ano", Snackbar.LENGTH_SHORT).show();

                    activeRadioTime = "Pelo menos um ano";
                }
            }
        });

        chkAccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Snackbar.make(findViewById(android.R.id.content), "Aceitou os termos de uso de depoimento", Snackbar.LENGTH_SHORT).show();

                    acceptance = "Sim";
                }
                else {
                    Snackbar.make(findViewById(android.R.id.content), "Não aceitou os termos de uso de depoimento", Snackbar.LENGTH_SHORT).show();

                    acceptance = "Não";
                }
            }
        });;

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("*/*");

                String body = "" +
                        "Sobre a viagem: " + edtPlace.getText().toString() +
                        "\nTipo de lugar: " + spnType.getSelectedItem().toString() +
                        "\nEsteve lá em: " + activeRadioTime +
                        "\nAceitou os termos de uso de depoimento: " + acceptance;

                intent.putExtra(Intent.EXTRA_EMAIL, "rodrigo18498@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Novo compartilhamento de viagem no App");
                intent.putExtra(Intent.EXTRA_TEXT, body);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
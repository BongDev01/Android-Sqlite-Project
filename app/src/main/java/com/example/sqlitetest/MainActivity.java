package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText name,age,address;
    TextView textView;
    Button button,button2;
    DBHelper dbHelper;
    Model model;

    long rowId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editText);
        age = findViewById(R.id.editText2);
        address = findViewById(R.id.edit_text_add);
        button = findViewById(R.id.btn);
        button2 = findViewById(R.id.btn2);
        textView = findViewById(R.id.textView);

        dbHelper = new DBHelper(this);

        if (getIntent().getBooleanExtra("isFromActivity2",false)){
            textView.setText("Update Profile");
            button2.setVisibility(View.GONE);
            button.setText("Update");

            name.setClickable(false);
            name.setFocusable(false);

            setData();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    
                if (!name.getText().toString().isEmpty() && !age.getText().toString().isEmpty() && !address.getText().toString().isEmpty()){
                    model = new Model(name.getText().toString(),age.getText().toString(),address.getText().toString());
                    if (getIntent().getBooleanExtra("isFromActivity2",false)){
                       int res =  dbHelper.updateValue(model);
                       if (res!=-1){
                           Toast.makeText(MainActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                           startActivity(intent);
                       }
                       else {
                           Toast.makeText(MainActivity.this, "Value Not Update ", Toast.LENGTH_SHORT).show();
                       }

                       return;

                    }
                   rowId =  dbHelper.addValueDataBase(model);

                   if (rowId != -1){
                       Toast.makeText(MainActivity.this, "Data added !", Toast.LENGTH_SHORT).show();
                       name.setText("");
                       age.setText("");
                       address.setText("");


                   }
                   else {
                       Toast.makeText(MainActivity.this, "Data not added", Toast.LENGTH_SHORT).show();
                   }
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });


    }

    private void setData() {

     Model model1 =   dbHelper.showSpecificRow(getIntent().getStringExtra("Name"));

     name.setText(model1.Name);
     age.setText(model1.Age);
     address.setText(model1.Address);

    }
}
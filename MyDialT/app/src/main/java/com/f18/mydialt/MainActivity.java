package com.f18.mydialt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBuiltInDialog();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateCustomDialog();
            }
        });
    }

    private void CreateBuiltInDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Default Title hy ye");
        b.setMessage("Default Message hy ye");
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
       AlertDialog ad = b.create();
       ad.show();
    }

    private void CreateCustomDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);

        View v = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        TextView tv = v.findViewById(R.id.textView);
        TextView tv2 = v.findViewById(R.id.textView2);

        tv.setText("Custom Title hy ye");
        tv2.setText("Custom Message hy ye");

        b.setView(v);
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad = b.create();
        ad.show();
    }
}
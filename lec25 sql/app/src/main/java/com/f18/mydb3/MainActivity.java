package com.f18.mydb3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper databaseHelper;
    TextView datalist;
    TextView datalist_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper=new DBHelper(MainActivity.this);

        Button insert=findViewById(R.id.insert_data);

        Button read=findViewById(R.id.refresh_data);
        datalist=findViewById(R.id.all_data_list);
        datalist_count=findViewById(R.id.data_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();

            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInputDialog();
            }
        });


    }

    private void refreshData()
    {
        datalist_count.setText("ALL DATA COUNT : "+databaseHelper.getTotalCount());

        List<UserModel> userModelList=databaseHelper.getALLUsers();
        datalist.setText("");
        for(UserModel userModel:userModelList){
            datalist.append("ID : "+userModel.getId()+
                    " | Name : "+userModel.getName()+
                    " | Email : "+userModel.getEmail()+" \n\n");
        }
    }


    private void ShowInputDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(MainActivity.this);
        View view=getLayoutInflater().inflate(R.layout.insert_dialog,null);
        final EditText name=view.findViewById(R.id.name);
        final EditText email=view.findViewById(R.id.email);
        Button insertBtn=view.findViewById(R.id.insert_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel=new UserModel();
                userModel.setName(name.getText().toString());
                userModel.setEmail(email.getText().toString());
                Date date=new Date();
                userModel.setCreated_at(""+date.getTime());
                databaseHelper.AddUser(userModel);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }
}
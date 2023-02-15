package com.example.simplecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button submit,delete,view,update,commit;
    EditText id,name,roll,marks;
    dbHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit=findViewById(R.id.submitbtn);
        delete=findViewById(R.id.deletebtn);
        view=findViewById(R.id.viewbtn);
        update=findViewById(R.id.updatebtn);
        id=findViewById(R.id.idfld);
        name=findViewById(R.id.Namefld);
        roll=findViewById(R.id.rollfld);
        marks=findViewById(R.id.marksfld);
        mydb=new dbHandler(this);
        commit=findViewById(R.id.commitbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=mydb.insertStudent(id.getText().toString(),name.getText().toString(),roll.getText().toString(),marks.getText().toString());
                if(isInserted)
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_LONG).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getData();
                if(res==null)
                {
                    Toast.makeText(MainActivity.this, "Nothing found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer bf=new StringBuffer();
                while(res.moveToNext()){
                    bf.append("ID : "+res.getString(0)+"  ");
                    bf.append("Name : "+res.getString(1)+"  ");
                    bf.append("Roll Num : "+res.getString(2)+"  ");
                    bf.append("Marks : "+res.getString(3)+"\n\n");
                }
                showMessage("Records" , bf.toString());
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate=mydb.Update(id.getText().toString(),name.getText().toString(),roll.getText().toString(),marks.getText().toString());
                if(isUpdate)
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleted=mydb.Delete(id.getText().toString());
                if(deleted>0)
                    Toast.makeText(MainActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Nothing to delete", Toast.LENGTH_SHORT).show();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://github.com/AmeenaFatima01/StudentRecordsApp/commits/main");
            }
        });

    }

    private void goLink(String s) {

    }

    private void showMessage(String records, String toString) {
        AlertDialog.Builder bd=new AlertDialog.Builder(this);
        bd.setCancelable(true);
        bd.setTitle(records);
        bd.setMessage(toString);
        bd.show();
    }

}
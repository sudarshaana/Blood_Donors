package ml.sudarshan.bloodheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ml.sudarshan.bloodheros.db.DBHandler;

public class AddPerson extends AppCompatActivity {

    private EditText name, bgroup, no;
    private Button add;
    private DBHandler dbHandler;
    private String check, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        dbHandler = new DBHandler(getApplicationContext());

        name = (EditText) findViewById(R.id.name);
        bgroup = (EditText) findViewById(R.id.bloodGroup);
        no = (EditText) findViewById(R.id.phoneNo);

        add = (Button) findViewById( R.id.add);

        check = getIntent().getStringExtra("check");
        if (check.contains("true")){

            name.setText(getIntent().getStringExtra("Name"));
            bgroup.setText(getIntent().getStringExtra("BG"));
            no.setText(getIntent().getStringExtra("No"));
        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().length() < 1 && bgroup.getText().toString().length()<1 && no.getText().toString().length()<1){
                    Toast.makeText(AddPerson.this, "Invalid inputs", Toast.LENGTH_SHORT).show();
                }else if(check.contains("true")){
                    Person person = new Person(getIntent().getStringExtra("id"), name.getText().toString(), bgroup.getText().toString(), no.getText().toString());
                    dbHandler.updateData(person);
                    finish();
                }
                else{
                    Person person = new Person(name.getText().toString(), bgroup.getText().toString(), no.getText().toString());
                    dbHandler.addTipsData(person);
                    finish();
                }
            }
        });


    }
}

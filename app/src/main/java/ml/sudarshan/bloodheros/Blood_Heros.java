package ml.sudarshan.bloodheros;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ml.sudarshan.bloodheros.db.DBHandler;

public class Blood_Heros extends AppCompatActivity implements SearchView.OnQueryTextListener {

private List<Person> list = new ArrayList<>();
    List<Person> allData = new ArrayList<>();
    private RecyclerView recyclerView;
    private DBHandler dataBaseHandler;
    AlbumListAdaptorRecycler adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood__heros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AddPerson.class);
                intent.putExtra("check", "false");
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        }

        //Person person = new Person("Amit", "O+", "0185955655");


        dataBaseHandler = new DBHandler(getApplicationContext());

       // dataBaseHandler.addTipsData(person);

        list = dataBaseHandler.getAllData();
        allData = dataBaseHandler.getAllData();

        adapter = new AlbumListAdaptorRecycler(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);
//
//        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                return false;
//            }
//        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blood__heros, menu);
        MenuItem menuItem = menu.findItem(R.id.searchTips);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        //Toast.makeText(this, "re", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.notifyDataSetChanged();

       // Toast.makeText(this, "pau", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        List<Person> filteredData = new ArrayList<>();
       // allData = list;



        for (Person person : allData) {
            String string = person.getBloodgroup().toLowerCase();
            if (string.contains(newText)) {
                filteredData.add(person);
            }
        }
        if (newText.length()==0){
            allData = dataBaseHandler.getAllData();
        }

        adapter.setFilter(filteredData);


        return true;
    }
}

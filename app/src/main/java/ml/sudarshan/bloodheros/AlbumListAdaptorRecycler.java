package ml.sudarshan.bloodheros;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Sudarshan on 18/04/2017.
 */
public class AlbumListAdaptorRecycler extends RecyclerView.Adapter<AlbumListAdaptorRecycler.ViewHolder> {

    private List<Person> personList;
    private Context context;


    public AlbumListAdaptorRecycler(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AlbumListAdaptorRecycler.ViewHolder holder, final int position) {
        final Person person = personList.get(position);


        holder.name.setText(person.getName());
        if (person.getPhoneNo() == "01737786189") {
            holder.name.setTextColor(Color.parseColor("#E91E63"));
        }
        holder.bloodGroup.setText(person.getBloodgroup());
        if (person.getBloodgroup().contains("O+"))
            holder.bloodGroup.setTextColor(Color.parseColor("#AB47BC"));

        if (person.getBloodgroup().contains("O-"))
            holder.bloodGroup.setTextColor(Color.parseColor("#F44336"));

        if (person.getBloodgroup().contains("A+"))
            holder.bloodGroup.setTextColor(Color.parseColor("#2196F3"));

        if (person.getBloodgroup().contains("A-"))
            holder.bloodGroup.setTextColor(Color.parseColor("#2196F3"));

        if (person.getBloodgroup().contains("B+"))
            holder.bloodGroup.setTextColor(Color.parseColor("#43A047"));
        if (person.getBloodgroup().contains("B-"))
            holder.bloodGroup.setTextColor(Color.parseColor("#FF9800"));

        if (person.getBloodgroup().contains("AB+"))
            holder.bloodGroup.setTextColor(Color.parseColor("#546E7A"));
        if (person.getBloodgroup().contains("AB-"))
            holder.bloodGroup.setTextColor(Color.parseColor("#76FF03"));



        holder.phoneNo.setText(person.getPhoneNo());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                callIntent.setData(Uri.parse("tel:" + person.getPhoneNo()));

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(
                            (Activity) context,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);

                    return;
                }else

                    context.startActivity(callIntent);

            }

        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent intent = new Intent(context, AddPerson.class);

                intent.putExtra("check", "true");
                intent.putExtra("id", person.getID());
                intent.putExtra("Name", person.getName());
                intent.putExtra("BG", person.getBloodgroup());
                intent.putExtra("No", person.getPhoneNo());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                return true;

            }
        });



    }


    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setFilter(List<Person> filteredData) {

        personList.clear();
        personList.addAll(filteredData);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView name, bloodGroup, phoneNo;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            bloodGroup = (TextView) itemView.findViewById(R.id.bloodGroup);
            phoneNo = (TextView) itemView.findViewById(R.id.phoneNo);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


        }


    }


}



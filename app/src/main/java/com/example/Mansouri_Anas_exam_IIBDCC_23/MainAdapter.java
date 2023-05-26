package com.example.Mansouri_Anas_exam_IIBDCC_23;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //Create constructor
    public MainAdapter(Activity context, List<MainData> dataList)
    {
       this.context=context;
       this.dataList=dataList;
       notifyDataSetChanged();
    }
    {

    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Initialize view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position) {

        // Initialize main data
        MainData data=dataList.get(position);

        // Initialize database
        database=RoomDB.getInstance(context);

        // Set text on text view
        holder.textView.setText(data.getText());
        holder.CapitalView.setText(data.getCapital());
        holder.NbrView.setText(data.getNombrehabitants());
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainData d=dataList.get(holder.getAdapterPosition());
                database.mainDao().delete(d);
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                notifyDataSetChanged();
            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialize main data
                MainData d=dataList.get(holder.getAdapterPosition());

                // Get id
                final int sID=d.getID();

                // Get text
                String sText=d.getText();
                String sCapital=d.getCapital();
                String sNbr=d.getNombrehabitants();

                // create dialog
                final Dialog dialog=new Dialog(context);

                // set content view
                dialog.setContentView(R.layout.dialog_update);

                //Initialize width
                int width= WindowManager.LayoutParams.MATCH_PARENT;

                //Initialize height
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                //Set layout
                dialog.getWindow().setLayout(width,height);

                //show dialog
                dialog.show();

                //Initialize and assign variable
                final EditText editText=dialog.findViewById(R.id.edit_text);
                final EditText editCapital=dialog.findViewById(R.id.edit_capital);
                final EditText editNombre=dialog.findViewById(R.id.edit_nbr);
                Button btUpdate=dialog.findViewById(R.id.bt_update);

                // Set text on edit text
                editText.setText(sText);
                editCapital.setText(sCapital);
                editNombre.setText(sNbr);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss Dialog
                        dialog.dismiss();

                        //Get Updated text from edit text
                        String uText=editText.getText().toString().trim();
                        String uCapital=editCapital.getText().toString().trim();
                        String uNbr=editNombre.getText().toString().trim();

                        // Update text in database
                        database.mainDao().update(sID, uText,uNbr,uCapital);

                        //notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Initialize variable
        TextView textView;
        TextView CapitalView;
        TextView NbrView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable

            textView=itemView.findViewById(R.id.text_view);
            CapitalView=itemView.findViewById(R.id.capital_view);
            NbrView=itemView.findViewById(R.id.nbr_view);
            btEdit=itemView.findViewById(R.id.bt_edit);
            btDelete=itemView.findViewById(R.id.bt_delete);
        }
    }
}
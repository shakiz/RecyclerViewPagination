package app.shakil.com.recyclerviewpagination;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    //Here we are taking an arraylist to hold our data and a context to know the context we are in
    private ArrayList<String> stringArrayListData;
    private Context context;
    //Constructor with two parameters(ArrayList<String> and COntext)
    public RecyclerviewAdapter(ArrayList<String> stringArrayListData,Context context){
        this.stringArrayListData=stringArrayListData;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Getting the layout inflater from the context
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        //Creating the item view with our custom item layout
        View view=layoutInflater.inflate(R.layout.recyclerview_item,viewGroup,false);
        //Returning the view with the viewHolder class
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //Setting the recycler view items by means of their position
        viewHolder.textView.setText(stringArrayListData.get(position));
    }

    @Override
    public int getItemCount() {
        return stringArrayListData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.nameTXTXML);
        }
    }
}

package app.shakil.com.recyclerviewpagination;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RecyclerviewAdapter recyclerviewAdapter;
    private ArrayList<String> dataArrayList;
    private LinearLayoutManager linearLayoutManager;
    private int currentVisibleItems,totalItems,scrolledOutItems;
    private boolean isScrolling=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        String data[]={"Sakhawat ","Hossain","Shakil","Rahim","Karim","Barkat","Salam","Jobbar","Mizan","Jahid","Touhid","Rashed","Sagor","Foysal",
                "Sakhawat ","Hossain","Shakil","Rahim","Karim","Barkat","Salam","Jobbar","Mizan","Jahid","Touhid","Rashed","Sagor","Foysal"};
        dataArrayList=new ArrayList(Arrays.asList(data));
        recyclerviewAdapter=new RecyclerviewAdapter(dataArrayList,this);
        recyclerView.setAdapter(recyclerviewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Setting the on scroll listener on recycler view
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //This method defines the state when the user will start scrolling
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true;
                }
            }
            //This method defines when the user stop the scrolling
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentVisibleItems =linearLayoutManager.getChildCount();
                totalItems=linearLayoutManager.getItemCount();
                scrolledOutItems=linearLayoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentVisibleItems+scrolledOutItems==totalItems)){
                    //We need to fetch more data
                    //We will be fetching data each five seconds interval
                    isScrolling=false;
                    progressBar.setVisibility(View.VISIBLE);
                    fetchExistingData();
                }
            }
        });
    }

    private void fetchExistingData() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Here we will be fetching the remaining data
                //In real time we will be fetching those data from server but for now we have some static data so we will be using those
                for(int count=0;count<5;count++){
                    dataArrayList.add("Random "+Math.random());
                    recyclerviewAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, 5000);
    }

    public void init(){
        recyclerView=findViewById(R.id.recyclerviewXML);
        progressBar=findViewById(R.id.progressBarXML);
        progressBar=findViewById(R.id.progressBarXML);
        dataArrayList=new ArrayList<>();
        linearLayoutManager=new LinearLayoutManager(this);
    }
}

package vsevolod.recyclerviewbs;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MY_TAG";
    private RecyclerView myRecyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        adapter = new MyAdapter(new MyAdapter.ClickListener() {
            @Override
            public void itemClick(View view, int position) {
                Log.d(TAG, "itemClick() called with: view = [" + view + "], position = [" + position + "]");
                Toast.makeText(MainActivity.this, "Was clicke row " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void itemButtonclick(View view, int position) {
                Log.d(TAG, "itemButtonclick() called with: view = [" + view + "], position = [" + position + "]");
                Toast.makeText(MainActivity.this, "Was clicke btn " + position, Toast.LENGTH_SHORT).show();
            }
        });
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.addItemDecoration(new DividerItemDecoration
                (this,DividerItemDecoration.VERTICAL));
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        myRecyclerView.addOnItemTouchListener(new MyOnItemTouchListener
                (this,myRecyclerView,new MyOnItemTouchListener.MyOnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Was clicked " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButtonClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Was cklick on Button " + position, Toast.LENGTH_SHORT).show();
            }
        }


        ));

        MyTouchHelperCllback callback = new MyTouchHelperCllback(this,new MyTouchHelperCllback.MyTouchHelperListener() {
            @Override
            public void onSwiped(int position) {
                showDeleteDialog(position);
            }

            @Override
            public void onMoved(int from, int to) {
                adapter.move(from,to);
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(myRecyclerView);



     //   layoutInflaterExample();
    }

    private void showDeleteDialog(final int position){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Dlete row "+position)
                .setMessage("Are you sure that you want delete it?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeItem(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemChanged(position);
                    }
                })
                .setCancelable(false)
                .create();
        dialog.show();
    }

    private void layoutInflaterExample() {
        FrameLayout fl = new FrameLayout(this);


        View view1 = getLayoutInflater().inflate(R.layout.activity_main,null);
        if (view1 instanceof RelativeLayout) {
            Log.d(TAG, "onCreate:View1 = relativeLayout");
        } else{
            Log.d(TAG, "onCreate: View1 != relativeLayout");
        }
        View view2 = getLayoutInflater().inflate(R.layout.activity_main,fl);
        if (view2 instanceof RelativeLayout){
            Log.d(TAG,"onCreate:View2 = relativeLayout");
        } else if (view2 instanceof FrameLayout){
            Log.d(TAG, "onCreate: View2 = FrameLayout");
        }

        View view3 = getLayoutInflater().inflate(R.layout.activity_main,fl,false);
        if (view3 instanceof RelativeLayout){
            Log.d(TAG,"onCreate:View3 = relativeLayout");
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view3.getLayoutParams();

        } else if (view3 instanceof FrameLayout){
            Log.d(TAG, "onCreate: View3 = FrameLayout");        }

        View view4 = getLayoutInflater().inflate(R.layout.activity_main,fl,true);
        if (view4 instanceof RelativeLayout){
            Log.d(TAG,"onCreate:View4 = relativeLayout");
        } else if (view4 instanceof FrameLayout){
            Log.d(TAG, "onCreate: View4 = FrameLayout");
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view4.getLayoutParams();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mein_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_item){
            adapter.addItem();
        } else if (item.getItemId() == R.id.remove_item){
            adapter.removeItem();
        }
        return super.onOptionsItemSelected(item);
    }
}

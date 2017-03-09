package vsevolod.recyclerviewbs;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Owner on 06.03.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private static final int FIRST_TYPE = 1;
    private static final int SECOND_TYPE = 2;
    private ClickListener listener;

    private static final String TAG = "MyAdapter";
    private ArrayList<User> users = new ArrayList<>();

    public MyAdapter(ClickListener listener){
        fill();
        this.listener = listener;
    }


    private void fill(){
        for (int i = 0; i < 50; i++) {
            users.add(new User("Vasja" + i,"05489132" + i));

        }
    }
    public void addItem(){
        users.add(3,new User("Petya","5555555"));
        notifyItemInserted(3);
    }
    public void removeItem(){
        users.remove(4);
        notifyItemRemoved(4);
    }

    public void removeItem(int position){
        users.remove(position);
        notifyItemRemoved(position);
    }

    public void move (int from, int to){
        User user = users.remove(from);
        users.add(to,user);
        notifyItemMoved(from,to);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {

        if ((position&1) == 0){
            return FIRST_TYPE;
        } else {
            return SECOND_TYPE;
        }
//        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        Log.d(TAG, "onBindViewHolder: ");

        User user = users.get(position);
        holder.nameTxt.setText(user.getName());
        holder.phoneTxt.setText(user.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView nameTxt, phoneTxt;
        Button clickbtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTxt = (TextView) itemView.findViewById(R.id.name_txt);
            phoneTxt = (TextView) itemView.findViewById(R.id.phone_txt);
//            clickbtn = (Button) itemView.findViewById(R.id.clickBtn);
//            itemView.setOnClickListener(this);
//            clickbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.item_row){
                listener.itemClick(v,getAdapterPosition());
            } else if (v.getId() == R.id.clickBtn){
                listener.itemButtonclick(v,getAdapterPosition());
            }
        }
    }

    class SecondViewHolder extends RecyclerView.ViewHolder{

        public SecondViewHolder(View itemView) {
            super(itemView);
        }
    }



    interface ClickListener{
        void itemClick(View view, int position);
        void itemButtonclick(View view,int position);
    }

}

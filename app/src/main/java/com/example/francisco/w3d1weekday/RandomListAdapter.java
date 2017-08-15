package com.example.francisco.w3d1weekday;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by FRANCISCO on 10/08/2017.
 */

public class RandomListAdapter extends RecyclerView.Adapter<RandomListAdapter.ViewHolder> {

    private static final String TAG = "RandomListAdapter";
    ArrayList<Randoms> randomList = new ArrayList<>();
    Context context;

    public RandomListAdapter(ArrayList<Randoms> randomList) {
        this.randomList = randomList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvrandomlist;

        public ViewHolder(View itemView) {
            super(itemView);

            tvrandomlist = (TextView) itemView.findViewById(R.id.tvrandomlist);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.randomlist_item, parent, false);
        return new ViewHolder(view);
    }

    private int lastPosition = -1;
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position > lastPosition){
            //Animation animation = AnimationUtils
        }

        Log.d(TAG, "onBindViewHolder: ");
        final Randoms randoms = randomList.get(position);
        holder.tvrandomlist.setText(""+randoms.getRandom());

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+randomList.size());
        return randomList.size();
    }
}

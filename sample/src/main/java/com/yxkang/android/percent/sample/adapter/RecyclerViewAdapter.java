package com.yxkang.android.percent.sample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxkang.android.percent.sample.DesignActivity;
import com.yxkang.android.percent.sample.R;

/**
 * Created by yexiaokang.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private int[] colors = {R.color.color_0, R.color.color_1, R.color.color_2, R.color.color_3,
            R.color.color_4, R.color.color_5, R.color.color_6, R.color.color_7,
            R.color.color_8, R.color.color_9};
    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(position));
        int index = position % 10;
        holder.textView.setBackgroundColor(context.getResources().getColor(colors[index]));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DesignActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mParent;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            mParent = itemView;
            initView();
        }

        private void initView() {
            textView = findTextViewById(R.id.rv_textView);
        }

        private TextView findTextViewById(int id) {
            return (TextView) mParent.findViewById(id);
        }
    }
}

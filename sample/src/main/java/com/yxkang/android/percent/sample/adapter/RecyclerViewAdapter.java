package com.yxkang.android.percent.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxkang.android.percent.sample.R;

/**
 * Created by yexiaokang.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str = "position -> " +position;
        holder.textView.setText(str);
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

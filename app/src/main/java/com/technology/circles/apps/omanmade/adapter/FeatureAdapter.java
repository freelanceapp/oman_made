package com.technology.circles.apps.omanmade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.technology.circles.apps.omanmade.R;
import com.technology.circles.apps.omanmade.databinding.FeatureRowBinding;
import com.technology.circles.apps.omanmade.models.FeatureDataModel;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FeatureDataModel.FeatureModel> list;
    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;

    public FeatureAdapter(List<FeatureDataModel.FeatureModel> list, Context context,Fragment fragment) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragment = fragment;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        FeatureRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.feature_row, parent, false);
        return new MyHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;

        myHolder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public FeatureRowBinding binding;

        public MyHolder(@NonNull FeatureRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}

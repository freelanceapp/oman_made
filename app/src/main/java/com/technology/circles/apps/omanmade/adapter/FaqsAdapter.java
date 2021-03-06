package com.technology.circles.apps.omanmade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.technology.circles.apps.omanmade.R;
import com.technology.circles.apps.omanmade.databinding.FaqRowBinding;
import com.technology.circles.apps.omanmade.models.FaqsModel;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class FaqsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FaqsModel.Faqs> faqsList;
    private Context context;
    private LayoutInflater inflater;
    private String lang;

    public FaqsAdapter(List<FaqsModel.Faqs> faqsList, Context context) {
        this.faqsList = faqsList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        Paper.init(context);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        FaqRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.faq_row, parent, false);
        return new MyHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder eventHolder = (MyHolder) holder;

        eventHolder.binding.setLang(lang);
        eventHolder.binding.setModel(faqsList.get(position));

    }

    @Override
    public int getItemCount() {
        return faqsList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public FaqRowBinding binding;

        public MyHolder(@NonNull FaqRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}

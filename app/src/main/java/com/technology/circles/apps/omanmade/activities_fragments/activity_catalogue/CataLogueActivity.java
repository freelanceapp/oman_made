package com.technology.circles.apps.omanmade.activities_fragments.activity_catalogue;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.technology.circles.apps.omanmade.R;
import com.technology.circles.apps.omanmade.databinding.ActivityCatalogueBinding;
import com.technology.circles.apps.omanmade.interfaces.Listeners;
import com.technology.circles.apps.omanmade.language.LanguageHelper;

import java.util.Locale;

import io.paperdb.Paper;

public class CataLogueActivity extends AppCompatActivity implements Listeners.BackListener {
    private ActivityCatalogueBinding binding;
    private String lang;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LanguageHelper.updateResources(newBase, Paper.book().read("lang", Locale.getDefault().getLanguage())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_catalogue);
        initView();

    }


    private void initView() {

        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        binding.setBackListener(this);
      //  binding.progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

    }

    @Override
    public void back() {
        finish();
    }
}

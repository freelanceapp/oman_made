package com.technology.circles.apps.omanmade.activities_fragments.activity_home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.tabs.TabLayout;
import com.technology.circles.apps.omanmade.R;
import com.technology.circles.apps.omanmade.activities_fragments.activity_about.AboutActivity;
import com.technology.circles.apps.omanmade.activities_fragments.activity_catalogue.CataLogueActivity;
import com.technology.circles.apps.omanmade.activities_fragments.activity_contact.ContactUsActivity;
import com.technology.circles.apps.omanmade.activities_fragments.activity_faqs.FaqsActivity;
import com.technology.circles.apps.omanmade.activities_fragments.activity_home.fragments.Fragment_Directory;
import com.technology.circles.apps.omanmade.activities_fragments.activity_home.fragments.Fragment_Home;
import com.technology.circles.apps.omanmade.activities_fragments.activity_home.fragments.Fragment_Industry;
import com.technology.circles.apps.omanmade.activities_fragments.activity_home.fragments.Fragment_Sponsor;
import com.technology.circles.apps.omanmade.activities_fragments.activity_packages.PackagesActivity;
import com.technology.circles.apps.omanmade.activities_fragments.activity_peie.PeieActivity;
import com.technology.circles.apps.omanmade.language.LanguageHelper;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private AHBottomNavigation ah_bottom_nav;
    private FragmentManager fragmentManager;
    private Fragment_Home fragment_home;
    private Fragment_Directory fragment_directory;
    private Fragment_Industry fragment_industry;
    private Fragment_Sponsor fragment_sponsor;
    private String lang;
    ///////////////////////////////////////////////////
    private CardView cardViewMainHome, cardViewHome, cardViewDirectory, cardViewCreateList, cardViewProfile,
            cardViewMainAbout, cardViewCatalogue, cardViewService, cardViewPackage, cardViewAbout, cardViewPeie,
            cardViewMainSupport, cardViewFaq, cardViewContact, cardViewSetting,
            cardViewMainLegal, cardViewPrivacy, cardViewTerms;

    private ImageView arrow1, arrow2, arrow3, arrow4, imgFacebook, imgIntagram, imgWhatsApp, imgTwitter;
    private ExpandableLayout expandLayoutHome, expandLayoutAbout, expandLayoutSupport, expandLayoutLegal;
    private TabLayout tab;
    private TextView tvRate, tvTitle;
    private LinearLayout llHomeContent;


    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LanguageHelper.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            DisplayFragmentHome();
        }

    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, R.color.black));
        ah_bottom_nav = findViewById(R.id.ah_bottom_nav);
        setUpBottomNavigation();


        cardViewMainHome = findViewById(R.id.cardViewMainHome);
        cardViewHome = findViewById(R.id.cardViewHome);
        cardViewDirectory = findViewById(R.id.cardViewDirectory);
        cardViewCreateList = findViewById(R.id.cardViewCreateList);
        cardViewProfile = findViewById(R.id.cardViewProfile);
        cardViewMainAbout = findViewById(R.id.cardViewMainAbout);
        cardViewCatalogue = findViewById(R.id.cardViewCatalogue);
        cardViewService = findViewById(R.id.cardViewService);
        cardViewPackage = findViewById(R.id.cardViewPackage);
        cardViewAbout = findViewById(R.id.cardViewAbout);
        cardViewPeie = findViewById(R.id.cardViewPeie);
        cardViewMainSupport = findViewById(R.id.cardViewMainSupport);
        cardViewFaq = findViewById(R.id.cardViewFaq);
        cardViewContact = findViewById(R.id.cardViewContact);
        cardViewSetting = findViewById(R.id.cardViewSetting);
        cardViewMainLegal = findViewById(R.id.cardViewMainLegal);
        cardViewPrivacy = findViewById(R.id.cardViewPrivacy);
        cardViewTerms = findViewById(R.id.cardViewTerms);
        arrow1 = findViewById(R.id.arrow1);
        arrow2 = findViewById(R.id.arrow2);
        arrow3 = findViewById(R.id.arrow3);
        arrow4 = findViewById(R.id.arrow4);
        imgFacebook = findViewById(R.id.imgFacebook);
        imgIntagram = findViewById(R.id.imgIntagram);
        imgWhatsApp = findViewById(R.id.imgWhatsApp);
        imgTwitter = findViewById(R.id.imgTwitter);
        expandLayoutHome = findViewById(R.id.expandLayoutHome);
        expandLayoutAbout = findViewById(R.id.expandLayoutAbout);
        expandLayoutSupport = findViewById(R.id.expandLayoutSupport);
        expandLayoutLegal = findViewById(R.id.expandLayoutLegal);
        llHomeContent = findViewById(R.id.llHomeContent);

        tab = findViewById(R.id.tab);
        tvRate = findViewById(R.id.tvRate);
        tvTitle = findViewById(R.id.tvTitle);

        tab.addTab(tab.newTab().setText("عربي"));
        tab.addTab(tab.newTab().setText("English"));

        if (lang.equals("ar")) {
            tab.getTabAt(0).select();
        } else {
            tab.getTabAt(1).select();

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawer.setElevation(0.0f);
        }

        drawer.setScrimColor(Color.TRANSPARENT);

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                float slideX = drawerView.getWidth() * slideOffset;
                if (lang.equals("ar")) {
                    slideX = slideX * -1;
                }
                llHomeContent.setTranslationX(slideX);

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                drawer.closeDrawer(GravityCompat.START);


                int pos = tab.getPosition();
                if (pos == 0) {
                    RefreshActivity("ar");
                } else {
                    RefreshActivity("en");

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        cardViewMainHome.setOnClickListener(view -> {

            if (expandLayoutHome.isExpanded()) {
                expandLayoutHome.collapse(true);
                arrow1.animate().rotationBy(-180).setDuration(500).start();
            } else {
                expandLayoutHome.expand(true);
                arrow1.animate().rotationBy(180).setDuration(500).start();
            }

        });

        cardViewMainAbout.setOnClickListener(view -> {

            if (expandLayoutAbout.isExpanded()) {
                expandLayoutAbout.collapse(true);
                arrow2.animate().rotationBy(-180).setDuration(500).start();
            } else {
                expandLayoutAbout.expand(true);
                arrow2.animate().rotationBy(180).setDuration(500).start();
            }

        });

        cardViewMainSupport.setOnClickListener(view -> {

            if (expandLayoutSupport.isExpanded()) {
                expandLayoutSupport.collapse(true);
                arrow3.animate().rotationBy(-180).setDuration(500).start();
            } else {
                expandLayoutSupport.expand(true);
                arrow3.animate().rotationBy(180).setDuration(500).start();
            }

        });

        cardViewMainLegal.setOnClickListener(view -> {

            if (expandLayoutLegal.isExpanded()) {
                expandLayoutLegal.collapse(true);
                arrow4.animate().rotationBy(-180).setDuration(500).start();
            } else {
                expandLayoutLegal.expand(true);
                arrow4.animate().rotationBy(180).setDuration(500).start();
            }

        });

        cardViewContact.setOnClickListener(view -> {

            arrow4.animate().rotationBy(-180).setDuration(500).start();
            expandLayoutLegal.collapse(true);
            new Handler()
                    .postDelayed(() -> drawer.closeDrawer(GravityCompat.START), 500);

            new Handler()
                    .postDelayed(this::navigateToContactActivity, 1000);

        });

        cardViewHome.setOnClickListener(view -> {

            arrow1.animate().rotationBy(-180).setDuration(500).start();
            expandLayoutHome.collapse(true);
            new Handler()
                    .postDelayed(() -> {
                        drawer.closeDrawer(GravityCompat.START);
                        DisplayFragmentHome();
                    }, 500);


        });

        cardViewDirectory.setOnClickListener(view -> {
            arrow1.animate().rotationBy(-180).setDuration(500).start();

            expandLayoutHome.collapse(true);
            new Handler()
                    .postDelayed(() -> {
                        drawer.closeDrawer(GravityCompat.START);
                        DisplayFragmentDirectory();
                    }, 500);


        });
        cardViewAbout.setOnClickListener(v -> {
            arrow2.animate().rotationBy(-180).setDuration(500).start();
            expandLayoutAbout.collapse(true);
            new Handler()
                    .postDelayed(() -> drawer.closeDrawer(GravityCompat.START), 500);


            new Handler()
                    .postDelayed(this::navigateToAboutActivity, 1000);


        });

        cardViewCatalogue.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CataLogueActivity.class);
            startActivity(intent);
        });

        cardViewPeie.setOnClickListener(v -> {

            arrow2.animate().rotationBy(-180).setDuration(500).start();
            expandLayoutAbout.collapse(true);
            new Handler()
                    .postDelayed(() -> drawer.closeDrawer(GravityCompat.START), 500);


            new Handler().postDelayed(this::navigateToPeieActivity, 1000);
        });
        cardViewFaq.setOnClickListener(v -> {
            arrow3.animate().rotationBy(-180).setDuration(500).start();
            expandLayoutSupport.collapse(true);

            new Handler().postDelayed(() -> drawer.closeDrawer(GravityCompat.START), 500);


            new Handler().postDelayed(this::navigateToFaqActivity, 1000);


        });

        cardViewPackage.setOnClickListener(v -> {

            arrow2.animate().rotationBy(-180).setDuration(500).start();
            expandLayoutAbout.collapse(true);
            new Handler()
                    .postDelayed(() -> drawer.closeDrawer(GravityCompat.START), 500);


            new Handler().postDelayed(this::navigateToPackageActivity, 1000);
        });
    }

    private void navigateToPackageActivity() {

        Intent intent = new Intent(HomeActivity.this, PackagesActivity.class);
        startActivity(intent);
    }

    private void navigateToPeieActivity() {
        Intent intent = new Intent(HomeActivity.this, PeieActivity.class);
        startActivity(intent);
    }

    private void navigateToAboutActivity() {
        Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    private void navigateToFaqActivity() {
        Intent intent = new Intent(HomeActivity.this, FaqsActivity.class);
        startActivity(intent);
    }

    private void navigateToContactActivity() {
        Intent intent = new Intent(this, ContactUsActivity.class);
        startActivity(intent);
    }


    private void setUpBottomNavigation() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(getString(R.string.home), R.drawable.ic_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(getString(R.string.directory), R.drawable.ic_search);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(getString(R.string.indus_Area), R.drawable.ic_industry);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(getString(R.string.spons), R.drawable.ic_team);

        ah_bottom_nav.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        ah_bottom_nav.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.white));
        ah_bottom_nav.setTitleTextSizeInSp(14, 12);
        ah_bottom_nav.setForceTint(true);
        ah_bottom_nav.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimary));
        ah_bottom_nav.setInactiveColor(ContextCompat.getColor(this, R.color.black));
        ah_bottom_nav.addItem(item1);
        ah_bottom_nav.addItem(item2);
        ah_bottom_nav.addItem(item3);
        ah_bottom_nav.addItem(item4);

        ah_bottom_nav.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case 0:
                    DisplayFragmentHome();
                    break;
                case 1:
                    DisplayFragmentDirectory();
                    break;
                case 2:
                    DisplayFragmentIndustrialArea();
                    break;
                case 3:
                    DisplayFragmentSponsor();
                    break;


            }
            return false;
        });

        ah_bottom_nav.setCurrentItem(0, false);


    }


    private void DisplayFragmentHome() {
        if (fragment_home == null) {
            fragment_home = Fragment_Home.newInstance();
        }

        if (fragment_directory != null && fragment_directory.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_directory).commit();
        }

        if (fragment_industry != null && fragment_industry.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_industry).commit();
        }

        if (fragment_sponsor != null && fragment_sponsor.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_sponsor).commit();
        }


        if (fragment_home.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_home).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_home, "fragment_home").addToBackStack("fragment_home").commit();

        }
        ah_bottom_nav.setCurrentItem(0, false);
        tvTitle.setText(getString(R.string.home));

    }

    private void DisplayFragmentDirectory() {

        if (fragment_directory == null) {
            fragment_directory = Fragment_Directory.newInstance();
        }

        if (fragment_home != null && fragment_home.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_home).commit();
        }

        if (fragment_industry != null && fragment_industry.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_industry).commit();
        }

        if (fragment_sponsor != null && fragment_sponsor.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_sponsor).commit();
        }


        if (fragment_directory.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_directory).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_directory, "fragment_directory").addToBackStack("fragment_directory").commit();

        }
        ah_bottom_nav.setCurrentItem(1, false);
        tvTitle.setText(getString(R.string.directory));


    }

    private void DisplayFragmentIndustrialArea() {


        if (fragment_industry == null) {
            fragment_industry = Fragment_Industry.newInstance();
        }

        if (fragment_home != null && fragment_home.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_home).commit();
        }

        if (fragment_directory != null && fragment_directory.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_directory).commit();
        }

        if (fragment_sponsor != null && fragment_sponsor.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_sponsor).commit();
        }


        if (fragment_industry.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_industry).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_industry, "fragment_industry").addToBackStack("fragment_industry").commit();

        }
        ah_bottom_nav.setCurrentItem(2, false);

        tvTitle.setText(getString(R.string.indus_Area));

    }

    private void DisplayFragmentSponsor() {

        if (fragment_sponsor == null) {
            fragment_sponsor = Fragment_Sponsor.newInstance();
        }

        if (fragment_home != null && fragment_home.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_home).commit();
        }

        if (fragment_directory != null && fragment_directory.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_directory).commit();
        }

        if (fragment_industry != null && fragment_industry.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_industry).commit();
        }


        if (fragment_sponsor.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_sponsor).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_sponsor, "fragment_sponsor").addToBackStack("fragment_sponsor").commit();

        }
        ah_bottom_nav.setCurrentItem(3, false);

        tvTitle.setText(getString(R.string.spons));

    }


    public void RefreshActivity(String lang) {
        Paper.book().write("lang", lang);
        LanguageHelper.setNewLocale(this, lang);

        new Handler()
                .postDelayed(() -> {

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }, 1050);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);


        } else {
            if (fragment_home != null && fragment_home.isAdded() && fragment_home.isVisible()) {
                finish();

            } else {
                DisplayFragmentHome();
            }


        }
    }

}

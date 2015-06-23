package com.wizmacau.androidrecipes;

import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.wizmacau.androidrecipes.core.App;
import com.wizmacau.androidrecipes.model.Article;
import com.wizmacau.androidrecipes.model.Articles;

import javax.inject.Inject;

/**
 * Created by llitfkitfk on 6/19/15.
 */
public class MainActivity extends AppCompatActivity implements ArticlesConsumer {

    private static final String DATA_FRAGMENT_TAG = DataFragment.class.getCanonicalName();
    private static final int MENU_GROUP = 0;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Articles articles;

    @Inject
    LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).component().inject(this);
        Log.d("MainActivity", locationManager.toString());

        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setupToolbar();
        setupNavigationView();
        setupDataFragment();
    }

    private void setupDataFragment() {
        DataFragment dataFragment = (DataFragment) getSupportFragmentManager().findFragmentByTag(DATA_FRAGMENT_TAG);
        if (dataFragment == null) {
            dataFragment = (DataFragment) Fragment.instantiate(this, DataFragment.class.getName());
            dataFragment.setRetainInstance(true);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(dataFragment,DATA_FRAGMENT_TAG);
            transaction.commit();
        }
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                selectArticle(menuItem.getTitle());

                return true;
            }
        });
    }


    private void selectArticle(CharSequence title) {
        Article article = articles.getArticle(title.toString());
        setCurrentArticle(article);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setArticles(Articles articles) {
        Menu menu = navigationView.getMenu();
        menu.clear();
        this.articles = articles;
        int item = 0;
        for (String articleTitle : articles) {
            menu.add(MENU_GROUP, item, item, articleTitle);
            if (item == 0) {
                menu.getItem(0).setChecked(true);
            }
            item++;
        }
        menu.setGroupCheckable(MENU_GROUP, true, true);
        setCurrentArticle(articles.getFirst());
    }

    private void setCurrentArticle(Article article) {
        setTitle(article.getTitle());
    }

    @Override
    public void handleError(String message) {

    }
}

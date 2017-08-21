package com.adityadua.fragments9demo;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
        implements HeadlineFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_article);

        Toast.makeText(this, "MainActivity + onCreate + Start", Toast.LENGTH_SHORT).show();
        // Load :: First Fragment : Headline Fragment
        if(findViewById(R.id.fragment_container) !=null){

            if(savedInstanceState !=null){
                return ;
            }

            HeadlineFragment fragment = new HeadlineFragment();
            Toast.makeText(this, "MainActivity + onCreate + HeadlineFragment", Toast.LENGTH_SHORT).show();

            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();

        }
        Toast.makeText(this, "MainActivity + onCreate + End", Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onArticleSelected(int position) {

        ArticleFragment articleFragment =
                (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);


        if(articleFragment != null){
            articleFragment.updateArticle(position);
        }else{
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();

            args.putInt(ArticleFragment.ARG_POSITION,position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container,newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }
}

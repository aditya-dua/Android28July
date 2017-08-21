package com.adityadua.fragments9demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AdityaDua on 18/08/17.
 */


public class ArticleFragment extends Fragment {

    static String ARG_POSITION="position";
    int mCurrentPosition=-1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState !=null){
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);

        }

        Toast.makeText(getActivity(), "ArticleFragment + onCreateView", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.article_news,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "ArticleFragment + onStart", Toast.LENGTH_SHORT).show();

        Bundle args = getArguments();

        if(args !=null){
            updateArticle(args.getInt(ARG_POSITION));
        }else if(mCurrentPosition != -1){
            updateArticle(mCurrentPosition);
        }

    }

    public void updateArticle(int position){
        Toast.makeText(getActivity(), "ArticleFragment + updateArticle", Toast.LENGTH_SHORT).show();

        TextView article=(TextView)getActivity().findViewById(R.id.article);
        article.setText(Ipsum.article[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION,mCurrentPosition);
    }
}// create a view



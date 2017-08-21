package com.adityadua.fragments9demo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by AdityaDua on 18/08/17.
 */

public class HeadlineFragment extends ListFragment {
    // The list basically will have only String
    // ArrayAdaptor<String>

    // onClick => onClickListener is called
    OnHeadlineSelectedListener mCallback;

    public interface OnHeadlineSelectedListener{

        public void onArticleSelected(int position);


    }

    // the first method whihc will be called once the activity is linked with the fragment

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Toast.makeText(activity, "HeadlineFragment + onAttach", Toast.LENGTH_SHORT).show();
        // the parent activity is implementing the :: OnHeadlinesleectd...
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        }catch (ClassCastException e){
            throw  new ClassCastException(activity.toString());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Listview : so I will create the listview
        // ListFragment
        // I just need to pass the adaptor to my list...
        // ArrayAdaptor<> = ne Array....(context,layout,array);
        // 2 layouts might be possible
        // android.R.layout.simple_list_item_1
        // android.R.layout.simple_list_item_a  ctivated_1

        int layout = Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        Toast.makeText(getActivity(), "HeadlineFragment + onCreate", Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(getActivity(), layout,Ipsum.headline);
        setListAdapter(adaptor);
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "HeadlineFragment + onStart", Toast.LENGTH_SHORT).show();

        if(getFragmentManager().findFragmentById(R.id.fragment_container)!=null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // for verisons which are tab Based
        int layout = Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        setListAdapter(new ArrayAdapter<String>(getActivity(),layout,Ipsum.headline));
    }*/

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "HeadlineFragment + onListItemClick + "+position, Toast.LENGTH_SHORT).show();

        mCallback.onArticleSelected(position);
        getListView().setItemChecked(position,true);

    }
}

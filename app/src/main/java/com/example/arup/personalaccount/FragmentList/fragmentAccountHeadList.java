package com.example.arup.personalaccount.FragmentList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.arup.personalaccount.CustomListView.AccountHeadListAdapter;
import com.example.arup.personalaccount.DBHelper.AccountHeadHelper;
import com.example.arup.personalaccount.Fragment.fragmentAccountHead;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentAccountHeadList extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {

    ArrayList<IncomeExpenseHead> dataModels;
    ListView listView;
    Button btnAddNew;
    public static AccountHeadListAdapter adapter;
    private AccountHeadHelper accountHeadHelper;
    private SearchView mSearchView;
    android.widget.Filter filter;
    public fragmentAccountHeadList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_account_head_list, container, false);

        btnAddNew = (Button)view.findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(this);
        mSearchView = view.findViewById(R.id.searchAccHead);
        listView=(ListView)view.findViewById(R.id.lvAccountHead);


        dataModels= new ArrayList<IncomeExpenseHead>();
        accountHeadHelper = new AccountHeadHelper(getActivity());
        dataModels = accountHeadHelper.getIncomeExpenseHeadList();

        dataModels.add(new IncomeExpenseHead(1,"Big Fish","Expense"));
        dataModels.add(new IncomeExpenseHead(2,"Black bengal (Mutton)","Expense"));
        dataModels.add(new IncomeExpenseHead(3,"Soyabin oil","Expense"));
        dataModels.add(new IncomeExpenseHead(4,"Nazir shai rice","Expense"));
        dataModels.add(new IncomeExpenseHead(5,"Vegitables","Expense"));
        dataModels.add(new IncomeExpenseHead(6,"Bashmoti rice","Expense"));
        dataModels.add(new IncomeExpenseHead(7,"Mug dal","Expense"));

        AccountHeadListAdapter adapter = new AccountHeadListAdapter(getActivity(),R.layout.accountheadlistview,dataModels);
        listView.setAdapter(adapter);

        listView.setTextFilterEnabled(false);
        listView.setAdapter(adapter);
        filter = adapter.getFilter();
        searchView();
        return view;
    }

    @Override
    public void onClick(View v) {
        try{
            FragmentManager fragmentManager=getFragmentManager();
            //FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer,new fragmentAccountHead());
            fragmentTransaction.commit();

//            if(getSupportActionBar()!=null){
////                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////                getSupportActionBar().setDisplayShowHomeEnabled(true);
////            }
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }


    private void searchView(){
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            //ctlist.clearTextFilter();
            filter.filter("");

        } else {
            //ctlist.setFilterText(newText);
            filter.filter(newText);
        }
        return true;
    }
}

package com.example.arup.personalaccount.FragmentList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.arup.personalaccount.CustomListView.ExpenseListAdapter;
import com.example.arup.personalaccount.DBHelper.IncomeExpenseJournalHelper;
import com.example.arup.personalaccount.Fragment.fragmentExpenseTrans;
import com.example.arup.personalaccount.Model.IncomeExpenseJournal;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentExpenseList extends Fragment {


    public fragmentExpenseList() {
        // Required empty public constructor
    }

    Button btnAddNew;
    //SearchView searchExpense;

    ArrayList<IncomeExpenseJournal> dataModels;
    ListView listView;
    //Button btnAddNew;
    public static ExpenseListAdapter adapter;
    private IncomeExpenseJournalHelper incomeExpenseJournalHelper;
    private SearchView mSearchView;
    android.widget.Filter filter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_expense_list, container, false);
        btnAddNew = (Button)view.findViewById(R.id.btnExpenseNew);
        mSearchView = view.findViewById(R.id.searchExpense);

        listView = view.findViewById(R.id.listViewExpense);

        dataModels= new ArrayList<IncomeExpenseJournal>();
        incomeExpenseJournalHelper = new IncomeExpenseJournalHelper(getActivity());
        dataModels = incomeExpenseJournalHelper.getExpenseList();

        dataModels.add(new IncomeExpenseJournal(1,"2018/05/01", 12322,"","Expense For buying big fish"));
        dataModels.add(new IncomeExpenseJournal(2,"2018/05/01", 200,"","Expense For buying vegitable"));
        adapter = new ExpenseListAdapter(getActivity(),R.layout.expenselistview,dataModels);
        listView.setAdapter(adapter);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipTofragmentExpense();
            }
        });
        return view;
    }

    private void flipTofragmentExpense(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmMainContainer,new fragmentExpenseTrans());
        fragmentTransaction.commit();
    }


}

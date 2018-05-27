package com.example.arup.personalaccount.CustomListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AccountHeadListAdapter extends ArrayAdapter<IncomeExpenseHead> implements Filterable {
    Context context;
    ArrayList<IncomeExpenseHead> incomeExpenseHeadslist;
    ArrayList<IncomeExpenseHead>searchIncomeExpense;
    IncomeExpenseHead incomeExpenseHead;

    public AccountHeadListAdapter(@NonNull Context context, int resource, @NonNull List<IncomeExpenseHead> objects) {
        super(context, R.layout.accountheadlistview, objects);
        //comment
    }


    @Override
    public Filter getFilter() {
        return null;
    }


}

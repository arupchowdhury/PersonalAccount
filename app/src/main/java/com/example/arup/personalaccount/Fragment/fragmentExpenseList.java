package com.example.arup.personalaccount.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.arup.personalaccount.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentExpenseList extends Fragment {


    public fragmentExpenseList() {
        // Required empty public constructor
    }

    Button btnAddNew;
    SearchView searchExpense;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_expense_list, container, false);
        btnAddNew = (Button)view.findViewById(R.id.btnExpenseNew);
        searchExpense = view.findViewById(R.id.searchExpense);
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

package com.example.arup.personalaccount.FragmentList;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.arup.personalaccount.CustomListView.LedgerListAdapter;
import com.example.arup.personalaccount.DBHelper.IncomeExpenseJournalHelper;
import com.example.arup.personalaccount.Model.IncomeExpenseJournal;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountLedger extends Fragment implements View.OnClickListener {


    public AccountLedger() {
        // Required empty public constructor
    }
    EditText etFromDate,etToDate;
    Button btnFind;

    ArrayList<IncomeExpenseJournal> dataModels;
    ListView listView;
    //Button btnAddNew;
    public static LedgerListAdapter adapter;
    private IncomeExpenseJournalHelper incomeExpenseJournalHelper;
    private SearchView mSearchView;
    android.widget.Filter filter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_ledger, container, false);

        etFromDate = view.findViewById(R.id.etFromDate);
        etToDate = view.findViewById(R.id.etToDate);
        btnFind = view.findViewById(R.id.btnFind);
        listView = view.findViewById(R.id.listViewLedger);

        btnFind.setOnClickListener(this);
        etFromDate.setOnClickListener(this);
        etToDate.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==etFromDate){
            Calendar calendar = Calendar.getInstance();
            int year= calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    getDate(i,i1,i2,etFromDate);
                }
            },year,month,day);
            //datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis()+7 * 1000 * 60 * 60 * 24);
            //datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()-7 * 1000 * 60 * 60 * 24);
            datePickerDialog.show();
        }
        else if(v==etToDate){
            Calendar calendar = Calendar.getInstance();
            int year= calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    getDate(i,i1,i2,etToDate);
                }
            },year,month,day);
            //datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis()+7 * 1000 * 60 * 60 * 24);
            //datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()-7 * 1000 * 60 * 60 * 24);
            datePickerDialog.show();
        }
        else if(v==btnFind){

            String frondate = etFromDate.getText().toString();
            String todate = etToDate.getText().toString();

            dataModels= new ArrayList<IncomeExpenseJournal>();
            incomeExpenseJournalHelper = new IncomeExpenseJournalHelper(getActivity());
            dataModels = incomeExpenseJournalHelper.getLedgerList(frondate,todate);

//        dataModels.add(new IncomeExpenseJournal(1,"2018/05/01", 12322,"","Expense For buying big fish"));
//        dataModels.add(new IncomeExpenseJournal(2,"2018/05/01", 200,"","Expense For buying vegitable"));
            adapter = new LedgerListAdapter(getActivity(),R.layout.ledgerlistview,dataModels);
            listView.setAdapter(adapter);
        }
    }

    private void getDate(int year,int month, int day,EditText editText){
        StringBuilder str = new StringBuilder();
        String date = year+"/"+month+"/"+day+" 00:00:00";//str.append(day)+str.append(:)+str.append(month)+"/"+str.append(year);
        editText.setText(date);

    }
}

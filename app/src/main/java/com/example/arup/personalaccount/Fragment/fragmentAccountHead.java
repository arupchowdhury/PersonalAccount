package com.example.arup.personalaccount.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arup.personalaccount.DBHelper.AccountHeadHelper;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentAccountHead extends Fragment implements View.OnClickListener {


    EditText etHeadId,etHeadName;
    Spinner spHeadType;
    Button btnSaveAccHead,btnDeleteAccHead;
    IncomeExpenseHead incomeExpenseHead;

    String[] headtypelist = new String[]{"Expense","Income"};
    ArrayAdapter<String> headTypeAdapter;

    public fragmentAccountHead() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_account_head, container, false);

        etHeadId = view.findViewById(R.id.etHeadId);
        etHeadName = view.findViewById(R.id.etHeadName);
        spHeadType  = view.findViewById(R.id.spinAccountType);
        btnSaveAccHead = view.findViewById(R.id.btnSaveAcchead);
        btnDeleteAccHead = view.findViewById(R.id.btnDeleteAccHead);
        spinHeadTypeAdapter();
        if(getArguments()!=null){
            String strtext = getArguments().getString("accheadId");
            Toast.makeText(getActivity(),""+strtext,Toast.LENGTH_LONG).show();
//        loadAllContent(strtext);
        }


        btnSaveAccHead.setOnClickListener(this);
        btnDeleteAccHead.setOnClickListener(this);


        return view;
    }

    private void spinHeadTypeAdapter(){
        headTypeAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,headtypelist);
        spHeadType.setAdapter(headTypeAdapter);
    }

    private void loadAllContent(String id){
        AccountHeadHelper accountHeadHelper = new AccountHeadHelper(getActivity());
        IncomeExpenseHead incomeExpenseHead = accountHeadHelper.getIncomeExpenseHead(Integer.parseInt(id));
        etHeadName.setText(incomeExpenseHead.getHeadName());
        Toast.makeText(getActivity(),""+incomeExpenseHead.getHeadName(),Toast.LENGTH_LONG).show();
        //spHeadType.setAdapter();
    }

    @Override
    public void onClick(View v) {
        try{
            if(v==btnSaveAccHead){
                Toast.makeText(getActivity(),""+"Save",Toast.LENGTH_LONG).show();

                AccountHeadHelper accountHeadHelper = new AccountHeadHelper(getActivity());

                String accheadName = etHeadName.getText().toString();
                String accheadType = spHeadType.getSelectedItem().toString();


                AtomicLong id = new AtomicLong();
                if(!etHeadId.getText().toString().matches("")){
                    int accheadId = Integer.parseInt(etHeadId.getText().toString());
                    incomeExpenseHead = new IncomeExpenseHead(accheadId,accheadName,accheadType);
                    id.set(accountHeadHelper.updateAccountHead(incomeExpenseHead));
                }
                else {
                    incomeExpenseHead = new IncomeExpenseHead(accheadName,accheadType);
                    id.set(accountHeadHelper.insertAccountHead(incomeExpenseHead));
                }
                if(id.get()>0){
                   Toast.makeText(getActivity(),""+"Saved successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),""+"Saving error",Toast.LENGTH_LONG).show();
                }

            }
            else if(v==btnDeleteAccHead){
                Toast.makeText(getActivity(),""+"Delete",Toast.LENGTH_LONG).show();
//                int accheadId = Integer.parseInt(etHeadId.getText().toString());
//                String accheadName = etHeadName.getText().toString();
//                String accheadType = spHeadType.getSelectedItem().toString();
            }
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }
}

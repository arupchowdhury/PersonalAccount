package com.example.arup.personalaccount.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.arup.personalaccount.DBHelper.BankAccInfoHelper;
import com.example.arup.personalaccount.DBHelper.BankInfoHelper;
import com.example.arup.personalaccount.Model.BankAccInformation;
import com.example.arup.personalaccount.Model.BankInformation;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentBankAcc extends Fragment implements View.OnClickListener{

    EditText etBankAccId,etAccName,etBranchName;
    Spinner spinBAnkId;
    Button btnSaveAcc,btnDeleteAcc;
    BankInfoHelper bankInfoHelper;
    BankAccInfoHelper bankAccInfoHelper;
    ArrayAdapter<BankInformation> bankInformationArrayAdapter;
    BankAccInformation bankAccInformation;
    int spinBankIdSelecttedval=0;

    public fragmentBankAcc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_bank_acc, container, false);
//        Toolbar toolbar = view.findViewById(R.id.toobarAcc);
//        toolbar.setNavigationIcon(R.drawable.ic_launcher_background);


        etBankAccId = view.findViewById(R.id.etBankAccIdAcc);
        etAccName = view.findViewById(R.id.etAccNameAcc);
        etBranchName = view.findViewById(R.id.etBranchNameAcc);
        spinBAnkId = view.findViewById(R.id.spinBankIdAcc);

        btnSaveAcc=view.findViewById(R.id.btnSaveAcc);
        btnDeleteAcc = view.findViewById(R.id.btnDeleteAcc);

        loadspinBankId();

        spinBAnkId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BankInformation bankInformation = (BankInformation) parent.getSelectedItem();
                spinBankIdSelecttedval=bankInformation.getBankId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSaveAcc.setOnClickListener(this);
        btnDeleteAcc.setOnClickListener(this);

        return view;
    }

    public void loadspinBankId(){
        bankInfoHelper = new BankInfoHelper(getActivity());
        ArrayList<BankInformation> bankInformationArrayList = bankInfoHelper.getBankList();
        bankInformationArrayAdapter = new ArrayAdapter<BankInformation>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankInformationArrayList);
        spinBAnkId.setAdapter(bankInformationArrayAdapter);
    }

    @Override
    public void onClick(View v) {
        try{
            if(v==btnSaveAcc){
                bankAccInfoHelper = new BankAccInfoHelper(getActivity());

                int bankId = spinBankIdSelecttedval;
                String accName = etAccName.getText().toString();
                String branchName = etBranchName.getText().toString();

                AtomicLong id = new AtomicLong();
                String message="";

                if(!etBankAccId.getText().toString().matches("")){
                    int bankAccId = Integer.parseInt(etBankAccId.getText().toString());
                    bankAccInformation = new BankAccInformation(bankAccId,bankId,accName,branchName);
                    id.set(bankAccInfoHelper.updateBankAccInfo(bankAccInformation));
                    message="Updated successfully";
                }
                else {
                    bankAccInformation = new BankAccInformation(bankId,accName,branchName);
                    id.set(bankAccInfoHelper.insertBankAccInfo(bankAccInformation));
                    message="Saved successfully";
                }
                if(id.get()>0){
                    Toast.makeText(getActivity(),""+message,Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                }
            }

            else if(v==btnDeleteAcc){
                Toast.makeText(getActivity(),""+spinBankIdSelecttedval,Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }
}

package com.example.arup.personalaccount.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arup.personalaccount.DBHelper.BankInfoHelper;
import com.example.arup.personalaccount.Model.BankInformation;
import com.example.arup.personalaccount.R;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentBankInfo extends Fragment implements View.OnClickListener {

    BankInfoHelper bankInfoHelper;
    BankInformation bankInformation;
    EditText etBankId,etBankName;
    Button btnSaveBankInfo,btnDeleteBankInfo;
    public fragmentBankInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_bank_info, container, false);

        etBankId = view.findViewById(R.id.etBankId);
        etBankName = view.findViewById(R.id.etBankName);
        btnSaveBankInfo = view.findViewById(R.id.btnSaveBank);
        btnDeleteBankInfo = view.findViewById(R.id.btnDeleteBank);

        btnSaveBankInfo.setOnClickListener(this);
        btnDeleteBankInfo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        try{
            if(v==btnSaveBankInfo){
                bankInfoHelper = new BankInfoHelper(getActivity());
                String bankName = etBankName.getText().toString();
                AtomicLong id = new AtomicLong();
                String message="";
                if(!etBankId.getText().toString().matches("")){
                    int bankId = Integer.parseInt(etBankId.getText().toString());
                    bankInformation = new BankInformation(bankId,bankName);
                    id.set(bankInfoHelper.updateBankInfo(bankInformation));
                    message = "Successfully updated";
                }
                else {
                    bankInformation = new BankInformation(bankName);
                    id.set(bankInfoHelper.insertBankInfo(bankInformation));
                    message = "Successfully saved";

                }
                if(id.get()>0){
                    Toast.makeText(getActivity(),""+message,Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }
}

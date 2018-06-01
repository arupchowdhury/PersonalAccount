package com.example.arup.personalaccount.Fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arup.personalaccount.DBHelper.BankInfoHelper;
import com.example.arup.personalaccount.FragmentList.fragmentBankList;
import com.example.arup.personalaccount.Model.BankAccInformation;
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
    Button btnSaveBankInfo,btnDeleteBankInfo,btnRefreshBank;
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
        btnRefreshBank = view.findViewById(R.id.btnRefreshBank);

        if(getArguments()!=null){
            String strtext = getArguments().getString("bankId");
            Toast.makeText(getActivity(),""+strtext,Toast.LENGTH_LONG).show();
            loadAllContent(strtext);
        }

        btnSaveBankInfo.setOnClickListener(this);
        btnDeleteBankInfo.setOnClickListener(this);
        btnRefreshBank.setOnClickListener(this);

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
                    clearAll();
                    Toast.makeText(getActivity(),""+message,Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                }
            }
            else if(v==btnDeleteBankInfo){
                try{

                    if(etBankId.getText().toString().equals(""))
                        return;
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Are you sure want to delete?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            BankInfoHelper bankInfoHelper = new BankInfoHelper(getActivity());
                            long _id =  bankInfoHelper.deleteBankInfo(Integer.parseInt(etBankId.getText().toString()));
                            if(_id>0){
                                clearAll();
                                FragmentManager fragmentManager=getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frmMainContainer,new fragmentBankList());
                                fragmentTransaction.commit();
                                dialogInterface.dismiss();
                            }
                            dialogInterface.cancel();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                }
                catch (Exception ex){
                    throw ex;
                }
            }
            else if(v==btnRefreshBank){
                clearAll();
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }

    private void loadAllContent(String id){
        BankInfoHelper dbhelper = new BankInfoHelper(getActivity());
        BankInformation bankInformation = dbhelper.getBankInfo(Integer.parseInt(id));
        etBankId.setText(Integer.toString(bankInformation.getBankId()));
        etBankName.setText(bankInformation.getBankName());
    }

    private void clearAll(){
        etBankId.setText("");
        etBankName.setText("");
    }
}

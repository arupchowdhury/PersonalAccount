package com.example.arup.personalaccount.Fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.arup.personalaccount.DBHelper.AccountHeadHelper;
import com.example.arup.personalaccount.DBHelper.BankAccInfoHelper;
import com.example.arup.personalaccount.DBHelper.BankInfoHelper;
import com.example.arup.personalaccount.Model.BankAccInformation;
import com.example.arup.personalaccount.Model.BankInformation;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */


public class fragmentExpenseTrans extends Fragment implements View.OnClickListener {

EditText etexpenseAmount,etchequeNo,etdescription,etjournalRemark,etpostingDate,etreferenceNo;

Spinner spinExpenseAccId,spinPaymentMethod,spinBankId,spinBankAcc,spinPaymentStatus;
Button btnSaveExpense,btnCancelExpense;

int spinIncomeHeadVal=0;
int spinBankNameVal=0;
int spinBankAccNoVal=0;
AccountHeadHelper accountHeadHelper;

ArrayAdapter<IncomeExpenseHead> adapterIncome;
ArrayAdapter<BankInformation> adapterBankInfo;
ArrayAdapter<BankAccInformation> adapterBankAccInfo;

String[]paymentMethodarry = new String[]{"Cash","Bank"};
String[]paymentStatusArray = new String[]{"Paid","Due","Advance"};
ArrayAdapter<String> paymentMethodAdapter;
ArrayAdapter<String> paymentStatusAdapter;

    public fragmentExpenseTrans() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_expense_trans, container, false);

        spinExpenseAccId = view.findViewById(R.id.spinAccountHead);
        etexpenseAmount = view.findViewById(R.id.etExpenseAmount);
        spinPaymentMethod = view.findViewById(R.id.spinPaymentMethod);
        spinBankId = view.findViewById(R.id.spinBankName);
        spinBankAcc = view.findViewById(R.id.spinBankAcc);
        spinPaymentStatus = view.findViewById(R.id.spinPaymentStatus);
        etchequeNo = view.findViewById(R.id.etChequeNo);
        etdescription = view.findViewById(R.id.etDescription);
        etjournalRemark = view.findViewById(R.id.etRemark);
        etpostingDate = view.findViewById(R.id.dpPostingDate);


        btnSaveExpense = view.findViewById(R.id.btnSaveExpense);
        btnCancelExpense = view.findViewById(R.id.btnCancelExpense);

        loadspinExpense();
        loadspinPaymentMethodAdapter();
        initializeCboAll();
        loadspinPaymentStatusAdapter();

        spinExpenseAccId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IncomeExpenseHead incomeExpenseHead = (IncomeExpenseHead) parent.getSelectedItem();
                spinIncomeHeadVal=incomeExpenseHead.getHeadId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(paymentMethodarry[position].toString()=="Bank"){
                    loadBankInfoList();
                }
                else {
                    initializeCboAll();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinBankId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    BankInformation bankInformation = (BankInformation) parent.getSelectedItem();
                    spinBankNameVal=bankInformation.getBankId();
                    loadBankAccList(bankInformation.getBankId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//        final Calendar myCalendar = Calendar.getInstance();
//        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                //updateLabel();
//            }
//
//        };

//        etpostingDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(getActivity(), date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });







        btnSaveExpense.setOnClickListener(this);
        btnCancelExpense.setOnClickListener(this);

        return view;
    }

    public void loadspinExpense(){
        accountHeadHelper = new AccountHeadHelper(getActivity());
        ArrayList<IncomeExpenseHead> incomeExpenseHeadArrayList = accountHeadHelper.getcboIncomeExpenseHeadList("Expense");
        if(incomeExpenseHeadArrayList.size()>0){
            adapterIncome = new ArrayAdapter<IncomeExpenseHead>(getActivity(),android.R.layout.simple_spinner_dropdown_item,incomeExpenseHeadArrayList);
            spinExpenseAccId.setAdapter(adapterIncome);
        }

    }

    private void loadspinPaymentMethodAdapter(){
        paymentMethodAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,paymentMethodarry);
        spinPaymentMethod.setAdapter(paymentMethodAdapter);
    }

    private void loadspinPaymentStatusAdapter(){
        paymentStatusAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,paymentStatusArray);
        spinPaymentStatus.setAdapter(paymentStatusAdapter);
    }

    private void initializeCboAll(){
        ArrayList<BankInformation> bankInformationArrayList = new ArrayList<>();
        bankInformationArrayList.add(new BankInformation(0,"--Select Bank--"));
        adapterBankInfo = new ArrayAdapter<BankInformation>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankInformationArrayList);
        spinBankId.setAdapter(adapterBankInfo);

        ArrayList<BankAccInformation> bankAccInformationArrayList = new ArrayList<>();
        bankAccInformationArrayList.add(new BankAccInformation(0,"--Select Accunt--"));
        adapterBankAccInfo = new ArrayAdapter<BankAccInformation>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankAccInformationArrayList);
        spinBankAcc.setAdapter(adapterBankAccInfo);
    }

    private void loadBankInfoList(){
        BankInfoHelper bankInfoHelper = new BankInfoHelper(getActivity());
        ArrayList<BankInformation> bankInformationArrayList = new ArrayList<>();
        bankInformationArrayList = bankInfoHelper.getBankList();
        adapterBankInfo = new ArrayAdapter<BankInformation>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankInformationArrayList);
        spinBankId.setAdapter(adapterBankInfo);
    }

    private void loadBankAccList(int bankId){
        BankAccInfoHelper bankAccInfoHelper = new BankAccInfoHelper(getActivity());
        ArrayList<BankAccInformation> bankAccInformationArrayList = bankAccInfoHelper.getBakAccListByBankId(bankId);
        adapterBankAccInfo = new ArrayAdapter<BankAccInformation>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankAccInformationArrayList);
        spinBankAcc.setAdapter(adapterBankAccInfo);

    }

    private int getIndexAccHead (ArrayAdapter<IncomeExpenseHead> adapter, String myString){

        int index = 0;

        for (int i=0;i<adapter.getCount();i++){
            if(adapter.getItem(i).getHeadId()==Integer.parseInt(myString)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public void onClick(View v) {
//        try{
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//
//            if(v==btnSaveExpense){
//                try {
//
//                    String postingDate=etpostingDate.getText().toString();
//                    int headId =spinIncomeHeadVal;
//                    double incomeAmount=0;
//                    double expenseAmount=Double.parseDouble(etexpenseAmount.getText().toString());
//                    String accountTypeName="Expense";
//                    String paymentMethodId=spinPaymentMethod.getSelectedItem().toString();
//                    String paymentStatusId=spinPaymentStatus.getSelectedItem().toString();
//                    String description=etdescription.getText().toString();
//                    String journalRemark=etjournalRemark.getText().toString();
//                    String refrenceNum="";
//                    String createdate= dateFormat.format(new Date());
//                    String updatedDate="";
//
//                    int bankName=spinBankNameVal;
//                    int accountName=spinBankAccNoVal;
//                }
//                catch (Exception ex){
//                    throw ex;
//                }
//            }
//        }
//        catch (Exception ex){
//            throw ex;
//        }
    }
}

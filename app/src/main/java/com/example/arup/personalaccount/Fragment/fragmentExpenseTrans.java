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
import android.widget.Toast;


import com.example.arup.personalaccount.DBHelper.AccountHeadHelper;
import com.example.arup.personalaccount.DBHelper.BankAccInfoHelper;
import com.example.arup.personalaccount.DBHelper.BankInfoHelper;
import com.example.arup.personalaccount.DBHelper.IncomeExpenseJournalHelper;
import com.example.arup.personalaccount.Model.BankAccInformation;
import com.example.arup.personalaccount.Model.BankInformation;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.Model.IncomeExpenseJournal;
import com.example.arup.personalaccount.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple {@link Fragment} subclass.
 */


public class fragmentExpenseTrans extends Fragment implements View.OnClickListener {

EditText etexpenseAmount,etchequeNo,etdescription,etjournalRemark,etpostingDate,etreferenceNo,etTransIdExpense;

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

IncomeExpenseJournalHelper incomeExpenseJournalHelper;

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
        etTransIdExpense = view.findViewById(R.id.etTransIdExpense);


        btnSaveExpense = view.findViewById(R.id.btnSaveExpense);
        btnCancelExpense = view.findViewById(R.id.btnCancelExpense);

        //etpostingDate.setEnabled(false);


        loadspinExpense();
        loadspinPaymentMethodAdapter();
        //initializeCboAll();
        loadspinPaymentStatusAdapter();
        loadBankInfoList();

        if(getArguments()!=null){
            String strtext = getArguments().getString("transId");
            Toast.makeText(getActivity(),""+strtext,Toast.LENGTH_LONG).show();
            loadAllContent(strtext);
        }

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
//                    loadBankInfoList();
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

        spinBankAcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BankAccInformation bankAccInformation=(BankAccInformation)parent.getSelectedItem();
                spinBankAccNoVal = bankAccInformation.getAccId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnSaveExpense.setOnClickListener(this);
        btnCancelExpense.setOnClickListener(this);
        etpostingDate.setOnClickListener(this);

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
//        ArrayList<BankInformation> bankInformationArrayList = new ArrayList<>();
//        bankInformationArrayList.add(new BankInformation(0,"--Select Bank--"));
//        adapterBankInfo = new ArrayAdapter<BankInformation>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankInformationArrayList);
//        spinBankId.setAdapter(adapterBankInfo);

        ArrayList<BankAccInformation> bankAccInformationArrayList = new ArrayList<>();
        bankAccInformationArrayList.add(new BankAccInformation(0,"--Select Accunt--"));
        adapterBankAccInfo = new ArrayAdapter<BankAccInformation>(getActivity(),android.R.layout.simple_spinner_dropdown_item,bankAccInformationArrayList);
        spinBankAcc.setAdapter(adapterBankAccInfo);
    }

    private void loadBankInfoList(){
        BankInfoHelper bankInfoHelper = new BankInfoHelper(getActivity());
        ArrayList<BankInformation> bankInformationArrayList = new ArrayList<>();
        bankInformationArrayList = bankInfoHelper.getcboBankList();
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

    private int getIndexBank (ArrayAdapter<BankInformation> adapter, String myString){

        int index = 0;

        for (int i=0;i<adapter.getCount();i++){
            if(adapter.getItem(i).getBankId()==Integer.parseInt(myString)){
                index = i;
            }
        }
        return index;
    }

    private int getIndexBankAcc (ArrayAdapter<BankAccInformation> adapter, String myString){

        int index = 0;

        for (int i=0;i<adapter.getCount();i++){
            if(adapter.getItem(i).getAccId()==Integer.parseInt(myString)){
                index = i;
            }
        }
        return index;
    }

    private void loadAllContent(String id){
        IncomeExpenseJournalHelper dbhelper = new IncomeExpenseJournalHelper(getActivity());
        IncomeExpenseJournal incomeExpenseJournal = dbhelper.getIncomeExpenseByTransId(Integer.parseInt(id));

        etexpenseAmount.setText(Double.toString(incomeExpenseJournal.getExpenseAmount()));
        etchequeNo.setText((incomeExpenseJournal.getChequeNo()==null)?"":incomeExpenseJournal.getChequeNo());
        etdescription.setText((incomeExpenseJournal.getDescription()==null)?"":incomeExpenseJournal.getDescription());
        etjournalRemark.setText((incomeExpenseJournal.getJournalRemark()==null)?"":incomeExpenseJournal.getJournalRemark());
        etpostingDate.setText(incomeExpenseJournal.getPostingDate());
        if(!incomeExpenseJournal.getRefrenceNum().equals(""))
            etreferenceNo.setText((incomeExpenseJournal.getRefrenceNum()==null)?"":incomeExpenseJournal.getRefrenceNum());
        etTransIdExpense.setText(Integer.toString(incomeExpenseJournal.getTransId()));


        //int indexAccHead = getIndexAccHead(adapterIncome,Integer.toString(incomeExpenseJournal.getHeadId()));
        spinExpenseAccId.setSelection(getIndexAccHead(adapterIncome,Integer.toString(incomeExpenseJournal.getHeadId())));

        int index = Arrays.asList(paymentMethodarry).indexOf(incomeExpenseJournal.getPaymentMethodId());
        spinPaymentMethod.setSelection(index);
//        loadBankInfoList();
        if(incomeExpenseJournal.getPaymentMethodId().equals("Bank")){
//            loadBankInfoList();
//            int indexbank = getIndexBank(adapterBankInfo,Integer.toString(incomeExpenseJournal.getBankName()));
            spinBankId.setSelection(getIndexBank(adapterBankInfo,Integer.toString(incomeExpenseJournal.getBankName())));


            loadBankAccList(incomeExpenseJournal.getBankName());
//            int indexbankAcc = getIndexBankAcc(adapterBankAccInfo,Integer.toString(incomeExpenseJournal.getAccountName()));
            spinBankAcc.setSelection(getIndexBankAcc(adapterBankAccInfo,Integer.toString(incomeExpenseJournal.getAccountName())));
        }

        int indexPaymentStatus = Arrays.asList(paymentStatusArray).indexOf(incomeExpenseJournal.getPaymentStatusId());
        spinPaymentStatus.setSelection(indexPaymentStatus);


    }

    @Override
    public void onClick(View v) {
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            if(v==btnSaveExpense){
                try {

                    incomeExpenseJournalHelper = new IncomeExpenseJournalHelper(getActivity());


                    String postingDate=etpostingDate.getText().toString();
                    int headId =spinIncomeHeadVal;
                    double incomeAmount=0;
                    double expenseAmount=Double.parseDouble(etexpenseAmount.getText().toString());
                    String accountTypeName="Expense";
                    String paymentMethodId=spinPaymentMethod.getSelectedItem().toString();
                    String paymentStatusId=spinPaymentStatus.getSelectedItem().toString();
                    String description=etdescription.getText().toString();
                    String journalRemark=etjournalRemark.getText().toString();
                    String refrenceNum="";
                    String createdate= dateFormat.format(new Date());
                    String updatedDate="";
                    String chequeNo=etchequeNo.getText().toString();

                    int bankName=spinBankNameVal;
                    int accountName=spinBankAccNoVal;

                    AtomicLong id = new AtomicLong();
                    String message="";

                    if(!etTransIdExpense.getText().toString().matches("")){
                        int transId = Integer.parseInt(etTransIdExpense.getText().toString());
                        IncomeExpenseJournal incomeExpenseJournal = new IncomeExpenseJournal(
                                transId,postingDate,headId,incomeAmount,expenseAmount,accountTypeName,paymentMethodId,chequeNo,
                                paymentStatusId,description,journalRemark,refrenceNum,createdate,updatedDate,bankName,accountName
                        );
                        id.set(incomeExpenseJournalHelper.updateIncomeExpenseJournal(incomeExpenseJournal));
                        message="Successfully updated";

                    }
                    else {
                        IncomeExpenseJournal incomeExpenseJournal = new IncomeExpenseJournal(
                                postingDate,headId,incomeAmount,expenseAmount,accountTypeName,paymentMethodId,chequeNo,
                                paymentStatusId,description,journalRemark,refrenceNum,createdate,updatedDate,bankName,accountName
                        );
                        id.set(incomeExpenseJournalHelper.insertIncomeExpenseJournal(incomeExpenseJournal));
                        message="Successfully saved";
                    }
                    if(id.get()>0){
                        Toast.makeText(getActivity(),""+message,Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception ex){
                    throw ex;
                }
            }
            else if(v==btnCancelExpense){
                try{
                    String postingDate=etpostingDate.getText().toString();
                    int headId =spinIncomeHeadVal;
                    double incomeAmount=0;
                    double expenseAmount= - Double.parseDouble(etexpenseAmount.getText().toString());
                    String accountTypeName="Expense";
                    String paymentMethodId=spinPaymentMethod.getSelectedItem().toString();
                    String paymentStatusId=spinPaymentStatus.getSelectedItem().toString();
                    String description=etdescription.getText().toString();
                    String journalRemark=(etjournalRemark.getText().toString().matches(""))? etTransIdExpense.getText().toString()+"- Cancel":etjournalRemark.getText().toString();
                    String refrenceNum=etTransIdExpense.getText().toString();
                    String createdate= dateFormat.format(new Date());
                    String updatedDate="";
                    String chequeNo=etchequeNo.getText().toString();

                    int bankName=spinBankNameVal;
                    int accountName=spinBankAccNoVal;
                }
                catch (Exception ex){
                    throw ex;
                }
            }
            else if(v==etpostingDate){
                Calendar calendar = Calendar.getInstance();
                int year= calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        getDate(i,i1,i2);
                    }
                },year,month,day);
                //datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis()+7 * 1000 * 60 * 60 * 24);
                //datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()-7 * 1000 * 60 * 60 * 24);
                datePickerDialog.show();
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }

    private void getDate(int year,int month, int day){
        StringBuilder str = new StringBuilder();
        String date = year+"/"+month+"/"+day+" 00:00:00";//str.append(day)+str.append(:)+str.append(month)+"/"+str.append(year);
        etpostingDate.setText(date);

    }
}

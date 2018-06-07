package com.example.arup.personalaccount.Fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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

public class fragmentIncomeTrans extends Fragment implements OnClickListener, AdapterView.OnItemSelectedListener {


    public fragmentIncomeTrans() {

    }

    //region input control declaration
    EditText etexpenseAmount,etchequeNo,etdescription,etjournalRemark,etpostingDate,etreferenceNo,etTransIdExpense;
    Spinner spinExpenseAccId,spinPaymentMethod,spinBankId,spinBankAcc,spinPaymentStatus;
    Button btnSaveExpense,btnCancelExpense,btnRefreshExpense;
    //endregion

    //region for spinner variable
    int spinIncomeHeadVal=0;
    int spinBankNameVal=0;
    int spinBankAccNoVal=0;
    //endregion

    //region helper class
    AccountHeadHelper accountHeadHelper;
    IncomeExpenseJournalHelper incomeExpenseJournalHelper;
    //endregion

    //region static array for payment method and payment status
    String[]paymentMethodarry = new String[]{"Cash","Bank"};
    String[]paymentStatusArray = new String[]{"Paid","Due","Advance"};
    //endregion

    //region declare adapter variable
    ArrayAdapter<IncomeExpenseHead> adapterIncome;
    ArrayAdapter<BankInformation> adapterBankInfo;
    ArrayAdapter<BankAccInformation> adapterBankAccInfo;
    ArrayAdapter<String> paymentMethodAdapter;
    ArrayAdapter<String> paymentStatusAdapter;
    //endregion



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_income_trans, container, false);

        //region variable initialization
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
        etreferenceNo = view.findViewById(R.id.etreferenceNo);

        btnSaveExpense = view.findViewById(R.id.btnSaveExpense);
        btnCancelExpense = view.findViewById(R.id.btnCancelExpense);
        btnRefreshExpense = view.findViewById(R.id.btnRefreshExpense);

        etjournalRemark.setEnabled(false);
        etreferenceNo.setEnabled(false);
        //endregion

        //region calling dropdown method
        loadspinExpense();
        loadspinPaymentMethodAdapter();
        loadspinPaymentStatusAdapter();
        initializeCboAll();
//        loadBankInfoList();
//        loadBankAccList(0);
        //endregion

        //region Drop down selected
        spinExpenseAccId.setOnItemSelectedListener(this);
        spinPaymentMethod.setOnItemSelectedListener(this);
        spinBankAcc.setOnItemSelectedListener(this);
        spinBankId.setOnItemSelectedListener(this);
        //endregion

        //region click listener
        btnSaveExpense.setOnClickListener(this);
        btnCancelExpense.setOnClickListener(this);
        etpostingDate.setOnClickListener(this);
        btnRefreshExpense.setOnClickListener(this);

        //endregion

        //region get data from list
        if(getArguments()!=null){
            String strtext = getArguments().getString("transId");
            Toast.makeText(getActivity(),""+strtext,Toast.LENGTH_LONG).show();
            disableAllControl();
            loadAllContent(strtext);
        }
        //endregion
        return view;
    }

    //region click event
    @Override
    public void onClick(View v) {
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            if(v==btnSaveExpense){
                try {

                    if(!checkvalidation())
                        return;
                    incomeExpenseJournalHelper = new IncomeExpenseJournalHelper(getActivity());


                    AtomicLong id = new AtomicLong();
                    String message="";

                    if(!etTransIdExpense.getText().toString().matches("")&& etreferenceNo.getText().toString().matches("")){
                        id.set(incomeExpenseJournalHelper.updateIncomeExpenseJournal(makeincomeexpenseJournal("update")));
                        message="Successfully updated";
                    }
                    else if(etTransIdExpense.getText().toString().matches("")){
                        id.set(incomeExpenseJournalHelper.insertIncomeExpenseJournal(makeincomeexpenseJournal("insert")));
                        message="Successfully saved";
                    }
                    if(id.get()>0){
                        Toast.makeText(getActivity(),""+message,Toast.LENGTH_LONG).show();
                        clearAll();
                    }
                    else {
                        if(!etreferenceNo.getText().toString().matches(""))
                            Toast.makeText(getActivity(),"Document has been canceled",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception ex){
                    throw ex;
                }
            }
            else if(v==btnCancelExpense){
                try{
                    incomeExpenseJournalHelper = new IncomeExpenseJournalHelper(getActivity());

                    if(etTransIdExpense.getText().toString().matches("")){
                        Toast.makeText(getActivity(),"Document has been Canceled",Toast.LENGTH_LONG).show();
                        return;
                    }

                    else if(!etjournalRemark.getText().toString().matches("")){
                        Toast.makeText(getActivity(),"Document has been Canceled",Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(!etreferenceNo.getText().toString().matches("")){
                        Toast.makeText(getActivity(),"Document has been Canceled",Toast.LENGTH_LONG).show();
                        return;
                    }

                    AtomicLong id = new AtomicLong();
                    AtomicLong idupdate = new AtomicLong();
                    String message="";

                    id.set(incomeExpenseJournalHelper.insertIncomeExpenseJournal(makeincomeexpenseJournal("cancel")));
                    idupdate.set(incomeExpenseJournalHelper.updateIncomeExpenseJournal(makeincomeexpenseJournal("updateaftercancel")));
                    message="Successfully updated";
                    if(id.get()>0){
                        Toast.makeText(getActivity(),""+message,Toast.LENGTH_LONG).show();
                        clearAll();
                    }
                    else {
                        Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                    }

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
                datePickerDialog.show();
            }
            else if(v==btnRefreshExpense){
                clearAll();
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }
    //endregion

    //region drop down loading (spinner)
    public void loadspinExpense(){
        accountHeadHelper = new AccountHeadHelper(getActivity());
        ArrayList<IncomeExpenseHead> incomeExpenseHeadArrayList = accountHeadHelper.getcboIncomeExpenseHeadList("Income");
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

    //endregion

    //region drop down indexing
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


    //endregion

    //region object making
    private IncomeExpenseJournal makeincomeexpenseJournal(String crudname) {
        try {
            IncomeExpenseJournal incomeExpenseJournal = null;

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String postingDate = etpostingDate.getText().toString();
            int headId = spinIncomeHeadVal;
            double incomeAmount = Double.parseDouble(etexpenseAmount.getText().toString());
            double expenseAmount = 0;
            String accountTypeName = "Income";
            String paymentMethodId = spinPaymentMethod.getSelectedItem().toString();
            String paymentStatusId = spinPaymentStatus.getSelectedItem().toString();
            String description = etdescription.getText().toString();
            String journalRemark = etjournalRemark.getText().toString();
            String refrenceNum = etreferenceNo.getText().toString();
            String createdate = dateFormat.format(new Date());
            String updatedDate = "";
            String chequeNo = etchequeNo.getText().toString();

            int bankName = spinBankNameVal;
            int accountName = spinBankAccNoVal;

            if (crudname.equals("insert")) {
                incomeExpenseJournal = new IncomeExpenseJournal(
                        postingDate, headId, incomeAmount, expenseAmount, accountTypeName, paymentMethodId, chequeNo,
                        paymentStatusId, description, journalRemark, refrenceNum, createdate, updatedDate, bankName, accountName
                );
            } else if (crudname.equals("update")) {
                int transId = Integer.parseInt(etTransIdExpense.getText().toString());
                updatedDate = dateFormat.format(new Date());

                incomeExpenseJournal = new IncomeExpenseJournal(
                        transId, postingDate, headId, incomeAmount, expenseAmount, accountTypeName, paymentMethodId, chequeNo,
                        paymentStatusId, description, journalRemark, refrenceNum, createdate, updatedDate, bankName, accountName
                );
            } else if (crudname.equals("cancel")) {
                incomeAmount = -Double.parseDouble(etexpenseAmount.getText().toString());
                journalRemark = etTransIdExpense.getText().toString() + " - Cancel";
                refrenceNum = etTransIdExpense.getText().toString();

                incomeExpenseJournal = new IncomeExpenseJournal(
                        postingDate, headId, incomeAmount, expenseAmount, accountTypeName, paymentMethodId, chequeNo,
                        paymentStatusId, description, journalRemark, refrenceNum, createdate, updatedDate, bankName, accountName
                );

            } else if (crudname.equals("updateaftercancel")) {
                int transId = Integer.parseInt(etTransIdExpense.getText().toString());
                refrenceNum = etTransIdExpense.getText().toString();
                incomeExpenseJournal = new IncomeExpenseJournal(
                        transId, postingDate, headId, incomeAmount, expenseAmount, accountTypeName, paymentMethodId, chequeNo,
                        paymentStatusId, description, journalRemark, refrenceNum, createdate, updatedDate, bankName, accountName
                );
            }
            return incomeExpenseJournal;
        } catch (Exception ex) {
            ex.getStackTrace();
            return null;
        }

    }
    //endregion object making

    //region utility ui method
    private void disablebutton() {
        btnSaveExpense.setEnabled(false);
        btnCancelExpense.setEnabled(false);
    }

    private void disableAllControl(){
        etjournalRemark.setEnabled(false);
        etTransIdExpense.setEnabled(false);
        spinExpenseAccId.setEnabled(false);
        spinPaymentMethod.setEnabled(false);
        spinBankId.setEnabled(false);
        spinBankAcc.setEnabled(false);
        etexpenseAmount.setEnabled(false);
        etchequeNo.setEnabled(false);
        etpostingDate.setEnabled(false);

    }

    private void clearAll(){
        spinExpenseAccId.setEnabled(true);
        spinPaymentMethod.setEnabled(true);
        spinBankId.setEnabled(true);
        spinBankAcc.setEnabled(true);
        etexpenseAmount.setEnabled(true);
        etchequeNo.setEnabled(true);
        etpostingDate.setEnabled(true);
        spinPaymentStatus.setEnabled(true);

        etexpenseAmount.setText("");
        etchequeNo.setText("");
        etdescription.setText("");
        etjournalRemark.setText("");
        etpostingDate.setText("");
        etreferenceNo.setText("");
        etTransIdExpense.setText("");
        etTransIdExpense.setText("");

        loadspinExpense();
        loadspinPaymentMethodAdapter();
        loadspinPaymentStatusAdapter();
        loadBankInfoList();
        loadBankAccList(0);

        btnSaveExpense.setEnabled(true);
        btnCancelExpense.setEnabled(true);
    }

    private boolean checkvalidation(){
        if(etpostingDate.getText().toString().matches("")){
            Toast.makeText(getActivity(),"Posting date required",Toast.LENGTH_LONG).show();
            return false;
        }

        else if(spinIncomeHeadVal==0){
            Toast.makeText(getActivity(),"Income head required",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(etexpenseAmount.getText().toString().matches("")){
            Toast.makeText(getActivity(),"Income amount required",Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;
    }
    private void getDate(int year,int month, int day){
        StringBuilder str = new StringBuilder();
        String date = year+"/"+month+"/"+day+" 00:00:00";//str.append(day)+str.append(:)+str.append(month)+"/"+str.append(year);
        etpostingDate.setText(date);

    }
    //endregion

    //region onItemSelected event (drop down)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent==spinExpenseAccId){
            IncomeExpenseHead incomeExpenseHead = (IncomeExpenseHead) parent.getSelectedItem();
            spinIncomeHeadVal=incomeExpenseHead.getHeadId();
        }
        else if(parent==spinPaymentMethod){
            Toast.makeText(getActivity(),""+spinPaymentMethod.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            if(spinPaymentMethod.getSelectedItem().toString()=="Bank"){
                if(spinBankId.getSelectedItemId()==0)
                    loadBankInfoList();
            }
            else{
                initializeCboAll();
            }
        }
        else if(parent==spinBankId){
            BankInformation bankInformation = (BankInformation) parent.getSelectedItem();
            spinBankNameVal=bankInformation.getBankId();
            if(spinBankAcc.getSelectedItemId()==0)
                loadBankAccList(spinBankNameVal);
        }
        else if(parent==spinBankAcc){
            BankAccInformation bankAccInformation=(BankAccInformation)parent.getSelectedItem();
            spinBankAccNoVal = bankAccInformation.getAccId();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
//endregion

    //region loan all control
    private void loadAllContent(String id){
        IncomeExpenseJournalHelper dbhelper = new IncomeExpenseJournalHelper(getActivity());
        IncomeExpenseJournal incomeExpenseJournal = dbhelper.getIncomeExpenseByTransId(Integer.parseInt(id));

        etexpenseAmount.setText(Double.toString(incomeExpenseJournal.getIncomeAmount()));
        etchequeNo.setText((incomeExpenseJournal.getChequeNo()==null)?"":incomeExpenseJournal.getChequeNo());
        etdescription.setText((incomeExpenseJournal.getDescription()==null)?"":incomeExpenseJournal.getDescription());
        etjournalRemark.setText((incomeExpenseJournal.getJournalRemark()==null)?"":incomeExpenseJournal.getJournalRemark());
        etpostingDate.setText(incomeExpenseJournal.getPostingDate());
        if(!incomeExpenseJournal.getRefrenceNum().equals(""))
            etreferenceNo.setText(incomeExpenseJournal.getRefrenceNum());
        etTransIdExpense.setText(Integer.toString(incomeExpenseJournal.getTransId()));



        spinExpenseAccId.setSelection(getIndexAccHead(adapterIncome,Integer.toString(incomeExpenseJournal.getHeadId())));
        spinIncomeHeadVal=incomeExpenseJournal.getHeadId();

        int index = Arrays.asList(paymentMethodarry).indexOf(incomeExpenseJournal.getPaymentMethodId());
        spinPaymentMethod.setSelection(index);

        if(incomeExpenseJournal.getPaymentMethodId().equals("Bank")){
            loadBankInfoList();

            spinBankId.setSelection(getIndexBank(adapterBankInfo,Integer.toString(incomeExpenseJournal.getBankName())));
            spinBankNameVal=incomeExpenseJournal.getBankName();

            loadBankAccList(incomeExpenseJournal.getBankName());
            spinBankAcc.setSelection(getIndexBankAcc(adapterBankAccInfo,Integer.toString(incomeExpenseJournal.getAccountName())));
            spinBankAccNoVal=incomeExpenseJournal.getAccountName();
        }

        if(incomeExpenseJournal.getPaymentStatusId().equals("Paid"))
            spinPaymentStatus.setEnabled(false);
        int indexPaymentStatus = Arrays.asList(paymentStatusArray).indexOf(incomeExpenseJournal.getPaymentStatusId());
        spinPaymentStatus.setSelection(indexPaymentStatus);


        if(!incomeExpenseJournal.getRefrenceNum().matches(""))
            disablebutton();


    }
    //endregion

}

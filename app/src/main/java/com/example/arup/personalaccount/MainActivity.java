package com.example.arup.personalaccount;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arup.personalaccount.Fragment.fragmentAccountHead;
import com.example.arup.personalaccount.Fragment.fragmentBankAcc;
import com.example.arup.personalaccount.Fragment.fragmentBankInfo;
import com.example.arup.personalaccount.Fragment.fragmentExpenseTrans;
import com.example.arup.personalaccount.FragmentList.fragmentAccountHeadList;
import com.example.arup.personalaccount.Fragment.fragmentDashboard;
import com.example.arup.personalaccount.FragmentList.fragmentBankAccountList;
import com.example.arup.personalaccount.FragmentList.fragmentBankList;
import com.example.arup.personalaccount.FragmentList.fragmentExpenseList;
import com.example.arup.personalaccount.Fragment.fragmentPaymentMethod;

public class MainActivity extends AppCompatActivity {
    //    FragmentManager fragmentManager;
//    FragmentTransaction fragmentTransaction;

    String fname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fragment = getIntent().getStringExtra("fragment");
        String parm = getIntent().getStringExtra("id");
//        Toast.makeText(this, "" + fragment+": "+parm, Toast.LENGTH_LONG).show();
        if(fragment!=null){
            if(fragment.equals("fragmentAccountHead")){
                Bundle bundle = new Bundle();
                bundle.putString("accheadId",parm);
                fragmentAccountHead fragmentAccountHead = new fragmentAccountHead();
                fragmentAccountHead.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer, fragmentAccountHead);
                fragmentTransaction.commit();
            }
            else if(fragment.equals("fragmentBankAcc")){
                Bundle bundle = new Bundle();
                bundle.putString("accId",parm);
                fragmentBankAcc fragmentBankAcc = new fragmentBankAcc();
                fragmentBankAcc.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer, fragmentBankAcc);
                fragmentTransaction.commit();
            }
            //fragmentExpenseTrans
            else if(fragment.equals("fragmentBankInfo")){
                Bundle bundle = new Bundle();
                bundle.putString("bankId",parm);
                fragmentBankInfo fragmentBankInfo = new fragmentBankInfo();
                fragmentBankInfo.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer, fragmentBankInfo);
                fragmentTransaction.commit();
            }

            else if(fragment.equals("fragmentExpenseTrans")){
                Bundle bundle = new Bundle();
                bundle.putString("transId",parm);
                fragmentExpenseTrans fragmentExpenseTrans = new fragmentExpenseTrans();
                fragmentExpenseTrans.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer, fragmentExpenseTrans);
                fragmentTransaction.commit();
            }
        }


        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer, new fragmentDashboard());
            fragmentTransaction.commit();
            if(getSupportActionBar()!=null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itmAccountHead) {
//            Toast.makeText(this, "" + R.id.itmAccountHead, Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer, new fragmentAccountHeadList());
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.itmBankInfo) {
//            Toast.makeText(this, "" + R.id.itmBankInfo, Toast.LENGTH_LONG).show();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer, new fragmentBankList());
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.itmBankAcc) {
//            Toast.makeText(this, "" + R.id.itmBankAcc, Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer, new fragmentBankAccountList());
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.itmIncome) {
            Toast.makeText(this, "" + R.id.itmIncome, Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.itmExpense) {
//            Toast.makeText(this, "" + R.id.itmExpense, Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer, new fragmentExpenseList());
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.itmIncomeReport) {
            Toast.makeText(this, "" + R.id.itmIncomeReport, Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.itmExpenseReport) {
            Toast.makeText(this, "" + R.id.itmExpenseReport, Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.itmHeadWiserReport) {
            Toast.makeText(this, "" + R.id.itmHeadWiserReport, Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.itmIncomeExpenseLedger) {
            Toast.makeText(this, "" + R.id.itmIncomeExpenseLedger, Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==android.R.id.home){

            if(fname.equals("fragmentBankAcc")){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer, new fragmentBankAccountList());
                fragmentTransaction.commit();
            }
            else if(fname.equals("fragmentAccountHead")){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer, new fragmentAccountHeadList());
                fragmentTransaction.commit();
            }
            else if(fname.equals("fragmentBankInfo")){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer, new fragmentBankList());
                fragmentTransaction.commit();
            }
            else{
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmMainContainer, new fragmentDashboard());
                fragmentTransaction.commit();
            }
        }
        return true;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        android.app.Fragment fragment1 = this.getFragmentManager().findFragmentById(R.id.frmMainContainer);
        //Toast.makeText(this, "" + fragment + "----" + fragment1, Toast.LENGTH_LONG).show();
        //str = str.replaceAll("\\(.*?\\) ?", "");
        fname = fragment.toString().replaceAll("\\{.*?\\} ?", "").trim();

        if(!fname.equals("fragmentDashboard")){
            if(getSupportActionBar()!=null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
//                Toast.makeText(this, "" + fname, Toast.LENGTH_LONG).show();
            }
        }
    }

}

package com.example.arup.personalaccount;

//import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arup.personalaccount.FragmentList.fragmentAccountHeadList;
import com.example.arup.personalaccount.Fragment.fragmentDashboard;
import com.example.arup.personalaccount.FragmentList.fragmentBankList;
import com.example.arup.personalaccount.FragmentList.fragmentExpenseList;
import com.example.arup.personalaccount.Fragment.fragmentPaymentMethod;

public class MainActivity extends AppCompatActivity {
//    FragmentManager fragmentManager;
//    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmMainContainer,new fragmentDashboard());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.itmAccountHead){
            Toast.makeText(this,""+R.id.itmAccountHead,Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer,new fragmentAccountHeadList());
            fragmentTransaction.commit();
        }
        else if(item.getItemId()==R.id.itmBankInfo){
            Toast.makeText(this,""+R.id.itmBankInfo,Toast.LENGTH_LONG).show();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer,new fragmentBankList());
            fragmentTransaction.commit();
        }
        else if(item.getItemId()==R.id.itmPaymentStatus){
            Toast.makeText(this,""+R.id.itmPaymentStatus,Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.itmIncome){
            Toast.makeText(this,""+R.id.itmIncome,Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.itmExpense){
            Toast.makeText(this,""+R.id.itmExpense,Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmMainContainer,new fragmentExpenseList());
            fragmentTransaction.commit();
        }
        else if(item.getItemId()==R.id.itmIncomeReport){
            Toast.makeText(this,""+R.id.itmIncomeReport,Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.itmExpenseReport){
            Toast.makeText(this,""+R.id.itmExpenseReport,Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.itmHeadWiserReport){
            Toast.makeText(this,""+R.id.itmHeadWiserReport,Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.itmIncomeExpenseLedger){
            Toast.makeText(this,""+R.id.itmIncomeExpenseLedger,Toast.LENGTH_LONG).show();
        }
        return true;
    }
}


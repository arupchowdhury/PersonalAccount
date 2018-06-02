package com.example.arup.personalaccount.CustomListView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arup.personalaccount.MainActivity;
import com.example.arup.personalaccount.Model.IncomeExpenseJournal;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class LedgerListAdapter extends ArrayAdapter<IncomeExpenseJournal> {

    Context context;
    ArrayList<IncomeExpenseJournal>incomeExpenseJournalArrayList;


    public LedgerListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<IncomeExpenseJournal> incomeExpenseJournalArrayList) {
        super(context, R.layout.ledgerlistview, incomeExpenseJournalArrayList);
        this.context=context;
        this.incomeExpenseJournalArrayList=incomeExpenseJournalArrayList;
    }

    private int lastPosition =0;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        IncomeExpenseJournal incomeExpenseJournal = getItem(position);
        final ViewHolder viewHolder;
        final View result;

        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ledgerlistview, parent, false);

            viewHolder.TransIdExVH = (TextView)convertView.findViewById(R.id.tvTransIdExLVE);
            viewHolder.ExpenseHeadNameVH=(TextView)convertView.findViewById(R.id.tvExpenseHeadExLVE);
            viewHolder.PostingDateVH=(TextView)convertView.findViewById(R.id.tvPostingDateExLVE);
            viewHolder.ExpenseAmountVH=(TextView)convertView.findViewById(R.id.tvExpenseAmountExLVE);
            viewHolder.JournalRemarkVH=(TextView)convertView.findViewById(R.id.tvJournalRemarkExLVE);
            viewHolder.IncomeAmountVH = (TextView)convertView.findViewById(R.id.tvIncomeAmountExLVE);


            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        lastPosition = position;
        viewHolder.TransIdExVH.setText(Integer.toString(incomeExpenseJournal.getTransId()));
        viewHolder.ExpenseHeadNameVH.setText(incomeExpenseJournal.getHeadName());
        viewHolder.PostingDateVH.setText(incomeExpenseJournal.getPostingDate());
        viewHolder.ExpenseAmountVH.setText( Double.toString(incomeExpenseJournal.getExpenseAmount()));
        viewHolder.JournalRemarkVH.setText(incomeExpenseJournal.getJournalRemark());
        viewHolder.IncomeAmountVH.setText(Double.toString(incomeExpenseJournal.getIncomeAmount()));



        return convertView;
    }

    public static class ViewHolder{
        TextView TransIdExVH;
        TextView ExpenseHeadNameVH;
        TextView PostingDateVH;
        TextView ExpenseAmountVH;
        TextView IncomeAmountVH;
        TextView JournalRemarkVH;
        //LinearLayout layoutExpense;
    }
}

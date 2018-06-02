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

public class IncomeListAdapter extends ArrayAdapter<IncomeExpenseJournal> {

    Context context;
    ArrayList<IncomeExpenseJournal>incomeExpenseJournalArrayList;


    public IncomeListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<IncomeExpenseJournal> incomeExpenseJournalArrayList) {
        super(context, R.layout.incomelistview, incomeExpenseJournalArrayList);
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
            convertView = inflater.inflate(R.layout.incomelistview, parent, false);

            viewHolder.TransIdExVH = (TextView)convertView.findViewById(R.id.tvTransIdExLVE);
            viewHolder.ExpenseHeadNameVH=(TextView)convertView.findViewById(R.id.tvExpenseHeadExLVE);
            viewHolder.PostingDateVH=(TextView)convertView.findViewById(R.id.tvPostingDateExLVE);
            viewHolder.ExpenseAmountVH=(TextView)convertView.findViewById(R.id.tvExpenseAmountExLVE);
            viewHolder.JournalRemarkVH=(TextView)convertView.findViewById(R.id.tvJournalRemarkExLVE);
            viewHolder.layoutExpense = (LinearLayout)convertView.findViewById(R.id.layoutExpenseParentE);

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
        viewHolder.ExpenseAmountVH.setText( Double.toString(incomeExpenseJournal.getIncomeAmount()));
        viewHolder.JournalRemarkVH.setText(incomeExpenseJournal.getJournalRemark());

        viewHolder.layoutExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence events[] = new CharSequence[] {"Edit", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Action");

                final AlertDialog.Builder builderdialog = builder.setItems(events, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0 || which==1){
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("fragment","fragmentIncomeTrans");
                            intent.putExtra("id",viewHolder.TransIdExVH.getText().toString());
                            startActivity(getContext(),intent,null);
                        }
                    }
                });
                builderdialog.show();
            }
        });

        return convertView;
    }

    public static class ViewHolder{
        TextView TransIdExVH;
        TextView ExpenseHeadNameVH;
        TextView PostingDateVH;
        TextView ExpenseAmountVH;
        TextView JournalRemarkVH;
        LinearLayout layoutExpense;
    }
}

package com.example.arup.personalaccount.CustomListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AccountHeadListAdapter extends ArrayAdapter<IncomeExpenseHead> implements Filterable {
    Context context;
    ArrayList<IncomeExpenseHead> incomeExpenseHeadslist;
    //ArrayList<IncomeExpenseHead>searchIncomeExpense;
    //IncomeExpenseHead incomeExpenseHead;

    public AccountHeadListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<IncomeExpenseHead> incomeExpenseHeadslist) {
        super(context, R.layout.accountheadlistview, incomeExpenseHeadslist);
        this.context=context;
        this.incomeExpenseHeadslist=incomeExpenseHeadslist;
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    private int lastPosition = 0;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        IncomeExpenseHead incomeExpenseHead = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.accountheadlistview, parent, false);

            viewHolder.accheadId = (TextView)convertView.findViewById(R.id.tvHeadId);
            viewHolder.accHeadName=(TextView)convertView.findViewById(R.id.tvAccountHeadName);
            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.accheadId.setText(incomeExpenseHead.getHeadId());
        viewHolder.accHeadName.setText(incomeExpenseHead.getHeadName());

        return convertView;
    }

    public static class ViewHolder{
        TextView accheadId;
        TextView accHeadName;
    }
}

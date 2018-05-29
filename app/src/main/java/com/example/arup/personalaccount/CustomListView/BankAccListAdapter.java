package com.example.arup.personalaccount.CustomListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.arup.personalaccount.Model.BankAccInformation;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;
import java.util.List;

public class BankAccListAdapter extends ArrayAdapter<BankAccInformation> {

    Context context;
    ArrayList<BankAccInformation> bankAccInformationArrayList;
    public BankAccListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<BankAccInformation> bankAccInformationArrayList) {
        super(context, R.layout.bankacclistview, bankAccInformationArrayList);
        this.context=context;
        this.bankAccInformationArrayList=bankAccInformationArrayList;
    }

    private int lastPosition =0;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BankAccInformation bankAccInformation = getItem(lastPosition);
        ViewHolder viewHolder;
        final View result;

        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bankacclistview, parent, false);

            viewHolder.tvBankAccId = (TextView)convertView.findViewById(R.id.tvBankAccIdLV);
            viewHolder.tvBankAccName = (TextView)convertView.findViewById(R.id.tvBankAccNameLV);
            viewHolder.tvBankId=(TextView)convertView.findViewById(R.id.tvBankIdLV);
            viewHolder.tvBranchName=(TextView)convertView.findViewById(R.id.tvBranchNameLV);
            viewHolder.tvBankName = (TextView)convertView.findViewById(R.id.tvBankNamLVe);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        lastPosition = position;

        viewHolder.tvBankAccId.setText(Integer.toString(bankAccInformation.getAccId()));
        viewHolder.tvBankId.setText(Integer.toString(bankAccInformation.getBankId()));
        viewHolder.tvBankAccName.setText(bankAccInformation.getAccName());
        viewHolder.tvBankName.setText(bankAccInformation.getBankName());
        viewHolder.tvBranchName.setText(bankAccInformation.getBranchName());


        return convertView;
    }

    public static class ViewHolder{
        TextView tvBankAccId;
        TextView tvBankId;
        TextView tvBankAccName;
        TextView tvBranchName;
        TextView tvBankName;
    }
}

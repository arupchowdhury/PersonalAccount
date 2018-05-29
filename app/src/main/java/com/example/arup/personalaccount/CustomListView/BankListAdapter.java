package com.example.arup.personalaccount.CustomListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.arup.personalaccount.Model.BankInformation;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;
import java.util.List;

public class BankListAdapter extends ArrayAdapter<BankInformation> {

    Context context;
    ArrayList<BankInformation> bankInformationArrayList;



    public BankListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<BankInformation> bankInformationArrayLista) {
        super(context, R.layout.bankinfolistview, bankInformationArrayLista);
        this.context=context;
        this.bankInformationArrayList=bankInformationArrayLista;
    }

    private int lastPosition =0;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BankInformation bankInformation = getItem(lastPosition);
        ViewHolder viewHolder;
        final View result;

        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bankinfolistview, parent, false);

            viewHolder.bankId = (TextView)convertView.findViewById(R.id.tvBankId);
            viewHolder.bankName=(TextView)convertView.findViewById(R.id.tvBankName);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.bankId.setText(Integer.toString(bankInformation.getBankId()));
        viewHolder.bankName.setText(bankInformation.getBankName());

        return convertView;
    }

    public static class ViewHolder{
        TextView bankId;
        TextView bankName;
    }
}

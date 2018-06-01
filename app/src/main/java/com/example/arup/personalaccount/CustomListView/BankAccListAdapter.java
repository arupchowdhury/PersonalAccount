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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.arup.personalaccount.MainActivity;
import com.example.arup.personalaccount.Model.BankAccInformation;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class BankAccListAdapter extends ArrayAdapter<BankAccInformation> implements Filterable {

    Context context;
    ArrayList<BankAccInformation> bankAccInformationArrayList;
    ArrayList<BankAccInformation>searchList;

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
        final ViewHolder viewHolder;
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

        viewHolder.tvBankAccName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence events[] = new CharSequence[] {"Edit", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Action");

                final AlertDialog.Builder builderdialog = builder.setItems(events, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0 || which==1){
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("fragment","fragmentBankAcc");
                            intent.putExtra("id",viewHolder.tvBankAccId.getText().toString());
                            startActivity(getContext(),intent,null);
                        }
                    }
                });
                builderdialog.show();
            }
        });

        viewHolder.tvBankName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence events[] = new CharSequence[] {"Edit", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Action");

                final AlertDialog.Builder builderdialog = builder.setItems(events, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0 || which==1){
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("fragment","fragmentBankAcc");
                            intent.putExtra("id",viewHolder.tvBankAccId.getText().toString());
                            startActivity(getContext(),intent,null);
                        }
                    }
                });
                builderdialog.show();
            }
        });

        viewHolder.tvBranchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence events[] = new CharSequence[] {"Edit", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Action");

                final AlertDialog.Builder builderdialog = builder.setItems(events, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0 || which==1){
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("fragment","fragmentBankAcc");
                            intent.putExtra("id",viewHolder.tvBankAccId.getText().toString());
                            startActivity(getContext(),intent,null);
                        }
                    }
                });
                builderdialog.show();
            }
        });

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<BankAccInformation> result = new ArrayList<BankAccInformation>();

                if(searchList==null)
                    searchList = bankAccInformationArrayList;

                if(charSequence!=null){
                    if(searchList!=null && searchList.size()>0){
                        for(BankAccInformation bankAccInformation:searchList){
                            if(bankAccInformation.getBankName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                result.add(bankAccInformation);
                            }
                        }
                        oReturn.count = result.size();
                        oReturn.values=result;

                    }
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                bankAccInformationArrayList = (ArrayList<BankAccInformation>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return bankAccInformationArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class ViewHolder{
        TextView tvBankAccId;
        TextView tvBankId;
        TextView tvBankAccName;
        TextView tvBranchName;
        TextView tvBankName;
    }
}

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
import com.example.arup.personalaccount.Model.BankInformation;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class BankListAdapter extends ArrayAdapter<BankInformation> implements Filterable {

    Context context;
    ArrayList<BankInformation> bankInformationArrayList;
    ArrayList<BankInformation>searchList;



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
        final ViewHolder viewHolder;
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

        viewHolder.bankName.setOnClickListener(new View.OnClickListener() {
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
                            intent.putExtra("fragment","fragmentBankInfo");
                            intent.putExtra("id",viewHolder.bankId.getText().toString());
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
                final ArrayList<BankInformation> result = new ArrayList<BankInformation>();

                if(searchList==null)
                    searchList = bankInformationArrayList;

                if(charSequence!=null){
                    if(searchList!=null && searchList.size()>0){
                        for(BankInformation bankInformation:searchList){
                            if(bankInformation.getBankName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                result.add(bankInformation);
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
                bankInformationArrayList = (ArrayList<BankInformation>)results.values;
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
        return bankInformationArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        TextView bankId;
        TextView bankName;
    }
}

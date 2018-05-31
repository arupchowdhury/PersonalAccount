package com.example.arup.personalaccount.CustomListView;

//import android.app.FragmentManager;
//import android.app.Activity;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.arup.personalaccount.Fragment.fragmentAccountHead;
import com.example.arup.personalaccount.MainActivity;
import com.example.arup.personalaccount.Model.IncomeExpenseHead;
import com.example.arup.personalaccount.R;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;


public class AccountHeadListAdapter extends ArrayAdapter<IncomeExpenseHead> implements Filterable {
    Context context;
//    android.app.Fragment fragment;
    ArrayList<IncomeExpenseHead> incomeExpenseHeadslist;
    ArrayList<IncomeExpenseHead>searchList;
    //IncomeExpenseHead incomeExpenseHead;

    public AccountHeadListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<IncomeExpenseHead> incomeExpenseHeadslist) {
        super(context, R.layout.accountheadlistview, incomeExpenseHeadslist);
        this.context=context;
//        this.fragment=fragment;
        this.incomeExpenseHeadslist=incomeExpenseHeadslist;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<IncomeExpenseHead> result = new ArrayList<IncomeExpenseHead>();

                if(searchList==null)
                    searchList = incomeExpenseHeadslist;

                if(charSequence!=null){
                    if(searchList!=null && searchList.size()>0){
                        for(IncomeExpenseHead incomeExpenseHead:searchList){
                            if(incomeExpenseHead.getHeadName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                result.add(incomeExpenseHead);
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
                incomeExpenseHeadslist = (ArrayList<IncomeExpenseHead>)results.values;
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
        return incomeExpenseHeadslist.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private int lastPosition =0;


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        IncomeExpenseHead incomeExpenseHead = getItem(position);
        final View result;
        final ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.accountheadlistview, parent, false);

            viewHolder.accheadId = (TextView)convertView.findViewById(R.id.tvHeadId);
            viewHolder.accHeadName=(TextView)convertView.findViewById(R.id.tvAccountHeadName);
            viewHolder.accheadType=(TextView)convertView.findViewById(R.id.tvAccountHeadType);
            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        //lastPosition = position;

        viewHolder.accheadId.setText(Integer.toString(incomeExpenseHead.getHeadId()));
        viewHolder.accHeadName.setText(incomeExpenseHead.getHeadName());
        viewHolder.accheadType.setText(incomeExpenseHead.getHeadType());

        viewHolder.accHeadName.setOnClickListener(new View.OnClickListener() {
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
                            intent.putExtra("fragment","fragmentAccountHead");
                            intent.putExtra("id",viewHolder.accheadId.getText().toString());
                            startActivity(getContext(),intent,null);
                        }
                    }
                });
                builderdialog.show();
            }
        });
       viewHolder.accheadType.setOnClickListener(new View.OnClickListener() {
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
                           intent.putExtra("fragment","fragmentAccountHead");
                           intent.putExtra("id",viewHolder.accheadId.getText().toString());
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
        TextView accheadId;
        TextView accHeadName;
        TextView accheadType;
    }
}

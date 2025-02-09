package com.example.rajgiraseabhinavtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rajgiraseabhinavtask.R;
import com.example.rajgiraseabhinavtask.databinding.MemberItemsBinding;
import com.example.rajgiraseabhinavtask.model.MemberModel;

import java.util.List;

public class MemberRecyclerViewAdapter extends RecyclerView.Adapter<MemberRecyclerViewAdapter.MemberViewHolder> {
    Context context;
    List<MemberModel> allMemberList;

    public MemberRecyclerViewAdapter(Context context, List<MemberModel> allMemberList) {
        this.context = context;
        this.allMemberList = allMemberList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemberViewHolder(LayoutInflater.from(context).inflate(R.layout.member_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {

        MemberModel memberModel = allMemberList.get(position);

        holder.binding.memberNameTv.setText(memberModel.getMemberName());
        holder.binding.memberNoTv.setText(memberModel.getMemberMobileNo());
        holder.binding.memberRoleTv.setText(memberModel.getMemberSelectRole());
        holder.binding.memberSubscriptionAmtTv.setText(memberModel.getMemberSubscriptionFee());
        holder.binding.loanAmountTv.setText(memberModel.getMemberLoanAmount());
        holder.binding.depositAmountTv.setText(memberModel.getMemberDepositAmount());


    }

    @Override
    public int getItemCount() {
        return allMemberList.size();
    }

    public void updateMember(List<MemberModel> updateList){
        this.allMemberList = updateList;
        notifyDataSetChanged();
    }


    public static class MemberViewHolder extends RecyclerView.ViewHolder{

         MemberItemsBinding binding;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MemberItemsBinding.bind(itemView);
        }
    }
}

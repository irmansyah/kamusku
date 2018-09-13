package com.irmansyah.kamusku.ui.translete;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.irmansyah.kamusku.data.model.db.IndonesiaEnglish;
import com.irmansyah.kamusku.databinding.ItemIndEngBinding;
import com.irmansyah.kamusku.ui.base.BaseViewHolder;

import java.util.List;

/**
 * Created by irmansyah on 09/03/18.
 */

public class IndEngAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<IndonesiaEnglish> indonesiaEnglishList;

    public ItemResultClickListener mItemResultClickListener;

    public void setItemResultClickListener(ItemResultClickListener listener) {
        this.mItemResultClickListener = listener;
    }

    public IndEngAdapter(List<IndonesiaEnglish> indonesiaEnglishes) {
        this.indonesiaEnglishList = indonesiaEnglishes;
    }

    public void addItems(List<IndonesiaEnglish> indonesiaEnglishes) {
        indonesiaEnglishList.addAll(indonesiaEnglishes);
        notifyDataSetChanged();
    }

    public void clearItems() {
        indonesiaEnglishList.clear();
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemIndEngBinding binding = ItemIndEngBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return indonesiaEnglishList.size();
    }

    public class ItemViewHolder extends BaseViewHolder implements
            ItemIndEngVIewModel.ItemResultViewModelListener {

        private ItemIndEngBinding mBinding;
        private ItemIndEngVIewModel mViewModel;

        public ItemViewHolder(ItemIndEngBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final IndonesiaEnglish model = indonesiaEnglishList.get(position);
            mViewModel = new ItemIndEngVIewModel(position, model, this);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(int post) {
            mItemResultClickListener.onItemResultIndEngClicked(indonesiaEnglishList, post);
        }
    }

    public interface ItemResultClickListener {
        void onItemResultIndEngClicked(List<IndonesiaEnglish> indonesiaEnglishes, int post);
    }
}

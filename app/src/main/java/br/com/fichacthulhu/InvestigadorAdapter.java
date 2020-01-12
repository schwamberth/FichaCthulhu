package br.com.fichacthulhu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.List;

import br.com.fichacthulhu.model.Investigador;

public class InvestigadorAdapter extends ArrayAdapter<Investigador> {

    private LayoutInflater mInflater;
    private OnDeleteListener<Investigador> onDeleteListener;
    private OnClickListener<Investigador> onClickListener;
    private OnClickListener<Investigador> onClickEditListener;

    public void setOnClickEditListener(OnClickListener<Investigador> onClickEditListener) {
        this.onClickEditListener = onClickEditListener;
    }

    public void setOnClickListener(OnClickListener<Investigador> onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnDeleteListener(OnDeleteListener<Investigador> onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public InvestigadorAdapter(@NonNull Context context, @NonNull List<Investigador> objects) {
        super(context, 0, objects);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Investigador item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_investigador, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getBinding().setVariable(BR.investigador, item);
        holder.getBinding().setVariable(BR.onDeleteListener, onDeleteListener);
        holder.getBinding().setVariable(BR.onClickListener, onClickListener);
        holder.getBinding().setVariable(BR.onClickEditListener, onClickEditListener);
        holder.getBinding().executePendingBindings();

        return convertView;
    }

    static class ViewHolder {
        private ViewDataBinding binding;
        public ViewHolder(View convertView) {
            binding = DataBindingUtil.bind(convertView);
        }
        public ViewDataBinding getBinding() {
            return binding;
        }
    }


}

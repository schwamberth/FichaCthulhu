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

import br.com.fichacthulhu.model.Skill;

public class SkillAdapter extends ArrayAdapter<Skill> {

    private LayoutInflater mInflater;
    private OnClickListener<Skill> onClickEvoluirListener;
    private OnClickListener<Skill> onClickSomarListener;
    private OnClickListener<Skill> onClickSubtrairListener;

    public void setOnClickEvoluirListener(OnClickListener<Skill> onClickEvoluirListener) {
        this.onClickEvoluirListener = onClickEvoluirListener;
    }

    public void setOnClickSomarListener(OnClickListener<Skill> onClickSomarListener) {
        this.onClickSomarListener = onClickSomarListener;
    }

    public void setOnClickSubtrairListener(OnClickListener<Skill> onClickSubtrairListener) {
        this.onClickSubtrairListener = onClickSubtrairListener;
    }

    public SkillAdapter(@NonNull Context context, @NonNull List<Skill> objects) {
        super(context, 0, objects);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Skill item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_skill, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getBinding().setVariable(BR.skillDTO, item);
        holder.getBinding().setVariable(BR.onClickEvoluirListener, onClickEvoluirListener);
        holder.getBinding().setVariable(BR.onClickSomarListener, onClickSomarListener);
        holder.getBinding().setVariable(BR.onClickSubtrairListener, onClickSubtrairListener);
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

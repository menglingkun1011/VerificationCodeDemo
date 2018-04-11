package com.example.mlk.customview.dialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.example.mlk.customview.R;
import com.example.mlk.customview.view.VirtualKeyboardView;
import com.flyco.dialog.widget.base.BaseDialog;

/**
 * 作者：mlk on 2018/4/10 16:48
 */
public class KeyBordDialog extends BaseDialog<KeyBordDialog> {

    public VirtualKeyboardView getVirtualKeyboardViewview() {
        return virtualKeyboardViewview;
    }

    private VirtualKeyboardView virtualKeyboardViewview;

    public KeyBordDialog(Context context) {
        super(context);
    }

    public KeyBordDialog(Context context, boolean isPopupStyle) {
        super(context, isPopupStyle);
    }

    @Override
    public View onCreateView() {

        widthScale(1.0f);
        View view = View.inflate(getContext(), R.layout.keyboard_layout, null);
        virtualKeyboardViewview = view.findViewById(R.id.virtualKeyboardView);
        return view;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemOnclickListener){
        virtualKeyboardViewview.getGridView().setOnItemClickListener(onItemOnclickListener);
    }

    @Override
    public void setUiBeforShow() {

    }

}

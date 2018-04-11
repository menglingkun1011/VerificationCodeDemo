package com.example.mlk.customview.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mlk.customview.R;

import java.lang.reflect.Method;

/**
 * 作者：mlk on 2018/4/10 09:07
 * 安全码
 */
public class VerificationCodeView extends RelativeLayout {

    private int max = 6;
    private EditText etContent;
    private View[] views = new View[max];
    private InputMethodManager imm;
    private Handler handler = new Handler();

    public VerificationCodeView(Context context) {
        this(context,null);
    }

    public VerificationCodeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VerificationCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
//        showMethodManager(context);

        // 设置不调用系统键盘
        disableSystemInput((Activity) context);
    }

    //不调用系统键盘
    public void disableSystemInput(Activity context) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            etContent.setInputType(InputType.TYPE_NULL);
        } else {
            context.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(etContent, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //弹出软键盘
    public void showMethodManager(final Context context) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etContent, 0);
            }
        },200);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.verification_code_layout, this);
        LinearLayout llRoot = view.findViewById(R.id.ll_root);
        etContent = view.findViewById(R.id.et_content);

        for (int i = 0; i < max; i++) {
            View roundView = View.inflate(context, R.layout.round_view, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-1);
            lp.width = 0;
            lp.weight = 1;
            lp.gravity = Gravity.CENTER;

            views[i] = roundView;
            llRoot.addView(views[i],lp);
        }

        initListener();
    }

    private void initListener() {
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = etContent.getText().toString();
                int length = content.length();
                if(length<=max){
                    for (int i = 0; i < max; i++) {//重置布局
                        views[i].findViewById(R.id.v1).setVisibility(VISIBLE);
                        views[i].findViewById(R.id.v2).setVisibility(GONE);
                    }
                    for (int i = 0; i < length; i++) {
                        views[i].findViewById(R.id.v1).setVisibility(GONE);
                        views[i].findViewById(R.id.v2).setVisibility(VISIBLE);
                    }
                }

                if(length == max && onInputFinishListener !=null){
                    onInputFinishListener.inputFinish(content);
                }
            }
        });
    }


    public void setOnInputFinishListener(VerificationCodeView.onInputFinishListener onInputFinishListener) {
        this.onInputFinishListener = onInputFinishListener;
    }

    private  onInputFinishListener onInputFinishListener;

    public interface onInputFinishListener{

        void inputFinish(String verificationCode);

    }

    public EditText getEtContent() {
        return etContent;
    }

}

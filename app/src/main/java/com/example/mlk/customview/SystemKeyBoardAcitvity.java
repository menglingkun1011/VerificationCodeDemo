package com.example.mlk.customview;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mlk.customview.view.VerificationCodeView;

public class SystemKeyBoardAcitvity extends AppCompatActivity {

    private VerificationCodeView verificationCodeView;
    private FrameLayout flRoot;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_key_board_acitvity);

        context = this;
        initView();
        initListener();
        verificationCodeView.showMethodManager(this);//弹出系统软键盘

    }

    private void initListener() {
        verificationCodeView.setOnInputFinishListener(new VerificationCodeView.onInputFinishListener() {
            @Override
            public void inputFinish(String verificationCode) {
                Toast.makeText(SystemKeyBoardAcitvity.this, verificationCode, Toast.LENGTH_SHORT).show();
            }
        });

        flRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSoftShowing()){
                    verificationCodeView.showMethodManager(context);
                }
            }
        });
    }

    private void initView() {
        verificationCodeView = (VerificationCodeView) findViewById(R.id.verificationCodeView);
        flRoot = (FrameLayout) findViewById(R.id.fl_root);
    }

    //软键盘是否显示
    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom != 0;
    }
}

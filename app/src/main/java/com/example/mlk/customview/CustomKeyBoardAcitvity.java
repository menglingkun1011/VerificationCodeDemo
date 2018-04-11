package com.example.mlk.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mlk.customview.dialog.KeyBordDialog;
import com.example.mlk.customview.view.VerificationCodeView;

public class CustomKeyBoardAcitvity extends AppCompatActivity {

    private VerificationCodeView verificationCodeView;
    private KeyBordDialog keyBordDialog;
    private FrameLayout flRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_key_board_acitvity);

        verificationCodeView = (VerificationCodeView) findViewById(R.id.verificationCodeView);
        flRoot = (FrameLayout) findViewById(R.id.fl_root);

        initListener();
        showDialog();

    }

    private void showDialog() {
        keyBordDialog = new KeyBordDialog(this);
        keyBordDialog.show();
        keyBordDialog.setOnItemClickListener(onItemClickListener);
    }

    private void initListener() {
        verificationCodeView.setOnInputFinishListener(new VerificationCodeView.onInputFinishListener() {
            @Override
            public void inputFinish(String verificationCode) {
                Toast.makeText(CustomKeyBoardAcitvity.this, verificationCode, Toast.LENGTH_SHORT).show();
            }
        });

        flRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyBordDialog.show();
            }
        });
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (position < 11 && position != 9) {    //点击0~9按钮

                String amount = verificationCodeView.getEtContent().getText().toString().trim();
                amount = amount + keyBordDialog.getVirtualKeyboardViewview().getValueList().get(position).get("name");

                verificationCodeView.getEtContent().setText(amount);

                Editable ea = verificationCodeView.getEtContent().getText();
                verificationCodeView.getEtContent().setSelection(ea.length());
            } else {

                if (position == 9) {      //点击  小数点
//                    String amount = verificationCodeView.getEtContent().getText().toString().trim();
//                    if (!amount.contains(".")) {
//                        amount = amount + keyBordDialog.getVirtualKeyboardViewview().getValueList().get(position).get("name");
//                        verificationCodeView.getEtContent().setText(amount);
//
//                        Editable ea = verificationCodeView.getEtContent().getText();
//                        verificationCodeView.getEtContent().setSelection(ea.length());
//                    }
                }

                if (position == 11) {      //点击退格键
                    String amount = verificationCodeView.getEtContent().getText().toString().trim();
                    if (amount.length() > 0) {
                        amount = amount.substring(0, amount.length() - 1);
                        verificationCodeView.getEtContent().setText(amount);

                        Editable ea = verificationCodeView.getEtContent().getText();
                        verificationCodeView.getEtContent().setSelection(ea.length());
                    }
                }
            }
        }
    };
}

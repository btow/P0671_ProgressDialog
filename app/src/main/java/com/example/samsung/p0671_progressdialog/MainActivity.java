package com.example.samsung.p0671_progressdialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClicButton(View view) {

        String message = "";
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.title));

        switch (view.getId()) {
            case R.id.btnDefault :
                message += ("Button \"" + getString(R.string.dial_default) + "\" pressed");
                progressDialog.setMessage(message);
                //Добавление кнопки
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                        getString(R.string.btn_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                progressDialog.show();
                break;
            case R.id.btnHorizontal :
                message += ("Button \"" + getString(R.string.dial_horizont) + "\" pressed");
                progressDialog.setMessage(message);
                //Изменение стиля диалога на "horizontal_indicator
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //Установка максимального значения шкалы
                progressDialog.setMax(2048);
                //Включение анимации ожидания
                progressDialog.setIndeterminate(true);
                progressDialog.show();

                handler = new Handler() {
                    public void handleMessage(Message msg) {
                        //Выключение анимации ожидания
                        progressDialog.setIndeterminate(false);

                        if (progressDialog.getProgress() < progressDialog.getMax()) {
                            //Увеличение значений индикаторов
                            progressDialog.incrementProgressBy(50);
                            progressDialog.incrementSecondaryProgressBy(75);
                            handler.sendEmptyMessageDelayed(0 , 100);
                        } else {
                            progressDialog.dismiss();
                        }
                    }
                };

                handler.sendEmptyMessageDelayed(0, 2000);
                break;

        }
    }
}

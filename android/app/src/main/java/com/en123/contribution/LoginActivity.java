package com.en123.contribution;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class LoginActivity extends Activity {

    private EditText name_txt, password_txt;
    private Button login_btn;

    static String URL_LOGIN = "http://192.168.9.138:2016/api/accounts/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.Ext.init(getApplication());
        setContentView(R.layout.activity_login);
        findViews();
        setViews();
    }

    private void findViews() {
        name_txt = (EditText) findViewById(R.id.name_txt);
        password_txt = (EditText) findViewById(R.id.password_txt);
        login_btn = (Button) findViewById(R.id.login_btn);
    }

    private void setViews() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams(URL_LOGIN);
                params.addParameter("email", name_txt.getText().toString());
                params.addParameter("password", password_txt.getText().toString());
                x.http().post(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        log(result.toString());
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace(System.out);
                        log(ex.getLocalizedMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {
                    }
                });
            }
        });
    }

    private void log(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}


package com.cloudbread.example.createauthkey;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.windowsazure.mobileservices.*;


import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceAuthenticationProvider;
import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceUser;

public class MainActivity extends Activity {

    private MobileServiceClient mClient;
    private EditText txtSID;
    private EditText txtAuthKey;
    private Button btnAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSID = (EditText)findViewById(R.id.txtSID);
        txtAuthKey = (EditText)findViewById(R.id.txtAuthKey);
        btnAuth = (Button)findViewById(R.id.btnAuth);
        try {
            mClient = new MobileServiceClient(
                    "https://dw-cloudbread-ys.azurewebsites.net",
                    this
            );
            if(mClient==null)
                Log.d("mClient", "fail");
            else {
                Log.d("mClient", "ok");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("mClient", "Exception");
        }
        btnAuth.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(mClient!=null)
                    authenticate();
            }
        });


    }
    private void authenticate() {

        // Login using the Google provider.

        ListenableFuture<MobileServiceUser> mLogin = mClient.login(MobileServiceAuthenticationProvider.Facebook);

        Futures.addCallback(mLogin, new FutureCallback<MobileServiceUser>() {
            @Override
            public void onFailure(Throwable exc) {
                Log.d("Exception", exc.getMessage());
            }

            @Override
            public void onSuccess(MobileServiceUser user) {
                txtSID.setText(user.getUserId());
                txtAuthKey.setText(user.getAuthenticationToken());
                Log.d(String.format(
                        "UserId - %1$2s",
                        user.getUserId()), String.format(
                        "Authentication - %1$2s",
                        user.getAuthenticationToken()));
            }
        });
    }

}

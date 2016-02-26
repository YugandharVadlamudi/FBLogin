package com.example.kiran.fblogin;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends Activity {
public View other_View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            printHashKey();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public void printHashKey() throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageInfo packageInfo=getPackageManager().getPackageInfo("com.example.kiran.fblogin", PackageManager.GET_SIGNATURES);
        for(Signature signature:packageInfo.signatures)
        {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA");
            messageDigest.update(signature.toByteArray());
            Log.d("KeyHash:", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
        }

    }

}

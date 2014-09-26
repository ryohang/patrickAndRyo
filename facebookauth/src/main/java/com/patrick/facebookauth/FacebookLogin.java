package com.patrick.facebookauth;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.facebook.UiLifecycleHelper;


public class FacebookLogin extends FragmentActivity {
    private static final String TAG = "FacebookLogin";
    private UiLifecycleHelper uiHelper;
    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            loginFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, loginFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            loginFragment = (LoginFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
    }

}

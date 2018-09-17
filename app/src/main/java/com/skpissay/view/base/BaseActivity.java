package com.skpissay.view.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.HashMap;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Context mContext;

    public BaseFragment m_cObjFragmentBase;

    public UIHandler m_cObjUIHandler;

    protected abstract void handleUIMessage(Message pObjMessage);

    public BaseActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        m_cObjUIHandler = new UIHandler();
        this.mContext = this;
    }

    public final class UIHandler extends Handler {
        @Override
        public void handleMessage(Message pObjMessage) {
            if (null != m_cObjFragmentBase && m_cObjFragmentBase.m_cIsActivityAttached) {
                m_cObjFragmentBase.handleUIhandler(pObjMessage);
            }
            handleUIMessage(pObjMessage);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 16908332) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
    }
}
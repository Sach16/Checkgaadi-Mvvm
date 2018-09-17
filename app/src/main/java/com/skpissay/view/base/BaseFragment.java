package com.skpissay.view.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected BaseActivity m_cObjMainActivity;
    protected UIHandler m_cObjUIHandler;
    protected View m_cObjMainView;
    protected boolean m_cIsActivityAttached;

    protected String mErrorText;

    protected Toolbar mToolbar;

    @Override
    public void onAttach(Activity pObjActivity) {
        super.onAttach(pObjActivity);
        m_cObjMainActivity = (BaseActivity) getActivity();
        m_cObjUIHandler = new UIHandler();
        m_cIsActivityAttached = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        m_cIsActivityAttached = false;
    }

    public final class UIHandler extends Handler {
        public void handleMessage(Message pObjMessage) {
            handleUIMessage(pObjMessage);
        }
    }

    public void handleUIhandler(Message pObjMessage) {
        Message lObJMsg = new Message();
        lObJMsg.what = pObjMessage.what;
        lObJMsg.arg1 = pObjMessage.arg1;
        lObJMsg.arg2 = pObjMessage.arg2;
        lObJMsg.obj = pObjMessage.obj;
        m_cObjUIHandler.sendMessage(lObJMsg);
    }

    protected abstract void handleUIMessage(Message pObjMessage);

    protected void closeFragment() {
        int count = this.getFragmentManager().getBackStackEntryCount();
        if(count > 0) {
            this.getFragmentManager().popBackStackImmediate();
        }
    }

    public void setArguments(Bundle bundle) {
        try {
            super.setArguments(bundle);
        } catch (Exception var3) {
            this.updateArgs(bundle);
        }
    }

    protected void updateArgs(Bundle bundle) {
    }

    protected boolean isAttached() {
        return this.getActivity() != null;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClick(View view) {
    }

    //Todo : call the below methods from their respective methods

}
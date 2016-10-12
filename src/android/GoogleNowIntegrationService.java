package com.plugin.okgoogle;


import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
/**
 * Created by Gerjan on 11-10-2016.
 */

public class GoogleNowIntegrationService extends AccessibilityService {

    private static GoogleNowIntegrationService _service;

    private CallbackContext _context;

    public static GoogleNowIntegrationService getInstance() {
        return _service;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
        AccessibilityNodeInfo source = paramAccessibilityEvent.getSource();
        if (source != null) {
            String className = source.getClassName().toString();
            if (className.equals("com.google.android.apps.gsa.searchplate.widget.StreamingTextView") && source.getText() != null) {
                String command = source.getText().toString();
                SendCallback(command);
            }
        }
    }

    public void SetContext(CallbackContext context){
        this._context = context;
    }

    public void SendCallback(String command){
       if (_context != null) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, command);
            result.setKeepCallback(true);
            _context.sendPluginResult(result);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void CloseGoogleNowApp(){
        GoogleNowIntegrationService.getInstance().performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    protected void onServiceConnected() {
        super.onServiceConnected();
        _service = this;
    }

    public void onDestroy() {
        super.onDestroy();
        _service = null;
    }

    @Override
    public void onInterrupt() {
    }

}
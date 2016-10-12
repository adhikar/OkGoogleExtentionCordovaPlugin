package com.plugin.okgoogle;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Method;


public class OkGooglePlugin extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("isServiceActive")){
            IsServiceActive(callbackContext);
        }
        else if(action.equals("closeGoogleNowApp")){
            GoogleNowIntegrationService service = GoogleNowIntegrationService.getInstance();
             if(service!=null){
                 service.CloseGoogleNowApp();
             }
        }
        else if(action.equals("closeGoogleNowApp")){
            GoogleNowIntegrationService service = GoogleNowIntegrationService.getInstance();
             if(service!=null){
                 service.CloseGoogleNowApp();
             }
        }
        else if(action.equals("onCommand")){
            GoogleNowIntegrationService service = GoogleNowIntegrationService.getInstance();
             if(service!=null){
                 service.SetContext(callbackContext);
             }
        }
        return true;
    }

    public void IsServiceActive(CallbackContext callbackContext){
         try {
             GoogleNowIntegrationService service = GoogleNowIntegrationService.getInstance();
             if(service!=null){
                callbackContext.success("true");
             }else{
                callbackContext.success("false");
             }
        } catch (Exception ex) {
            callbackContext.error(ex.getMessage());
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}


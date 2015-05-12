/**
	com.lampa.startapp
	https://github.com/lampaa/com.lampa.startapp
	
	Phonegap 3 plugin for check or launch other application in android device (iOS support).
	bug tracker: https://github.com/lampaa/org.apache.cordova.startapp/issues
*/
package com.lampa.startapp;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import java.util.Iterator;
import android.net.Uri;

/**
 * This class provides access to vibration on the device.
 */
public class startApp extends CordovaPlugin {

    /**
     * Constructor.
     */
    public startApp() { }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param URL              URL to opent
     * @param callbackContext   The callback context used when calling back into JavaScript.
     * @return                  True when the action was valid, false otherwise.
     */
    public boolean execute(String action, String URL, CallbackContext callbackContext) throws JSONException {
        if (action.equals("start")) {
            this.start(URL, callbackContext);
        }
		else if(action.equals("check")) {
			this.check("forgepond.com.mobileiron.android.securebrowser", callbackContext);
		}
		
		return true;
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------
    /**
     * startApp
     */
    public void start(String URL, CallbackContext callback) {
		
	try {
		/*
		String packageName = "forgepond.com.mobileiron.android";
		String className = "forgepond.com.mobileiron.android.securebrowser";
		Intent internetIntent = new Intent("forgepond.android.intent.action.VIEW");
		internetIntent.addCategory("android.intent.category.DEFAULT");
		internetIntent.setClassName(packageName, className);
		this.cordova.getActivity().startActivity(internetIntent);
		*/
		
		Intent intent = new Intent("forgepond.android.intent.action.VIEW").setData(Uri.parse(URL));
		this.cordova.getActivity().startActivity(intent);
		callback.success();
		
	} catch (Exception e) {
		callback.error("intent: " + e.toString());
	}
	
    }

    /**
     * checkApp
     */	 
	public void check(String component, CallbackContext callback) {
		PackageManager pm = this.cordova.getActivity().getApplicationContext().getPackageManager();
		try {
			pm.getPackageInfo(component, PackageManager.GET_ACTIVITIES);
			callback.success();
		} catch (Exception e) {
			callback.error(e.toString());
		}
	}
}

package com.vodafone.openfile;

import java.io.File;

import android.content.Intent;
import android.net.Uri;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

public class OpenFile extends CordovaPlugin {
	
	public static final String OPERATOR_NAME = "showPdf";

   @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	
        if (OPERATOR_NAME.equals(action)){
		
            try
			{
				
				PluginResult.Status status = PluginResult.Status.OK;
			
				String resultadoLectura = showPdf(args.getString(0));
				
				if(resultadoLectura.length() > 0){
					status = PluginResult.Status.ERROR;
				}

				PluginResult pluginResult 	= 	new PluginResult(status, resultadoLectura);
				pluginResult.setKeepCallback(true);
				callbackContext.sendPluginResult(pluginResult);

				return true;

			}
			catch (Exception e)
			{
				callbackContext.error(e.getMessage());
				return false;
			}
            
        }
        return false;
    }

 	public String showPdf(String fileName) {

    	File file = new File(fileName);
        if (file.exists()) {
		
        	try {

	        	Uri path = Uri.fromFile(file);
	            Intent intent = new Intent(Intent.ACTION_VIEW);
	            intent.setDataAndType(path, "application/pdf");
	            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            
	            this.cordova.getActivity().startActivity(intent);
                return "";
                
            } catch (android.content.ActivityNotFoundException e) {
                Log.d("DownloaderPlugin", "Error loading url "+fileName+": " + e);
                return "ACTIVITY_NOT_FOUND";
            }            

        }else{
        	return "FILE_NOT_FOUND";
        }
 	}
}

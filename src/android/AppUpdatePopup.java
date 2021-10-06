package cordova.plugin.appupdatepopup;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.play;

/**
 * This class echoes a string called from JavaScript.
 */
public class AppUpdatePopup extends CordovaPlugin {

    private Task<AppUpdateInfo> appUpdateInfoTask;
    private AppUpdateManager appUpdateManager;

    @Override
    public boolean execute(String action, CallbackContext callbackContext) throws JSONException {
        if (action.equals("showPopUp")) {
            this.showPopUp(message, callbackContext);
            return true;
        }else if(action.equals("startUpdate")){
            this.showPopUp(message, callbackContext);
            return true;
        }
        return false;
    }

    private showPopUp(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            appUpdateManager = AppUpdateManagerFactory.create(this.cordova.getActivity());
            appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
            callbackContext.success(appUpdateInfoTask);            
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void startUpdate(CallbackContext callbackContext){
        appUpdateManager.startUpdateFlowForResult(appUpdateInfoTask, this, AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE)
            .setAllowAssetPackDeletion(true)
            .build(), 4);
    }
}

package com.type.sdk.android.qtld;

import java.io.UnsupportedEncodingException;

import com.type.sdk.android.TypeSDKData;
import com.type.sdk.android.TypeSDKDefine;
import com.type.sdk.android.TypeSDKDefine.ReceiveFunction;
import com.type.sdk.android.TypeSDKEvent;
import com.type.sdk.android.TypeSDKEventManager;
import com.type.sdk.android.TypeSDKLogger;
import com.type.sdk.android.TypeSDKDefine.AttName;
import com.type.sdk.android.TypeSDKEvent.EventType;

public class TypeSDKNotify_QTLD {
	public void sendToken(String _in_string,String _in_uid) {
		// TODO Auto-generated method stub
		TypeSDKLogger.i("login success original usertoken:" + _in_string);
		//String userToken = _in_string;
		String userToken = null;
		try {
			userToken = java.net.URLEncoder.encode(_in_string, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			userToken = _in_string;
			e.printStackTrace();
		}
		TypeSDKLogger.i("login success encoded usertoken:" + userToken);

		TypeSDKData.UserInfoData userData = TypeSDKBonjour.Instance().userInfo;
		userData.SetData(TypeSDKDefine.AttName.USER_TOKEN, userToken);
		userData.SetData(TypeSDKDefine.AttName.USER_ID,_in_uid);
		userData.CopyAtt(TypeSDKBonjour.Instance().platform, AttName.CP_ID);
		userData.CopyAtt(TypeSDKBonjour.Instance().platform, AttName.SDK_NAME);
		userData.CopyAtt(TypeSDKBonjour.Instance().platform, AttName.PLATFORM);
		TypeSDKEventManager.Instance().SendEvent(TypeSDKEvent.EventType.AND_EVENT_LOGIN, 
				ReceiveFunction.MSG_LOGIN, 
				userData.DataToString(), 
				TypeSDKBonjour.Instance().platform.GetData(AttName.PLATFORM));
	}

	public void Init()
	{
		TypeSDKEventManager.Instance().SendEvent(TypeSDKEvent.EventType.AND_EVENT_INIT_FINISH, 
				ReceiveFunction.MSG_INITFINISH, 
				TypeSDKBonjour.Instance().platform.DataToString(), 
				TypeSDKBonjour.Instance().platform.GetData(AttName.PLATFORM));
		TypeSDKEventManager.Instance().SendEvent(TypeSDKEvent.EventType.AND_EVENT_UPDATE_FINISH, 
				ReceiveFunction.MSG_UPDATEFINISH, 
				TypeSDKBonjour.Instance().platform.DataToString(), 
				TypeSDKBonjour.Instance().platform.GetData(AttName.PLATFORM));		
	}
	
	public void Pay(String string)
	{
		TypeSDKLogger.i("pay");
		TypeSDKEventManager.Instance().SendEvent(EventType.AND_EVENT_PAY_RESULT,
				ReceiveFunction.MSG_PAYRESULT, 
				string, 
				TypeSDKBonjour.Instance().platform.GetData(AttName.PLATFORM));			
	}
	
	public void Logout()
	{
		TypeSDKLogger.i("user sdk logout");			
		TypeSDKData.UserInfoData userData = TypeSDKBonjour.Instance().userInfo;
		TypeSDKEventManager.Instance().SendEvent(EventType.AND_EVENT_LOGOUT,
				ReceiveFunction.MSG_LOGOUT, 
				userData.DataToString(), 
				TypeSDKBonjour.Instance().platform.GetData(AttName.PLATFORM));	
	}

	public void reLogin()
	{
		TypeSDKLogger.i("user sdk reLogin");
		TypeSDKData.UserInfoData userData = TypeSDKBonjour.Instance().userInfo;
		TypeSDKLogger.i("logout info:" + userData.DataToString());
		TypeSDKEventManager.Instance().SendEvent(EventType.AND_EVENT_RELOGIN,
				ReceiveFunction.MSG_RELGOIN, 
				userData.DataToString(), 
				TypeSDKBonjour.Instance().platform.GetData(AttName.PLATFORM));		
		TypeSDKLogger.i("user sdk reLogin2");
	}

}

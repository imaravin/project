/**
 * 
 */
package com.chipersmssystem;

import java.io.IOException;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @author sentamilpandi.m
 *
 */
public class CallService {
	private static String NAMESPACE="http://aravinth.me/";
	private static String URL="http://localhost:8084/Chip2/Tracking?wsdl";

	
	/**
	 * login service
	 * @param username
	 * @param password
	 * @param method
	 * @return String
	 */
	public static String loginService(String username,String password,String method) {
		String restex=null;
		SoapObject soap=new SoapObject(NAMESPACE, method);
		soap.addProperty("username",username);
		soap.addProperty("password",password);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try { 
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
			restex=primitive.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return "error";
			
		}
		
		return restex;
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param method
	 * @return
	 */
	public static String registerService(String username,String password,String email,String method) {
		String restex=null;
		SoapObject soap=new SoapObject(NAMESPACE, method);
		soap.addProperty("username",username);
		soap.addProperty("password",password);
		soap.addProperty("email",email);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
			restex=primitive.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return "error";
			
		}
		
		return restex;
	}
	
	
	public static String mailService(String email,String key_value,String method) {
		String restex=null;
		SoapObject soap=new SoapObject(NAMESPACE, method);
		soap.addProperty("mail_id",email);
		soap.addProperty("key_value",key_value);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try { 
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
			restex=primitive.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return "error";
			
		}
		
		return restex;
	}
	public static String updatePosition(String latitude,String longitude,String username,String method) {
		String result="";
		SoapObject soap = new SoapObject(NAMESPACE, method);
		soap.addProperty("latitude", latitude);
		soap.addProperty("longitude", longitude);
		soap.addProperty("username", username);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			result = primitive.toString();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (XmlPullParserException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getDeviceList(String tablename,String method) {
		String result="";
		SoapObject soap = new SoapObject(NAMESPACE, method);
		soap.addProperty("tablename", tablename);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			result = primitive.toString();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (XmlPullParserException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getDeviceStatus(String username,String devicename,String method) {
		String result="";
		SoapObject soap = new SoapObject(NAMESPACE, method);
		soap.addProperty("username", username);	
		soap.addProperty("devicename", devicename);	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			result = primitive.toString();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (XmlPullParserException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	public static String updateMessage(String distance,String username,String devicename,String method) {
		String result="";
		SoapObject soap = new SoapObject(NAMESPACE, method);
		soap.addProperty("distance", distance);	
		soap.addProperty("username", username);	
		soap.addProperty("devicename", devicename);			
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			result = primitive.toString();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (XmlPullParserException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	public static String predictMessage(String message,String username,String method) {
		String result="";
		SoapObject soap = new SoapObject(NAMESPACE, method);
		soap.addProperty("message", message);	
		soap.addProperty("username", username);	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			result = primitive.toString();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (XmlPullParserException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
	public static String trackerDistanceCalculator(String trackerphonenumber,String username,String method) {
		String result="";
		SoapObject soap = new SoapObject(NAMESPACE, method);
		soap.addProperty("trackerphonenumber", trackerphonenumber);
		soap.addProperty("username", username);	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			result = primitive.toString();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (XmlPullParserException e) {			
			e.printStackTrace();
		}
		return result;
	}
}

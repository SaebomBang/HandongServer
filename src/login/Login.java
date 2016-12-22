package login;


import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import database.SSLHostnameVerifier;
import database.SSLTrustManager;

public class Login {
		
		private static String loginUrl = "https://hisnet.handong.edu/login/_login.php";
	    private static String loginUrl2 = "https://hisnet.handong.edu/login/goMenu_eval.php?cleaninet=1&language=Korean";	//get
	    private static String loginUrl3 = "https://hisnet.handong.edu/main.php";	//post. param:memo(null)
	   
	    private String cookies ="";
	    private boolean available;
	        
	    // crawl personal information page
	    public boolean isLoginAvailable(String id, String pw){
	    	this.login(loginUrl, "id="+id+"&password="+pw+"&Language=Korean", "POST");
	        this.login(loginUrl2, null, "GET");
	        this.loginAvailable(loginUrl3, "memo=", "POST");
	     
	        return available;
	    }
	   
	    
	    // do login process
	    public void loginAvailable(String urlString, String param, String method) {
	        try {
	            URL url = new URL(urlString);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
	            conn.setUseCaches(true);            
	            conn.setInstanceFollowRedirects(false);
	            conn.setDoOutput(true);
	            conn.setRequestMethod(method);
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            conn.setRequestProperty("Host", "hisnet.handong.edu");
	            conn.setRequestProperty("Cookie", cookies);

	            
	            if(param != null){
		            java.io.OutputStream opStr = conn.getOutputStream();
		            opStr.write(param.getBytes());
		            opStr.flush();
		            opStr.close();
	            }
	            int length = conn.getInputStream().available();
	           
	            if(length == 90)
	            	available = true;
	            else if(length == 85)
	            	available = false;
	            else{
	            	IllegalArgumentException ex = new IllegalArgumentException();
	            	throw ex;
	            }
	            	
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	    
	    // do login process
	    public void login(String urlString, String param, String method) {
	        try {
	            URL url = new URL(urlString);         
	            trustAllHttpsCertificates();
	            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	            
	            conn.setUseCaches(true);            
	            conn.setInstanceFollowRedirects(false);
	            conn.setDoOutput(true);
	            conn.setRequestMethod(method);
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            conn.setRequestProperty("Host", "hisnet.handong.edu");
	            conn.setRequestProperty("Cookie", cookies);
//	     
	            
	            if(param != null){
		            java.io.OutputStream opStr = conn.getOutputStream();
		            opStr.write(param.getBytes());
		            opStr.flush();
		            opStr.close();
	            }
	            
	     
	            cookies = getCookie(conn);
	           
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	    
	    private void trustAllHttpsCertificates() throws Exception {
	        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
	        trustAllCerts[0] = new SSLTrustManager();
	        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, null);
	        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        HttpsURLConnection.setDefaultHostnameVerifier(new SSLHostnameVerifier());
	    }
	  
	    
	    // cookie handling utility method
	    private String getCookie(URLConnection conn) {        
	        Map<String,List<String>> m = conn.getHeaderFields();
	        
	        if(!m.containsKey("Set-Cookie")) {
	           return "";
	        }
	        
	        boolean isFirst = true;
	        StringBuilder sb = new StringBuilder();
	        
	        for(String cookie : m.get("Set-Cookie")) {
	            if(isFirst)
	                isFirst = false;
	            else
	                sb.append(";");
	            
	            sb.append(cookie);
	        }
	        
	        if(cookies != null){
	        	sb.append(";");
	        	sb.append(cookies);
	        }
	        
	        return sb.toString();
	    }
}

package moms;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import database.SSLHostnameVerifier;
import database.SSLTrustManager;
/**
 * Crawler for Website
 * used for HISNET crawling
 */
public class MomsCrawler {
    private static String loginUrl = "https://hisnet.handong.edu/login/_login.php";
    private static String loginUrl2 = "https://hisnet.handong.edu/login/goMenu_eval.php?cleaninet=1&language=Korean";	//get
    private static String loginUrl3 = "http://hisnet.handong.edu/main.php";	//post. param:memo(null)
    private static String loginUrl4 = "http://hisnet.handong.edu/login/login.php";	//get
    
    private String menuUrl = 	"https://hisnet.handong.edu/for_student/campus_info/02_2.php";
    private String cookies ="";
        
    // crawl personal information page
    public InputStream getMenuInfo(String id, String pw){
    	this.login(loginUrl, "id="+id+"&password="+pw+"&Language=Korean", "POST");
        this.login(loginUrl2, null, "GET");
        this.loginWithHttp(loginUrl3, "memo=", "POST");
        this.loginWithHttp(loginUrl4, null, "GET");
        
        return this.crawl(menuUrl);
    }
   
    
    // do login process
    public void loginWithHttp(String urlString, String param, String method) {
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
//            conn.setRequestProperty("Referer", "https://hisnet.handong.edu/login/login.php");
            
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
    
    // do login process
    public void login(String urlString, String param, String method) {
        try {
            URL url = new URL(urlString);         
            trustAllHttpsCertificates();
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            
           // String param = "id=khw0867&password=900512&Language=Korean";
            
            conn.setUseCaches(true);            
            conn.setInstanceFollowRedirects(false);
            conn.setDoOutput(true);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Host", "hisnet.handong.edu");
            conn.setRequestProperty("Cookie", cookies);
//            conn.setRequestProperty("Referer", "https://hisnet.handong.edu/login/login.php");
            
            if(param != null){
	            java.io.OutputStream opStr = conn.getOutputStream();
	            opStr.write(param.getBytes());
	            opStr.flush();
	            opStr.close();
            }
            
            /*
            String buffer = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((buffer = in.readLine()) != null){
            	System.out.println(buffer);
            }*/
            cookies = getCookie(conn);
            //System.out.println("Login Complete...");
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
    
    // crawling method
    public InputStream crawl(String crawlUrl) {
        
        // WEBParser parser = new WEBParser(writer);
        InputStream result = null;
        try {
            URL url = new URL(crawlUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Cookie", cookies);
            // parser.parse(conn, parsingType);
            
            result = conn.getInputStream();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
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
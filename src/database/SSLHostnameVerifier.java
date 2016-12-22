package database;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/**
 *
 * @author rev
 */
public class SSLHostnameVerifier implements HostnameVerifier {
    @Override 
    public boolean verify(String paramString, SSLSession paramSSLSession) { 
        System.out.println("Warning: URL Host: " + paramString + " vs. " 
        + paramSSLSession.getPeerHost()); 
        return true; 
    } 
}
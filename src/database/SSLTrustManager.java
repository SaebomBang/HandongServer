package database;

public class SSLTrustManager implements javax.net.ssl.TrustManager,
       javax.net.ssl.X509TrustManager {
   public java.security.cert.X509Certificate[] getAcceptedIssuers() {
       return null;
   }
   public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
       //System.out.println("X509CertificateSever : " + certs);
       return true;
   }
   public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
       //System.out.println("X509CertificateClient : " + certs);
       return true;
   }
   public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
           String authType) throws java.security.cert.CertificateException {
       //System.out.println("AUTH TYPE Sever : " + certs[0]);
       return;
   }
   public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
           String authType) throws java.security.cert.CertificateException {
       //System.out.println("AUTH TYPE Server : " + authType);
       return;
   }
}
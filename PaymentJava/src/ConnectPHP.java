import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;



import java.io.OutputStream; 
public class ConnectPHP {
  public static void main(String args[]) {


	// Create a trust manager that does not validate certificate chains like the default 

	TrustManager[] trustAllCerts = new TrustManager[]{
	        new X509TrustManager() {

	            public java.security.cert.X509Certificate[] getAcceptedIssuers()
	            {
	                return null;
	            }
	            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
	            {
	                //No need to implement.
	            }
	            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
	            {
	                //No need to implement.
	            }
	        }
	};

	// Install the all-trusting trust manager
	try 
	{
	    SSLContext sc = SSLContext.getInstance("SSL");
	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	} 
	catch (Exception e) 
	{
	    System.out.println(e);
	}
	try{
	//System.out.println("prince");
	  URL url = new URL("https://wwwpersevarancecom.000webhostapp.com/new 1.php");
	  URLConnection conn = url.openConnection();
      URL url1 = new URL("http://10.0.2.2:8080//test/example.php");
      URLConnection con = url1.openConnection();
	  con.setDoOutput(true);
      PrintStream ps = new PrintStream(con.getOutputStream());
      // send your parameters to your site
      

      // close the print stream
      

	  // Get the response
	  String b = "";
	  BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	  String line = "";
      
	  while ((line = rd.readLine()) != null) {
	      b+=line;
	  //    System.out.println(line);
	  }
	  //System.out.println(b);
      JSONObject obj = new JSONObject(b);
      //System.out.println("y");
      JSONArray arr = obj.getJSONArray("server_respone");
      System.out.println("hello ok");
      for(int i=0;i<arr.length();i++)
      {
    	  JSONObject tmp = arr.getJSONObject(i);
    	  //System.out.println(tmp);
    	  String noplate =tmp.getString("noplate");
    	  String fault = tmp.getString("fault");
    	  String place = tmp.getString("place");
    	  String fine = tmp.getString("fine");
    	  //$row[0],"fault"=>$row[1],"place"=>$row[2],"fine"=>$row[4],
    	  String mobile = tmp.getString("mobile");
    	  String mail = tmp.getString("mail");
    	  String owner = tmp.getString("owner");
    	  String dati = tmp.getString("dati");
    	  String msg = "challan for "+ owner +"for"+fault+"\n"+"no plate = "+noplate+"dati="+dati;
    	  String id = tmp.getString("id");
    	  //testsms chk = new testsms();
    	  //System.out.println(chk.test(msg, mobile));
    	  //System.out.println("hello");
 	      TestMail.send("perseverancenitj@gmail.com","perseverance@nitj",mail,"e-challan",msg);  
 	      ps.print("&id="+id);
 	      // we have to get the input stream in order to actually send the request
 	      con.getInputStream();
    	  //mobile"=>$row[6],"mail"=>$row[7],"owner"=>$row[8],"dati"=>$row[10]
      }
      ps.close();
	   
  }
  catch(Exception e) {
	  System.out.println(e);
  }
}
}


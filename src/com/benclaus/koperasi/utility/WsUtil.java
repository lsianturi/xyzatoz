package com.benclaus.koperasi.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
/*import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;*/

import com.benclaus.koperasi.model.WsUrl;

public class WsUtil {
	private String cookies;
	private DefaultHttpClient client = new DefaultHttpClient();
	private final String USER_AGENT = "Mozilla/5.0";
	private static Logger log = Logger.getLogger(WsUtil.class);

	public static void main(String[] args) throws Exception {

		String url = "https://gsgapp.gunungsewu.com/";
		String loginUrl = "https://gsgapp.gunungsewu.com/names.nsf?Login";
//		String gmail = "https://gsgapp.gunungsewu.com/Application/LeaveRequest.nsf/lkPublicHoliday?readviewentries&outputformat=json&RestrictToCategory=2016";
//		String gmail = "https://gsgapp.gunungsewu.com/Application/webservice.nsf/provider?openagent&cmd=getempapp&key=lambok.sianturi@gunungsewu.com";
//		String gmail = "https://gsgapp.gunungsewu.com/Application/leaverequest.nsf/provider?openagent&cmd=getleavereq&key=070903904&df=20170101&dt=20170131";
//		String gmail = "https://gsgapp.gunungsewu.com/Application/leaverequest.nsf/provider?openagent&cmd=getleavereq&key=090401001&df=20161215&dt=20161215";
		String gmail = "https://gsgapp.gunungsewu.com/Application/webservice.nsf/provider?openagent&cmd=getallemp&key=Baramutiara+Prima";
//		String gmail = "https://gsgapp.gunungsewu.com/Application/webservice.nsf/provider?openagent&cmd=getempapp&key=lambok.sianturi@gunungsewu.com";
//		String gmail = "http://svc.gunungsewu.com/ldap/ldapgw.php?txtpassword=Siant1236&txtemail=lambok.sianturi@gunungsewu.com&app=eksi";

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());
		WsUtil http = new WsUtil();

		String page = http.getPageContent(url, null);

		/*List<NameValuePair> postParams = http.getFormParams(page, "web.service", "Qhr3q8nv");

		String cookie = http.sendPost(loginUrl, postParams);

		String result = http.getPageContent(gmail, cookie);
		System.out.println(result);*/

		//System.out.println("Done");
	}
	
	/*public String logintoWsAndGetResult(WsUrl wsUrl, String url) {
		String result = null;
		log.debug(url);
		try {
			result = getPageContent(wsUrl.getQuestWsLoginPage(), null);
			List<NameValuePair> postParams = getFormParams(result, wsUrl.getQuestWsLoginUser(), wsUrl.getQuestWsLoginPass());
			String cookie = sendPost(wsUrl.getQuestWsLoginAuth(), postParams);
			result = getPageContent(url, cookie);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}*/

	public String sendPost(String url, List<NameValuePair> postParams) throws Exception {

		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("Host", "gsgapp.gunungsewu.com");
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		post.setHeader("Accept-Language", "en-US,en;q=0.5");
		post.setHeader("Cookie", "_ga=GA1.2.1091800330.1478569406;");
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Referer", url);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");

		post.setEntity(new UrlEncodedFormEntity(postParams));

		HttpResponse response = client.execute(post);
//		System.out.println("Cookie req:" + post.getFirstHeader("Cookie").getValue());
//		System.out.println("Cookie res:" + response.getFirstHeader("Set-Cookie").getValue());

//		int responseCode = response.getStatusLine().getStatusCode();

//		System.out.println("\nSending 'POST' request to URL : " + url);
//		System.out.println("Post parameters : " + postParams);
//		System.out.println("Response Code : " + responseCode);	

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return response.getFirstHeader("Set-Cookie").getValue();

	}

	public String getPageContent(String url, String cookie) throws Exception {

		HttpGet request = new HttpGet(url);

		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Host", "gsgapp.gunungsewu.com");
		request.setHeader("Connection", "keep-alive");
		request.setHeader("Upgrade-Insecure-Requests", "1");
		request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-Language", "en-US,en;q=0.5");
		if(cookie != null) request.setHeader("Cookie",cookie);

		HttpResponse response = client.execute(request);
//		int responseCode = response.getStatusLine().getStatusCode();

//		System.out.println("\nSending 'GET' request to URL : " + url);
//		System.out.println("Response Code : " + responseCode);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		// set cookies
		setCookies(
				response.getFirstHeader("Set-Cookie") == null ? "" : response.getFirstHeader("Set-Cookie").toString());

		return result.toString();

	}

	/*public List<NameValuePair> getFormParams(String html, String username, String password)
			throws UnsupportedEncodingException {

//		System.out.println("Extracting form's data...");

		Document doc = Jsoup.parse(html);

		// Google form id
		Element loginform = doc.select("form[name=_DominoForm]").first();
		Elements inputElements = loginform.getElementsByTag("input");

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");

			if (key.equals("Username"))
				value = username;
			else if (key.equals("Password"))
				value = password;

			paramList.add(new BasicNameValuePair(key, value));

		}

		return paramList;
	}*/

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

}

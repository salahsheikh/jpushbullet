package com.shakethat.jpushbullet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.shakethat.jpushbullet.net.PushbulletDevice;

public class PushbulletClient {

	private CredentialsProvider	credsProvider	= new BasicCredentialsProvider();
	private CloseableHttpClient	client;
	private Gson				gson;
	
	private String URL = "https://api.pushbullet.com/api";
	
	private Log log = LogFactory.getLog(getClass());

	public PushbulletClient(String api_key) {
		client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		credsProvider.setCredentials(new AuthScope("api.pushbullet.com", 443), new UsernamePasswordCredentials(api_key,
				null));
		gson = new Gson();
	}

	public PushbulletDevice getDevices() throws IllegalStateException, IOException {
		HttpGet httpget = new HttpGet(URL + "/devices");
		CloseableHttpResponse response = client.execute(httpget);
		StringBuffer result = new StringBuffer();
		try {
			log.info(response.getStatusLine());
			try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
				for (String line; (line = br.readLine()) != null;) {
					result.append(line);
				}
				br.close();
			}
		} finally {
			response.close();
			
		}
		return gson.fromJson(result.toString(), PushbulletDevice.class);
	}

	public String sendNote(String iden, String title, String body) {
		HttpPost post = new HttpPost(URL + "/pushes");
		StringBuffer result = new StringBuffer();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("type", "note"));
			nameValuePairs.add(new BasicNameValuePair("device_iden", iden));
			nameValuePairs.add(new BasicNameValuePair("title", title));
			nameValuePairs.add(new BasicNameValuePair("body", body));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			log.info(response.getStatusLine());
			try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
				for (String line; (line = br.readLine()) != null;) {
					result.append(line);
				}
				br.close();
			}
		} catch (IOException e) {
			log.error(e);
		}
		return result.toString();
	}
	
	public String sendLink(String iden, String title, String url) {
		HttpPost post = new HttpPost(URL + "/pushes");
		StringBuffer result = new StringBuffer();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("type", "link"));
			nameValuePairs.add(new BasicNameValuePair("device_iden", iden));
			nameValuePairs.add(new BasicNameValuePair("title", title));
			nameValuePairs.add(new BasicNameValuePair("url", url));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			log.info(response.getStatusLine());
			try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
				for (String line; (line = br.readLine()) != null;) {
					result.append(line);
				}
				br.close();
			}
		} catch (IOException e) {
			log.error(e);
		}
		return result.toString();
	}
	
	public String sendList(String iden, String title, ArrayList<String> list) {
		HttpPost post = new HttpPost(URL + "/pushes");
		StringBuffer result = new StringBuffer();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("type", "list"));
			nameValuePairs.add(new BasicNameValuePair("device_iden", iden));
			nameValuePairs.add(new BasicNameValuePair("title", title));
			for(String s : list) {
				nameValuePairs.add(new BasicNameValuePair("items", s));
			}
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			log.info(response.getStatusLine());
			try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
				for (String line; (line = br.readLine()) != null;) {
					result.append(line);
				}
				br.close();
			}
		} catch (IOException e) {
			log.error(e);
		}
		return result.toString();
	}
	
	public String sendList(String iden, String title, String... list) {
		HttpPost post = new HttpPost(URL + "/pushes");
		StringBuffer result = new StringBuffer();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("type", "list"));
			nameValuePairs.add(new BasicNameValuePair("device_iden", iden));
			nameValuePairs.add(new BasicNameValuePair("title", title));
			for(String s : list) {
				nameValuePairs.add(new BasicNameValuePair("items", s));
			}
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			log.info(response.getStatusLine());
			try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
				for (String line; (line = br.readLine()) != null;) {
					result.append(line);
				}
				br.close();
			}
		} catch (IOException e) {
			log.error(e);
		}
		return result.toString();
	}
	
	public String sendAddress(String iden, String name, String address) {
		HttpPost post = new HttpPost(URL + "/pushes");
		StringBuffer result = new StringBuffer();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("type", "addess"));
			nameValuePairs.add(new BasicNameValuePair("device_iden", iden));
			nameValuePairs.add(new BasicNameValuePair("name", name));
			nameValuePairs.add(new BasicNameValuePair("address", address));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			log.info(response.getStatusLine());
			try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
				for (String line; (line = br.readLine()) != null;) {
					result.append(line);
				}
				br.close();
			}
		} catch (IOException e) {
			log.error(e);
		}
		return result.toString();
	}
	
	public String sendFile(String iden, File file) throws Exception {

		if(file.length() >= 26214400){
			throw new Exception("The file you are trying to upload is too big.");
		}

		HttpPost post = new HttpPost("https://api.pushbullet.com/api/pushes");
		StringBuffer result = new StringBuffer();
		try {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create(); 
		    builder.addBinaryBody("file", file);
		    builder.addTextBody("device_iden", iden);
		    builder.addTextBody("type", "file");
			post.setEntity(builder.build());

			HttpResponse response = client.execute(post);
			log.info(response.getStatusLine());
			try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
				for (String line; (line = br.readLine()) != null;) {
					result.append(line);
				}
				br.close();
			}
		} catch (IOException e) {
			log.error("Unable to access file!", e);
		}
		return result.toString();
	}
}
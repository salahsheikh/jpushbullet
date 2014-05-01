package com.shakethat.jpushbullet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.shakethat.jpushbullet.net.PushbulletDevice;

/**
 * Controls all connection to the Pushbullet API. Contains all methods to access and send data.
 * @author shakethat
 *
 */
public class PushbulletClient {

	/***
	 * Carries the api key that verifies with the API
	 */
	private CredentialsProvider	credsProvider	= new BasicCredentialsProvider();
	
	/**
	 * Our http client
	 */
	private CloseableHttpClient	client;
	
	/**
	 * Used for parsing resulting data from the API in JSON
	 */
	private Gson				gson;
	
	private String URL = "https://api.pushbullet.com/api";
	
	private Log log = LogFactory.getLog(getClass());

	/**
	 * Create instances of the http client and other needed things.
	 * @param api_key The only credential to be passed. Acts as user/password
	 */
	public PushbulletClient(String api_key) {
		client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		credsProvider.setCredentials(new AuthScope("api.pushbullet.com", 443), new UsernamePasswordCredentials(api_key,
				null));
		gson = new Gson();
	}

	/**
	 * Parse all the devices available. This is needed if you want to use it to send any data.
	 * @return PushbulletDevice, a class holding all the devices.
	 * @throws IllegalStateException
	 * @throws IOException
	 */
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

	/**
	 * Send a note
	 * @param iden The device identification code
	 * @param title Title of the note
	 * @param body Body text of the note
	 * @return resulting json from the api
	 */
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
	
	/**
	 * Send a link
	 * @param iden Device identification code
	 * @param title Link title
	 * @param url Url of the link
	 * @return resulting json from api
	 */
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
	
	/**
	 * Send a list of items
	 * @param iden device identification code
	 * @param title Title of the list
	 * @param list ArrayList of items to send
	 * @return resulting json from api
	 */
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
	
	/**
	 * Send a list of items 
	 * @param iden device identification code
	 * @param title Title of the list
	 * @param list Multiple string objects to send
	 * @return resulting json from api
	 */
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
	
	/**
	 * Send an address
	 * @param iden device identification code
	 * @param name name of the location
	 * @param address address of the location or google map query
	 * @return resulting json from api
	 */
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
	
	/**
	 * Send a file
	 * @param iden Device identification code
	 * @param file the file to send
	 * @return resulting json from api
	 * @throws Exception Any exception that were to occur
	 */
	public String sendFile(String iden, File file) throws Exception {

		if(file.length() >= 26214400){
			throw new Exception("The file you are trying to upload is too big.");
		}

		HttpPost post = new HttpPost(URL + "/pushes");
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
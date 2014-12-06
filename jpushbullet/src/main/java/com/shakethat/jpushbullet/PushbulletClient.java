package com.shakethat.jpushbullet;

import com.google.gson.Gson;
import com.shakethat.jpushbullet.net.PushList;
import com.shakethat.jpushbullet.net.PushbulletDevice;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls all connection to the Pushbullet API. Contains all methods to access and send data.
 *
 * @author shakethat
 */
public class PushbulletClient {

    /**
     * Carries the api key that verifies with the API
     */
    private CredentialsProvider credsProvider = new BasicCredentialsProvider();

    /**
     * Our http client
     */
    private CloseableHttpClient client;
    /**
     * Used for parsing resulting data from the API in JSON
     */
    private Gson gson;
    private String URL = "https://api.pushbullet.com/v2";
    private Log log = LogFactory.getLog(getClass());
    /**
     * Logging level
     */
    private int log_level;

    /**
     * Create instances of the http client and other needed things. No logging done with this constructor
     *
     * @param api_key The only credential to be passed. Acts as user/password
     */
    public PushbulletClient(String api_key) {
        client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        credsProvider.setCredentials(new AuthScope("api.pushbullet.com", 443), new UsernamePasswordCredentials(api_key, null));
        gson = new Gson();
        this.log_level = 0;
    }

    /**
     * Create instances of the http client and other needed things. Also specifies what logging level to use.
     *
     * @param api_key   The only credential to be passed. Acts as user/password
     * @param log_level Used to select what level of logging needed.
     *                  Ex: PushbulletClient("", PushbulletClient.LOG_LEVEL.INFO) for only logging information
     *                  or  PushbulletClient("", PushbulletClient.LOG_LEVEL.INFO | PushbulletClient.LOG_LEVEL.ERROR) for logging both info and errors.
     */
    public PushbulletClient(String api_key, int log_level) {
        client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        credsProvider.setCredentials(new AuthScope("api.pushbullet.com", 443), new UsernamePasswordCredentials(api_key, null));
        gson = new Gson();
        this.log_level = log_level;
    }

    /**
     * Parse all the devices available. This is needed if you want to use it to send any data.
     *
     * @return PushbulletDevice, a class holding all the devices.
     * @throws IllegalStateException
     * @throws IOException
     */
    public PushbulletDevice getDevices() throws IllegalStateException, IOException {
        HttpGet httpget = new HttpGet(URL + "/devices");
        StringBuilder result = new StringBuilder();
        try (CloseableHttpResponse response = client.execute(httpget)) {
            if (log_level == 1 || log_level == 3) {
                log.info(response.getStatusLine());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        }
        System.out.println(result.toString());
        return gson.fromJson(result.toString(), PushbulletDevice.class);
    }

    public PushList getPushes(long modified) throws IOException {
        HttpGet get = new HttpGet(URL + "/pushes?modified_after=" + modified);
        StringBuilder result = new StringBuilder();
        try (CloseableHttpResponse response = client.execute(get)) {
            if (log_level == 1 || log_level == 3) {
                log.info(response.getStatusLine());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        }
        //System.out.println(result);
        return gson.fromJson(result.toString(), PushList.class);
    }

    /**
     * Send a note
     *
     * @param iden   The device identification code
     * @param title  Title of the note
     * @param body   Body text of the note
     * @param device Sending to a device or email
     * @return resulting json from the api
     */
    public String sendNote(boolean device, String iden, String title, String body) {
        HttpPost post = new HttpPost(URL + "/pushes");
        StringBuilder result = new StringBuilder();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("type", "note"));
            nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            nameValuePairs.add(new BasicNameValuePair("body", body));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse response = client.execute(post);
            if (log_level == 1 || log_level == 3) {
                log.info(response.getStatusLine());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            if (log_level == 2 || log_level == 3) {
                log.error(e);
            }
        }
        return result.toString();
    }

    /**
     * Send a link
     *
     * @param iden  Device identification code
     * @param title Link title
     * @param url   Url of the link
     * @return resulting json from api
     */
    public String sendLink(boolean device, String iden, String title, String url) {
        HttpPost post = new HttpPost(URL + "/pushes");
        StringBuilder result = new StringBuilder();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("type", "link"));
            nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            nameValuePairs.add(new BasicNameValuePair("url", url));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse response = client.execute(post);
            if (log_level == 1 || log_level == 3) {
                log.info(response.getStatusLine());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            if (log_level == 2 || log_level == 3) {
                log.error(e);
            }
        }
        return result.toString();
    }

    /**
     * Send a list of items
     *
     * @param iden  device identification code
     * @param title Title of the list
     * @param list  ArrayList of items to send
     * @return resulting json from api
     */
    public String sendList(boolean device, String iden, String title, ArrayList<String> list) {
        HttpPost post = new HttpPost(URL + "/pushes");
        StringBuilder result = new StringBuilder();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("type", "list"));
            nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            for (String s : list) {
                nameValuePairs.add(new BasicNameValuePair("items", s));
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse response = client.execute(post);
            if (log_level == 1 || log_level == 3) {
                log.info(response.getStatusLine());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            if (log_level == 2 || log_level == 3) {
                log.error(e);
            }
        }
        return result.toString();
    }

    /**
     * Send a list of items
     *
     * @param iden  device identification code
     * @param title Title of the list
     * @param list  Multiple string objects to send
     * @return resulting json from api
     */
    public String sendList(boolean device, String iden, String title, String... list) {
        HttpPost post = new HttpPost(URL + "/pushes");
        StringBuilder result = new StringBuilder();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("type", "list"));
            nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            for (String s : list) {
                nameValuePairs.add(new BasicNameValuePair("items", s));
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse response = client.execute(post);
            if (log_level == 1 || log_level == 3) {
                log.info(response.getStatusLine());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            if (log_level == 2 || log_level == 3) {
                log.error(e);
            }
        }
        return result.toString();
    }

    /**
     * Send an address
     *
     * @param iden    device identification code
     * @param name    name of the location
     * @param address address of the location or google map query
     * @return resulting json from api
     */
    public String sendAddress(boolean device, String iden, String name, String address) {
        HttpPost post = new HttpPost(URL + "/pushes");
        StringBuilder result = new StringBuilder();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("type", "addess"));
            nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("address", address));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse response = client.execute(post);
            if (log_level == 1 || log_level == 3) {
                log.info(response.getStatusLine());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            if (log_level == 2 || log_level == 3) {
                log.error(e);
            }
        }
        return result.toString();
    }

    /**
     * Send a file
     *
     * @param iden Device identification code
     * @param file the file to send
     * @return resulting json from api
     * @throws Exception Any exception that were to occur
     */
    public String sendFile(boolean device, String iden, File file) throws Exception {

        if (file.length() >= 26214400) {
            if (log_level == 2 || log_level == 3) {
                log.error("The file you are trying to upload is too big. File: " + file.getName() + " Size: " + file.length());
            }
            throw new Exception("The file you are trying to upload is too big.");
        }

        HttpPost post = new HttpPost(URL + "/pushes");
        StringBuilder result = new StringBuilder();
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file);
            builder.addTextBody(device ? "device_iden" : "email", iden);
            builder.addTextBody("type", "file");
            post.setEntity(builder.build());

            HttpResponse response = client.execute(post);
            if (log_level == 1 || log_level == 3) {
                log.info(response.getStatusLine());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            if (log_level == 2 || log_level == 3) {
                log.error("Unable to access file!", e);
            }
        }
        return result.toString();
    }

    public class LOG_LEVEL {
        public static final int INFO = 1;
        public static final int ERROR = 2;
    }
}
package com.github.silk8192.jpushbullet;

import com.github.silk8192.jpushbullet.net.Devices;
import com.github.silk8192.jpushbullet.net.Push;
import com.github.silk8192.jpushbullet.net.PushList;
import com.github.silk8192.jpushbullet.net.PushbulletDevice;
import com.google.gson.Gson;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private Logger logger = LogManager.getLogger();
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
        getDevices();
    }

    /**
     * Create instances of the http client and other needed things. Also specifies what logging level to use.
     *
     * @param api_key   The only credential to be passed. Acts as user/password
     * @param log_level Used to select what level of logging needed.
     *                  Ex: PushbulletClient(api_key, PushbulletClient.LOG_LEVEL.INFO) for only logging information
     *                  or  PushbulletClient(api_key, PushbulletClient.LOG_LEVEL.INFO | PushbulletClient.LOG_LEVEL.ERROR) for logging both info and errors.
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
    public List<Devices> getDevices() {
        HttpGet httpget = new HttpGet(URL + "/devices");
        String result = null;
        try (CloseableHttpResponse response = client.execute(httpget)) {
            if (log_level == 1 || log_level == 3) {
                logger.info(response.getStatusLine());
            }
            result = collectResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(result.toString(), PushbulletDevice.class).getDevices();
    }

    public List<Push> getPushes(long modified) throws IOException {
        HttpGet get = new HttpGet(URL + "/pushes?modified_after=" + Long.toString(modified));
        String result;
        try (CloseableHttpResponse response = client.execute(get)) {
            logger.info(response.getStatusLine());
            result = collectResponse(response);
        }
        return gson.fromJson(result, PushList.class).getPushes();
    }

    /**
     * Send a note
     *
     * @param device Sending to a device or email
     * @param iden   The device identification code or email address if device is true
     * @param title  Title of the note
     * @param body   Body text of the note
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
            logger.info(response.getStatusLine());
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            logger.catching(e);
        }
        return result.toString();
    }

    /**
     * Send a link
     *
     * @param device Sending to a device or email
     * @param iden   The device identification code or email address if device is true
     * @param title  Link title
     * @param url    Url of the link
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

            logger.info(response.getStatusLine());
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            logger.catching(e);
        }
        return result.toString();
    }

    /**
     * Send a link
     *
     * @param iden  Device identification code
     * @param title Link title
     * @param body  Body text of the link
     * @param url   Url of the link
     * @return resulting json from api
     */
    public String sendLink(boolean device, String iden, String title, String body, String url) {
        HttpPost post = new HttpPost(URL + "/pushes");
        StringBuilder result = new StringBuilder();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("type", "link"));
            nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            nameValuePairs.add(new BasicNameValuePair("body", body));
            nameValuePairs.add(new BasicNameValuePair("url", url));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse response = client.execute(post);

            logger.info(response.getStatusLine());
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            if (log_level == 2 || log_level == 3) {
                logger.catching(e);
            }
        }
        return result.toString();
    }

    /**
     * Send a list of items
     *
     * @param device Sending to a device or email
     * @param iden   The device identification code or email address if device is truev
     * @param title  Title of the list
     * @param list   ArrayList of items to send
     * @return resulting json from api
     */
    public String sendList(boolean device, String iden, String title, ArrayList<String> list) {
        HttpPost post = new HttpPost(URL + "/pushes");
        String result = null;
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("type", "list"));
            nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            for (String s : list) {
                nameValuePairs.add(new BasicNameValuePair("items", s));
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse response = client.execute(post);
            logger.info(response.getStatusLine());
            result = collectResponse(response);
        } catch (IOException e) {
            if (log_level == 2 || log_level == 3) {
                logger.catching(e);
            }
        }
        return result;
    }

    /**
     * Send a list of items
     *
     * @param device Sending to a device or email
     * @param iden   The device identification code or email address if device is true
     * @param title  Title of the list
     * @param list   Multiple string objects to send
     * @return resulting json from api
     */
    public String sendList(boolean device, String iden, String title, String... list) {
        HttpPost post = new HttpPost(URL + "/pushes");
        Optional<HttpResponse> response = Optional.empty();
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("type", "list"));
            nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            for (String s : list) {
                nameValuePairs.add(new BasicNameValuePair("items", s));
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            response = Optional.of(client.execute(post));
            logger.info(response.get().getStatusLine());
        } catch (IOException e) {
            logger.catching(e);
        }
        return collectResponse(response.get());
    }

    /**
     * Send an address
     *
     * @param device  Sending to a device or email
     * @param iden    The device identification code or email address if device is true
     * @param name    name of the location
     * @param address address of the location or google map query
     * @return resulting json from api
     */
    public String sendAddress(boolean device, String iden, String name, String address) {
        HttpPost post = new HttpPost(URL + "/pushes");
        HttpResponse response = null;
        List<NameValuePair> nameValuePairs = new ArrayList<>(1);
        nameValuePairs.add(new BasicNameValuePair("type", "addess"));
        nameValuePairs.add(new BasicNameValuePair(device ? "device_iden" : "email", iden));
        nameValuePairs.add(new BasicNameValuePair("name", name));
        nameValuePairs.add(new BasicNameValuePair("address", address));
        try {
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            response = client.execute(post);
            logger.info(response.getStatusLine());
        } catch (Exception e) {
            logger.catching(e);
        }
        return collectResponse(response);
    }

    /**
     * Send a file
     *
     * @param device Sending to a device or email
     * @param iden   The device identification code or email address if device is true
     * @param file   the file to send
     * @return resulting json from api
     * @throws Exception Any exception that were to occur
     */
    public String sendFile(boolean device, String iden, File file) {

        if (file.length() >= 26214400) {
            logger.error("The file you are trying to upload is too big. File: " + file.getName() + " Size: " + file.length());
        }

        HttpPost post = new HttpPost(URL + "/pushes");
        HttpResponse response = null;
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file);
            builder.addTextBody(device ? "device_iden" : "email", iden);
            builder.addTextBody("type", "file");
            post.setEntity(builder.build());

            response = client.execute(post);
            logger.info(response.getStatusLine());
        } catch (IOException e) {
            logger.catching(e);
        }
        return collectResponse(response);
    }

    private String collectResponse(HttpResponse response) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            for (String line; (line = br.readLine()) != null; ) {
                result.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public Devices getDevice(int id) {
        return this.getDevices().get(id);
    }

}
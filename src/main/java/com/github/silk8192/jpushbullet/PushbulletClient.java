package com.github.silk8192.jpushbullet;

import com.github.silk8192.jpushbullet.items.chat.Chat;
import com.github.silk8192.jpushbullet.items.chat.Chats;
import com.github.silk8192.jpushbullet.items.device.Device;
import com.github.silk8192.jpushbullet.items.device.Devices;
import com.github.silk8192.jpushbullet.items.push.FileUploadRequest;
import com.github.silk8192.jpushbullet.items.push.Push;
import com.github.silk8192.jpushbullet.items.push.Pushes;
import com.github.silk8192.jpushbullet.items.subscription.Subscription;
import com.github.silk8192.jpushbullet.items.subscription.Subscriptions;
import com.github.silk8192.jpushbullet.items.user.User;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Controls all connection to the Pushbullet API. Contains all methods to access and send data.
 *
 * @author silk8192
 */
public class PushbulletClient {

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

    private List<Device> devices;

    /**
     * Create instances of the http client and other needed things. Also specifies what logging level to use.
     *
     * @param api_key The only credential to be passed. Acts as user/password.
     */
    public PushbulletClient(String api_key) {
        // Carries the api key that verifies with the API
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope("api.pushbullet.com", 443), new UsernamePasswordCredentials(api_key, null));
        this.client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        this.gson = new Gson();
        this.devices = listDevices();
    }

    public List<Device> listDevices() {
        return gson.fromJson(get(URL + "/devices"), Devices.class).getDevices();
    }

    public List<Chat> getChats() {
        return gson.fromJson(get(URL + "/chats"), Chats.class).getChats();
    }

    public Chat createChat(String email) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("email", email));
        return gson.fromJson(post(URL + "/chats", nameValuePairs), Chat.class);
    }

    public Chat updateChat(String iden, boolean muted) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("muted", String.valueOf(muted)));
        return gson.fromJson(post(URL + "/chats/" + iden, nameValuePairs), Chat.class);
    }

    public void deleteChat(String iden) {
        delete(URL + "/chats/" + iden);
    }

    public List<Subscription> listSubscriptions() {
        return gson.fromJson(get(URL + "/subscriptions"), Subscriptions.class).getSubscriptions();
    }

    public Subscription createSubscription(String channel_tag) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("channel_tag", channel_tag));
        return gson.fromJson(post(URL + "subscriptions", nameValuePairs), Subscription.class);
    }

    public Subscription updateSubscription(String iden, boolean muted) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("muted", String.valueOf(muted)));
        return gson.fromJson(post(URL + "/subscriptions/" + iden, nameValuePairs), Subscription.class);
    }

    public void deleteSubscription(String iden) {
        delete(URL + "/subscriptions/" + iden);
    }

    public User getCurrentUser() {
        return gson.fromJson(get(URL + "/users/me"), User.class);
    }

    public List<Push> listPushes(String modifiedAfter, String active, String cursor, int limit) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("modified_after", modifiedAfter));
        nameValuePairs.add(new BasicNameValuePair("active", active));
        nameValuePairs.add(new BasicNameValuePair("cursor", cursor));
        nameValuePairs.add(new BasicNameValuePair("limit", Integer.toString(limit)));
        return gson.fromJson(post(URL + "/pushes", nameValuePairs), Pushes.class).getPushes();
    }

    public Push sendNotePush(String title, String body) {
        try {
            return sendPush("note", title, body, "", "", "", "", "", "", "", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Push sendLinkPush(String title, String body, String link) {
        try {
            return sendPush("link", title, body, link, "", "", "", "", "", "", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Push sendPush(String type, String title, String body, String url,
                         String file_name, String file_type, String file_url,
                         String source_device_iden, String device_iden,
                         String client_iden, String channel_tag, String email, String guid) throws Exception {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if(type.equals("link")) {
            nameValuePairs.add(new BasicNameValuePair("title", title));
            nameValuePairs.add(new BasicNameValuePair("body", body));
            nameValuePairs.add(new BasicNameValuePair("url", url));
            return gson.fromJson(post(URL + "/pushes", nameValuePairs), Push.class);
        } else if(type.equals("note")) {
            nameValuePairs.add(new BasicNameValuePair("type", "note"));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            nameValuePairs.add(new BasicNameValuePair("body", body));
            return gson.fromJson(post(URL + "/pushes", nameValuePairs), Push.class);
        } else {
            throw new Exception("Invalid push type!");
        }
    }

    public Push sendFilePush(String body, String fileName, String fileType) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("file_name", fileName));
        nameValuePairs.add(new BasicNameValuePair("file_type", fileType));
        FileUploadRequest request = gson.fromJson(post(URL + "/upload-request", nameValuePairs), FileUploadRequest.class);
        nameValuePairs.add(new BasicNameValuePair("type", "file"));
        nameValuePairs.add(new BasicNameValuePair("file_url", request.getFileUrl()));
        return gson.fromJson(post(URL + "/pushes", nameValuePairs), Push.class);
    }

    public Push updatePush(String iden, boolean dismissed) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("dismissed", String.valueOf(dismissed)));
        return gson.fromJson(post(URL + "/pushes/" + iden, nameValuePairs), Push.class);
    }

    public void deletePush(String iden) {
        delete(URL + "/pushes/" + iden);
    }

    public void deleteAllPushes() {
        delete(URL + "/pushes");
    }

    private String post(String path, List<NameValuePair> nameValuePairs) {
        HttpPost post = new HttpPost(path);
        String result = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = client.execute(post);

            logger.info(response.getStatusLine());
            result = collectResponse(response);
        } catch (IOException e) {
            logger.catching(e);
        }
        return result;
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

    private String get(String path) {
        HttpGet get = new HttpGet(path);
        String result = null;
        try (CloseableHttpResponse response = client.execute(get)) {
            logger.info(response.getStatusLine());
            result = collectResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String delete(String path) {
        HttpDelete delete = new HttpDelete(path);
        String result = null;
        try {
            HttpResponse response = client.execute(delete);
            logger.info(response.getStatusLine());
            result = collectResponse(response);
        } catch (IOException e) {
            logger.catching(e);
        }
        return result;
    }

    public Device getDevice(int id) {
        return this.devices.get(id);
    }

    public Device getDeviceByIden(String iden) {
        Optional<Device> device = this.devices.stream().filter(d -> d.getIden().equals(iden)).filter(Objects::nonNull).findFirst();
        return device.get();
    }

}
jpushbullet
===========

A simple library to interface/use/access the Pushbullet API (v2) written with Java 8.
Current feature set:

#### Chat: list, create, update, delete. 
#### Device: list, create, update, delete.
#### Push: list, create, update, delete, delete all
#### Subscriptions: list, create, update, get channel info
#### User: get current user info

Dependencies:
=============
* [Apache HttpClient library](http://hc.apache.org/httpcomponents-client-ga/)
* [Google gson library](https://code.google.com/p/google-gson/)
* [Apache Commons Logging](http://commons.apache.org/proper/commons-logging/)
* Log4j Library

How to use:
===========

jpushbullet is intended to be very simple and lightweight. To start, you need to create a PushbulletClient.

```
PushbulletClient client = new PushbulletClient(api_key);
```

This creates the connection to Pushbullet's REST API and supplies the api_key used to identify users (as passed into the constructor). FYI, you can get the api key from https://www.pushbullet.com/account when logged in.

To get devices:
```
PushbulletDevice devices = client.getDevices();
```

> *How to send things via jpushbullet*

```
//where n is the device id as ordered by the list. 

//To send notes:
client.sendNotePush(title, body);

//To send links
client.sendLinkPush(title, body, link);
```

Thank you for using this!

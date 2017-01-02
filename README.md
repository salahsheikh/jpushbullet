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
* [SLF4J Simple Logging Facade for Java](http://www.slf4j.org/)

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
Chats:
getChats()
returns a list of all chats

createChat(the email to chat with)
returns data about the recipient

updateChat(identifing key of the chat, boolean muted)
returns data about the recipient

deleteChat(identifing key of the chat)

Device:
listDevices()
returns a list of all devices

getDevices()
returns a list of cached devices

createDevice(nickname of device, model of device, manufacturer, app version of pushbullet, icon ("desktop", "browser", "website", "laptop", "tablet", "phone", "watch", "system", "ios"), whether the device has SMS capability)
returns the new device 

deleteDevice(String iden)

Subscriptions:
listSubscriptions()
returns a list of all Subscriptions

createSubscription(the channel tag)
returns a Subscription object

updateSubscription(subscription iden, boolean muted)
returns the updated Subscription object

deleteSubscription(subscription iden)



General:
client.sendNotePush(title, body);

//To send links
client.sendLinkPush(title, body, link);

Ephemerals:
For sms:
sendSMSPush(phone number, message, package name of application, targetDeviceIden)

For notification:
sendNotificationPush(notification title, the notification message, the name of the application, the version of the application, whether the notification is dismissable, whether the phone is rooted or not, a JPG icon that is base64 encoded, the notification id, name of the application package, the identity of the device where the notification is coming from)

For clipboard:
sendClipboardPush(the contents that should be transferred to the clipboard)
```

Thank you for using this!

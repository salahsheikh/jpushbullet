jpushbullet
===========

A simple Java library to interface/use/access Pushbullet's API. 

Dependencies:
=============
To use the jpushbullet library, you require the following libraries:
* [Apache's HttpClient library for GET/POST requests.](http://hc.apache.org/httpcomponents-client-ga/)
* [Google's gson library for parsing data from Pushbullet](https://code.google.com/p/google-gson/)
* [Apache Commons Logging (HttpClient needs it)](http://commons.apache.org/proper/commons-logging/)

How to use:
===========

jpushbullet is intended to be very simple and lightweight. To start, you need to create a PushbulletClient.

```
PushbulletClient client = new PushbulletClient(api_key);
```

This creates the connection to Pushbullet's REST API and supplies the api_key used to identify users (as passed into the constructor). FYI, you can get the api key from https://www.pushbullet.com/account when logged in.

To identify user devices, you need to get the devices. 
```
		try {
			PushbulletDevice devices = client.getDevices();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
```
This method is absolutely vital to use this library as you need device identification keys to send things via Pushbullet. 

Finally, onto the real meat of things, *How to send things via jpushbullet*

```
//To send notes:
client.sendNote(client.getDevices().getDevices().get(0).getIden(), "SUPER TITLE YO", "JPUSHBULLET ROCKS!");

//To send files
client.sendFile(client.getDevices().getDevices().get(0).getIden(), new File("path to file");

//To send links
client.sendLink(client.getDevices().getDevices().get(0).getIden(), "title here yo", "uhh the url here");
```

Thank you for using this! If you do. Maybe put this in your credits perhaps. With a link maybe.

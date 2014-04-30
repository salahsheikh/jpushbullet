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

Finally, onto the real meat of things, 
> *How to send things via jpushbullet*

```
//where n is the device id as ordered by the list. 

//To send notes:
client.sendNote(devices.getDevices().get(n).getIden(), "SUPER TITLE YO", "JPUSHBULLET ROCKS!");

//To send files
client.sendFile(devices.getDevices().get(n).getIden(), new File("path to file");

//To send links
client.sendLink(devices.getDevices().get(n).getIden(), "title here=", "uhh the url here");

//To send list
client.sendList(devices.getDevices().get(n).getIden(), "title here", ArrayList<String> itemsToPush);

OR if you dont want to create an arraylist, you can insert lots of strings into the parameters instead.
client.sendList(devices.getDevices().get(n).getIden(), "title", "item 1", "item 2", "item 3" ... );

//To send an address
client.sendAddress(devices.getDevices().get(n).getIden(), "name of place", "address");
```
So far it only supports files, notes, and urls. Working on the rest when I can. In the meanwhile, you can help with this project. 

Thank you for using this! If you do. Maybe put this in your credits perhaps. With a link maybe.

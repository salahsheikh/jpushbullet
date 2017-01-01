package com.github.silk8192.jpushbullet.net;

public class Devices{

	private boolean active;
	private boolean pushable;
	private Number created;
	private Number modified;
	private Number app_version;
	private String iden;
	private String nickname;
	private String type;
	private String kind;
	private String manufacturer;
	private String model;
	private String fingerprint;
	private String push_token;

	public Number getApp_version(){
		return app_version;
	}

	public boolean isActive(){
		return active;
	}

	public boolean isPushable(){
		return pushable;
	}

	public Number getModified(){
		return modified;
	}

	public Number getCreated(){
		return created;
	}

	public String getNickname(){
		return nickname;
	}


	public String getType(){
		return type;
	}

	public String getKind(){
		return kind;
	}

	public String getManufacturer(){
		return manufacturer;
	}

	public String getModel(){
		return model;
	}

	public String getFingerprint(){
		return fingerprint;
	}

	public String getPush_token(){
		return push_token;
	}

	public String getIden(){
		return this.iden;
	}

}

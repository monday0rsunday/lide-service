package com.broduce.lide.model;

public class Info {

	private String title;
	private String link;
	private String type;

	public Info() {

	}

	public Info(String title, String link, String type) {
		setTitle(title);
		setLink(link);
		setType(type);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Info [title=" + title + ", link=" + link + ", type=" + type
				+ "]";
	}

}

package com.broduce.lide.model;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class Info extends ScriptableObject {

	private static final long serialVersionUID = 5106951819519512582L;
	private String title;
	private String link;
	private String type;

	public Info() {

	}

	@JSConstructor
	public Info(String title, String link, String type) {
		setTitle(title);
		setLink(link);
		setType(type);
	}

	@JSGetter
	public String getTitle() {
		return title;
	}

	@JSSetter
	public void setTitle(String title) {
		this.title = title;
	}

	@JSGetter
	public String getLink() {
		return link;
	}

	@JSSetter
	public void setLink(String link) {
		this.link = link;
	}

	@JSGetter
	public String getType() {
		return type;
	}

	@JSSetter
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Info [title=" + title + ", link=" + link + ", type=" + type
				+ "]";
	}

	@Override
	public String getClassName() {
		return "Info";
	}

}

package com.broduce.lide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.broduce.lide.model.Error;
import com.broduce.lide.model.Message;

/**
 * 
 * helper for creating {@link Message}
 * 
 * @author congnh
 * 
 */
public class MessageBuilder {

	private Message message;
	private Object data;

	public MessageBuilder() {
		message = new Message();
	}

	/**
	 * append new field to message
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public MessageBuilder append(String key, Object value) {
		message.put(key, value);
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public MessageBuilder data(String key, Object value) {
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		if (!(data instanceof HashMap)) {
			throw new RuntimeException("data is not object");
		}
		((HashMap<String, Object>) data).put(key, value);
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public MessageBuilder data(Object value) {
		if (data == null) {
			data = new ArrayList<Object>();
		}
		if (!(data instanceof ArrayList)) {
			throw new RuntimeException("data is not array");
		}
		((ArrayList<Object>) data).add(value);
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public MessageBuilder dataAll(List<?> value) {
		if (data == null) {
			data = new ArrayList<Object>();
		}
		if (!(data instanceof ArrayList)) {
			throw new RuntimeException("data is not array");
		}
		((ArrayList<Object>) data).addAll(value);
		return this;
	}

	public MessageBuilder error(Error error) {
		data = Collections.EMPTY_LIST;
		message.put("error", error);
		return this;
	}

	/**
	 * 
	 * @return required message
	 */
	public Message build() {
		message.put("data", data);
		return message;
	}

}

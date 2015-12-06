package com.broduce.lide;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONObject;

import com.broduce.lide.model.Error;

@Path("/")
public class MainApplication {
	private Lider lider;

	public MainApplication() {
		lider = new Lider();
	}

	@GET
	@Path("/detect")
	@Produces("application/json;charset=UTF-8")
	public String searchKeyword(@QueryParam("url") String url) {
		try {
			return new JSONObject(new MessageBuilder().dataAll(
					lider.detect(url)).build()).toString();
		} catch (Exception e) {
			return new JSONObject(new MessageBuilder().error(
					new Error(1, e.getMessage())).build()).toString();
		}
	}

	@GET
	@Path("/support")
	@Produces("application/json;charset=UTF-8")
	public String getSupportPattern() {
		try {
			return new JSONObject(new MessageBuilder().dataAll(
					lider.getSupportPatterns()).build()).toString();
		} catch (Exception e) {
			return new JSONObject(new MessageBuilder().error(
					new Error(1, e.getMessage())).build()).toString();
		}
	}
}

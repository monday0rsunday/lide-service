package com.broduce.lide;

import java.util.List;
import com.broduce.lide.model.Info;

public interface ILider {
	public List<Info> detect(String url);

	public List<String> getSupportPatterns();
}

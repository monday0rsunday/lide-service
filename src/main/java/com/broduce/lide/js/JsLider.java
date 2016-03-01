package com.broduce.lide.js;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mozilla.javascript.*;

import com.broduce.lide.ILider;
import com.broduce.lide.model.Info;

public class JsLider implements ILider {

	private Context cx;
	private Function fct;
	private Scriptable scope;

	public JsLider() {
		cx = Context.enter();
		try {
			scope = cx.initStandardObjects();
			ScriptableObject.defineClass(scope, Downloader.class);
			ScriptableObject.defineClass(scope, Info.class);
			cx.evaluateString(scope, "var dld = new Downloader();",
					"definer.js", 1, null);
			// Scriptable testCounter = cx.newObject(scope, "Downloader");
			// System.out.println(ScriptableObject.callMethod(testCounter,
			// "get",

			cx.evaluateString(scope,
					ReaderUtil.read("./data/extractor_v0.0.1.js"),
					"extractor_v0.0.1.js", 1, null);

			fct = (Function) scope.get("detect", scope);

			// for (int i = 0; i < 1000000; i++) {
			// NativeArray nr = (NativeArray) fct
			// .call(cx,
			// scope,
			// scope,
			// new Object[] { "http://www.tudou.com/programs/view/NXQVT5ZFz6w/"
			// });
			// // NativeArray nr = (NativeArray) cx
			// // .evaluateString(
			// // scope,
			// // "extract('http://www.tudou.com/programs/view/NXQVT5ZFz6w/')",
			// // "tudou_extractor.js", 1, null);
			//
			// for (Object obj : nr) {
			// System.out.println(obj);
			// }
			// }
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("", e);
		} finally {
			// Context.exit();
		}
	}

	@Override
	public List<Info> detect(String url) {
		List<Info> infos = new ArrayList<Info>();
		NativeArray nr = (NativeArray) fct.call(cx, scope, scope,
				new Object[] { url });
		for (Object obj : nr) {
			infos.add((Info) obj);
		}
		return infos;
	}

	@Override
	public List<String> getSupportPatterns() {
		return new ArrayList<String>();
	}

	public static void main(String[] args) throws IllegalAccessException,
			InstantiationException, InvocationTargetException {
		ILider lider = new JsLider();
		for (int i = 0; i < 1000000; i++) {
			lider.detect("http://www.tudou.com/programs/view/NXQVT5ZFz6w/");
		}
		// Context cx = Context.enter();
		// try {
		// Scriptable scope = cx.initStandardObjects();
		// ScriptableObject.defineClass(scope, Downloader.class);
		// ScriptableObject.defineClass(scope, Info.class);
		// cx.evaluateString(scope, "var dld = new Downloader();",
		// "definer.js", 1, null);
		// // Scriptable testCounter = cx.newObject(scope, "Downloader");
		// // System.out.println(ScriptableObject.callMethod(testCounter,
		// // "get",
		//
		// cx.evaluateString(scope,
		// ReaderUtil.read("./data/extractor_v0.0.1.js"),
		// "extractor_v0.0.1.js", 1, null);
		//
		// Function fct = (Function) scope.get("detect", scope);
		//
		// // NativeArray nr = (NativeArray) fct
		// // .call(cx,
		// // scope,
		// // scope,
		// // new Object[] {
		// // "https://www.facebook.com/minhcuong/videos/10153480402439495/"
		// // });
		// // for (Object obj : nr) {
		// // System.out.println(obj);
		// // }
		//
		// for (int i = 0; i < 1000000; i++) {
		// NativeArray nr = (NativeArray) fct
		// .call(cx,
		// scope,
		// scope,
		// new Object[] { "http://www.tudou.com/programs/view/NXQVT5ZFz6w/" });
		// // NativeArray nr = (NativeArray) cx
		// // .evaluateString(
		// // scope,
		// // "extract('http://www.tudou.com/programs/view/NXQVT5ZFz6w/')",
		// // "tudou_extractor.js", 1, null);
		//
		// for (Object obj : nr) {
		// System.out.println(obj);
		// }
		// }
		// } finally {
		// Context.exit();
		// }
	}

}

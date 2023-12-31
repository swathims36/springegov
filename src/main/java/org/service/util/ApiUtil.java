package org.service.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.NativeWebRequest;

public class ApiUtil {
	public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
		try {
			HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
			res.setCharacterEncoding("UTF-8");
			res.addHeader("Content-Type", contentType);
			res.getWriter().print(example);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String fetchURL(String theUrl) {
		StringBuilder content = new StringBuilder();

		try {
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}

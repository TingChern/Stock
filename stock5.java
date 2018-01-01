package iii.stock;

import java.io.*;
import java.net.*;
import java.util.*;

public class stock5 {
	public static void main(String[] args) {
		new stock5().stockprice("201702", "2330");
	}

	public void stockprice(String yearmonth, String stocknum) {
		String urlConnect = "http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=html&date=" + yearmonth + "01"
				+ "&stockNo=" + stocknum;
		URL url;

		try {
			url = new URL(urlConnect);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String s = null;
			String content = "";
			int start = 0;
			int end = 0;
			// StringBuilder test = new StringBuilder();
			// String s;
			FileWriter fw = new FileWriter("C:\\_JSP\\test1.html");
			while ((s = reader.readLine()) != null) {
				content += s + "\n";
				if (s.contains("<tbody>")) {
					start = content.indexOf("<tbody>");
					System.out.println(start);
				}
				;
				if (s.contains("</tbody>")) {
					end = content.indexOf("</tbody>");
					System.out.println(end);
				};

			}
//			System.out.println(content);
			 content=content.substring(start,end);
//			 content = test.toString();
			// System.out.print(content);
			// System.out.println(s);
			fw.write(content);
			fw.flush();

			fw.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

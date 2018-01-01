package iii.stock;

import java.io.*;
import java.net.*;
import java.util.*;

public class stock5 {
	public static void main(String[] args) {
		new stock5().stockprice("201702", "2330");
	}

	public void stockprice(String yearmonth, String stocknum) {
		// 輸入年月和股票號碼來查某月股價
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
			String[] daydata;
			// StringBuilder test = new StringBuilder();
			// String s;
			FileWriter fw = new FileWriter("C:\\_JSP\\test1.html");
			while ((s = reader.readLine()) != null) {
				content += s + "\n";
				if (s.contains("<tbody>")) {
					start = content.indexOf("<tbody>");
					// System.out.println(start);
				}
				;
				if (s.contains("</tbody>")) {
					end = content.indexOf("</tbody>");
					// System.out.println(end);
				}
				;

			}
			// System.out.println(content);
			content = content.substring(start + "<tbody>".length(), end).trim();
			daydata = content.split("</tr>");

			// content = test.toString();
			// System.out.print(content);
			// System.out.println(s);
			for (int i = 0; i < daydata.length; i++) {
				daydata[i] = daydata[i].replaceFirst("<tr>", "").trim();
				// System.out.println(daydata[i]);
				// fw.write(daydata[i]+"\n");
				// fw.write("==========================="+"\n");
				String[] data = daydata[i].split("</td>");
				for (int j = 0; j < data.length; j++) {
					data[j] = data[j].replaceFirst("<td>", "").trim();
					fw.write(data[j] + "\n");
				}
				fw.write("\n");

			}
			;

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

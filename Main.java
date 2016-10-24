package com.whatever.webspider;

public class Main {

	public static String		HOST					= "https://book.douban.com";
	public static String[]	TARGETCONTENT	= { "»¥ÁªÍø", "Ëã·¨", "±à³Ì" };

	public static void main(String[] args) {
		for (int i = 0; i < Main.TARGETCONTENT.length; i++) {
			HtmlParser.targetContent.add(Main.TARGETCONTENT[i]);
		}
		HtmlParser hp = new HtmlParser(Main.HOST);
		hp.getParser();
		// iterating the URL
		for (String url : hp.getUrlStrings()) {
			if (HtmlParser.targetContent.isEmpty()) {
				// stop iterating, when find the right columns
				break;
			}
			try {
				if (HtmlParser.checkTitle(HtmlParser.getTitle(url))) {
					// get to the right column
					HtmlParser.columnUrl.add(url);
				}
				else {
					// wrong column
					// no need to check this URL anymore
					HtmlParser.urlSetOld.add(url);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				continue;
			}
		}
		for (String string : HtmlParser.columnUrl) {
			System.out.println(string);
		}
	}
}

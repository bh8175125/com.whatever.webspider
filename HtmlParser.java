package com.whatever.webspider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParser {

	public static List<String>	targetContent	= new ArrayList<String>();
	// URLs finished iterated
	public static Set<String>		urlSetOld			= new HashSet<String>();
	// target column URLs
	public static Set<String>		columnUrl			= new HashSet<String>();
	private String							url;
	// URLs need to be iterated
	private Set<String>					urlSetNew			= new HashSet<String>();

	// content that searching for
	private Parser							parser;

	public HtmlParser() {
		super();
	}

	public HtmlParser(String url) {
		super();
		this.url = url;
	}

	public static boolean checkTitle(String title) {

		for (int i = 0; i < HtmlParser.targetContent.size(); i++) {
			if (title.indexOf(HtmlParser.targetContent.get(i)) > -1) {
				HtmlParser.targetContent.remove(i);
				return true;
			}
		}
		return false;
	}

	public static String getTitle(String strl) throws NullPointerException {
		HtmlParser hp = new HtmlParser(strl);
		hp.getParser();
		String filterStr = "title";
		TagNameFilter filter = new TagNameFilter(filterStr);
		try {
			NodeList list = hp.parser.extractAllNodesThatMatch(filter);
			// System.out.println(list.elementAt(0).toString());
			return list.elementAt(0).toString();
		}
		catch (ParserException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public Set<String> getUrlStrings() {
		String filterStr = "a";
		TagNameFilter filter = new TagNameFilter(filterStr);
		try {
			NodeList nodes = this.parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < nodes.size(); i++) {
				String strl;
				LinkTag LinkTag = (LinkTag) nodes.elementAt(i);
				strl = LinkTag.getLink();
				// if the URL is from the host
				if (strl.equals("") || (strl.indexOf(Main.HOST) == -1)) {
					continue;
				}
				// test if exist in urlSetNew or already be iterated
				if (this.urlSetNew.contains(strl)
						|| HtmlParser.urlSetOld.contains(strl)) {
					continue;
				}
				else {
					// pass all the tests, save the URL to Set

					this.urlSetNew.add(strl);
				}

			}
			return this.urlSetNew;

		}
		catch (ParserException e) {
			// TODO Auto-generated catch block
		}
		return this.urlSetNew;

	}

	public Parser getParser() {
		try {
			this.parser = new Parser(this.url);
			return this.parser;
		}
		catch (Exception e) {
			return null;
		}
	}

	public Set<String> getUrlSetOld() {
		return HtmlParser.urlSetOld;
	}

	public Set<String> getUrlSetNew() {
		return this.urlSetNew;
	}

	public static List<String> getTargetContent() {
		return HtmlParser.targetContent;
	}

	public static Set<String> getColumnUrl() {
		return HtmlParser.columnUrl;
	}

	public String getUrl() {
		return this.url;
	}

}

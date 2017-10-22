package coms572;

import org.htmlparser.*;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
public class WebServerHTMLParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Parser parser = new Parser("https://en.wikipedia.org/wiki/Main_Page");
			NodeList list = parser.parse(null);
			Node node = list.elementAt(0);
			System.out.println(list.toHtml());
			//String arr = list.toHtml().toString().replaceAll("=", " = ");
			//System.out.println(arr);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

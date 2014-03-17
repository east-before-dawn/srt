package tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
	private ArrayList<String> posWordList = new ArrayList<String>();
	private ArrayList<String> negWordList = new ArrayList<String>();
	
	public WordCounter(String posWordFile, String negWordFile) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(posWordFile), "utf-8"));
			String word;
			while((word = br.readLine()) != null) {
				posWordList.add(word);
			}
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(negWordFile), "utf-8"));
			while((word = br.readLine()) != null) {
				negWordList.add(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int countPosWord(String text) {
		Pattern p = Pattern.compile("[\u4E00-\u9FA5]{1,8}/[a-z]");
		Matcher m = p.matcher(text);
		int ret = 0;
		while(m.find()) {
			String find = m.group();
			if (posWordList.contains(find.substring(0, find.lastIndexOf('/')))) {
				ret++;
			}
		}
		return ret;
	}
	
	public int countNegWord(String text) {
		Pattern p = Pattern.compile("[\u4E00-\u9FA5]{1,8}/[a-z]");
		Matcher m = p.matcher(text);
		int ret = 0;
		while(m.find()) {
			String find = m.group();
			if (negWordList.contains(find.substring(0, find.lastIndexOf('/')))) {
				ret++;
			}
		}
		return ret;
	}
}

package tools;

import info.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class PersonalDataProcessor extends DataProcessor{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File data = new File("./data_json");
		String[] files = data.list();
		ArrayList<DataInfo> statusInfos, blogInfos, shareInfos;
		
		PersonalDataProcessor dp = new PersonalDataProcessor();
		
		for (String personFile : files ) {
			File personArff = new File("./data_arff/"+personFile);
			if (!personArff.exists())
				personArff.mkdir();
			
			statusInfos = dp.getSingleStatusInfos("./data_json/"+personFile);
			if (statusInfos != null)
				ARFFGenerator.generateARFF(statusInfos, "./data_arff/"+personFile+"/status.arff");
			
			blogInfos = dp.getSingleBlogInfos("./data_json/"+personFile);
			if (blogInfos != null)
				ARFFGenerator.generateARFF(blogInfos, "./data_arff/"+personFile+"/blog.arff");
			
			shareInfos = dp.getSingleShareInfos("./data_json/"+personFile);
			if (shareInfos != null)
				ARFFGenerator.generateARFF(shareInfos, "./data_arff/"+personFile+"/share.arff");
		}
	}
	
	private ArrayList<DataInfo> getSingleStatusInfos(String filename) {
		File statusfile = new File(filename+"/status.dat");
		System.out.println(statusfile.getPath());
		if (!statusfile.exists()) return null;

		ArrayList<DataInfo> statusinfos = new ArrayList<DataInfo>();
		JSONObject obj;
		
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(statusfile), "utf-8"));
			String text, temp;
			while ((temp = br.readLine())!=null) {
				SingleStatusInfo status = new SingleStatusInfo();
				obj = (JSONObject) parser.parse(temp);
				text = (String) obj.get("text");
				
				status.setShare(Integer.parseInt((String) obj.get("share")));				
				status.setCmt(Integer.parseInt((String) obj.get("comment")));
				
				if (text.contains("转自"))
					text = text.substring(0, text.indexOf("转自"));
				status.setTextlength(text.length());
				
				emoMatcher = emoPattern.matcher(text);				
				int emoticonsum = 0;
				while (emoMatcher.find()) {
					if (Emoticon.contains(emoMatcher.group()))
						emoticonsum += 1;
				}
				status.setEmoticon(emoticonsum);
				status.setTimestamp((String) obj.get("time"));
				
				// count words of different personality lexicons.
				String splittedText = splitter.splitString(text);
				wc = new WordCounter("./personality_lexicon/Dic_A+.txt", "./personality_lexicon/Dic_A-.txt");
				status.setAplus(wc.countPosWord(splittedText));
				status.setAminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_C+.txt", "./personality_lexicon/Dic_C-.txt");
				status.setCplus(wc.countPosWord(splittedText));
				status.setCminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_E+.txt", "./personality_lexicon/Dic_E-.txt");
				status.setEplus(wc.countPosWord(splittedText));
				status.setEminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_O+.txt", "./personality_lexicon/Dic_O-.txt");
				status.setOplus(wc.countPosWord(splittedText));
				status.setOminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_N+.txt", "./personality_lexicon/Dic_N-.txt");
				status.setNplus(wc.countPosWord(splittedText));
				status.setNminus(wc.countNegWord(splittedText));
				
				statusinfos.add(status);
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | ParseException  e) {
			e.printStackTrace();
		}
		
		return statusinfos;		
	}
	
	private ArrayList<DataInfo> getSingleBlogInfos(String filename) {
		File blogfile = new File(filename+"/blog.dat");
		System.out.println(blogfile.getPath());
		if (!blogfile.exists()) return null;
		
		ArrayList<DataInfo> bloginfos = new ArrayList<DataInfo>();
		JSONObject obj;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(blogfile), "utf-8"));
			String text, temp;
			while ((temp = br.readLine())!=null) {
				SingleBlogInfo blog = new SingleBlogInfo();
				obj = (JSONObject) parser.parse(temp);
				blog.setTitlelength(((String) obj.get("title")).length());
				blog.setTextlength(Integer.parseInt((String) obj.get("textlength")));
				text = (String) obj.get("text");
				
				int imgnum = 0;
				imgMatcher = imgPattern.matcher(text);
				while (imgMatcher.find())
					imgnum += 1;
				blog.setImg(imgnum);
				
				blog.setShare(Integer.parseInt((String) obj.get("share")));
				
				blog.setCmt(Integer.parseInt((String) obj.get("comment")));

				blog.setView(Integer.parseInt((String)obj.get("view")));
				
				if (Integer.parseInt((String)obj.get("access"))==0)
					blog.setIspublic(1);
				else blog.setIspublic(0);
				
				blog.setTimestamp(((String) obj.get("time")).substring(0, 19));
				
				// count words of different personality lexicons.
				String splittedText = splitter.splitString(text);
				wc = new WordCounter("./personality_lexicon/Dic_A+.txt", "./personality_lexicon/Dic_A-.txt");
				blog.setAplus(wc.countPosWord(splittedText));
				blog.setAminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_C+.txt", "./personality_lexicon/Dic_C-.txt");
				blog.setCplus(wc.countPosWord(splittedText));
				blog.setCminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_E+.txt", "./personality_lexicon/Dic_E-.txt");
				blog.setEplus(wc.countPosWord(splittedText));
				blog.setEminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_O+.txt", "./personality_lexicon/Dic_O-.txt");
				blog.setOplus(wc.countPosWord(splittedText));
				blog.setOminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_N+.txt", "./personality_lexicon/Dic_N-.txt");
				blog.setNplus(wc.countPosWord(splittedText));
				blog.setNminus(wc.countNegWord(splittedText));
				
				bloginfos.add(blog);
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return bloginfos;		
	}
	
	private ArrayList<DataInfo> getSingleShareInfos(String filename) {
		File sharefile = new File(filename+"/share.dat");
		System.out.println(sharefile.getPath());
		if (!sharefile.exists()) return null;
		
		ArrayList<DataInfo> shareinfos = new ArrayList<DataInfo>();
		JSONObject obj;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(sharefile), "utf-8"));
			String text, temp, url;
			while ((temp = br.readLine())!=null) {
				SingleShareInfo share = new SingleShareInfo();
				obj = (JSONObject) parser.parse(temp);
				text = (String) obj.get("text");
				url = (String) obj.get("url");
				
				share.setCmt(Integer.parseInt(((String) obj.get("comment"))));
				share.setTimestamp((String) obj.get("time"));
				
				if (url.contains("photo")) {
					share.setIsphoto(1);
					share.setIsblog(0);
					share.setIsvideo(0);
					share.setIsother(0);
				} else if (url.contains("blog")) {
					share.setIsphoto(0);
					share.setIsblog(1);
					share.setIsvideo(0);
					share.setIsother(0);
				} else if (url.contains("youku")||url.contains("www.56.com")
						||url.contains("tudou")||url.contains("video")||
						url.contains("youtube")||url.contains("acfun")||
						url.contains("iqiyi")) {
					share.setIsphoto(0);
					share.setIsblog(0);
					share.setIsvideo(1);
					share.setIsother(0);
				} else {
					share.setIsphoto(0);
					share.setIsblog(0);
					share.setIsvideo(0);
					share.setIsother(1);
				}
				
				// count words of different personality lexicons.
				String splittedText = splitter.splitString(text);
				wc = new WordCounter("./personality_lexicon/Dic_A+.txt", "./personality_lexicon/Dic_A-.txt");
				share.setAplus(wc.countPosWord(splittedText));
				share.setAminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_C+.txt", "./personality_lexicon/Dic_C-.txt");
				share.setCplus(wc.countPosWord(splittedText));
				share.setCminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_E+.txt", "./personality_lexicon/Dic_E-.txt");
				share.setEplus(wc.countPosWord(splittedText));
				share.setEminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_O+.txt", "./personality_lexicon/Dic_O-.txt");
				share.setOplus(wc.countPosWord(splittedText));
				share.setOminus(wc.countNegWord(splittedText));
				wc = new WordCounter("./personality_lexicon/Dic_N+.txt", "./personality_lexicon/Dic_N-.txt");
				share.setNplus(wc.countPosWord(splittedText));
				share.setNminus(wc.countNegWord(splittedText));
				
				shareinfos.add(share);
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return shareinfos;		
	}
}

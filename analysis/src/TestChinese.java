import info.ProfileInfo;
import info.StatusInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class TestChinese {
	static BufferedReader br;
	static JSONParser parser = new JSONParser(); 
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static Pattern chiPattern = Pattern.compile("[\u4e00-\u9fa5]");
	static Pattern emoPattern = Pattern.compile("\\([0-9a-z\u4e00-\u9fa5]+\\)");
	static Matcher emoMatcher, chiMatcher;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//StatusInfo statusInfo = getStatusInfo("220956324");
		//System.out.println(statusInfo.toString());
		ProfileInfo profileInfo = getProfileInfo("261570854");
		System.out.println(profileInfo.toString());
	}
	
	public static StatusInfo getStatusInfo(String filename) {
		File status = new File("./data_json/"+filename+"/status.dat");
		System.out.println(status.getPath());
		if (!status.exists()) return new StatusInfo();
		int num = 0;
		int sharesum = 0;
		int commentsum = 0;
		int timediff = 0;
		int textlong = 0;
		int emoticonsum = 0;
		JSONObject obj;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(status), "utf-8"));
			String content, temp;
			Date date, preDate = null;
			while ((temp = br.readLine())!=null) {
				obj = (JSONObject) parser.parse(temp);
				content = (String) obj.get("text");
				date = df.parse((String) obj.get("time"));
				if (num > 0) {
					timediff += (preDate.getTime()-date.getTime())/(1000*60);
				}
				preDate = df.parse((String) obj.get("time"));
				sharesum += Integer.parseInt((String) obj.get("share"));
				commentsum += Integer.parseInt((String) obj.get("comment"));
				//仅保留本人言论
				if (content.contains("转自"))
					content = content.substring(0, content.indexOf("转自"));
				textlong += content.length();
				emoMatcher = emoPattern.matcher(content);
				while (emoMatcher.find()) {
					if (Emoticon.contains(emoMatcher.group()))
						emoticonsum += 1;
				}
				num ++;
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		StatusInfo statusInfo = new StatusInfo();
		if (num > 0) {
			statusInfo.setNum(num);
			statusInfo.setAvgshare((double)sharesum/num);
			statusInfo.setAvgcmt((double)commentsum/num);
			statusInfo.setTimediff((double)timediff/(Math.max(num-1, 1)));
			statusInfo.setTextlength((double)textlong/num);
			statusInfo.setAvgemoticon((double)emoticonsum/num);
		} else {
			statusInfo.setNum(0);
		}
		return statusInfo;
	}
	
	public static ProfileInfo getProfileInfo(String filename) {
		File profile = new File("./data_json/"+filename+"/profile.dat");
		System.out.println(profile.getPath());
		if (!profile.exists()) return new ProfileInfo();
		ProfileInfo profileInfo = new ProfileInfo();
		int hanzinum = 0;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(profile), "utf-8"));
			JSONObject obj = (JSONObject) parser.parse(br.readLine());
			String name = (String) obj.get("name");
			chiMatcher = chiPattern.matcher(name);
			while (chiMatcher.find()) hanzinum += 1;
			
			profileInfo.setHanziratio((double)hanzinum/name.length());
			profileInfo.setStar((int)(long) obj.get("star"));
			profileInfo.setBasicinfo(((JSONObject) obj.get("basicInformation")).size());
			profileInfo.setEducation(((JSONArray) obj.get("education")).size());
			if ((JSONArray) obj.get("work") != null)
				profileInfo.setWorknum(((JSONArray) obj.get("work")).size());
			profileInfo.setLikenum(((JSONArray) obj.get("like")).size());
			profileInfo.setAppcount((int)(long) obj.get("appCount"));
			profileInfo.setVisitorcount((int)(long) obj.get("visitorCount"));
			profileInfo.setPagecount((int)(long) obj.get("pageCount"));
			profileInfo.setZhancount((int)(long) obj.get("zhanCount"));
			profileInfo.setMusiccount((int)(long) obj.get("musicCount"));
			profileInfo.setMoviecount((int)(long) obj.get("movieCount"));
			profileInfo.setFriendcount((int)(long) obj.get("friendCount"));
			//profileInfo.setFriendDensity();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		return profileInfo;
	}

}

import info.BlogInfo;
import info.ProfileInfo;
import info.ShareInfo;
import info.StatusInfo;
import info.UserSample;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataProcessor {
	BufferedReader br;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Pattern emoPattern = Pattern.compile("\\([0-9a-z\u4e00-\u9fa5]+\\)");
	Pattern imgPattern = Pattern.compile("(img)|(gif)");
	Pattern chiPattern = Pattern.compile("[\u4e00-\u9fa5]");
	Pattern doublePattern = Pattern.compile("\\d\\.(\\d)+");
	Matcher emoMatcher, imgMatcher, chiMatcher;
	JSONParser parser = new JSONParser();
	
	public static void main(String[] args) {
		File data = new File("./data_json");
		String[] files = data.list();
		StatusInfo statusInfo;
		BlogInfo blogInfo;
		ShareInfo shareInfo;
		ProfileInfo profileInfo;
		DataProcessor dp = new DataProcessor();
		ArrayList<UserSample> samples = new ArrayList<UserSample>();
		BufferedReader reader;
		
		for (String personFile : files ) {
			UserSample sample = new UserSample();
			statusInfo = dp.getStatusInfo(personFile);
			if (statusInfo!=null) System.out.println(statusInfo.toString());
			sample.addInfo(0, statusInfo);
			blogInfo = dp.getBlogInfo(personFile);
			if (blogInfo!=null) System.out.println(blogInfo.toString());
			sample.addInfo(1, blogInfo);
			shareInfo = dp.getShareInfo(personFile);
			if (shareInfo!=null) System.out.println(shareInfo.toString());
			sample.addInfo(2, shareInfo);
			profileInfo = dp.getProfileInfo(personFile);
			if (profileInfo!=null) System.out.println(profileInfo.toString());
			sample.addInfo(3, profileInfo);
			try {
				reader = new BufferedReader(new InputStreamReader(
						new FileInputStream("./result/"+personFile+".txt")));
				String result = reader.readLine();
				Matcher resultMatcher = dp.doublePattern.matcher(result);
				double[] scores = new double[5];
				int i = 0;
				while (resultMatcher.find()) {
					scores[i] = Double.parseDouble(resultMatcher.group());
					i++;
				}
				sample.setBigfive(scores[0], scores[1], scores[2], scores[3], scores[4]);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			samples.add(sample);
		}
		ARFFGenerator.generateARFF(samples);		
	}
	
	public StatusInfo getStatusInfo(String filename) {
		File status = new File("./data_json/"+filename+"/status.dat");
		System.out.println(status.getPath());
		if (!status.exists()) return new StatusInfo();
		int num = 0;
		int sharesum = 0, maxshare = 0;
		int commentsum = 0, maxcmt = 0;
		int timediff = 0, mintimediff = Integer.MAX_VALUE;
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
					long diff = (preDate.getTime()-date.getTime())/1000;
					if (mintimediff > diff)
						mintimediff = (int) diff;
					timediff += diff;
				}
				preDate = df.parse((String) obj.get("time"));
				
				int share = Integer.parseInt((String) obj.get("share"));
				if (maxshare < share)
					maxshare = share;
				sharesum += share;
				
				int cmt = Integer.parseInt((String) obj.get("comment"));
				if (maxcmt < cmt)
					maxcmt = cmt;
				commentsum += cmt;
				
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
		} catch (IOException | ParseException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		StatusInfo statusInfo = new StatusInfo();
		if (num > 0) {
			statusInfo.setNum(num);
			statusInfo.setAvgshare((double)sharesum/num);
			statusInfo.setMaxshare(maxshare);
			statusInfo.setAvgcmt((double)commentsum/num);
			statusInfo.setMaxcmt(maxcmt);
			statusInfo.setTimediff((double)timediff/(Math.max(num-1, 1)));
			statusInfo.setMintimediff(mintimediff);
			statusInfo.setTextlength((double)textlong/num);
			statusInfo.setAvgemoticon((double)emoticonsum/num);
		} else {
			statusInfo.setNum(0);
		}
		return statusInfo;
	}
	
	public BlogInfo getBlogInfo(String filename) {
		File blog = new File("./data_json/"+filename+"/blog.dat");
		System.out.println(blog.getPath());
		if (!blog.exists()) return new BlogInfo();
		int num = 0;
		int sharesum = 0, maxshare = 0;
		int commentsum = 0, maxcmt = 0;
		int viewsum = 0, maxview = 0;
		int timediff = 0, mintimediff = Integer.MAX_VALUE;
		int titlelong = 0;
		int textlong = 0;
		int publicnum = 0;
		int imgnum = 0, maximg = 0;
		JSONObject obj;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(blog), "utf-8"));
			String content, temp;
			Date date, preDate = null;
			while ((temp = br.readLine())!=null) {
				obj = (JSONObject) parser.parse(temp);
				titlelong += ((String) obj.get("title")).length();
				textlong += Integer.parseInt((String) obj.get("textlength"));
				content = (String) obj.get("text");
				
				//统计日志中的图片资源
				int curimg = 0;
				imgMatcher = imgPattern.matcher(content);
				while (imgMatcher.find())
					curimg += 1;
				if (maximg < curimg)
					maximg = curimg;
				imgnum += curimg;
				
				String time = (String) obj.get("time");
				date = df.parse(time.substring(0, time.length()-4));
				if (num > 0) {
					long diff = (preDate.getTime()-date.getTime())/1000;
					if (mintimediff > diff)
						mintimediff = (int) diff;
					timediff += diff;
				}
				preDate = df.parse(time.substring(0, time.length()-4));
				
				int share = Integer.parseInt((String) obj.get("share"));
				if (maxshare < share)
					maxshare = share;
				sharesum += share;
				
				int cmt = Integer.parseInt((String) obj.get("comment"));
				if (maxcmt < cmt)
					maxcmt = cmt;
				commentsum += cmt;
				
				if (Integer.parseInt((String)obj.get("access"))==0)
					publicnum += 1;
				
				int view = Integer.parseInt((String)obj.get("view"));
				if (maxview < view)
					maxview = view;
				viewsum += view;
				num += 1;
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | ParseException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		BlogInfo blogInfo = new BlogInfo();
		if (num > 0) {
			blogInfo.setNum(num);
			blogInfo.setAvgshare((double)sharesum/num);
			blogInfo.setMaxshare(maxshare);
			blogInfo.setAvgcmt((double)commentsum/num);
			blogInfo.setMaxcmt(maxcmt);
			blogInfo.setTimediff((double)timediff/(Math.max(num-1, 1)));
			blogInfo.setMintimediff(mintimediff);
			blogInfo.setTextlength((double)textlong/num);
			blogInfo.setAvgview((double)viewsum/num);
			blogInfo.setMaxview(maxview);
			blogInfo.setTitlelength((double)titlelong/num);
			blogInfo.setPublicratio((double)publicnum/num);
			blogInfo.setAvgimg((double)imgnum/num);
			blogInfo.setMaximg(maximg);
		} else {
			blogInfo.setNum(0);
		}
		return blogInfo;
	}
	
	public ShareInfo getShareInfo(String filename) {
		File share = new File("./data_json/"+filename+"/share.dat");
		System.out.println(share.getPath());
		if (!share.exists()) return new ShareInfo();
		int num = 0;
		int photosum = 0;
		int blogsum = 0;
		int videosum = 0;
		int othersum = 0;
		int commentsum = 0, maxcmt = 0;
		int timediff = 0, mintimediff = Integer.MAX_VALUE;
		JSONObject obj;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(share), "utf-8"));
			String content, temp, url;
			Date date, preDate = null;
			while ((temp = br.readLine())!=null) {
				obj = (JSONObject) parser.parse(temp);
				content = (String) obj.get("text");
				url = (String) obj.get("url");
				
				int cmt = Integer.parseInt(((String) obj.get("comment")));
				if (maxcmt < cmt)
					maxcmt = cmt;
				commentsum += cmt;
				
				if (url.contains("photo")) photosum += 1;
				else if (url.contains("blog")) blogsum += 1;
				else if (url.contains("youku")||url.contains("www.56.com")
						||url.contains("tudou")||url.contains("video")||
						url.contains("youtube")||url.contains("acfun")||
						url.contains("iqiyi")) videosum += 1;
				else othersum += 1;

				date = df.parse((String)obj.get("time"));
				if (num > 0) {
					long diff = (preDate.getTime()-date.getTime())/1000;
					if(mintimediff > diff)
						mintimediff = (int) diff;
					timediff += diff;
				}
				preDate = df.parse((String)obj.get("time"));
				num ++;
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | ParseException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		ShareInfo shareInfo = new ShareInfo();
		if (num > 0) {
			shareInfo.setNum(num);
			shareInfo.setAvgcmt((double)commentsum/num);
			shareInfo.setMaxcmt(maxcmt);
			shareInfo.setTimediff((double)timediff/(Math.max(num-1, 1)));
			shareInfo.setMintimediff(mintimediff);
			shareInfo.setPhotoratio((double)photosum/num);
			shareInfo.setBlogratio((double)blogsum/num);
			shareInfo.setVideoratio((double)videosum/num);
			shareInfo.setOtherratio((double)othersum/num);
		} else {
			shareInfo.setNum(0);
		}
		return shareInfo;
	}
	
	public ProfileInfo getProfileInfo(String filename) {
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
			if ((JSONObject) obj.get("basicInformation") != null)
				profileInfo.setBasicinfo(((JSONObject) obj.get("basicInformation")).size());
			if ((JSONArray) obj.get("education") != null)
				profileInfo.setEducation(((JSONArray) obj.get("education")).size());
			if ((JSONArray) obj.get("work") != null)
				profileInfo.setWorknum(((JSONArray) obj.get("work")).size());
			if ((JSONArray) obj.get("like") != null)
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

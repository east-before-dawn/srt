import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessor {
	BufferedReader br;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Pattern emoPattern = Pattern.compile("\\([0-9a-z\u4e00-\u9fa5]+\\)");
	Pattern imgPattern = Pattern.compile("(img)|(gif)");
	Pattern chiPattern = Pattern.compile("[\u4e00-\u9fa5]");
	Matcher emoMatcher, imgMatcher, chiMatcher;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File data = new File("./data");
		String[] files = data.list();
		StatusInfo statusInfo;
		BlogInfo blogInfo;
		ShareInfo shareInfo;
		ProfileInfo profileInfo;
		DataProcessor dp = new DataProcessor();
		for (String personFile : files ) {
			statusInfo = dp.getStatusInfo(personFile);
			if (statusInfo!=null) System.out.println(statusInfo.toString());
			blogInfo = dp.getBlogInfo(personFile);
			if (blogInfo!=null) System.out.println(blogInfo.toString());
			shareInfo = dp.getShareInfo(personFile);
			if (shareInfo!=null) System.out.println(shareInfo.toString());
			profileInfo = dp.getProfileInfo(personFile);
			if (profileInfo!=null) System.out.println(profileInfo.toString());
		}
	}
	
	public StatusInfo getStatusInfo(String filename) {
		File status = new File("./data/"+filename+"/status.dat");
		System.out.println(status.getPath());
		if (!status.exists()) return null;
		int num = 0;
		int sharesum = 0;
		int commentsum = 0;
		int timediff = 0;
		int textlong = 0;
		int emoticonsum = 0;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(status), "utf-8"));
			String content = "", temp;
			Date date, preDate = null;
			while ((temp = br.readLine())!=null) {
				if (!Pattern.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}",	temp)) {
					content += temp;
					continue;
				}
				date = df.parse(temp);
				if (num > 0) {
					timediff += (preDate.getTime()-date.getTime())/(1000*60);
				}
				preDate = df.parse(temp);
				sharesum += Integer.parseInt(br.readLine());
				commentsum += Integer.parseInt(br.readLine());
				//仅保留本人言论
				if (content.contains("转自"))
					content = content.substring(0, content.indexOf("转自"));
				textlong += content.length();
				emoMatcher = emoPattern.matcher(content);
				while (emoMatcher.find()) {
					if (Emoticon.contains(emoMatcher.group()))
						emoticonsum += 1;
				}
				num += 1;
				br.readLine();
				content = "";
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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
	
	public BlogInfo getBlogInfo(String filename) {
		File blog = new File("./data/"+filename+"/blog.dat");
		System.out.println(blog.getPath());
		if (!blog.exists()) return null;
		int num = 0;
		int sharesum = 0;
		int commentsum = 0;
		int viewsum = 0;
		int timediff = 0;
		int titlelong = 0;
		int textlong = 0;
		int publicnum = 0;
		int imgnum = 0;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(blog), "utf-8"));
			String content = "", temp;
			Date date, preDate = null;
			while ((temp = br.readLine())!=null) {
				titlelong += temp.length();
				textlong += Integer.parseInt(br.readLine());
				content = "";
				temp = br.readLine();
				while (temp.length()!=1 || !Character.isDigit(temp.charAt(0))) {
					content += temp;
					temp = br.readLine();
				}
				//统计日志中的图片资源
				imgMatcher = imgPattern.matcher(content);
				while (imgMatcher.find()) {
					imgnum += 1;
				}
				temp = br.readLine();
				date = df.parse(temp.substring(0, temp.length()-4));
				if (num > 0) {
					timediff += (preDate.getTime()-date.getTime())/(1000*60);
				}
				preDate = df.parse(temp.substring(0, temp.length()-4));
				sharesum += Integer.parseInt(br.readLine());
				if (Integer.parseInt(br.readLine())==0)
					publicnum += 1;
				viewsum += Integer.parseInt(br.readLine());
				commentsum += Integer.parseInt(br.readLine());
				num += 1;
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		BlogInfo blogInfo = new BlogInfo();
		if (num > 0) {
			blogInfo.setNum(num);
			blogInfo.setAvgshare((double)sharesum/num);
			blogInfo.setAvgcmt((double)commentsum/num);
			blogInfo.setTimediff((double)timediff/(Math.max(num-1, 1)));
			blogInfo.setTextlength((double)textlong/num);
			blogInfo.setAvgview((double)viewsum/num);
			blogInfo.setTitlelength((double)titlelong/num);
			blogInfo.setPublicratio((double)publicnum/num);
			blogInfo.setAvgimg((double)imgnum/num);
		} else {
			blogInfo.setNum(0);
		}
		return blogInfo;
	}
	
	public ShareInfo getShareInfo(String filename) {
		File share = new File("./data/"+filename+"/share.dat");
		System.out.println(share.getPath());
		if (!share.exists()) return null;
		int num = 0;
		int photosum = 0;
		int blogsum = 0;
		int videosum = 0;
		int othersum = 0;
		int commentsum = 0;
		int timediff = 0;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(share), "utf-8"));
			String content = "", temp, url = "";
			Date date, preDate = null;
			while ((temp = br.readLine())!=null) {
				if (content == ""||!Pattern.matches("[0-9]{1,3}", temp)) {
					content += url;
					url = temp;
					continue;
				}
				commentsum += Integer.parseInt(temp);
				if (url.contains("photo")) photosum += 1;
				else if (url.contains("blog")) blogsum += 1;
				else if (url.contains("youku")||url.contains("www.56.com")
						||url.contains("tudou")||url.contains("video")||
						url.contains("youtube")||url.contains("acfun")||
						url.contains("iqiyi")) videosum += 1;
				else othersum += 1;

				temp = br.readLine();
				date = df.parse(temp);
				if (num > 0) {
					timediff += (preDate.getTime()-date.getTime())/(1000*60);
				}
				preDate = df.parse(temp);
				num += 1;
				content = "";
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ShareInfo shareInfo = new ShareInfo();
		if (num > 0) {
			shareInfo.setNum(num);
			shareInfo.setAvgcmt((double)commentsum/num);
			shareInfo.setTimediff((double)timediff/(Math.max(num-1, 1)));
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
		File profile = new File("./data/"+filename+"/profile.dat");
		System.out.println(profile.getPath());
		if (!profile.exists()) return null;
		ProfileInfo profileInfo = new ProfileInfo();
		int hanzinum = 0;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(profile), "utf-8"));
			String name = br.readLine();
			chiMatcher = chiPattern.matcher(name);
			while (chiMatcher.find()) hanzinum += 1;
			
			profileInfo.setHanziratio((double)hanzinum/name.length());
			profileInfo.setStar(Integer.parseInt(br.readLine()));
			profileInfo.setBasicratio(Double.parseDouble(br.readLine()));
			profileInfo.setEduratio(Double.parseDouble(br.readLine()));
			profileInfo.setWorknum(Integer.parseInt(br.readLine()));
			profileInfo.setLikenum(Integer.parseInt(br.readLine()));
			profileInfo.setAppcount(Integer.parseInt(br.readLine()));
			profileInfo.setVisitorcount(Integer.parseInt(br.readLine()));
			profileInfo.setPagecount(Integer.parseInt(br.readLine()));
			profileInfo.setZhancount(Integer.parseInt(br.readLine()));
			profileInfo.setMusiccount(Integer.parseInt(br.readLine()));
			profileInfo.setMoviecount(Integer.parseInt(br.readLine()));
			profileInfo.setFriendcount(Integer.parseInt(br.readLine()));
			profileInfo.setFriendDensity(Double.parseDouble(br.readLine()));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return profileInfo;
	}

}

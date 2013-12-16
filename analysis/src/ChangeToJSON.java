import info.BlogInfo;
import info.ProfileInfo;
import info.ShareInfo;
import info.StatusInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;


public class ChangeToJSON {

	BufferedReader br;
	BufferedWriter bw;
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
		ChangeToJSON changer = new ChangeToJSON();
		for (String personFile : files ) {
			changer.getStatusInfo(personFile);
			changer.getBlogInfo(personFile);
			changer.getShareInfo(personFile);
			changer.getProfileInfo(personFile);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getStatusInfo(String filename) {
		File status = new File("./data/"+filename+"/status.dat");
		File path = new File("./data_jason/"+filename);
		if (!path.exists()) path.mkdirs();
		File out = new File("./data_jason/"+filename+"/status.dat");
		
		System.out.println(status.getPath());
		if (!status.exists()) return;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(status), "utf-8"));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(out, true), "utf-8"));
			JSONObject obj = new JSONObject();
			String content = "", temp;
			while ((temp = br.readLine())!=null) {
				if (!Pattern.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}",	temp)) {
					content += temp;
					continue;
				}
				obj.put("text", content);
				obj.put("time", temp);
				obj.put("share", br.readLine());
				obj.put("comment", br.readLine());
				obj.put("sharedID", br.readLine());
				content = "";
				bw.write(obj.toJSONString());
				bw.newLine();
			}
			br.close();
			bw.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getBlogInfo(String filename) {
		File blog = new File("./data/"+filename+"/blog.dat");
		File path = new File("./data_jason/"+filename);
		if (!path.exists()) path.mkdirs();
		File out = new File("./data_jason/"+filename+"/blog.dat");
		
		System.out.println(blog.getPath());
		if (!blog.exists()) return;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(blog), "utf-8"));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(out, true), "utf-8"));
			JSONObject obj = new JSONObject();
			String content = "", temp;
			while ((temp = br.readLine())!=null) {
				obj.put("title", temp);
				obj.put("textlength", br.readLine());
				content = "";
				temp = br.readLine();
				while (temp.length()!=1 || !Character.isDigit(temp.charAt(0))) {
					content += temp;
					temp = br.readLine();
				}
				obj.put("text", content);
				obj.put("type", temp);
				obj.put("time", br.readLine());
				obj.put("share", br.readLine());
				obj.put("access", br.readLine());
				obj.put("view", br.readLine());
				obj.put("comment", br.readLine());
				bw.write(obj.toJSONString());
				bw.newLine();
			}
			br.close();
			bw.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getShareInfo(String filename) {
		File share = new File("./data/"+filename+"/share.dat");
		File path = new File("./data_jason/"+filename);
		if (!path.exists()) path.mkdirs();
		File out = new File("./data_jason/"+filename+"/share.dat");
		
		System.out.println(share.getPath());
		if (!share.exists()) return;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(share), "utf-8"));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(out, true), "utf-8"));
			JSONObject obj = new JSONObject();
			String content = "", temp, url = "";
			if (filename.contains("test")) {
				System.out.println();
			}
			while ((temp = br.readLine())!=null) {
				if (content == ""||!Pattern.matches("[0-9]{1,3}", temp)) {
					content += url;
					url = temp;
					continue;
				}
				obj.put("text", content);
				obj.put("comment", temp);
				obj.put("url", url);
				obj.put("time", br.readLine());
				content = "";
				bw.write(obj.toJSONString());
				bw.newLine();
			}
			br.close();
			bw.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getProfileInfo(String filename) {
		File profile = new File("./data/"+filename+"/profile.dat");
		File path = new File("./data_jason/"+filename);
		if (!path.exists()) path.mkdirs();
		File out = new File("./data_jason/"+filename+"/profile.dat");
		
		System.out.println(profile.getPath());
		if (!profile.exists()) return;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(profile), "utf-8"));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(out, true), "utf-8"));
			JSONObject obj = new JSONObject();
			obj.put("name", br.readLine());
			obj.put("star", br.readLine());
			obj.put("basicratio", br.readLine());
			obj.put("eduratio", br.readLine());
			obj.put("worknum", br.readLine());
			obj.put("likenum", br.readLine());
			obj.put("appcount", br.readLine());
			obj.put("visitorcount", br.readLine());
			obj.put("pagecount", br.readLine());
			obj.put("zhancount", br.readLine());
			obj.put("musiccount", br.readLine());
			obj.put("moviecount", br.readLine());
			obj.put("friendcount", br.readLine());
			obj.put("frienddensity", br.readLine());
			
			bw.write(obj.toJSONString());
			bw.newLine();
			br.close();
			bw.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

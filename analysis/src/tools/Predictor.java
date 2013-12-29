package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import weka.classifiers.rules.M5Rules;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import info.*;


public class Predictor {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		DataProcessor dp = new DataProcessor();
		if (args.length < 1) return;
		String id = args[0];
		System.out.println(args[0]);
		StatusInfo statusInfo = dp.getStatusInfo(id);
		if (statusInfo!=null) System.out.println(statusInfo.toString());
		BlogInfo blogInfo = dp.getBlogInfo(id);
		if (blogInfo!=null) System.out.println(blogInfo.toString());
		ShareInfo shareInfo = dp.getShareInfo(id);
		if (shareInfo!=null) System.out.println(shareInfo.toString());
		ProfileInfo profileInfo = dp.getProfileInfo(id);
		if (profileInfo!=null) System.out.println(profileInfo.toString());
		photoInfo PhotoInfo = dp.getPhotoInfo(id);
        if (PhotoInfo!=null) System.out.println(PhotoInfo.toString());
        
		try {
		ArffLoader loader = new ArffLoader();
		loader.setSource(new File("./agreeableness.arff"));
		Instances data = loader.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
		M5Rules clsf = new M5Rules();
		
		double[] values = new double[data.numAttributes()];
		values[0] = statusInfo.getNum();
		values[1] = statusInfo.getAvgshare();
		values[2] = statusInfo.getMaxshare();
		values[3] = statusInfo.getAvgcmt();
		values[4] = statusInfo.getMaxcmt();
		values[5] = statusInfo.getTimediff();
		values[6] = statusInfo.getMintimediff();
		values[7] = statusInfo.getTextlength();
		values[8] = statusInfo.getAvgemoticon();
		values[9] = blogInfo.getNum();
		values[10] = blogInfo.getAvgshare();
		values[11] = blogInfo.getMaxshare();
		values[12] = blogInfo.getAvgcmt();
		values[13] = blogInfo.getMaxcmt();
		values[14] = blogInfo.getAvgview();
		values[15] = blogInfo.getMaxview();
		values[16] = blogInfo.getTimediff();
		values[17] = blogInfo.getMintimediff();
		values[18] = blogInfo.geTitlelength();
		values[19] = blogInfo.geTextlength();
		values[20] = blogInfo.getPublicratio();
		values[21] = blogInfo.getAvgimg();
		values[22] = blogInfo.getMaximg();
		values[23] = shareInfo.getNum();
		values[24] = shareInfo.getPhotoratio();
		values[25] = shareInfo.getBLogratio();
		values[26] = shareInfo.getVideoratio();
		values[27] = shareInfo.getOtherratio();
		values[28] = shareInfo.getAvgcmt();
		values[29] = shareInfo.getMaxcmt();
		values[30] = shareInfo.getTimediff();
		values[31] = shareInfo.getMintimediff();
		values[32] = profileInfo.getHanziratio();
		values[33] = profileInfo.getStar();
		values[34] = profileInfo.getBasicinfo();
		values[35] = profileInfo.getEducation();
		values[36] = profileInfo.getWorknum();
		values[37] = profileInfo.getLikenum();
		values[38] = profileInfo.getAppcount();
		values[39] = profileInfo.getVisitorcount();
		values[40] = profileInfo.getPagecount();
		values[41] = profileInfo.getZhancount();
		values[42] = profileInfo.getMusiccount();
		values[43] = profileInfo.getMoviecount();
		values[44] = profileInfo.getFriendcount();
		values[45] = profileInfo.getFriendDensity();
		values[46] = PhotoInfo.getNum();
		values[47] = PhotoInfo.getAlbumNum();
		values[48] = PhotoInfo.getAvgPhotoNum();
		values[49] = PhotoInfo.getFaceNum();
		values[50] = PhotoInfo.getAvgFaceNum();
		values[51] = PhotoInfo.getF1();
		values[52] = PhotoInfo.getF2();
		values[53] = PhotoInfo.getF3();
		double colorf[] = PhotoInfo.getAvgColorInfo();
		int k = 54;
		for (double c:colorf) {
		  values[k] = c;
		  k++;
		}
        
		
		Instance ins = new Instance(1.0, values);
		
		clsf.buildClassifier(data);
		double AScore = clsf.classifyInstance(ins);
		System.out.println(AScore);
		
		loader.setSource(new File("./conscientiousness.arff"));
		data = loader.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
		clsf.buildClassifier(data);
		double CScore = clsf.classifyInstance(ins);
		System.out.println(CScore);
		
		loader.setSource(new File("./extraversion.arff"));
		data = loader.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
		clsf.buildClassifier(data);
		double EScore = clsf.classifyInstance(ins);
		System.out.println(EScore);
		
		loader.setSource(new File("./openness.arff"));
		data = loader.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
		clsf.buildClassifier(data);
		double OScore = clsf.classifyInstance(ins);
		System.out.println(OScore);
		
		loader.setSource(new File("./neuroticism.arff"));
		data = loader.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
		clsf.buildClassifier(data);
		double NScore = clsf.classifyInstance(ins);
		System.out.println(NScore);
		
		JSONObject obj = new JSONObject();
		obj.put("A", AScore);
		obj.put("C", CScore);
		obj.put("E", EScore);
		obj.put("O", OScore);
		obj.put("N", NScore);
		
		File path = new File("./predict_result");
		if (!path.exists()) path.mkdir();
		File dataJson = new File(id);
		String userToken = dataJson.getName();
		FileWriter writer = new FileWriter(new File("./predict_result/"+userToken+".txt"));
		writer.write(obj.toJSONString());
		writer.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

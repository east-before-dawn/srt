import info.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ARFFGenerator {
	static FileWriter[] bw = new FileWriter[5];
	
	public static void generateARFF(ArrayList<UserSample> samples) {
		try {
			bw[0] = new FileWriter("extraversion.arff", false);
			bw[1] = new FileWriter("neuroticism.arff", false);
			bw[2] = new FileWriter("openness.arff", false);
			bw[3] = new FileWriter("agreeableness.arff", false);
			bw[4] = new FileWriter("conscientiousness.arff", false);
			bw[0].write("@relation 'extraversion'\n");
			bw[1].write("@relation 'neuroticism'\n");
			bw[2].write("@relation 'openness'\n");
			bw[3].write("@relation 'agreeableness'\n");
			bw[4].write("@relation 'conscientiousness'\n");
			UserSample sample0 = samples.get(0);
			for (DataInfo info : sample0.getInfoList()) {
				for (FileWriter writer : bw) {
					writer.write(info.getAttribute());
				}
			}
			for (FileWriter writer : bw) {
				writer.write("@attribute score numeric\n");
				writer.write("@data\n");
			}
			
			for (UserSample sample : samples) {
				for (DataInfo info : sample.getInfoList()) {
					for (FileWriter writer : bw)
						writer.write(info.getData()+",");
				}
				bw[0].write(String.valueOf(sample.getEScore()) + "\n");
				bw[1].write(String.valueOf(sample.getNScore()) + "\n");
				bw[2].write(String.valueOf(sample.getOScore()) + "\n");
				bw[3].write(String.valueOf(sample.getAScore()) + "\n");
				bw[4].write(String.valueOf(sample.getCScore()) + "\n");
			}
			for (FileWriter writer : bw) {
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

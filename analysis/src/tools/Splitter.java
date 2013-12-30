package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Split Chinese text with preposition marks
 * @author wangyc
 *
 */
public class Splitter {
	Socket client;
	
	public String splitString(String input) {
		try {			
			client = new Socket("118.192.65.79", 12131);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(client
					.getOutputStream(), "UTF-8"));
			BufferedReader in = new BufferedReader(new InputStreamReader(client
					.getInputStream(), "UTF-8"));

			out.println(input);
			out.println("#END#");
			out.flush();
			StringBuilder sb = new StringBuilder();
			String line;
			while (true) {
				line = in.readLine();
				if (line == null || line.equals("#END#"))
					break;
				sb.append(line);
				sb.append('\n');
			}
			// finished reading--- close in stream
			in.close();
			out.close();
			return sb.toString().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

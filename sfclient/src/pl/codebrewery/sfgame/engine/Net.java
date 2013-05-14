package pl.codebrewery.sfgame.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import pl.codebrewery.sfgame.model.Game;

public class Net {

	private static final String ENDPOINT = "http://s1.sfgame.pl/request.php?req=%s%s%s&rnd=%s";
	//http://s1.sfgame.pl/request.php?req=Y5K2B4K42547d64A6066442485032S70503105577&random=%2&rnd=10469360351367927091655
	
	private String getRandom() {
		return String.format("%d%d", Math.round(Math.random() * 0x77359400), Game.I.getGameTime() / 1000);
	}
	
	public Response call(int a, String s, String... p) {
		Request r = new Request(a, s, p);
		return call(r);
	}
	
	public Response call(Request r) {
		String urlString = String.format(ENDPOINT, r.getSessionId(), r.getActionStr(), r.getAllParams(), getRandom());
//		System.out.println("calling: " + urlString);
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				if (sb.length() > 0) {
					sb.append("\n");
				}
				sb.append(line);
			}
			in.close();
			
			String plainResp = sb.toString();
			if (plainResp.length() < 3) {
				return null;
			}
			
			Response re = new Response();
			if (plainResp.charAt(0) == '+') {
				Game.I.setNewChat(true);
				plainResp = plainResp.substring(1);
			}
			
			if (plainResp.charAt(0) == 'E') {
				re.setError(true);
				re.setCode(-Integer.parseInt(plainResp.substring(1, 4)));
				plainResp = plainResp.substring(4);
			} else {
				re.setCode(Integer.parseInt(plainResp.substring(0, 3)));
				plainResp = plainResp.substring(3);
			}
			
			re.setParts(plainResp.split(";"));
			
			return re;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

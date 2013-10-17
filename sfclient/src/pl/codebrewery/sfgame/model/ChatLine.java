package pl.codebrewery.sfgame.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatLine {

	public enum Type {
		GOLD("dg", new Formatter() {
			@Override public String format(String[] parts) {
				int quantity = Integer.parseInt(parts[3]);
				quantity /= 100;
				return "#Y" + parts[2] + "#Z - " + quantity + " $";
			}
		}),
		SHROOM("dm", new Formatter() {
			@Override public String format(String[] parts) {
				int quantity = Integer.parseInt(parts[3]);
				quantity /= 100;
				return "#R" + parts[2] + "#Z - " + quantity + " shroom" + (quantity != 1 ? "s" : "");
			}
		}),
		CHAT("", new Formatter() {
			@Override public String format(String[] parts) {
				return parts[0] + ": #W" + parts[1] + "#Z";
			}
		}),
		OTHER("", null),
		DUNGEON("du", new Formatter() {
			@Override public String format(String[] parts) {
				long ts = Long.parseLong(parts[2]) * 1000; 
				Date d = new Date();
				d.setTime(ts);
				return "#_Bserver restart @ " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d)) + "#Z";
			}
		}),
		RESTART("sr", new Formatter() {
			@Override public String format(String[] parts) {
				return "#G" + parts[2] + "#Z has finished level " + parts[4] + " of dungeon #" + parts[3];
			}
		}),
		LEVEL_UP("lu", new Formatter() {
			@Override public String format(String[] parts) {
				return "#B" + parts[2] + "#Z has reached level " + parts[3];
			}
		});
		
		private String idString;
		private Formatter fmt;
		
		private static Map<String, Type> idMap;
		
		private interface Formatter {
			String format(String[] parts);
		}

		private Type(String id, Formatter f) {
			idString = id;
			fmt = f;
		}

		public String getIdString() {
			return idString;
		}
		
		public static Map<String, Type> getIdMap() {
			if (idMap == null)
			idMap = new HashMap<String, Type>();
			for(Type t : values()) {
				idMap.put(t.getIdString(), t);
			}
			return idMap ;
		}

		public Formatter getFmt() {
			return fmt;
		}
	}
	
	private int id;
	private Type type;
	private String line;
	
	public ChatLine(String in) {
//		System.out.println(in);
		if (in.startsWith("#")) {
			String[] parts = in.split("#");
			String key = parts[1];
			Map<String, Type> typeMap = Type.getIdMap();
			if (typeMap.containsKey(key)) {
				type = typeMap.get(key);
				line = type.getFmt().format(parts);
			} else {
				type = Type.OTHER;
				line = in;
			}
		} else {
			type = Type.CHAT;
			String parts[] = in.split(":\ufffd"); //dziwny separator...
			line = Type.CHAT.getFmt().format(parts);
		}
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ChatLine [id=" + id + ", type=" + type + ", line=" + line + "]";
	}
}

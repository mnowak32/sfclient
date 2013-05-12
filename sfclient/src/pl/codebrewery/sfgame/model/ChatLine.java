package pl.codebrewery.sfgame.model;

public class ChatLine {

	public enum Type {
		GOLD("złoto", "złota", "złota"), SHROOM("grzyb", "grzyby", "grzybów"), CHAT, OTHER;
		private String one, two, five;
		private Type() {
			
		}
		private Type(String o, String t, String f) {
			one = o;
			two = t;
			five = f;
		}
		
		String getQuant(int q) {
			if (q == 1) {
				return one;
			}
			final int mod100 = q % 100;
			if (mod100 > 11 && mod100 < 15) {
				return five;
			}
			final int mod10 = q % 10;
			if (mod10 > 1 && mod10 < 5) {
				return two;
			}
			return five;
		}
	}

	private int id;
	private Type type;
	private String line;
	
	public ChatLine(String in) {
		if (in.startsWith("#")) {
			String[] parts = in.split("#");
			int quantity = Integer.parseInt(parts[3]);
			if (parts[1].equals("dm")) {
				type = Type.SHROOM;
			} else if (parts[1].equals("dg")) {
				type = Type.GOLD;
				quantity /= 100;
			}
			line = parts[2] + " - " + quantity + " " + type.getQuant(quantity);
		} else {
			line = in;
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

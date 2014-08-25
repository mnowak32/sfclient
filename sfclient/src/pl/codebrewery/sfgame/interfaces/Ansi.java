package pl.codebrewery.sfgame.interfaces;

public enum Ansi {
	Z("\u001B[0m"), // reset
	K("\u001B[30m"), // black
	R("\u001B[31m"), // red
	G("\u001B[32m"), // green
	Y("\u001B[33m"), // yellow
	B("\u001B[34m"), // blue
	M("\u001B[35m"), // magena
	C("\u001B[36m"), // cyan
	W("\u001B[37m"), // white
	_K("\u001B[30;1m"), // black
	_R("\u001B[31;1m"), // red
	_G("\u001B[32;1m"), // green
	_Y("\u001B[33;1m"), // yellow
	_B("\u001B[34;1m"), // blue
	_M("\u001B[35;1m"), // magena
	_C("\u001B[36;1m"), // cyan
	_W("\u001B[37;1m"); // white

	public String esc;

	private Ansi(String e) {
		esc = e;
	}

	public String getCode() {
		return "#" + this;
	}
}

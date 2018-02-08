package macro303.keschet;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Tester {
	private static Tester ourInstance = new Tester();
	private boolean isTesting = false;

	public static Tester getInstance() {
		return ourInstance;
	}

	public boolean isTesting() {
		return isTesting;
	}

	public void setTesting(boolean testing) {
		isTesting = testing;
	}
}
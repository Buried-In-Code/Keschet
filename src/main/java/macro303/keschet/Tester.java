package macro303.keschet;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Macro303 on 2018-02-08.
 */
public class Tester {
	@NotNull
	private static Tester ourInstance = new Tester();
	private boolean isTesting = false;

	@Contract(pure = true)
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
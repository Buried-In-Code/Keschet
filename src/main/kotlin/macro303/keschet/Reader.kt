package macro303.keschet

import java.util.*

/**
 * Created by Macro303 on 27/06/2017.
 */
internal object Reader {
	private val reader: Scanner = Scanner(System.`in`)

	fun readConsole(prompt: String): String {
		println(prompt)
		print("> ")
		return reader.nextLine().trim()
	}
}
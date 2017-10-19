package macro303.base

import org.apache.logging.log4j.LogManager
import java.util.*

class Reader {
	private val reader = Scanner(System.`in`)

	fun readConsole(prompt: String): String {
		Thread.sleep(10)
		println(prompt)
		print("> ")
		val input = reader.nextLine().trim()
		LOGGER.trace("String readConsole(String) = $input")
		return input
	}

	companion object {
		private val LOGGER = LogManager.getLogger(Reader::class.java)
	}
}

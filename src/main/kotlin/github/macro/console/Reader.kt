package github.macro.console

import org.apache.logging.log4j.LogManager
import java.util.*

object Reader {
	private val LOGGER = LogManager.getLogger(Reader::class.java)
	private val READER = Scanner(System.`in`)

	@JvmStatic
	fun readConsole(prompt: String?): String {
		print("${Colour.CYAN}$prompt >> ")
		val input = READER.nextLine().trim()
		print(Colour.RESET)
		return input
	}
}
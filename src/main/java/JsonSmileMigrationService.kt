import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.dataformat.smile.SmileFactory
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.InputStream

object JsonSmileMigrationService {
    private val log = LoggerFactory.getLogger(JsonSmileMigrationService::class.java)

    fun convertToSmile(json: ByteArray, jsonFactory: JsonFactory, smileFactory: SmileFactory): ByteArray {
        val bos = ByteArrayOutputStream()

        try {
            smileFactory.createGenerator(bos).use { jg ->
                jsonFactory.createParser(json).use { jp ->
                    while (jp.nextToken() != null) {
                        jg.copyCurrentEvent(jp)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Error while converting json to smile", e)
        }

        return bos.toByteArray()
    }

    fun convertToJson(smile: InputStream, jsonFactory: JsonFactory, smileFactory: SmileFactory): ByteArray {
        val bos = ByteArrayOutputStream()

        try {
            smileFactory.createParser(smile).use { sp ->
                jsonFactory.createGenerator(bos).use { jg ->
                    while (sp.nextToken() != null) {
                        jg.copyCurrentEvent(sp)
                    }
                }
            }
        } catch (e: Exception) {
            log.error("Error while converting smile to json", e)
        }

        return bos.toByteArray()
    }
}
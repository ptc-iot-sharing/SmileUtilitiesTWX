import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.dataformat.smile.SmileFactory
import net.jpountz.lz4.LZ4BlockInputStream
import net.jpountz.lz4.LZ4BlockOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.System.out
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by placatus on 14.07.2016.
 */

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        out.println("Help: Run as \n java -jar convertor.jar [t2j/j2t] inputFile outputFile")
    }
    out.println("Converting " + args[1] + " to " + args[2])
    if (args[0] == "t2j") {
        val inputStream = LZ4BlockInputStream(FileInputStream(args[1]))

        val bytes = JsonSmileMigrationService.convertToJson(inputStream, JsonFactory(), SmileFactory())
        Files.write(Paths.get(args[2]), bytes)
    } else if (args[0] == "j2t") {
        val inputStream = FileInputStream(args[1])

        val bytes = JsonSmileMigrationService.convertToSmile(inputStream.readBytes(), JsonFactory(), SmileFactory())
        inputStream.close()
        val outputStream = FileOutputStream(args[2])
        val output = LZ4BlockOutputStream(outputStream)
        output.write(bytes)
        output.flush()
        output.close()
    }
}
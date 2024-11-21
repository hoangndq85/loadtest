package utils
import scala.util.Random

class LiveSearchParamsGenerator {
    var filePath = "./src/test/resources/liveSearchParams.csv"
    val csvReader = new CsvReader()
    var random = new Random()

    def getRandom[T](array: Array[T]): T = array(random.nextInt(array.length))

    def generateSearchParams(): String = {
        new StringBuilder(s"${getRandom(csvReader.readCSV(filePath))}").toString()
    }
}
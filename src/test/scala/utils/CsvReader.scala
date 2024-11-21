package utils

import scala.io.Source

class CsvReader {
  def readCSV(filePath: String): Array[String] = {
    Source
      .fromFile(filePath)
      .getLines()
      .toArray
  }
}

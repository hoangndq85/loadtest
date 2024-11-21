package utils
import java.util.Properties

class PropertiesReader(propertiesFileName: String) {
  private val properties: Properties = new Properties()
  private val is = getClass().getClassLoader().getResourceAsStream(propertiesFileName)
  properties.load(is)

  def getProperty(name: String): String = this.properties.getProperty(name)
}

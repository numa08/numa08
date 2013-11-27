package models.hatena

import scala.collection.JavaConversions._
import org.jsoup.Jsoup
import models.Content
import models.ContentCreator
import java.net.URL

case class HatenaBlog(title : String, url : String, favicon : String) extends Content
case class HatenaEntry(title : String, url : String)
object HatenaBlog {

}
class HatenaBlogCreator extends ContentCreator {
  
  def create : HatenaBlog = {
    val url = "http://numa08.hateblo.jp/"
    val title = Jsoup.connect(url)
    				 .get()
    				 .title()
    val favicon = "img/icon/hatena.png"
    val hatenaBlog = HatenaBlog(title, url, favicon)
    hatenaBlog
  }
}

class HatenaEntryAcuire {
  
  def acuire : List[HatenaEntry] = {
    val url = new URL("http://numa08.hateblo.jp/feed")
    // hatena blog feeds's return "Content-Type: application/atom+xml" in reponse header
    // jsoup does not parse Mime-type like that.
    val feeds = Jsoup.parse(url.openStream(), "UTF-8", url.toString())
    				 .getElementsByTag("entry")
    				 .map(elem => {
    				   val title = elem.getElementsByTag("title")(0).text()
    				   val link  = elem.getElementsByTag("link")(0).attr("href")
    				   HatenaEntry(title, link)
    				 }).toList
    feeds
  }
}
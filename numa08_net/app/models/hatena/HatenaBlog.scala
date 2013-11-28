package models.hatena

import scala.collection.JavaConversions._
import org.jsoup.Jsoup
import java.net.URL
import models.SocialContent
import controllers.routes
import controllers.Assets
import models._


case class HatenaBlog(title : String, url : String, favicon : String)
case class HatenaEntry(title : String, url : String)
object HatenaBlog {
	val URL = "http://numa08.hateblo.jp/"
	val FEED_URL = URL + "/feed"
}
class HatenaBlogCreator extends SocialContentCreator{
  
  def create : SocialContent = {
    val title = Jsoup.connect(HatenaBlog.URL)
    				 .get()
    				 .title()
    val favicon = routes.Assets.at("img/icon/hatena.png").toString()
    val hatenaBlog = InternalSocialContent(title, favicon, "javascript:$.pageslide({ direction: 'left', href: 'hatena_blog' })")
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
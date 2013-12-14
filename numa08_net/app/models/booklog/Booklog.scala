package models.booklog

import models.SocialContentCreator
import models.SocialContent
import models.InternalSocialContent
import models.EntryAcquire
import models.Entry
import org.jsoup.Jsoup
import controllers.routes
import java.net.URL
import java.net.URI
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits._
import scala.io.Source

object Booklog {
  val URL = "http://booklog.jp/users/numa08"
  val FEED_URL = "http://api.booklog.jp/json/numa08"
}
class BooklogCreator extends SocialContentCreator {
  
  def create : SocialContent = {
    val title = Jsoup.connect(Booklog.URL)
    				 .get()
    				 .title()
	val favicon = routes.Assets.at("img/icon/booklog.png").toString()
    val booklog = InternalSocialContent(title, favicon, "javascript:$.pageslide({ direction: 'left', href: 'blog/Booklog' })",
    									new URL(Booklog.URL), new URI("../api/feed/Booklog"))
    booklog
  }
}

class BooklogFeedAcquire extends EntryAcquire{
  def acquire : List[Entry] = {
    	  
    val json = Source.fromURL(Booklog.FEED_URL)
    				 .getLines
    				 .mkString

	val entries = Json.parse(json)
					 .\("books")
					 .as[JsArray]
					 .value
					 .map(b => {
					   val (title, url) = (b.\("title").as[String], b.\("url").as[String])
					   Entry(title, url)
					 }).toList
	 entries
  }
}
package models.blogger

import scala.collection.JavaConversions._
import org.jsoup.Jsoup
import controllers.routes
import models.SocialContent
import models.SocialContentCreator
import models.InternalSocialContent

object Blogger {
  val URL = "http://numa08.blogspot.jp/"
  val FEED_URL = "http://feeds.feedburner.com/Numa08"
}

case class BloggerFeed(title : String , url : String)
class BloggerCreator extends SocialContentCreator{

  def create : SocialContent = {
    val title = Jsoup.connect(Blogger.URL)
    				 .get()
    				 .title()
    val favicon = routes.Assets.at("img/icon/blogger.png").toString()
    val blogger = InternalSocialContent(title, favicon , "javascript:$.pageslide({ direction: 'left', href: 'blogger' })")
    blogger
  }
}

class BloggerFeedAcuire {
  
  def acuire : List[BloggerFeed] = {
    val url = Blogger.FEED_URL
    val feeds = Jsoup.connect(url)
    				 .get()
    				 .getElementsByTag("entry")
    				 .map(elem => {
    				   val title = elem.getElementsByTag("title")(0).text()
    				   val link  = elem.getElementsByTag("link")(0).attr("href")
    				   BloggerFeed(title, link)
    				 }).toList
    feeds
  }
}
package models.blogger

import org.jsoup.Jsoup
import controllers.routes
import models.SocialContent
import models.SocialContentCreator
import models.ExternalSocialContent

object Blogger {
  val URL = "http://numa08.blogspot.jp/"
  val FEED_URL = "http://feeds.feedburner.com/Numa08"
}

class BloggerCreator extends SocialContentCreator{

  def create : SocialContent = {
    val title = Jsoup.connect(Blogger.URL)
    				 .get()
    				 .title()
    val favicon = routes.Assets.at("img/icon/blogger.png").toString()
    val blogger = ExternalSocialContent(title, favicon , "http://numa08.blogspot.jp/")
    blogger
  }
}
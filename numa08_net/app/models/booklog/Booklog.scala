package models.booklog

import models.SocialContentCreator
import models.SocialContent
import models.InternalSocialContent
import org.jsoup.Jsoup
import controllers.routes
import java.net.URL
import java.net.URI

object Booklog {
  val URL = "http://booklog.jp/users/numa08"
}
class BooklogCreator extends SocialContentCreator {
  
  def create : SocialContent = {
    val title = Jsoup.connect(Booklog.URL)
    				 .get()
    				 .title()
	val favicon = routes.Assets.at("img/icon/booklog.png").toString()
    val booklog = InternalSocialContent(title, favicon, "javascript:$.pageslide({ direction: 'left', href: 'blog/Booklog' })",
    									new URL(Booklog.URL), new URI("../booklog_books"))
    booklog
  }
}
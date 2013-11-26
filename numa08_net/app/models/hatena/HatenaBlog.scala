package models.hatena

import org.jsoup.Jsoup
import models.Content
import models.ContentCreator

case class HatenaBlog(title : String, url : String, favicon : String) extends Content

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
package models

import java.net.URL
import java.net.URI
import play.api.libs.functional.syntax._
import play.api.libs.json._
import models.blogger.BloggerFeedAcquire
import models.hatena.HatenaEntryAcquire
import models.booklog.BooklogFeedAcquire


class Entries {

}
case class EntryPage(pageTitle:String, entryTitle:String)
case class EntryContent(contentUrl : URL, contentTitle : String , apiUrl : URI)

case class Entry(title : String, url : String) {
  def toJson : JsValue = {
    implicit val writers: Writes[Entry] = (
      (__ \ "title").write[String] and
      (__ \ "url").write[String])(unlift(Entry.unapply))
    val json = Json.toJson(this)
    json
  }
}

abstract class EntryAcquire {
  def acquire : List[Entry]
}

object EntryAcquireFactory {
  def acquires = Map("Blogger" -> new BloggerFeedAcquire(),
		  			 "HatenaBlog" -> new HatenaEntryAcquire(),
		  			 "Booklog" -> new BooklogFeedAcquire())
		  			 
 def buildByName(name : String) = acquires.get(name)
}
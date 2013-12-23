package models

import models.blogger.BloggerCreator
import models.hatena.HatenaBlogCreator
import java.net.URI
import java.net.URL
import models.booklog.BooklogCreator

abstract class Content

abstract class ContentCreator {
  def create : Content
}

class SocialContent
case class InternalSocialContent(title : String, favicon : String, link : String, url : URL, apiUri : URI) extends SocialContent
case class ExternalSocialContent(title : String, favicon : String, link : String) extends SocialContent

trait SocialContentCreator {
  def create : SocialContent
}

object ContentCreatorFactory {
  val creators = Map("Blogger" -> new BloggerCreator(),
		  			 "HatenaBlog"  -> new HatenaBlogCreator(),
		  			 "Booklog"  -> new BooklogCreator())
		  			 
  def buildByName(name : String) : Option[SocialContentCreator] = creators.get(name)

}
package controllers

import java.net.URL
import java.net.URI
import models.blogger._
import models.booklog.BooklogCreator
import models.ContentCreatorFactory
import models.EntryPage
import models.EntryContent
import models.foursquare.FourSquareCreator
import models.hatena._
import models.InternalSocialContent
import models.twitter.TwitterCreator
import models.SocialContentCreator
import play.api._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc._
import scala.concurrent.Future
import views.html.defaultpages.badRequest

object Application extends Controller {

  def index = Action.async {
    val futureImple = Future{
      val creators = new TwitterCreator() ::
    	  			 new HatenaBlogCreator() ::
    	  			 new FourSquareCreator() ::
    	  			 new BloggerCreator() ::
    	  			 new BooklogCreator() ::
    	  			 Nil
      val contents = creators.map(_.create)
      contents
    }
    futureImple.map { socials =>
    	val content = views.html.index(socials)
    	Ok(content)
    }
  }
  
  def blog(service : String) = Action.async{
    val page = ContentCreatorFactory.buildByName(service) match {
      case None => Future(BadRequest)
      case Some(creator) => { 
        val futureImple = Future(creator.create)
        val result = futureImple.map{ content => content match {
          case c: InternalSocialContent => {
            val entryPage = EntryPage(service, "Posts")
            val entryContent = EntryContent(c.url, c.title, c.apiUri)
            val page = views.html.entries(entryPage, entryContent)
            Ok(page)
          }
          case _ => BadRequest(service + " does not available")
        }}
        result
      }
    }
    page
  }
}

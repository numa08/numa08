package controllers

import scala.concurrent.Future

import java.net.URL
import models.InternalSocialContent
import models.blogger._
import models.foursquare.FourSquareCreator
import models.hatena._
import models.twitter.TwitterCreator
import play.api._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc._


object Application extends Controller {

  def index = Action.async {
    val futureImple = Future{
      val creators = new TwitterCreator() ::
    	  			 new HatenaBlogCreator() ::
    	  			 new FourSquareCreator() ::
    	  			 new BloggerCreator() ::
    	  			 Nil
      val contents = creators.map(_.create)
      contents
    }
    futureImple.map { socials =>
    	val content = views.html.index(socials)
    	Ok(content)
    }
  }
  
  def hatena =  Action.async{
    val futureImple = Future(new HatenaBlogCreator().create)
    futureImple.map{ hatena =>
    	val title = hatena match {
        case c : InternalSocialContent => c.title
        case _ => ""
      }
    	val content = views.html.hatenablog(title, new URL(HatenaBlog.URL))
    	Ok(content)
    }
  }
  
  def blogger = Action.async{
    val futureImple = Future(new BloggerCreator().create)
    futureImple.map{ blogger => 
      val title = hatena match {
        case c : InternalSocialContent => c.title
        case _ => ""
      }
      val content = views.html.blogger(title, new URL(Blogger.URL))
      Ok(content)
      }
  }
}

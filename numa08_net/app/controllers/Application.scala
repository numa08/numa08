package controllers

import scala.concurrent.Future

import models.hatena._
import models.twitter.TwitterCreator
import models.foursquare.FourSquareCreator
import models.blogger.BloggerCreator

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits._


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
  
  def hatena = TODO
//    Action.async{
//    val futureImple = Future(new HatenaBlogCreator().create)
//    futureImple.map{ hatena =>
//    	val content = views.html.hatenablog(hatena)
//    	Ok(content)
//    }
//  }
}

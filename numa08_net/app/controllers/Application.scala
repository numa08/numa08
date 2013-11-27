package controllers

import scala.concurrent.Future
import models.hatena._
import play.api._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc._
import play.api.libs.functional.syntax._
import play.api.libs.json._


object Application extends Controller {

  def index = Action.async{
    val futureImple = Future(new HatenaBlogCreator().create)
    futureImple.map{ hatena => 
    	val content = views.html.index(hatena)
    	Ok(content)
    }
  }
  
  def hatenaBlogFeeds = Action.async{
    implicit val hatenaEntryWrites: Writes[HatenaEntry] = (
      (__ \ "title").write[String] and
      (__ \ "url").write[String])(unlift(HatenaEntry.unapply))
      
    val futureImple = Future({
      val entriesJson = new HatenaEntryAcuire().acuire
    		  								   .map(Json.toJson(_))
      JsArray(entriesJson)
    })
    futureImple.map(Ok(_))
  }
  
  def hatena = Action.async{
    val futureImple = Future(new HatenaBlogCreator().create)
    futureImple.map{ hatena =>
    	val content = views.html.hatenablog(hatena)
    	Ok(content)
    }
  }
}

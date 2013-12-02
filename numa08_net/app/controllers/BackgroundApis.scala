package controllers

import scala.concurrent.Future
import models.hatena._
import play.api._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc._
import play.api.libs.functional.syntax._
import play.api.libs.json._
import models.blogger.BloggerFeed
import models.blogger.BloggerFeedAcuire

object BackgroundApis extends Controller {

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

  def bloggerFeeds = Action.async{
    implicit val hatenaEntryWrites: Writes[BloggerFeed] = (
      (__ \ "title").write[String] and
      (__ \ "url").write[String])(unlift(BloggerFeed.unapply))
      
      val futureImple = Future({
        val entriesJson = new BloggerFeedAcuire().acuire
    		  									.map(Json.toJson(_))
		JsArray(entriesJson)
      })
      futureImple.map(Ok(_))
  }
}
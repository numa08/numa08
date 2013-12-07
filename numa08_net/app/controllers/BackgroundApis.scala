package controllers

import scala.concurrent.Future
import models.hatena._
import play.api._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc._
import play.api.libs.functional.syntax._
import play.api.libs.json._
import models.blogger._
import models.EntryAcquireFactory


object BackgroundApis extends Controller {

  def feeds(service : String) = Action.async{
    val result = EntryAcquireFactory.buildByName(service) match {
      case None => Future(BadRequest)
      case Some(acquire) => {
        val futureImple = Future({
          val entryJson = acquire.acquire.map(_.toJson)
          JsArray(entryJson)
        })
        futureImple.map(Ok(_))
      }
    }
    result
  }
  
}
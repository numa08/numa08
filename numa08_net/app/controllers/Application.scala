package controllers

import models.hatena.HatenaBlog
import models.hatena.HatenaBlogCreator
import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future

object Application extends Controller {

  def index = Action.async{
    val futureImple = Future(new HatenaBlogCreator().create)
    futureImple.map{ hatena => 
    	val content = views.html.index(hatena)
    	Ok(content)
    }
  }
}

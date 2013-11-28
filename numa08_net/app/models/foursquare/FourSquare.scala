package models.foursquare

import models.SocialContentCreator
import models.SocialContent
import models.ExternalSocialContent

import controllers.routes

class FourSquareCreator extends SocialContentCreator {

  def create : SocialContent = {
    val foursquare = ExternalSocialContent("numanuma08",
    										routes.Assets.at("img/icon/4sq.png").toString(),
    										"https://ja.foursquare.com/numa08/")
	foursquare
  }
}
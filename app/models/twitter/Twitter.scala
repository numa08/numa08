package models.twitter

import models.SocialContentCreator
import models.SocialContent
import models.ExternalSocialContent

class TwitterCreator extends SocialContentCreator {
  
  def create : SocialContent = {
    val twitter = ExternalSocialContent("@numa08",
    									"https://dev.twitter.com/sites/default/files/images_documentation/bird_blue_16.png",
    									"http://twitter.com/numa08/")
	twitter
  }
}
package models.twitter

import models.SocialContentCreator
import models.SocialContent
import models.ExternalSocialContent

class TwitterCreator extends SocialContentCreator {
  
  def create : SocialContent = {
    val twitter = ExternalSocialContent("@numa08",
    									"https://twitter.com/images/resources/twitter-bird-16x16.png",
    									"http://twitter.com/numa08/")
	twitter
  }
}
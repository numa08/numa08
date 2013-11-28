package models

abstract class Content

abstract class ContentCreator {
  def create : Content
}

class SocialContent
case class InternalSocialContent(title : String, favicon : String, link : String) extends SocialContent
case class ExternalSocialContent(title : String, favicon : String, link : String) extends SocialContent

trait SocialContentCreator {
  def create : SocialContent
}
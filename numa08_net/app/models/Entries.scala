package models

import java.net.URL
import java.net.URI


class Entries {

}

case class EntryPage(pageTitle:String, entryTitle:String)
case class EntryContent(contentUrl : URL, contentTitle : String , apiUrl : URI)
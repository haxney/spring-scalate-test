package org.haxney.springtest

import java.util.Comparator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.graph.neo4j.finder.FinderFactory;
import org.springframework.data.graph.neo4j.finder.NodeFinder;
import org.springframework.stereotype.{Controller, Service, Component}
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, PathVariable}
import org.springframework.web.servlet.view.RedirectView

@Controller
class Home {
  @Autowired
  val finderFactory: FinderFactory
  val logger = LoggerFactory.getLogger(classOf[Home])

  @Autowired
  var comparator: Comparator[String] = _

  @RequestMapping(value = Array("/"))
  def home = "layout:home"

  @RequestMapping(value = Array("/compare"), method = Array(RequestMethod.GET))
  def compare(@RequestParam("input1")
              input1: String,

	      @RequestParam("input2")
              input2:String,

              model: Model) = {
    val result = comparator.compare(input1, input2)
    val inEnglish = result match {
      case _ if result < 0 => "less than"
      case _ if result > 0 => "greater than"
      case 0 => "equal to"
    }

    val output = "According to our Comparator, '" + input1 + "' is " + inEnglish + " '" + input2 + "'"

    model.addAttribute("output", output)
    "layout:compareResult"
  }

  @Transactional
  @RequestMapping(value = Array("/army/setup"))
  def setupArmies = {
    val prussia = Country("Prussia", 1000000)
    val firstDiv = Army("First Division", 4, prussia)
    new RedirectView("/country/Prussia", true)
  }

  @RequestMapping(value = Array("/country/{name}"))
  def showCountry(@PathVariable name: String, model: Model) = {
    val country = findCountryNamed(name)
    model.addAttribute("country", country)
    "layout:showCountry"
  }

  def finder = finderFactory.createNodeEntityFinder(classOf[Country])

  def findCountryNamed(name: String) = finder.findByPropertyValue("countries", "name", name)

}

@Service
class CaseInsensitiveComparator extends Comparator[String] {
  override def compare(s1: String, s2: String) = String.CASE_INSENSITIVE_ORDER.compare(s1, s2)
}

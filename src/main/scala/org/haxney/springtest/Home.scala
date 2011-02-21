package org.haxney.springtest

import java.util.Comparator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.{Controller, Service, Component}
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam}

@Controller
class Home {
  val logger = LoggerFactory.getLogger(classOf[Home])

  var comparator: Comparator[String] = new CaseInsensitiveComparator

  def setComparator(c: Comparator[String]) = {
    this.comparator = c
  }

  @RequestMapping(value = Array("/"))
  def home = {
    println("HomeController: Passing through...")
    "layout:home"
  }

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
}

@Service
class CaseInsensitiveComparator extends Comparator[String] {
  override def compare(s1: String, s2: String) = String.CASE_INSENSITIVE_ORDER.compare(s1, s2)
}

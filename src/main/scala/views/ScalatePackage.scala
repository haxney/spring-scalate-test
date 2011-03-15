package views

import org.fusesource.scalate.{TemplateSource, Binding}
import org.fusesource.scalate.support.TemplatePackage

/**
 * Defines some common imports, attributes and methods across templates in package views and below
 */
class ScalatePackage extends TemplatePackage {

  /** Returns the Scala code to add to the top of the generated template method */
  def header(source: TemplateSource, bindings: List[Binding]) = """
  // some shared imports
  import org.haxney.springtest._
  """
}

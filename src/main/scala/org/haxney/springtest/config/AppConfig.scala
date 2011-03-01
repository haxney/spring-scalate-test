package org.haxney.springtest
package config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration, Import, Feature, FeatureConfiguration, ComponentScanSpec}
import org.springframework.core.`type`.filter.AssignableTypeFilter
import org.springframework.web.servlet.mvc.annotation.{DefaultAnnotationHandlerMapping, AnnotationMethodHandlerAdapter}
import org.springframework.web.servlet.config.MvcDefaultServletHandler
import org.slf4j.LoggerFactory

@Configuration
@Import(Array(classOf[FeatureConfig]))
class AppConfig {

  val logger = LoggerFactory.getLogger(classOf[AppConfig])

  @Bean
  def handlerMapping = {
    val mapping = new DefaultAnnotationHandlerMapping
    mapping.setOrder(0)
    mapping
  }

  @Bean
  def handlerAdapter = new AnnotationMethodHandlerAdapter

  @Bean
  def scalateView = {
    val res = new org.fusesource.scalate.spring.view.ScalateViewResolver
    res.setPrefix("/WEB-INF/views/")
    res.setSuffix(".jade")
    res
  }
}

@FeatureConfiguration
class FeatureConfig {
  @Feature
  def componentScan = new ComponentScanSpec("org.haxney.springtest").excludeFilters(
    new AssignableTypeFilter(classOf[AppConfig]),
    new AssignableTypeFilter(classOf[FeatureConfig]))

  @Feature
  def defaultHandler = new MvcDefaultServletHandler
}

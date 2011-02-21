package org.haxney.springtest
package config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration, Import, Feature, FeatureConfiguration}
import org.springframework.web.servlet.mvc.annotation.{DefaultAnnotationHandlerMapping, AnnotationMethodHandlerAdapter}
import org.springframework.web.servlet.config.MvcDefaultServletHandler
import org.slf4j.LoggerFactory

@Configuration
@Import(Array(classOf[FeatureConfig]))
class AppConfig {

  val logger = LoggerFactory.getLogger(classOf[AppConfig])

  @Bean
  def homeController = new Home

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
  def defaultHandler = new MvcDefaultServletHandler
}

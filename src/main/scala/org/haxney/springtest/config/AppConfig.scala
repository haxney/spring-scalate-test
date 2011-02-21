package org.haxney.springtest
package config

import org.springframework.context.annotation.{Bean, Configuration, ImportResource}
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.servlet.mvc.annotation.{DefaultAnnotationHandlerMapping, AnnotationMethodHandlerAdapter}
import org.slf4j.LoggerFactory

@Configuration
@ImportResource(Array("classpath:/mvc-resources.xml"))
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

package org.haxney.springtest
package config

import org.haxney.springtest.Home
import org.springframework.context.annotation.{Bean,Configuration,ImportResource}
import org.springframework.beans.factory.annotation.Value
import org.slf4j.LoggerFactory

@Configuration
class AppConfig {

  val logger = LoggerFactory.getLogger(classOf[AppConfig])

  @Bean
  def homeController = new Home

  @Bean
  def scalateView = {
    val res = new org.fusesource.scalate.spring.view.ScalateViewResolver
    res.setPrefix("/WEB-INF/scalate/layouts/")
    res.setSuffix(".jade")
    res
  }
}

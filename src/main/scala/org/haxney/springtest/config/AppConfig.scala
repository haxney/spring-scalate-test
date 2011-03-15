package org.haxney.springtest
package config

import org.fusesource.scalate.spring.view.ScalateViewResolver
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration, ImportResource, Feature, FeatureConfiguration, ComponentScanSpec}
import org.springframework.core.`type`.filter.AnnotationTypeFilter
import org.springframework.data.graph.neo4j.config.Neo4jConfiguration
import org.springframework.web.servlet.mvc.annotation.{DefaultAnnotationHandlerMapping, AnnotationMethodHandlerAdapter}
import org.springframework.web.servlet.config.MvcDefaultServletHandler
import org.slf4j.LoggerFactory
import org.neo4j.kernel.EmbeddedGraphDatabase

@Configuration
class AppConfig extends Neo4jConfiguration {

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
    val res = new ScalateViewResolver
    res.setPrefix("/WEB-INF/views/")
    res.setSuffix(".jade")
    res
  }

  lazy val storeDir = "target/neo-store"

  @Bean(destroyMethod = "shutdown")
  def graphDb = new EmbeddedGraphDatabase(storeDir)
}

@FeatureConfiguration
class FeatureConfig {
  @Feature
  def componentScan = new ComponentScanSpec("org.haxney.springtest").excludeFilters(
    new AnnotationTypeFilter(classOf[Configuration]),
    new AnnotationTypeFilter(classOf[FeatureConfiguration]))

  @Feature
  def defaultHandler = new MvcDefaultServletHandler
}

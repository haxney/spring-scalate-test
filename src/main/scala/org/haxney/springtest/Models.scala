package org.haxney.springtest

import org.slf4j.LoggerFactory
import org.neo4j.graphdb.RelationshipType
import org.springframework.data.annotation.Indexed
import org.springframework.data.graph.annotation.{NodeEntity, RelatedTo}
import org.springframework.data.graph.core.Direction

@NodeEntity
case class Country(@Indexed(indexName = "countries")
                   var name: String,
                   var population: Int,

                   @RelatedTo(`type` = "OWNER", elementClass = classOf[Army], direction = Direction.INCOMING)
                   var armies: Set[Army] = Set[Army]())

@NodeEntity
case class Army(var name: String,
                var experience: Int,

                @RelatedTo(`type` = "OWNER", direction = Direction.OUTGOING)
                var owner: Country)

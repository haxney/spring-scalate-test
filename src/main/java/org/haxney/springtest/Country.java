package org.haxney.springtest;

import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.core.Direction;

@NodeEntity
public class Country {
    @Indexed(indexName = "countries")
    String name;

    int population;

    @RelatedTo(type = RelTypes.OWNER, elementClass = Army.class, direction = Direction.INCOMING)
    private Set<Army> armies;
}

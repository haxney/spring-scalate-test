package org.haxney.springtest;

import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.core.Direction;
import java.util.Set;

@NodeEntity
public class Country {
    @Indexed(indexName = "countries")
    public String name;

    public int population;

    @RelatedTo(type = "OWNER", elementClass = Army.class, direction = Direction.INCOMING)
    public Set<Army> armies;

    public Country(String name, int pop) {
        this.name = name;
        this.population = pop;
    }
    public Country() {}
}

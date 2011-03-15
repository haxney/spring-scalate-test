package org.haxney.springtest;

import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.core.Direction;
import java.util.Set;

@NodeEntity
public class Country {
    @Indexed(indexName = "countries")
    private String name;
    private int population;

    @RelatedTo(type = "OWNER", elementClass = Army.class, direction = Direction.INCOMING)
    private Set<Army> armies;

    public Country(String name, int pop) {
        this.name = name;
        this.population = pop;
    }
    public Country() {}

    public String name() { return this.name; }
    public void name_$eq(String inName) { this.name = inName; }

    public int population() { return this.population; }
    public void population_$eq(int inPop) { this.population = inPop; }

    public Set<Army> armies() { return this.armies; }
    public void armies_$eq(Set<Army> inArmies) { this.armies = inArmies; }
}

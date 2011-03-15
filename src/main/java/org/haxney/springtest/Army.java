package org.haxney.springtest;

import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.core.Direction;

@NodeEntity
public class Army {
    private String name;

    private int experience;

    @RelatedTo(type = "OWNER", direction = Direction.OUTGOING)
    private Country owner;

    public Army(String name, int exp, Country owner) {
        this.name = name;
        this.experience = exp;
        this.owner = owner;
    }
    public Army() {}

    public String name() { return this.name; }
    public void name_$eq(String inName) { this.name = inName; }

    public int experience() { return this.experience; }
    public void experience_$eq(int inExp) { this.experience = inExp; }

    public Country owner() { return this.owner; }
    public void owner_$eq(Country inOwner) { this.owner = inOwner; }
}

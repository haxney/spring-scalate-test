package org.haxney.springtest;

import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.core.Direction;

@NodeEntity
public class Army {
    String name;

    int experience;

    @RelatedTo(type = RelTypes.OWNER, direction = Direction.OUTGOING)
    Country owner;
}

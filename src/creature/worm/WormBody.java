package creature.worm;

import processing.core.PShape;
import processing.core.PVector;
import creature.Body;
import creature.Creature;

public class WormBody extends Body {
	protected int sineDegree; // degrees for wiggle.
	
	public WormBody(Creature _creature, PVector _pos, float _width, float _height) {
		super(_creature, _pos, _width, _height);
		setStrokeColor(0xff008888);
	}

	@Override
	public void update() {
		sineDegree++;
	    if(sineDegree % 360 == 0 && sineDegree != 0) sineDegree = 0;
	    wiggle(sineDegree);
	}
	
	protected void wiggle(int deg) {
		for(int i=0; i<bodyPShape.getVertexCount(); i++) {
			// take the sine update, ++ a float value as it travels down the line
			PVector vertex = bodyPShape.getVertex(i);
			vertex.y += p.sin(p.radians((deg + i) * 6)) * 0.5;
			bodyPShape.setVertex(i, vertex.x, vertex.y);
		}
	}
	
	@Override
	protected PShape createBody() {
		PShape wormBody = p.createShape();
		wormBody.beginShape();
		wormBody.noFill();
		wormBody.strokeWeight(height);
	    for(int i=(int)-width/2; i<width/2; i+=2) { // put to 1 for even more wormy goodness!
	    	wormBody.vertex(i, 0);
	    }
	    wormBody.endShape();
	    return wormBody;
	}

}

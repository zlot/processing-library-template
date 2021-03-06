package behaviour;

import creature.Behaviour;
import creature.Creature;
import processing.core.*;
import loader.PClass;
import main.World;

@SuppressWarnings("static-access")
public class MoveBehaviourWithAng extends Behaviour {

	float nOffset; // special offset to make virus movement unique.
	
	public MoveBehaviourWithAng(Creature  _creature) {
		super(_creature);
		nOffset = p.random(-100, 100);
	}
	
	// annoying field just for noise
	float noiseInc = 0;
	float mmStep = 0.015f; // movement step
	
	public float ang2;
	
	// noise-walk the pos
	public void move() {
	    PVector pos = creature.getBody().getPos();
		PVector acceleration = new PVector(0,0);
	    
	    float n = p.noise(noiseInc + nOffset);
	    float nMapped = p.map(n, 0, 1, -mmStep, mmStep); // for x
	    acceleration.x = nMapped;
	    
	    n = p.noise(noiseInc + nOffset + 13); // 13 is arbitrary to have it different to y
	    nMapped = p.map(n, 0, 1, -mmStep, mmStep); // for y
	    acceleration.y = nMapped;
	    
	    noiseInc += 0.002;

	    // update() in Creature does the rest.
	    creature.setAcceleration(acceleration);
	    

	    if(pos.x > World.getScreenWidthWithBuffer()) {
	      pos.x = 0;
	    } else if (pos.x < -World.getBuffer()) {
	      pos.x = World.getScreenWidthWithBuffer();
	    }
	    
	    if(pos.y > World.getScreenHeightWithBuffer()) {
	      pos.y = 0;
	    } else if (pos.y < -World.getBuffer()) {
	      pos.y = World.getScreenHeightWithBuffer();
	    }
	    
//	    p.println("acc.x: " + acc.x + " acc.y: " + acc.y);
//	    p.println("vel.x: " + vel.x + " vel.y: " + vel.y);
//	    p.println("pos.x: " + pos.x + " pos.y: " + pos.y);
    }

	@Override
	public void update() {
		move();
	}


	@Override
	public void startMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void freeze() {
		// TODO Auto-generated method stub
		
	}

}

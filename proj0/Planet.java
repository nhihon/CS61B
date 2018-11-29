public class Planet {
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public static final double G = 6.67 * Math.pow(10, -11);
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	
	/** Calculate the distance between the two planets */
	public double calcDistance(Planet p){
		double distance = 0;
		distance = (xxPos - p.xxPos)*(xxPos - p.xxPos) + (yyPos - p.yyPos)*(yyPos - p.yyPos);
		return Math.sqrt(distance);
	}
	
	/** Calculate the force between two planets */
	public double calcForceExertedBy(Planet p){
		double force = (G * mass * p.mass)/(calcDistance(p) * calcDistance(p));
		return force;
	}
	
	/** Calculate the X component of the force between two planets */
	public double calcForceExertedByX(Planet p){
		double xForce = 0;
		xForce = (calcForceExertedBy(p) * (p.xxPos - xxPos))/calcDistance(p);
		return xForce;
	}
	
	/** Calculate the Y component of the force between two planets */
	public double calcForceExertedByY(Planet p){
		double yForce = 0;
		yForce = (calcForceExertedBy(p) * (p.yyPos - yyPos))/calcDistance(p);
		return yForce;
	} 
	
	/** Calculate X component of net force of an array of planets */
	public double calcNetForceExertedByX(Planet[] planets){
		double totalXForce = 0;
		for (Planet p : planets){
			if (this.equals(p) == false){
				totalXForce += calcForceExertedByX(p);
			}
		}
		return totalXForce;
	}
	
	/** Calculate Y component of net force of an array of planets */
	public double calcNetForceExertedByY(Planet[] planets){
		double totalYForce = 0;
		for (Planet p : planets){
			if (this.equals(p) == false){
				totalYForce += calcForceExertedByY(p);
			}
		}
		return totalYForce;
	}
	
	//update change in X component and Y component for the period dt
	public void update(double dt,double fX,double fY){
		double aX = fX/mass;
		double aY = fY/mass;
		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}
	
		
	public void draw(){
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}
}
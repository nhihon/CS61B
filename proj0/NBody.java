public class NBody{
	
	/** read universe radius from file */
	public static double readRadius(String filename){
		double radius = 0;
		In in = new In(filename);
		int numPlanet = in.readInt();
		radius = in.readDouble();
		return radius;
	}
	
	/** read all planets parameters from file */
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int numPlanet = in.readInt();
		double radius = in.readDouble();
		Planet[] allPlanets = new Planet[numPlanet];
		for (int i = 0; i < numPlanet; i+=1){
			allPlanets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return allPlanets;
	}

	
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		int numPlanet = planets.length;
		String imageToDraw = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		for (int i = 0; i < numPlanet; i+=1){
			String image = "images/" + planets[i].imgFileName;
			StdDraw.picture(planets[i].xxPos, planets[i].yyPos, image);
		}
		StdDraw.show();
		StdDraw.enableDoubleBuffering();
		int t = 0;
		while (t <= T){
			double[] xForces = new double[numPlanet];
			double[] yForces = new double[numPlanet];
			for (int i = 0; i < numPlanet; i+=1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < numPlanet; i+=1){
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);
			for (int i = 0; i < numPlanet; i+=1){
				String image = "images/" + planets[i].imgFileName;
				StdDraw.picture(planets[i].xxPos, planets[i].yyPos, image);
			}
			StdDraw.show();
			t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
						  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
						  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}
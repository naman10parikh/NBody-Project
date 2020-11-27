public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete method
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		this.myXPos = b.myXPos;
		this.myYPos = b.myYPos;
		this.myXVel = b.myXVel;
		this.myYVel = b.myYVel;
		this.myMass = b.myMass;
		this.myFileName = b.myFileName;
	}

	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double distanceSquared = Math.pow(b.myXPos - myXPos, 2) + Math.pow(b.myYPos - myYPos, 2);
		return Math.sqrt(distanceSquared);
	}

	public double calcForceExertedBy(CelestialBody b) {
		return (6.67e-11) * (myMass * b.myMass) / (Math.pow(this.calcDistance(b), 2));
	}

	public double calcForceExertedByX(CelestialBody b) {
		return this.calcForceExertedBy(b) * (b.myXPos- myXPos) / this.calcDistance(b);
	}
	public double calcForceExertedByY(CelestialBody b) {
		return this.calcForceExertedBy(b) * (b.myYPos- myYPos) / this.calcDistance(b);
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for (int i = 0; i < bodies.length; i++)
		{
			if (!bodies[i].equals(this))
				sum += this.calcForceExertedByX(bodies[i]);
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for (int i = 0; i < bodies.length; i++)
		{
			if (!bodies[i].equals(this))
				sum += this.calcForceExertedByY(bodies[i]);
		}
		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		double Ax = xforce / this.myMass;
		double Ay = yforce/ this.myMass;
		double nvx = this.myXVel + Ax * deltaT;
		double nvy = this.myYVel + Ay * deltaT;
		double nx = this.myXPos + deltaT * nvx;
		double ny = this.myYPos + deltaT * nvy;
		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}

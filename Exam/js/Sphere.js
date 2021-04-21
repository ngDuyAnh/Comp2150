
class Sphere extends Shape
{
    #radius = undefined;

    constructor(radius)
    {
        super("Sphere", "3D");
        this.#radius = radius;
    }

    volume()
    {
        return (4 / 3) * Math.PI * Math.pow(this.#radius, 3);
    }

    area()
    {
        return 4 * Math.PI * Math.pow(this.#radius, 2);
    }

    report()
    {
        super.report();
        console.log("Area: " + this.area());
        console.log("Volume: " + this.volume());
    }

}

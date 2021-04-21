
class Rectangle extends Shape
{
    #length = undefined;
    #width = undefined;

    constructor(length, width)
    {
        super("Rectangle", "2D");
        this.#length = length;
        this.#width = width;
    }

    // Getter method
    length()
    {
        return this.#length;
    }
    width()
    {
        return this.#width;
    }

    isSquare()
    {
        let square = false;

        if (Math.abs(this.#width - this.#length) < 0.1)
        {
            square = true
        }

        return square;
    }

    area()
    {
        return this.#width * this.#length;
    }

    report()
    {
        super.report();
        console.log("Area: " + this.area());
        console.log("Volume: " + 0);
        console.log("Square: " + this.isSquare());
    }

}

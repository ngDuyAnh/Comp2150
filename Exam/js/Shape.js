
class Shape
{
    #name = undefined;
    #dimension = undefined;

    constructor(shapeName, dimension)
    {
        if (new.target === Shape)
        {
            throw new Error("Cannot create an instance of abstract class");
        }

        this.#name = shapeName;
        this.#dimension = dimension;
    }

    name()
    {
        return this.#name;
    }

    dimension()
    {
        return this.#dimension;
    }

    area()
    {
        throw new Error("Missing implementation of area() in concrete class");
        return 0;
    }

    report()
    {
        console.log("Shape Name: " + super.name());
        console.log("Shape type: " + super.dimension());
    }

}

class TestClass
{
    #a = null;

    constructor(A)
    {
        this.#a = A;
    }

    get all()
    {
        return this.#a;
    }

}

let test = new TestClass(10);

console.log(typeof test)
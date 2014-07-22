package shatteringstone.sandbox.patterns;

interface CarElementVisitor {
    void visit(Wheel wheel);

    void visit(Engine engine);

    void visit(Body body);

    void visit(Car car);
}

interface CarElement {
    void accept(CarElementVisitor visitor);
}

class Wheel implements CarElement {
    private final String name;

    public Wheel(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void accept(final CarElementVisitor visitor) {
        /*
         * accept(CarElementVisitor) in Wheel implements
         * accept(CarElementVisitor) in CarElement, so the call
         * to accept is bound at run time. This can be considered
         * the first dispatch. However, the decision to call
         * visit(Wheel) (as opposed to visit(Engine) etc.) can be
         * made during compile time since 'this' is known at compile
         * time to be a Wheel. Moreover, each implementation of
         * CarElementVisitor implements the visit(Wheel), which is
         * another decision that is made at run time. This can be
         * considered the second dispatch.
         */
        visitor.visit(this);
    }
}

class Engine implements CarElement {
    @Override
    public void accept(final CarElementVisitor visitor) {
        visitor.visit(this);
    }
}

class Body implements CarElement {
    @Override
    public void accept(final CarElementVisitor visitor) {
        visitor.visit(this);
    }
}

class Car implements CarElement {
    CarElement[] elements;

    public Car() {
        //create new Array of elements
        this.elements = new CarElement[] { new Wheel("front left"), new Wheel("front right"), new Wheel("back left"),
                new Wheel("back right"), new Body(), new Engine() };
    }

    @Override
    public void accept(final CarElementVisitor visitor) {
        for (CarElement elem : this.elements) {
            elem.accept(visitor);
        }
        visitor.visit(this);
    }
}

class CarElementPrintVisitor implements CarElementVisitor {
    @Override
    public void visit(final Wheel wheel) {
        System.out.println("Visiting " + wheel.getName() + " wheel");
    }

    @Override
    public void visit(final Engine engine) {
        System.out.println("Visiting engine");
    }

    @Override
    public void visit(final Body body) {
        System.out.println("Visiting body");
    }

    @Override
    public void visit(final Car car) {
        System.out.println("Visiting car");
    }
}

class CarElementDoVisitor implements CarElementVisitor {
    @Override
    public void visit(final Wheel wheel) {
        System.out.println("Kicking my " + wheel.getName() + " wheel");
    }

    @Override
    public void visit(final Engine engine) {
        System.out.println("Starting my engine");
    }

    @Override
    public void visit(final Body body) {
        System.out.println("Opening the door");
    }

    @Override
    public void visit(final Car car) {
        System.out.println("Starting my car");
    }
}

public class Visitor {
    static public void main(final String[] args) {
        Car car = new Car();
        CarElementPrintVisitor visitor = new CarElementPrintVisitor();
        car.accept(visitor);
        car.accept(new CarElementDoVisitor());
    }
}
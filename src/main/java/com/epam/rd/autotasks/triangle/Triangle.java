package com.epam.rd.autotasks.triangle;

public class Triangle {

    private Point a, b, c;
    private double ab, ac, bc;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.ab = lengthOfSegment(a, b);
        this.ac = lengthOfSegment(a, c);
        this.bc = lengthOfSegment(b, c);

        Point vectorAB = new Point(b.getX() - a.getX(), b.getY() - a.getY());
        Point vectorAC = new Point(c.getX() - a.getX(), c.getY() - a.getY());
        Point vectorBC = new Point(b.getX() - c.getX(), b.getY() - c.getY());
        Point vectorBC_1 = new Point(c.getX() - b.getX(), c.getY() - b.getY());

        double angleAB = getAngle(vectorAB, vectorAC);
        double angleAC = getAngle(vectorAB, vectorBC);
        double angleBC = getAngle(vectorAC, vectorBC_1);

// сумма длин двух сторон должна быть больше трете
        if (this.ab + this.ac < this.bc ||
                this.ab + this.bc < this.ac ||
                this.ac + this.bc < this.ab)
            throw new RuntimeException("The triangle possibly degenerate");


// сумма углов равна 180 и не равно 0
        if (Math.abs(angleAB + angleAC + angleBC - 180) >= 1.e-8 ||
                Math.abs(angleAB) <= 1.e-5 || Math.abs(angleAC) <= 1.e-5||  Math.abs(angleBC) <= 1.e-5 ||
                Math.abs(angleAB-180) <= 1.e-5 || Math.abs(angleAC-180) <= 1.e-5||  Math.abs(angleBC-180) <= 1.e-5
        )
            throw new RuntimeException("The triangle possibly degenerate");

    }

    public double getAngle(Point a, Point b) {
        return Math.toDegrees(Math.acos((a.getX() * b.getX() + a.getY() * b.getY()) /
                (Math.sqrt(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)) * Math.sqrt(Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2)))));
    }

    double lengthOfSegment(Point start, Point end) {
        double distanceX = StrictMath.pow(start.getX() - end.getX(), 2);
        double distanceY = StrictMath.pow(start.getY() - end.getY(), 2);
        return Math.sqrt(distanceX + distanceY);
    }

    public double area() {
        double p = (ab + ac + bc) / 2;
        double area = Math.sqrt((p - ab) * (p - ac) * (p - bc) * p);
        return area;
    }

    Point centroid() {
        return new Point((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
    }


}


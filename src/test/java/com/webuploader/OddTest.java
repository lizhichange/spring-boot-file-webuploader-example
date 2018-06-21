package com.webuploader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OddTest {

    public static void main(String[] args) {

        C f = new C(1.55, 3.40, 5.25);

        Y y = new Y();
        y = y.cl(100, 1.75, 3.40, 5.50);

        f.cl(100, y);


    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class C {


        double a;
        double b;
        double c;

        void cl(double amount, Y y) {
            double v = amount / a;

            double v1 = amount / b;

            double v2 = amount / c;

            double v3 = (amount / a) + y.getB() + y.getC();

            System.out.println("中胜-英平-英负:" + v3);

            double v4 = (amount / b) + y.getA() + y.getC();
            System.out.println("中平-英胜-英负:" + v4);

            double v5 = (amount / c) + y.getA() + y.getB();

            System.out.println("中负-英胜-英平:" + v5);
        }
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Y {

        double a;
        double b;
        double c;

        Y cl(double amount, double a, double b, double c) {
            Y f = new Y();
            f.setA(amount / a);
            f.setB(amount / b);
            f.setC(amount / c);
            return f;
        }

    }
}

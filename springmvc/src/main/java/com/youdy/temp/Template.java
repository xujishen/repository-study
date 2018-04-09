package com.youdy.temp;

public abstract class Template {

    abstract void eat();

    void  drink() {
        System.out.println("drink");
    }

    void print(I i) {
        eat();
        i.go();
        drink();
    }

    public static void main(String[] args) {
        Template t = new Template() {
            @Override
            void eat() {
                System.out.println("eat");
            }
        };
        t.print(new I() {
            @Override
            public void go() {
                System.out.println("go");
            }
        });
    }

}

interface I {
    void go();
}


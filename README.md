# The Mysterious Ticking Noise - hw4

## Project Description
This project implements a countdown simulation where various entities react in real-time to time ticks managed by a central countdown object.

## Design Patterns
The system is based on the Observer pattern to manage the ongoing update mechanism, integrates the State pattern to manage the cyclic sequence of each entity, and uses the Wrapper (Decorator) pattern to dynamically extend the capabilities of the listeners in the bonus sections.

## How to Run
The project is packaged in the `hw4.jar` file and is ready to be executed via the command line. You can (optionally) pass an integer as an argument to define the length of the countdown (the default is 160 ticks).

**To run the base simulation (`Main`):**

    java -jar hw4.jar
    java -jar hw4.jar 24

**To run the bonus simulation (`BonusMain`):**

    java -cp hw4.jar com.mysteryticking.BonusMain
    java -cp hw4.jar com.mysteryticking.BonusMain 40
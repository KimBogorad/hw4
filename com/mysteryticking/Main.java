package com.mysteryticking;

public class Main {
    private final static int DEFAULT_COUNTDOWN = 160;

    public static void main(String[] args) {
        int countdown = DEFAULT_COUNTDOWN;
        if (args.length > 0) {
            try {
                countdown = Integer.parseInt(args[0]);
            } catch (Exception e) {
                String errmsg = "Warning: Argument is not a valid integer. Using default countdown ("+ DEFAULT_COUNTDOWN+ ").";
                System.err.println(errmsg);
            }
        }
        PipeBomb pipebomb = new PipeBomb(countdown);
        buildEntities(pipebomb);
        pipebomb.start();
    }

    public static void buildEntities(PipeBomb pb) {
        // Snape:
        StateMachineEntity sme = new StateMachineEntity("Snape", 1, 
            new State("Snape", 2),
            new State("Snape", 2),
            new State("Severus Snape", 4));
        pb.register(sme);

        // Dumbledore:
        sme = new StateMachineEntity("Dumbledore", 16, 
            new State("Dumbledore", 8));
        pb.register(sme);

        // Ron:
        sme = new StateMachineEntity("Ron", 33, 
            new State("Ron", 2),
            new State("Ron", 2),
            new State("Ron Weasly", 4));
        pb.register(sme);

        // Hermione:
        sme = new StateMachineEntity("Hermione", 49, 
            new State("Hermione",4),
            new State("Hermione",4), 
            new State("Hermione",2),
            new State("Hermione",2), 
            new State("Hermione",4));
        pb.register(sme);

        // Harry Potter:
        sme = new StateMachineEntity("Harry", 65, 
            new State("Harry Potter",1), 
            new State("Harry Potter",1),
            new State("OoOohhhh",2), 
            new State("Harry Potter",1), 
            new State("Harry Potter",1), 
            new State("YeeeEeaahH",2));
        pb.register(sme);

        // Bonus1:
        sme = new StateMachineEntity("Bonus1", 1, 
            new State("BANANAS", 1));
        pb.register(new FiniteEntity(sme, pb, 5));

        // Bonus2:
        ConditionalEntity ce = new ConditionalEntity(tick -> tick%10 == 0, "got milk?");
        pb.register(ce);
        ConditionalEntity ce2 = new ConditionalEntity(tick -> tick%15 == 0, "got cookies?");
        pb.register(ce2);

        //blank line:
        pb.register(tick -> {
            if(tick%8 == 0) System.out.println(); 
        });
    }
}
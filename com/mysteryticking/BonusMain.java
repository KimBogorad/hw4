package com.mysteryticking;

public class BonusMain {
    public static void main(String[] args) {
        int countdown = 160;
        if (args.length > 0) {
            try {
                countdown = Integer.parseInt(args[0]);
            } catch (Exception e) {
                String errmsg = "Warning: Argument is not a valid integer. Using default countdown ("+ countdown+ ").";
                System.err.println(errmsg);
            }
        }
        PipeBomb pipebomb = new PipeBomb(countdown);
        buildEntities(pipebomb);
        pipebomb.start();
    }

    public static void buildEntities(PipeBomb pb) {
        // Bonus1:
        StateMachineEntity sme = new StateMachineEntity("Bonus1", 1, 
            new State("I AM TEMPORARY", 1));
        pb.register(new FiniteEntity(sme, pb, 5));

        // Bonus2:
        ConditionalEntity ce = new ConditionalEntity(tick -> tick%10 == 0, "divisible by 10");
        pb.register(ce);
        ConditionalEntity ce2 = new ConditionalEntity(tick -> tick%15 == 0, "divisible by 15");
        pb.register(ce2);

    }
    
}

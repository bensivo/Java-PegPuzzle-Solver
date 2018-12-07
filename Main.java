import java.util.List;

import pegboard.PegBoard;
import pegboard.Solver;
import pegboard.Move;

public class Main {
    public static void main (String[] args) {
        // The first 5 solutions are all that you need. 
        // All teh other configurations are just these solutions with a 1/3 rotation
        for(int i = 0; i<5; i++) {  
            PegBoard pegboard = new PegBoard(i);
            List<Move> moves = Solver.solve(pegboard);
            
            // Initial state
            System.out.println("=========");
            System.out.println("=========");
            System.out.println();
            System.out.println("Option " + i + ":");
            System.out.println(pegboard.toTriangle());

            // Print the state of the pegboard after each move
            for(Move move: moves) {
                pegboard.applyMove(move);

                System.out.println();
                System.out.println(move.toString());
                System.out.println(pegboard.toTriangle());
            }
        }
    }
}

package pegboard;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

import pegboard.Move;
import pegboard.PegBoard;


public class Solver {

    /**
     * Recursive implementation of the solve function
     */
    public static List<Move> solve(PegBoard pegboard) {

        // Base case, pegboard is already solved
        if(pegboard.getScore() == 14) {
            return new ArrayList<Move>();
        }


        // Try all of the moves on the current pegboard, recursively calling solve on the result
        for(Move move: Move.ALL_MOVES) {
            if(pegboard.canApplyMove(move)) {
                PegBoard nextBoard = new PegBoard(pegboard); // Nextboard has 1 less peg than this board
                nextBoard.applyMove(move);

                // Try to solve the smaller pegboard
                List<Move> otherMoves = solve(nextBoard);
                if(otherMoves == null){ // The smaller pegboard was unsolvable
                    continue;
                }

                // The smaller pegboard (nextBoard) was actually solved
                otherMoves.add(0, move);
                return otherMoves;
            }
        }

        // Base case, pegboard is not solved, but there are no more moves to try
        return null;
    }
}

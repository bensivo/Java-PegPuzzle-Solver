package pegboard;

import java.util.List;
import java.util.ArrayList;

import java.util.Arrays;

import pegboard.Move;

/**
 * A Pegboard is a 15-slot triangular set of pegs, containing pegs labeled 1-14 inserted into the slots
 *
 * {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14}
 *          ||
 *          \/
 *          0
 *         1 2
 *        3 4 5
 *       6 7 8 9
 *      0 1 2 3 4
 */
public class PegBoard {
    private int score;
    private boolean[] pegs;

    /** 
     * Default pegboard is empty
     */
    private PegBoard() {
        this.pegs = new boolean[15];
        Arrays.fill(pegs, false);
        this.score = 15;
    }
    
    /**
     * Make a pegboard, specifying only the true values by their integer indicies
     */
    public PegBoard(int[] filled) {
        this();
        for(int p: filled) {
            this.pegs[p] = true;
            this.score--;
        }
    }
    
    /**
     * Make a pegBoard with all the pieces filled in except one
     */
    public PegBoard(int i) {
        this.pegs = new boolean[15];
        Arrays.fill(pegs, true);
        this.score = 0;

        this.remove(i);
    }

    /**
     * A copy constructor
     */
    public PegBoard (PegBoard board) {
        this.pegs = Arrays.copyOf(board.pegs, 15);
        this.score = board.score;
    }
    
    /**
     * Return true if the given move can be made on this pegboard
     */
    public boolean canApplyMove(Move m) {
        return ( pegs[m.from] && pegs[m.over] && !pegs[m.to] );
    }

    /**
     * Apply a move to the pegboard, if one is possible
     *
     * Returns: true if move was applied, false if not
     */
    public void applyMove(Move m) {
        if( this.canApplyMove(m) ) {
            this.remove(m.over);
            this.movePiece(m.from, m.to);
        }
    }

    /**
     * Returns the score, where score is teh number of empty spaces in the board.
     * All pegboards have a score of at least 1. A score of 14 is a winning score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Remove the piece at index i
     */
    private void remove(int i) {
        if(!this.pegs[i])
            return;
        this.pegs[i] = false;
        this.score++;
        return;
    }

    /**
     * Move a piece from src to dest. 
     * This is NOT the same as applyMove.
     */
    private void movePiece(int src, int dest) {
        if(this.pegs[src] && !this.pegs[dest])
        {
            this.pegs[src] = false;
            this.pegs[dest] = true;
        }
    }

    public String toString() {
        String s = this.score + " : [";
        for(boolean b: pegs){
            s += b ? "1," : "0,";
        }
        s += "]";
        return s;
    }

    /**
     * Prints this triangle represntation of the pegboard using X's and O's
     * 123456789
     *     0     
     *    1 2      
     *   3 4 5    
     *  6 7 8 9  
     * 0 1 2 3 4
     */
    public String toTriangle() {
        String s = "";

        //    012345678
        s += "    4    \n"; // + 0
        s += "   3 5   \n"; // + 10
        s += "  2 4 6  \n"; // + 20
        s += " 1 3 5 7 \n"; // + 30
        s += "0 2 4 6 8\n"; // + 40

        char[] cs = s.toCharArray();
        cs[4] = this.pegs[0] ? 'X' : 'O';
        cs[13] = this.pegs[1] ? 'X' : 'O';
        cs[15] = this.pegs[2] ? 'X' : 'O';
        cs[22] = this.pegs[3] ? 'X' : 'O';
        cs[24] = this.pegs[4] ? 'X' : 'O';
        cs[26] = this.pegs[5] ? 'X' : 'O';
        cs[31] = this.pegs[6] ? 'X' : 'O';
        cs[33] = this.pegs[7] ? 'X' : 'O';
        cs[35] = this.pegs[8] ? 'X' : 'O';
        cs[37] = this.pegs[9] ? 'X' : 'O';
        cs[40] = this.pegs[10] ? 'X' : 'O';
        cs[42] = this.pegs[11] ? 'X' : 'O';
        cs[44] = this.pegs[12] ? 'X' : 'O';
        cs[46] = this.pegs[13] ? 'X' : 'O';
        cs[48] = this.pegs[14] ? 'X' : 'O';

        return new String(cs);
    }
}

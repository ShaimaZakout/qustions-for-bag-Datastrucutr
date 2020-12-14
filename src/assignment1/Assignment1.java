/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Random;

/**
 *
 * @author hp
 */
public class Assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        BagInterface<Integer> cards = new ArrayBag(6);
        Random a = new Random();
        for (int i = 0; i < 6; i++) {
            cards.add(a.nextInt(13));

        }
        Object[] o = cards.toArtay();
        for (int i = 0; i < cards.getCurrentSize(); i++) {
            System.out.println(o[i]);
        }
        BagInterface<Integer> player1 = new ArrayBag(6);
        BagInterface<Integer> player2 = new ArrayBag(6);

        int x, y;
        do {
            x = a.nextInt(13);
            y = a.nextInt(13);
            System.out.println("player1 = " + x + "  " + "player2 = " + y);
            if (cards.contains(x)) {
                player1.add(x);
                cards.removeEntry(x);
            }
            if (cards.contains(y)) {
                player1.add(y);
                cards.removeEntry(y);
            }
        } while (!cards.isEmpty());
        {
            System.out.println("     End     ");
        }
        System.out.println("score player 1 = " + player1.getCurrentSize());
        System.out.println("score player 2 = " + player2.getCurrentSize());
        if (player1.getCurrentSize() > player2.getCurrentSize()) {
            System.out.println("the win is player 1");
        } else if (player1.getCurrentSize() < player2.getCurrentSize()) {
            System.out.println("the win is player 1");
        } else {
            System.out.println("تعادل ");
        }
    }
    
}

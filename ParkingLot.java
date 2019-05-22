package com.dsa;

import java.util.HashMap;

public class ParkingLot {
    public static void main(String args[]){
        //int[] curr = {1, 3, 0, 4, 2, 5, 6, 7};
        //int[] desired = {3, 1, 4, 2, 0, 6, 5, 7};
        int[] curr = {1, 2, 3, 0};
        int[] desired = {1, 0 ,2, 3};
        ParkingLot p = new ParkingLot();
        System.out.print("Before Reordering:");
        for(int i=0; i<curr.length; i++){
            System.out.print(curr[i]);
            System.out.print(",");
        }
        System.out.println("");
        int moves = p.reorderParking(curr, desired);
        System.out.print("After Reordering:");
        for(int i=0; i<curr.length; i++){
            System.out.print(curr[i]);
            System.out.print(",");
        }
        System.out.println("");
        System.out.println("Number of moves: "+moves);
    }

    public int reorderParking(int[] curr, int[] desired){
        int currLength = curr.length;
        int emptyIndex = -1;
        int moves = 0;
        boolean skip = false;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for(int i=0; i<currLength; i++){
            hm.put(curr[i], i);
            if(curr[i] == 0)
                emptyIndex = i;
        }

        for(int i=0; i<currLength; i++){
            if(curr[i] != desired[i]){
                if(curr[i] == 0 || desired[i] == 0){
                    int desiredIndex = hm.get(desired[i]);
                    emptyIndex = (curr[i] == 0) ? desiredIndex : i;
                    int tmp = curr[i];
                    curr[i] = curr[desiredIndex];
                    curr[desiredIndex] = tmp;
                    hm.replace(curr[i], i);
                    hm.replace(curr[desiredIndex], desiredIndex);
                    moves++;
                }
                else{
                    int desiredIndex = hm.get(desired[i]);
                    curr[emptyIndex] = curr[i];
                    hm.replace(0, i);
                    hm.replace(curr[i], emptyIndex);
                    curr[i] = 0;
                    emptyIndex = i;

                    curr[i] = curr[desiredIndex];
                    hm.replace(curr[i], i);
                    hm.replace(0, desiredIndex);
                    curr[desiredIndex] = 0;
                    emptyIndex = desiredIndex;

                    moves = moves + 2;
                }
            }
        }
        return moves;
    }
}

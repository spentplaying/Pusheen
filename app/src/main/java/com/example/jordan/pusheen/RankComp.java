package com.example.jordan.pusheen;

import java.util.Comparator;

public class RankComp implements Comparator<Rank> {
    @Override
    public int compare(Rank o1, Rank o2) {
        int x=Integer.parseInt(o1.times) , y=Integer.parseInt(o2.times);
        if(x==y) return 0;
        else if(x>y) return -1;
        else return 1;
    }
}

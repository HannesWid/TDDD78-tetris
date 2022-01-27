package se.liu.tetris.tetris;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Highscore>
{
    @Override
    public int compare(Highscore o1, Highscore o2) {
	if(o1.score < o2.score){
	    return 1;
	}
	if(o1.score > o2.score){
	    return -1;
	}
	else{
	    return o1.name.compareTo(o2.name);
	}
    }
}

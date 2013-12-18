package com.fbergeron.ai;

import com.fbergeron.card.Stack;
import com.fbergeron.solitaire.GameState;
import com.fbergeron.solitaire.SequentialStack;
import com.fbergeron.solitaire.SolitaireStack;
import com.fbergeron.solitaire.Solitaire;
import com.fbergeron.card.ClassicCard;
import com.fbergeron.card.Card;

public class AI{
	private GameState currState;
	public AI(){
		
	}
	public AI(GameState gs){
		this.currState = gs;
	}
	public void updateState(GameState gameState){
		this.currState = gameState;
	}
	public int getEval(){
		return getEval(currState);
	}
	public int getEval(GameState gs){
		Stack revealedCards = gs.getRevealedCards();
		SolitaireStack[] solStack = gs.getSolStack();
		SequentialStack[] seqStack = gs.getSeqStack();
        SolitaireStack s1;
        SolitaireStack s2;
        ClassicCard card1;
        ClassicCard card2;
		int eval = 0;
        int minDist;
        int numDown = 0;
        int ratio = 100;
		for(int i=0;i<Solitaire.SOL_STACK_CNT;i++){
            minDist = 14;
            s1 = solStack[i];
            card1 = (ClassicCard)s1.top();
            if(card1==null) continue;
            for(Card  c:s1)
                if(c.isFaceDown()) numDown++;
		    for(int j=i+1;j<Solitaire.SOL_STACK_CNT;j++){
                s2 = solStack[j];
                card2 = (ClassicCard)s2.top();
                if(card2==null) continue;
                if(card1.equals(card2)) continue;
                boolean gate1 = (Math.abs(card1.getValue().getValue()-card2.getValue().getValue())/2)==0;
                boolean gate2 = card1.getColor()==card2.getColor();
                if(gate1^gate2){
                    int dist = Math.abs(card1.getValue().getValue()- card2.getValue().getValue());
                    if(dist <minDist)  minDist = dist;
                }
            }
            eval = eval + minDist;
		}
        eval = eval + ratio*(numDown);
		System.out.println("eval = "+ eval);
		return eval;
	}
}

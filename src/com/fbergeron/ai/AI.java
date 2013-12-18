package com.fbergeron.ai;

import com.fbergeron.card.Stack;
import com.fbergeron.solitaire.GameState;
import com.fbergeron.solitaire.SequentialStack;
import com.fbergeron.solitaire.SolitaireStack;
import com.fbergeron.card.ClassicCard;

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
        ClassicCard card1;
        ClassicCard card2;
		int eval = 0;
        int minDist = 14;
		for(SolitaireStack s1:solStack){
            card1 = (ClassicCard)s1.top();
            System.out.println(card1);
            for(SolitaireStack s2:solStack){
                card2 = (ClassicCard)s2.top();
                System.out.println(card2);
            }
            
		}
		System.out.println("eval = "+ eval);
		return eval;
	}
}

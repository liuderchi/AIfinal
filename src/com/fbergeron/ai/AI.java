package com.fbergeron.ai;

import java.util.*;
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
        int[] head = new int [Solitaire.SOL_STACK_CNT];
		for(int i=0;i<Solitaire.SOL_STACK_CNT;i++){
            s1 = solStack[i];
            if(s1.isEmpty()){
                head[i] = -1;
                continue;
            }
            for(int k=0;k<s1.cardCount();k++)
                if(s1.elementAt(k).isFaceDown()) numDown++;
                else{
                    head[i] = k;
                    break;
                }
        }
		for(int i=0;i<Solitaire.SOL_STACK_CNT;i++){
            minDist = 14;
            s1 = solStack[i];
            if(s1.isEmpty()) continue;
//            card1 = (ClassicCard)s1.top();
            card1 = (ClassicCard)s1.elementAt(head[i]);
            System.out.println("Card1 "+s1+" "+card1);
		    for(int j=0;j<Solitaire.SOL_STACK_CNT;j++){
                s2 = solStack[j];
                if(s2.isEmpty()) continue;
                card2 = (ClassicCard)s2.top();
                if(card1.getValue().getValue()>=card2.getValue().getValue()) continue;
//                card2 = (ClassicCard)s2.elementAt(head[j]);
                boolean gate1 = (Math.abs(card1.getValue().getValue()-card2.getValue().getValue())%2)==1;
                boolean gate2 = card1.getColor()==card2.getColor();
                if(gate1^gate2){
                    System.out.println("Card2 "+s2+" "+card2);
                    int dist = Math.abs(card1.getValue().getValue()- card2.getValue().getValue());
                    if(dist <minDist)  minDist = dist;
                }
            }
            if(minDist==14) minDist = 0;
            eval = eval + minDist;
		}
        eval = eval + ratio*(numDown);
		System.out.println("eval = "+ eval);
		return eval;
	}
}

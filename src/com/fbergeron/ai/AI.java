package com.fbergeron.ai;

import com.fbergeron.card.Stack;
import com.fbergeron.solitaire.GameState;
import com.fbergeron.solitaire.SequentialStack;
import com.fbergeron.solitaire.SolitaireStack;

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
		/*
		for(SolitaireStack s:solStack){
			System.out.println(s.cardCount() + ","+ s.firstFaceUp());
		}
		System.out.println("======");
		for(SequentialStack s:seqStack){
			System.out.println(s.cardCount() + ","+ s.firstFaceUp());
		}
		*/
		int eval = 0;
		for(SolitaireStack s:solStack){
			eval += s.firstFaceUp();
		}
		System.out.println("eval = "+ eval);
		return eval;
	}
}
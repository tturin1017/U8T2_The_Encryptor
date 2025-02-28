package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck(){
        String[] suits  = Utility.getSuits();
        String[] ranks = Utility.getRanks();

        for(int i =0; i < suits.length; i++){
            for(int j=0;j<ranks.length;j++){
                cards.add(new Card(ranks[j],suits[i]));
            }
        }
    }

    public  void shuffleDeck(){ //make students ceate a shuffle algorithm?
        Collections.shuffle(cards);
    }

    public  Card drawCard(){
        if(!cards.isEmpty()){
            return cards.remove(0);
        }
        Card c = cards.get(0);
        cards.remove(0);
        return c;
    }

    public  boolean isEmpty(){
        return cards.isEmpty();
    }

   


}
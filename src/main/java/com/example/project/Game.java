package com.example.project;
import java.util.ArrayList;


public class Game{
    
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int p1Result = Utility.getHandRanking(p1Hand);
        int p2Result = Utility.getHandRanking(p2Hand);
        String p1wins = "Player 1 wins!";
        String p2wins = "Player 2 wins!";
        String tie = "Tie!";
    

        if(p1Result>p2Result){
            return p1wins; //player two wins!
        }else if(p1Result<p2Result){
            return p2wins;
        }else{//we have a draw, we must determine who wins 
            //impossible for two players to both get royal flush 
            //impossible for two players to both get a straight flush 
            //impossible for two players to both have a 4 of a kind
            //impossible for two players to have 2 pair
            int p1_c1 = p1.getHand().get(0).getRankValue();
            int p1_c2 = p1.getHand().get(1).getRankValue();
            int p2_c1 = p2.getHand().get(0).getRankValue();
            int p2_c2 = p2.getHand().get(1).getRankValue();
            int p1_max = findMaxCard(p1_c1,p1_c2);
            int p2_max = findMaxCard(p2_c1, p2_c2);
            ArrayList<Integer> p1_rankFreqList = p1.findRankingFrequency();
            ArrayList<Integer> p2_rankFreqList = p2.findRankingFrequency();
            String p1_rank=""; String p2_rank="";
            String message = determineMaxWinner(p1_max, p2_max);
     
            
            if(p1Result== 8 && p2Result == 8){ //FULL HOUSE DRAW only possible is community cards is 3 of a kind, so find the max of the hands
                return message;
            }else if(p1Result == 7 && p2Result == 7){ //FLUSH DRAW. Determine who has the higher card in hand
                return message;
            }else if (p1Result == 6 && p2Result == 6){//STRAIGHT DRAW 
                return message;
            }else if(p1Result == 5 && p2Result == 5){ //THREE OF A KIND DRAW
                //find rank frequency list and deteremine who has the highest 3 pair 
                for(int i =0 ; i<p1_rankFreqList.size();i++){
                    if(p1_rankFreqList.get(i)==3){
                        p1_rank = Utility.getRanks()[i];
                    }else if(p2_rankFreqList.get(i)==3){
                        p2_rank = Utility.getRanks()[i];
                    }
                }
                if(Utility.getRankValue(p1_rank)>Utility.getRankValue(p2_rank)){
                    return p1wins;
                }else if(Utility.getRankValue(p1_rank)<Utility.getRankValue(p2_rank)){
                    return p2wins;
                }else{ //3 pair is the community cards so find the highest card 
                    return message; //max  between cards is calculated
                }
            }else if(p1Result == 4 && p2Result == 4){ //TWO PAIR DRAW
                int p1_i_firstPair  =-1; int p1_i_secondPair = -1; int p2_i_firstPair = -1; int p2_i_secondPair = -1;
                for(int i=0;i<p1_rankFreqList.size();i++){
                    if(p1_rankFreqList.get(i)==2){
                        if(p1_i_firstPair==-1){
                            p1_i_firstPair=i;
                        }else if(p1_i_firstPair!=-1 && p1_i_secondPair == -1){
                            p1_i_secondPair = i;
                        }
                    }
                    if(p2_rankFreqList.get(i)==2){
                        if(p2_i_firstPair==-1){
                            p2_i_firstPair=i;
                        }else if(p2_i_firstPair!=-1 && p2_i_secondPair == -1){
                            p2_i_secondPair = i;
                        }
                    }
                }

                int p1_firstPair_val = Utility.getRankValue(Utility.getRanks()[p1_i_firstPair]);
                int p1_secondPair_val = Utility.getRankValue(Utility.getRanks()[p1_i_secondPair]);
                int p2_firstPair_val = Utility.getRankValue(Utility.getRanks()[p2_i_firstPair]);
                int p2_secondPair_val = Utility.getRankValue(Utility.getRanks()[p2_i_secondPair]);

                if (p1_firstPair_val == p2_firstPair_val){
                    //check other two pairs to determine who is higher 
                    return determineMaxWinner(p1_secondPair_val, p2_secondPair_val);
                }else if(p1_firstPair_val == p2_secondPair_val){
                    return determineMaxWinner(p1_secondPair_val,p2_firstPair_val);
                }else if (p1_secondPair_val == p2_firstPair_val){
                    return determineMaxWinner(p1_firstPair_val,p2_secondPair_val);
                }else if (p1_secondPair_val == p2_secondPair_val){
                    return determineMaxWinner(p1_firstPair_val, p2_firstPair_val);
                }
            }else if(p1Result == 3 && p2Result == 3){ //PAIR DRAW. Find out which pair is the highest
                for(int i =0 ; i<p1_rankFreqList.size();i++){
                    if(p1_rankFreqList.get(i)==2){
                        p1_rank = Utility.getRanks()[i];
                    }
                    if(p2_rankFreqList.get(i)==2){
                        p2_rank = Utility.getRanks()[i];
                    }
                }

                if(Utility.getRankValue(p1_rank)>Utility.getRankValue(p2_rank)){
                    return p1wins;
                }else if(Utility.getRankValue(p1_rank)==Utility.getRankValue(p2_rank)){
                    return message; // if the community cards have the pair, then find the max high card between hands
                }else{
                    return p2wins;
                }
            }else if (p1Result == 2 && p2Result == 2){ //If they both have a high card, determine, which one is higher
                return message;
            }else if (p1Result == 1 && p2Result == 1){ //Both don't have anything. return tie
                return message;
            }
        }
        return "Error";
    }

    public static int findMaxCard(int c1, int c2){
        if(c1>c2){
            return c1;
        }else if (c2>c1){
            return c2;
        }else{ //its a tie 
            return c1;
        }
    }

    public static String determineMaxWinner(int p1, int p2){
        System.out.println(p1);
        System.out.println(p2);
        if(p1>p2){
            return "Player 1 wins!";
        }else if(p1<p2){
            return "Player 2 wins!";
        }else{
            return "Tie!";
        }
    }


    public static void play(){
            //initialize a deck
            Deck deck = new Deck();
            //initialize players 
            Player p1 = new Player();
            Player p2 = new Player ();
    
             // Deal two cards to each player
             for (int i = 0; i < 2; i++) {
                p1.addCard(deck.drawCard());
                p2.addCard(deck.drawCard());
            }
    
            // Deal three community cards
            ArrayList<Card> communityCards = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                communityCards.add(deck.drawCard());
            }
    
            // Print the community cards
            System.out.println("Community Cards: " + communityCards);
    
            // Print each player's hand
            System.out.println("Player 1's Hand: " + p1.getHand().toString());
            System.out.println("Player 2's Hand: " + p2.getHand().toString());
    
            // Evaluate each player's hand
            String p1Result = p1.playHand(communityCards);
            String p2Result = p2.playHand(communityCards);
    
            // Print the results
            System.out.println("Player 1's Hand Ranking: " + p1Result);
            System.out.println("Player 2's Hand Ranking: " + p2Result);
            
            //determine the winner 
            System.out.println(determineWinner(p1, p2, p1Result, p2Result, communityCards));
            
        }
        
        public static void main(String[] args) {
           
            Player player1 = new Player();
            Player player2 = new Player();
            
            player1.addCard(new Card("7", "♠"));
            player1.addCard(new Card("10", "♠"));
    
            player2.addCard(new Card("A", "♠"));
            player2.addCard(new Card("3", "♠"));

            
            // Community cards that could help form the flush
            ArrayList<Card> communityCards = new ArrayList<>();
            communityCards.add(new Card("J", "♠")); // Player 1 completes the flush with this card
            communityCards.add(new Card("J", "♥"));
            communityCards.add(new Card("Q", "♠"));
            
            // Player results after playing the hand
            String p1Result = player1.playHand(communityCards);
            String p2Result = player2.playHand(communityCards);
            System.out.println(p1Result);
            System.out.println(p2Result);
            
            // Determine the winner
            String winner = Game.determineWinner(player1, player2, p1Result, p2Result, communityCards);
            System.out.println(winner);
            
    }

}
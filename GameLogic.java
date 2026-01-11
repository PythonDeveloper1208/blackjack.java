import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {
  
  public static void main(String[] args) {
    
    final Deck DECK = new Deck();
    Card[] CARDS = DECK.getCards1D();
    CARDS = Random.shuffle(CARDS);
    Card[] DEALER_HAND = new Card[3];
    Card[] PLAYER_HAND = new Card[2];
    DEALER_HAND[0] = new Card("A", "spades");
    DEALER_HAND[1] = new Card("A", "hearts");
    DEALER_HAND[2] = new Card("10", "diamonds");
    PLAYER_HAND[0] = new Card("K", "diamonds");
    PLAYER_HAND[1] = new Card("Q", "clubs");

    CardHandsManager CARD_SUMMER = new CardHandsManager(DEALER_HAND, PLAYER_HAND);
    int dealerSum = CARD_SUMMER.getHandSum("player");
    System.out.println(dealerSum);
  }

}

class Random {
  
  public Random() {
    ;
  }
  
  public static int randint(int min, int max) {
    
    int num = (int) (Math.random() * (max - min + 1)) + min;
    return num;
    
  }
  
  public static Card[] shuffle(Card[] list) {
    
    ArrayList<Card> tempList = new ArrayList<>(Arrays.asList(list));
    ArrayList<Card> newList = new ArrayList<Card>();
    int max = list.length - 1;
    int addedElements = 0;
    
    while (tempList.size() > 0) {
      int num = Random.randint(0, max);
      Card element = tempList.get(num);
      newList.add(element);
      addedElements += 1;
      tempList.remove(element);
      max -= 1;
    }
    
    return newList.toArray(new Card[0]);
    
  }
  
}

class ListHelper {
  
  public ListHelper() {
    ;
  }
  
  public static boolean containsElement(String[] strings, String element) {
    
    for (int i = 0; i < strings.length; i++) {
      if (strings[i].equals(element)) {
        return true;
      }
    }
    return false;
    
  }
  
  public static boolean containsElement(Card[] cards, Card element) {
    
    for (int i = 0; i < cards.length; i++) {
      if (cards[i].equals(element)) {
        return true;
      }
    }
    return false;
    
  }  
  
}

class Card {
  
  public static final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
  public static final String[] suits = {"spades", "clubs", "diamonds", "hearts"};
  
  private final String rank;
  private final String suit;
  private int value;
  
  public Card(String rank, String suit) {
    
    if (!ListHelper.containsElement(ranks, rank) || !ListHelper.containsElement(suits, suit)) {
      throw new IllegalArgumentException();
    }
    
    this.rank = rank;
    this.suit = suit;
    
    if (ListHelper.containsElement(new String[]{"J", "Q", "K"}, rank)) {
      value = 10;
    }
    
    else if (rank.equals("A")) {
      value = 11;
    }
    
    else { // the rank must be a number
      value = Integer.parseInt(rank);
    }
    
  }
  
  public void setAceValue(int newValue) {
    
    if (!(this.rank.equals("A"))) {
      return;
    }
    
    if (newValue == 1 || newValue == 11) {
      this.value = newValue;
    }  
    
  }
  
  public <Type> Object getAttr(String attr) {
    
    switch (attr) {
      case "rank":
        return this.rank; // String
      case "suit":
        return this.suit; // String
      case "value":
        return this.value; // Integer
      default:
        throw new IllegalArgumentException();
    }
    
  }
  
  public String toString() {
    return this.rank + " of " + this.suit;
  }
  
  public boolean equals(Card other) {
    
    if (this.rank.equals(other.rank) && this.suit.equals(other.suit)) {
      return true;
    }
    return false;
    
  }
  
}

class Deck {
  
  private Card[] cards_1D;
  private Card[][] cards_2D;
  
  public Deck() {
    
    this.cards_1D = new Card[52];
    this.cards_2D = new Card[4][13];
    
    int count = 0;
    
    for (int row = 0; row < Card.suits.length; row++) {
      
      for (int col = 0; col < Card.ranks.length; col++) {
        
        this.cards_1D[count] = new Card(Card.ranks[col], Card.suits[row]);
        this.cards_2D[row][col] = new Card(Card.ranks[col], Card.suits[row]);
        count += 1;
        
      }
    }
    
  }
  
  public void printDeck() {
    
    for (int row = 0; row < this.cards_2D.length; row++) {
      
      for (int col = 0; col < this.cards_2D[row].length; col++) {
        
        System.out.print(this.cards_2D[row][col].toString() + " ");
        
      }
      
      System.out.println();
      
    }
    
  }
  
  public Card[] getCards1D() {
    return this.cards_1D;
  }
  
  public Card[][] getCards2D() {
    return this.cards_2D;
  }
  
}

class Dealer {
  //unfinished
  private String name;
  private ArrayList<Card> hand;

  public Dealer(String name) {
    
    this.name = name;
    this.hand = new ArrayList<Card>();

  }
  
  public void tableAction(Card[] cards, String action, int bet) {
    // unfinished
    boolean canSplit = false;
    boolean canDouble = false;
    
    if (cards.length == 2) {
      
    }
    
  }
  
}

class CardHandsManager {
  // unfinished
  private ArrayList<Card> dealerHand;
  private ArrayList<Card> playerHand;
  private int dealerSum = 0;
  private int playerSum = 0;
  
  public CardHandsManager(Card[] dealer, Card[] player) {
    this.dealerHand = new ArrayList<>(Arrays.asList(dealer));
    this.playerHand = new ArrayList<>(Arrays.asList(player));
  }
  
  public int getHandSum(String handOwner) {
    
    ArrayList<Card> hand;
    
    if (handOwner.equals("dealer")) {
      hand = this.dealerHand;
    }
    
    else if (handOwner.equals("player")) {
      hand = this.playerHand;
    }
    
    else {
      throw new IllegalArgumentException();
    }
    
    int sum = 0;
    
    for (int i = 0; i < hand.size(); i++) {
      
      Card currentCard = hand.get(i);
      int cardValue = ((Integer) (currentCard.getAttr("value"))).intValue();
      if (sum + cardValue > 21 && cardValue == 11) {
        currentCard.setAceValue(1);
        cardValue = 1;
      }
      sum += cardValue;
    }
    
    return sum;
    
  }
  
  public String actionResult(String handOwner) {
    
    ArrayList<Card> hand;
    
    switch (handOwner) {
      case "dealer":
        hand = this.dealerHand;
        break;
      case "player":
        hand = this.playerHand;
        break;
      default:
        throw new IllegalArgumentException();
    }
    
    int handSum = getHandSum(handOwner);
    String result = handOwner.substring(0, 1).toUpperCase() + handOwner.substring(1) + ": ";
    if (handSum > 21) {
      result += "bust";
    }
    
    else if (handSum == 21) {
      result += "Blackjack";
    }
    
    else {
      result += handSum;
    }
    
    return result;
    
  }
  
}

class Player {
  
  private String name;
  private int balance;
  
  public Player(String name, int deposit) {
    
    this.name = name;
    this.balance = deposit;
    
  }
  
  public int placeBet(int bet) {
    
    if (bet > this.balance) {
      System.out.println("Not enough balance remaining. ");
      return 0;
    }
    
    balance -= bet;
    return bet;
    
  }
  
  public int modifyBalance(int amount) {
    
    this.balance += amount;
    return this.balance;
    
  }
  
}

class Game {
  
  public Game() {
    ;
  }
  
  public static void printRules() {
    
    String ruleText = "Welcome to Blackjack. \n" + 
                      "The rules are as follows: \n" + 
                      "Each card has a point value. \n" + 
                      "Number cards are worth their shown number and face cards are 10. \n" + 
                      "Aces are 1 or 11 (the best value is determined based on the situation). \n " + 
                      "The goal for everyone is to get as close to 21 as possible without going over. To win, you must get a higher score than the dealer. " + 
                      "The player places a bet before the game begins. \n" + 
                      "Once the bet is placed, the dealer begins dealing the cards. " + 
                      "The dealer deals two cards to the player, face down, and deals two cards to themself: one face up, one face down. \n" + 
                      "The player must now perform an action. \n" + 
                      "There are two main actions: Hit and Stand. \n" + 
                      "Hit: Draw a card from the deck. If your new total exceeds 21, you lose. Otherwise, you can continue playing. \n" + 
                      "Stand: Finalizing your current total, choosing to draw no more cards, and passing the turn to the dealer. \n" + 
                      "Additionally, there are three special actions that can be played under specific circumstances: Split, Surrender, and Double. \n" + 
                      "Split: If your first two cards have the same value, then you can split your hand into two hands and double your bet. \n" + 
                      "For simplicity, the resulting two hands cannot be split again. \n" + 
                      "Surrender: For your very first action and only your very first action, you may forfeit your hand in exchange for 50% of your original bet. \n" + 
                      "Double: On your first turn, you may double your bet and draw exactly 1 card, and then stand and draw no more cards. The dealer then plays. ";


    
  }
  
}

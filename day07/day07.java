package day07;

import utils.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day07 {
    final static String day = "07";

    public static void main(String[] args) {
        List<String> lines = utils.readInput(day);

        System.out.println(partOne(lines));
        System.out.println(partTwo(lines));
    }

    public static int partOne(List<String> lines) {
        HashMap<String, String> handBidMap = new HashMap<>();

        for (String s : lines) {
            String[] handBid = s.split(" ");
            handBidMap.put(handBid[0], handBid[1]);
        }

        ArrayList<Integer> handTypes = new ArrayList<>();
        ArrayList<Integer> handValues = new ArrayList<>();
        ArrayList<Integer> bids = new ArrayList<>();
        handTypes.add(Integer.MAX_VALUE);
        handValues.add(Integer.MAX_VALUE);
        bids.add(Integer.MAX_VALUE);
        for (Map.Entry<String, String> m : handBidMap.entrySet()) {
            int handType = getHandType(m.getKey(), false);
            int handValue = getHandValue(m.getKey(), false);
            for (int i = 0; i < handTypes.size(); i++) {
                if (handType < handTypes.get(i) || handType == handTypes.get(i) && handValue < handValues.get(i)) {
                    handTypes.add(i, handType);
                    handValues.add(i, handValue);
                    bids.add(i, Integer.parseInt(m.getValue()));
                    break;
                }
            }
        }

        bids.removeLast();
        int total = 0;
        for (int i = 0; i < bids.size(); i++) {
            total += (i + 1) * bids.get(i);
        }

        return total;
    }

    public static int getHandType(String hand, boolean usePartTwoJokerRules) {
        HashMap<Character, Integer> cardCounts = new HashMap<>();

        // Count occurence of characters
        for (int i = 0; i < hand.length(); i++) {
            Character c = hand.charAt(i);
            cardCounts.putIfAbsent(c, 0);
            cardCounts.put(c, cardCounts.get(c) + 1);
        }

        ArrayList<Character> cards = new ArrayList<>();
        ArrayList<Integer> sortedCardCounts = new ArrayList<>();
        sortedCardCounts.add(-1);
        for (Map.Entry<Character, Integer> cardCount : cardCounts.entrySet()) {
            for (int i = 0; i < sortedCardCounts.size(); i++) {
                if (cardCount.getValue() > sortedCardCounts.get(i)) {
                    cards.add(i, cardCount.getKey());
                    sortedCardCounts.add(i, cardCount.getValue());
                    break;
                }
            }
        }
        sortedCardCounts.removeLast();

        if (usePartTwoJokerRules) {
            int jokerIndex = cards.indexOf('J');
            int jokerCount = 0;
            if (jokerIndex != -1) {
                jokerCount = sortedCardCounts.remove(jokerIndex);
            }
            if (jokerCount == 5) {
                return 6;
            }

            sortedCardCounts.set(0, sortedCardCounts.get(0) + jokerCount);
        }

        int type = 0;
        for (int i = 0; i < sortedCardCounts.size(); i++) {
            switch (sortedCardCounts.get(i)) {
                case 5:
                    type += 6;
                    break;
                case 4:
                    type += 5;
                    break;
                case 3:
                    type += 3;
                    break;
                case 2:
                    type += 1;
                    break;
                default:
                    break;
            }
        }

        return type;
    }

    public static final Map<Character, Integer> partOneCardOrder = Map.ofEntries(Map.entry('2', 1), Map.entry('3', 2),
            Map.entry('4', 3), Map.entry('5', 4), Map.entry('6', 5), Map.entry('7', 6), Map.entry('8', 7),
            Map.entry('9', 8), Map.entry('T', 9), Map.entry('J', 10), Map.entry('Q', 11), Map.entry('K', 12),
            Map.entry('A', 13));
    public static final Map<Character, Integer> partTwoCardOrder = Map.ofEntries(Map.entry('J', 1), Map.entry('2', 2),
            Map.entry('3', 3), Map.entry('4', 4), Map.entry('5', 5), Map.entry('6', 6), Map.entry('7', 7),
            Map.entry('8', 8), Map.entry('9', 9), Map.entry('T', 10), Map.entry('Q', 11), Map.entry('K', 12),
            Map.entry('A', 13));

    public static int getHandValue(String hand, boolean usePartTwoCardOrder) {
        int handValue = 0;
        int len = 5;
        Map<Character, Integer> cardOrder = usePartTwoCardOrder ? partTwoCardOrder : partOneCardOrder;
        for (int i = 0; i < len; i++) {
            handValue += cardOrder.get(hand.charAt(i)) * Math.pow(20, len - i);
        }
        return handValue;
    }

    public static int partTwo(List<String> lines) {
        HashMap<String, String> handBidMap = new HashMap<>();

        for (String s : lines) {
            String[] handBid = s.split(" ");
            handBidMap.put(handBid[0], handBid[1]);
        }

        ArrayList<Integer> handTypes = new ArrayList<>();
        ArrayList<Integer> handValues = new ArrayList<>();
        ArrayList<Integer> bids = new ArrayList<>();
        handTypes.add(Integer.MAX_VALUE);
        handValues.add(Integer.MAX_VALUE);
        bids.add(Integer.MAX_VALUE);
        for (Map.Entry<String, String> m : handBidMap.entrySet()) {
            int handType = getHandType(m.getKey(), true);
            int handValue = getHandValue(m.getKey(), true);
            for (int i = 0; i < handTypes.size(); i++) {
                if (handType < handTypes.get(i) || handType == handTypes.get(i) && handValue < handValues.get(i)) {
                    handTypes.add(i, handType);
                    handValues.add(i, handValue);
                    bids.add(i, Integer.parseInt(m.getValue()));
                    break;
                }
            }
        }

        bids.removeLast();
        System.out.println(bids.toString());
        int total = 0;
        for (int i = 0; i < bids.size(); i++) {
            total += (i + 1) * bids.get(i);
        }

        return total;
    }
}

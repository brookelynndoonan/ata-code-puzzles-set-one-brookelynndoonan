package com.kenzie.breadthfirstsearch.wordsearch;

import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Coordinate;
import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Direction;

import java.util.*;

import static com.kenzie.breadthfirstsearch.wordsearch.SampleWordSearches.ABRACADABRA_SEARCH;
import static com.kenzie.breadthfirstsearch.wordsearch.SampleWordSearches.SORE_SEARCH;

/**
 * Class for completing word search puzzles.
 */
public class WordSearcher {
    private final WordSearch wordSearch;

    /**
     * Create a word search instance for the provided problem.
     *
     * @param wordSearch - the word search puzzle to solve
     */
    public WordSearcher(WordSearch wordSearch) {
        this.wordSearch = wordSearch;
    }

    /**
     * Main method for manual testing.
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        WordSearcher wordSearcher = new WordSearcher(ABRACADABRA_SEARCH);

        System.out.println(wordSearcher.calculateWordCounts());
    }

    /**
     * Calculate how many ways (permutations) you can use the letters in the puzzle to spell
     * each word provided as part of the puzzle.
     *
     * @return a Map of the desired words, and how many ways (permutations) you can use the letters in the puzzle to
     * spell each word provided as part of the puzzle.
     */

    // Michael helped me round out some counting issues I was having throughout and helped me with my else statement.
    // Jacobus went over the pseudo code for the double breadth first search in friday Q and A, which was incredibly
    // helpful.
    public Map<String, Long> calculateWordCounts() {
        int column = wordSearch.getHeight();
        int row = wordSearch.getWidth();
        List<String> wordsToFind = wordSearch.getWordsToFind();
        String word = "";

        if (wordsToFind.size() == 0 || wordsToFind.get(0).isEmpty()) {
            return Collections.emptyMap();
        }
        Coordinate coordinate;
        int letterCounter = 0;
        int counter = 0;

        Queue<Coordinate> currentLevel = new ArrayDeque<>();
        Queue<Coordinate> nextLevel = new ArrayDeque<>();

        Map<String, Long> outPut = new HashMap<>();


        for (String words : wordSearch.getWordsToFind()) {
            Long wordCount = 0L;
            outPut.put(words, 0l);
            for (int i = 0; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    counter = 0;
                    word = String.valueOf(words.charAt(0));
                    char currentLetter = words.charAt(0);
                    if (currentLetter == wordSearch.getCharacterAt(new Coordinate(i, j))) {
                        currentLevel.add(new Coordinate(i, j));
                        letterCounter = 0;

                        while (!currentLevel.isEmpty()) {
                            coordinate = currentLevel.poll();

                            if (wordSearch.getCharacterAt(coordinate) == currentLetter) {
                                if (words.equals(word)) {
                                    counter++;
                                } else {
                                    for (Direction direction : Direction.ALL_DIRECTIONS) {
                                        Coordinate coordinates = direction.addToCoordinate(coordinate);
                                        int newRow = coordinates.getRow();
                                        int newCol = coordinates.getColumn();

                                        if (newRow >= 0 && newRow < wordSearch.getHeight() && newCol >= 0
                                                && newCol < wordSearch.getWidth()) {
                                            nextLevel.add(new Coordinate(newRow, newCol));
                                        }
                                    }
                                }

                            }
                            if (currentLevel.isEmpty()) {
                                letterCounter++;
                                if (letterCounter < words.length()) {
                                    currentLevel.addAll(nextLevel);
                                    nextLevel.clear();
                                    currentLetter = words.charAt(letterCounter);
                                    word += currentLetter;
                                }
                            }
                        }
                        wordCount += counter;
                        outPut.put(words, wordCount);
                    }

                }
            }

        }
        return outPut;
    }
}



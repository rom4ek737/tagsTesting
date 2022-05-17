package org.example;

import com.thoughtworks.gauge.*;
import com.thoughtworks.gauge.registry.StepRegistry;
import gauge.messages.Messages;
import org.apache.commons.lang.NotImplementedException;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {

    private static HashSet<Character> vowels;

    @BeforeSpec(tags = {"e2e"})
    public void setUp() {
        System.out.println("\nSetup");

    }

    @AfterSpec(tags = {"e2e"})
    public void tearDown() {
        System.out.println("\nTearDown");
    }

    @Step("Vowels in English language are <ololo>.")
    public void setLanguageVowels(String vowelString) {
        vowels = new HashSet<>();
        for (char ch : vowelString.toCharArray()) {
            vowels.add(ch);
        }
    }

    @Step("The word <word> has <expectedCount> vowels.")
    public void verifyVowelsCountInWord(String word, int expectedCount) {
        int actualCount = countVowels(word);
        assertThat(expectedCount).isEqualTo(actualCount);
    }

    @Step("Almost all words have vowels <wordsTable>")
    public void verifyVowelsCountInMultipleWords(Table wordsTable) {
        for (TableRow row : wordsTable.getTableRows()) {
            String word = row.getCell("Word");
            int expectedCount = Integer.parseInt(row.getCell("Vowel Count"));
            int actualCount = countVowels(word);

            assertThat(expectedCount).isEqualTo(actualCount);
        }
    }

    @Step("checking the enum values<type>")
    public void checkEnums(Type333 type) {
        if (type == Type333.ONE) {
            System.out.println("One");
        }

        if (type == Type333.TWO) {
            System.out.println("One");
        }
    }

    @Step("test csv <table>")
    public void testCsv(Table table) {
        for (TableRow row: table.getTableRows()) {
            System.out.println(row.getCell("start"));
        }
    }

    @Step("some table parameters <parameter>")
    public void steeep(String value) {
        //if (value.equalsIgnoreCase("One")) {
            System.out.println("Current value " + value);
        //}


    }

    @BeforeScenario(tags = {"skip"})
    public void skipBefore() {

    }

    @Step("skip execution <param>")
    public void skipTest(String param) {
        if (param.equalsIgnoreCase("TWO")) {
        }
    }

    private int countVowels(String word) {
        int count = 0;
        for (char ch : word.toCharArray()) {
            if (vowels.contains(ch)) {
                count++;
            }
        }
        return count;
    }
}

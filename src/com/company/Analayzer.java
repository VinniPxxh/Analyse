package com.company;

public class Analayzer {

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer textAnalyse : analyzers) {
            if (textAnalyse.processText(text) != Label.OK) {
                return textAnalyse.processText(text);
            }
        }
        return Label.OK;
    }

    public class SpamAnalyzer extends KeywordAnalyzer {
        public String[] keywords;

        public SpamAnalyzer(String[] keywords) {
            this.keywords = keywords;
        }

        @Override
        protected String[] getKeywords() {
            return keywords;
        }

        @Override
        protected Label getLabel() {
            return Label.SPAM;
        }
    }

    public class NegativeTextAnalyzer extends KeywordAnalyzer {
        private String[] keywordsNegative = {":(", "=(", ":|"};

        @Override
        protected String[] getKeywords() {
            return keywordsNegative;
        }

        @Override
        protected Label getLabel() {
            return Label.NEGATIVE_TEXT;
        }
    }

    public class TooLongTextAnalyzer implements TextAnalyzer {
        public int maxLength;

        public TooLongTextAnalyzer(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public Label processText(String text) {
            if (text.length() > maxLength) {
                return Label.TOO_LONG;
            }
            return Label.OK;
        }
    }

    public abstract class KeywordAnalyzer implements TextAnalyzer {
        protected abstract String[] getKeywords();

        protected abstract Label getLabel();

        @Override
        public Label processText(String text) {
            for (String keywords : getKeywords()) {
                if (text.contains(keywords)) {
                    return getLabel();
                }
            }
            return Label.OK;
        }
    }
}


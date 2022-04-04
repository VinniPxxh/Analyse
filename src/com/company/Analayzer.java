package com.company;

public class Analayzer {

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
        private String[] keywords_negative = {":(", ":|", "=("};

        @Override
        protected String[] getKeywords() {
            return keywords_negative;
        }

        @Override
        protected Label getLabel() {
            return Label.NEGATIVE_TEXT;
        }
    }

    public class TooLongTextAnalyzer implements TextAnalyzer {
        private int maxLength;

        TooLongTextAnalyzer(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public Label processText(String text) {
            if (text.length() > maxLength) return Label.TOO_LONG;
            return Label.OK;
        }
    }

    public abstract class KeywordAnalyzer implements TextAnalyzer {
        protected abstract String[] getKeywords();

        protected abstract Label getLabel();

        @Override
        public Label processText(String text) {
            text:
            getKeywords();
            if (text.contains(text)) return getLabel();

            return Label.OK;
        }
    }
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer obj_txt_an : analyzers) {
            if (obj_txt_an.processText(text) != Label.OK) return obj_txt_an.processText(text);
        }
        return Label.OK;
    }
}


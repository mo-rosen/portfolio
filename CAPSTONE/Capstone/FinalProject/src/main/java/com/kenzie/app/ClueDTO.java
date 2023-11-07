package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClueDTO {

    @JsonProperty("canon")
    private boolean canon;
    @JsonProperty("game")
    private Game game;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("invalidCount")
    private int invalidcount;
    @JsonProperty("gameId")
    private int gameid;
    @JsonProperty("categoryId")
    private int categoryid;
    @JsonProperty("value")
    private int value;
    @JsonProperty("question")
    private String question;
    @JsonProperty("answer")
    private String answer;
    @JsonProperty("id")
    private int id;

    public boolean getCanon() {
        return canon;
    }

    public void setCanon(boolean canon) {
        this.canon = canon;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getInvalidcount() {
        return invalidcount;
    }

    public void setInvalidcount(int invalidcount) {
        this.invalidcount = invalidcount;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Game {
        @JsonProperty("canon")
        private boolean canon;
        @JsonProperty("aired")
        private String aired;
        @JsonProperty("href")
        private String href;

        public boolean getCanon() {
            return canon;
        }

        public void setCanon(boolean canon) {
            this.canon = canon;
        }

        public String getAired() {
            return aired;
        }

        public void setAired(String aired) {
            this.aired = aired;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class Category {
        @JsonProperty("canon")
        private boolean canon;
        @JsonProperty("title")
        private String title;
        @JsonProperty("href")
        private String href;
        @JsonProperty("id")
        private int id;

        public boolean getCanon() {
            return canon;
        }

        public void setCanon(boolean canon) {
            this.canon = canon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

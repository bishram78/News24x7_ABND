package com.nano.degree.bishram.news24x7;

class GuardianNews {
    /* String for the Headline of the Guardian News */
    private String headline;

    /* String for the Author for the main news of the Guardian News. */
    private String author;

    /* String for the Section Name of the Guardian News. */
    private String sectionName;

    /* String for the Published date of the Guardian News. */
    private String timeStamp;

    /* String for the URL for the main news of the Guardian News. */
    private String newsUrl;

    /**
     * Constructs a new {@link GuardianNews} object.
     *
     * @param headline is the Headline of the Guardian News.
     * @param author is the Author of the Guardian News
     * @param newsUrl is the URL for the main news of the Guardian News.
     * @param sectionName is the Section Name of the Guardian News.
     * @param timeStamp is the Date published of the Guardian News.
     */
    GuardianNews(String headline, String author, String sectionName, String timeStamp, String newsUrl) {
        this.headline = headline;
        this.author = author;
        this.sectionName = sectionName;
        this.timeStamp = timeStamp;
        this.newsUrl = newsUrl;
    }

    /**
     * Returns the Headline of the Guardian News.
     */
    String getHeadline() {
        return headline;
    }

    /**
     * Returns the Author for the main news of the Guardian News.
     */
    String getAuthor() {
        return author;
    }

    /**
     * Returns the Section Name of the Guardian News.
     */
    String getSectionName() {
        return sectionName;
    }

    /**
     * Returns the Published date of the Guardian News.
     */
    String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Returns the URL for the main news of the Guardian News.
     */
    String getNewsUrl() {
        return newsUrl;
    }
}
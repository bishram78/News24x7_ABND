package com.nano.degree.bishram.news24x7;

class GuardianNews {
    /* String for the Headline of the Guardian News */
    private String headline;

    /* String for the URL for the main news of the Guardian News. */
    private String newsUrl;

    /* String for the Section Name of the Guardian News. */
    private String sectionName;

    /* String for the Published date of the Guardian News. */
    private String timeStamp;

    /**
     * Constructs a new {@link GuardianNews} object.
     *
     * @param headline is the Headline of the Guardian News.
     * @param newsUrl is the URL for the main news of the Guardian News.
     * @param sectionName is the Section Name of the Guardian News.
     */
    GuardianNews(String headline, String newsUrl, String sectionName, String timeStamp) {
        this.headline = headline;
        this.newsUrl = newsUrl;
        this.sectionName = sectionName;
        this.timeStamp = timeStamp;
    }

    /**
     * Returns the Headline of the Guardian News.
     */
    String getHeadline() {
        return headline;
    }

    /**
     * Returns the URL for the main news of the Guardian News.
     */
    String getNewsUrl() {
        return newsUrl;
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
}
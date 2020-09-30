package me.bl0ckchain.analyst.core.notification;

/**
 *
 * @author caisupeng
 * @version $Id: Alert.java, v 0.1 2019-02-27 10:42 AM caisupeng Exp $$
 */
public class Alert {
    private String title;
    private String subtitle;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
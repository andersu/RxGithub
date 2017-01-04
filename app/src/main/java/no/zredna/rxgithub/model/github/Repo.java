package no.zredna.rxgithub.model.github;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class Repo {
    private String name;

    @SerializedName("created_at")
    private String createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedFormattedString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MMM dd, YYYY");
        return new DateTime(createdAt).toString(dateTimeFormatter);
    }
}

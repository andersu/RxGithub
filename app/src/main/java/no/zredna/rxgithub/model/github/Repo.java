package no.zredna.rxgithub.model.github;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@SuppressWarnings("unused") // Fields are set by Gson when deserializing
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

    @Nullable
    public String getCreatedFormattedString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MMM d, YYYY");

        try {
            return new DateTime(createdAt).toString(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

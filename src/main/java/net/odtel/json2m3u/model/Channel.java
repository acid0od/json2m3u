package net.odtel.json2m3u.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    long id;
    String name;
    String description;
    boolean liked;
    boolean working;
    String aspect;
    String dimension;
    Map<String, String> images;
    String url;
    String type;
}

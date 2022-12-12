package com.example.arabicyoungsterwriterapp.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Story {
    private String storyID;
    private String title;
    private String para;
    private String image;
    private String audio;
    private String gif;
    private String tvColor;
    private String bgColor;
    private String genre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return title.equalsIgnoreCase(story.title) && para.equalsIgnoreCase(story.para);
    }


}

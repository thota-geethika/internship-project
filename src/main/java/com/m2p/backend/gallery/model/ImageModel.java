package com.m2p.backend.gallery.model;

import com.m2p.backend.authentication.model.UserDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="image_table")
public class ImageModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

   @Column(nullable = false,length = 200)
    private String imageLink;

    @Column(nullable = false,length = 20)
    private String imageCategory;

    @Column(nullable = false,length = 20)
    private int likes;

    public String getImageLink() {
        return imageLink;
    }

    public int getLikes() {
        return likes;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageModel imageModel = (ImageModel) o;
        return Objects.equals(imageId, imageModel.imageId) && Objects.equals(imageLink, imageModel.imageCategory) && Objects.equals(likes,imageModel.likes) ;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "imageId=" + imageId +
                ", imageLink='" + imageLink + '\'' +
                ", imageCategory='" + imageCategory + '\'' +
                ", likes=" + likes +
                '}';
    }
}

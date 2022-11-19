package com.zerobase.fastlms.banner.model;

import lombok.Data;

@Data
public class BannerInput {
    Long id;
    String imagePath;
    String bannerName;
    String alterText;
    String toUrl;
    String target;

    int sortValue;

    boolean usingYn;

    String idList;

    //Add
//    String filename;
//    String urlFilename;
}

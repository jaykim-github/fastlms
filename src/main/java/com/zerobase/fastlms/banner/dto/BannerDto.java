package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BannerDto {
    Long id;

    String imagePath;
    String bannerName;
    String alterText;
    String toUrl;
    String target;

    int sortValue;

    boolean usingYn;

    LocalDateTime regDt;


    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .imagePath(banner.getImagePath())
                .bannerName(banner.getBannerName())
                .alterText(banner.getAlterText())
                .toUrl(banner.getToUrl())
                .target(banner.getTarget())
                .usingYn(banner.isUsingYn())
                .regDt(banner.getRegDt())
                .build();
    }

    public static List<BannerDto> of(List<Banner> banners){
        if(banners == null){
            return null;
        }
        List<BannerDto> bannerList = new ArrayList<>();
        for(Banner x : banners){
            bannerList.add(BannerDto.of(x));
        }
        return bannerList;
    }
}

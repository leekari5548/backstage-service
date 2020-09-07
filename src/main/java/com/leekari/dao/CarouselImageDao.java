package com.leekari.dao;

import com.leekari.entity.CarouselImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/19 20:47
 * @description
 */
@Mapper
public interface CarouselImageDao {

    List<CarouselImage> getCarouselImage(Integer type);

    int deleteCarousels(@Param("idList") List<String> idList);

    int insertCarousel(CarouselImage carouselImage);
}

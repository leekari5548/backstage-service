package com.leekari.dao;

import com.leekari.entity.CarouselImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/19 20:47
 * @description
 */
@Mapper
public interface CarouselImageDao {

    List<CarouselImage> getCarouselImage(Integer type);
}

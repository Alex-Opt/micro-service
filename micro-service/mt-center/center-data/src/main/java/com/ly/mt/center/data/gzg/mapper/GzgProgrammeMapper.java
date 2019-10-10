package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgProgramme;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GzgProgrammeMapper {

    List<GzgProgramme> getGzgProgrammeListByName(GzgProgramme GzgProgramme);


}
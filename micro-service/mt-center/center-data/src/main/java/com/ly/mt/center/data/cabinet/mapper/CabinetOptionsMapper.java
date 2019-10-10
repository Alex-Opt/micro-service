package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetOptions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CabinetOptionsMapper {
    List<CabinetOptions> getOptions();
}

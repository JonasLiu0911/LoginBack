package org.example.service;

import org.example.dataobject.AnalyzeResultDO;
import org.example.dataobject.LocationTagsDO;
import org.example.dataobject.TempDO;
import org.example.error.BusinessException;
import org.example.service.model.LocationModel;
import org.example.service.model.ResultModel;
import org.example.service.model.TempModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {

    List<LocationTagsDO> getLocationTags();

    void addLocationToTemp(TempModel tempModel) throws BusinessException;

    List<TempDO> getAllTempData();

    void addResultToResult(ResultModel resultModel) throws BusinessException;

    List<AnalyzeResultDO> getResultsByTel(String telephone);

    void truncateTempFor24Hours();
}

package org.example.service.impl;

import org.example.dao.AnalyzeResultDOMapper;
import org.example.dao.LocationTagsDOMapper;
import org.example.dao.TempDOMapper;
import org.example.dataobject.AnalyzeResultDO;
import org.example.dataobject.LocationTagsDO;
import org.example.dataobject.TempDO;
import org.example.dataobject.UserScheduleDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.service.LocationService;
import org.example.service.model.LocationModel;
import org.example.service.model.ResultModel;
import org.example.service.model.ScheduleModel;
import org.example.service.model.TempModel;
import org.example.validator.ValidationResult;
import org.example.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private LocationTagsDOMapper locationTagsDOMapper;

    @Autowired
    private AnalyzeResultDOMapper analyzeResultDOMapper;

    @Autowired
    private TempDOMapper tempDOMapper;

    public LocationServiceImpl() {

    }

    @Override
    public List<LocationTagsDO> getLocationTags() {
        List<LocationTagsDO> locationTagsDOList = locationTagsDOMapper.selectAll();
//        for (LocationTagsDO i : locationTagsDOList) {
//            System.out.println(i.getLocationDesc());
//        }
        return locationTagsDOList;
    }

    @Override
    public List<AnalyzeResultDO> getResultsByTel(String telephone) {
        List<AnalyzeResultDO> analyzeResultDOList = analyzeResultDOMapper.selectByTel(telephone);

        for (AnalyzeResultDO i : analyzeResultDOList) {
            System.out.println(i.getResultTag());
        }

        return analyzeResultDOList;

    }

    @Override
    @Transactional
    public void addLocationToTemp(TempModel tempModel) throws BusinessException {
        if (tempModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidationResult result = validator.validate(tempModel);
        if (result.isHasError()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        System.out.println(tempModel.getTag() + " " + tempModel.getLocDescribe() + " " + tempModel.getCurTime());

        TempDO tempDO = convertFromModel(tempModel);
        System.out.println(tempDO.getLocDescribe());
        int num = tempDOMapper.insertSelective(tempDO);
        System.out.println(num);
    }

    @Override
    @Transactional
    public void addResultToResult(ResultModel resultModel) throws BusinessException {
        if (resultModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidationResult result = validator.validate(resultModel);
        if (result.isHasError()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        AnalyzeResultDO analyzeResultDO = modelToDo(resultModel);
        System.out.println(analyzeResultDO.getFinishTime());
        int num = analyzeResultDOMapper.insertSelective(analyzeResultDO);
        System.out.println(num);
    }

    @Override
    public void truncateTempFor24Hours() {
        int num = tempDOMapper.truncateTemp();
        System.out.println(num);
    }

    @Override
    public List<TempDO> getAllTempData() {
        List<TempDO> tempDOList = tempDOMapper.selectAll();
        return tempDOList;
    }

    private TempDO convertFromModel(TempModel tempModel) {
        if (tempModel == null) {
            return null;
        }
        TempDO tempDO = new TempDO();
        BeanUtils.copyProperties(tempModel, tempDO);
        return tempDO;
    }

    private AnalyzeResultDO modelToDo(ResultModel resultModel) {
        if (resultModel == null) {
            return null;
        }
        AnalyzeResultDO analyzeResultDO = new AnalyzeResultDO();
        BeanUtils.copyProperties(resultModel, analyzeResultDO);
        return analyzeResultDO;
    }
}

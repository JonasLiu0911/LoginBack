package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.dataobject.AnalyzeResultDO;
import org.example.dataobject.LocationTagsDO;
import org.example.dataobject.TempDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.response.CommonReturnType;
import org.example.service.LocationService;
import org.example.service.model.ResultModel;
import org.example.service.model.TempModel;
import org.example.utils.DistanceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller("location")
@RequestMapping("/location")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class LocationController extends BaseController {

    private static Logger Log = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    private List<LocationTagsDO> locationTagsDOList;  // 定义一个全局变量，方便使用

    private List<TempDO> tempDOList;

    private Map<String, List<TempModel>> listMap;  // 全局变量，存储temp中的数据分组后的结果

    @Scheduled(cron = "0 0 0 * * ?")
    public void testTasks() throws BusinessException {
        System.out.println("每天0点执行一次");

        dealDataFromTemp();

    }

    /**
     * 获取全部设定好的位置标签
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getTags")
    @ResponseBody
    public CommonReturnType getLocationTags() throws BusinessException {
        // 获取标签表中的位置信息，存入表中，将有很大作用
        locationTagsDOList = locationService.getLocationTags();

        if (locationTagsDOList.size() == 0) {
            throw new BusinessException(EmBusinessError.Tags_Not_Exist);
        }
        double lo = 112.87217710894859;
        double la = 27.888688191875733;

        for (LocationTagsDO i : locationTagsDOList) {
            int dis = (int) DistanceUtils.calculateDistance(la, lo, i.getLatitude().doubleValue(), i.getLongitude().doubleValue());
            if (dis <= i.getLocationRadius()) {
                System.out.println(i.getLocationTag());
                break;
            }
        }
        System.out.println("其他");

        return CommonReturnType.create(locationTagsDOList);
    }

    /**
     * 前端获取分析结果表的数据，根据手机号获取
     * @param telephone
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/getResultsByTel")
    @ResponseBody
    public CommonReturnType getResultsByTel(@RequestParam(name = "telephone") String telephone) throws BusinessException {
        List<AnalyzeResultDO> analyzeResultDOList = locationService.getResultsByTel(telephone);
        for (AnalyzeResultDO i : analyzeResultDOList) {
            System.out.println(i.getFinishTime());
        }

        return CommonReturnType.create(analyzeResultDOList);

    }

    @RequestMapping(value = "/getLocations", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getLocationsToDB(@RequestParam(name = "jsonStr") String jsonStr) throws BusinessException {

        locationTagsDOList = locationService.getLocationTags();

        if (locationTagsDOList.size() == 0) {
            throw new BusinessException(EmBusinessError.Tags_Not_Exist);
        }

        System.out.println(jsonStr);

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);  // 这是获取的位置json数据

        TempModel tempModel = new TempModel();
        tempModel.setUserTel(jsonObject.getString("tel"));
        tempModel.setCurTime(jsonObject.getLong("timeX"));

        double lo = jsonObject.getBigDecimal("longitude").doubleValue();
        double la = jsonObject.getBigDecimal("latitude").doubleValue();

        for (LocationTagsDO i : locationTagsDOList) {

            int dis = (int) DistanceUtils.calculateDistance(
                    la,
                    lo,
                    i.getLatitude().doubleValue(),
                    i.getLongitude().doubleValue());
            if (dis <= i.getLocationRadius()) {
                tempModel.setTag(i.getLocationTag());
                tempModel.setLocDescribe(i.getLocationDesc());
                break;
            }
        }

        if (tempModel.getTag() == null) {
            tempModel.setTag("其他");
            tempModel.setLocDescribe("其他");
        }

        locationService.addLocationToTemp(tempModel);
        return CommonReturnType.create(tempModel);
    }

    private void dealDataFromTemp() throws BusinessException {

        listMap = new LinkedHashMap<>();

        // 获得temp表中所有数据
        tempDOList = locationService.getAllTempData();
        if (tempDOList.size() == 0) {
            throw new BusinessException(EmBusinessError.Temp_Not_Exist);
        }

        // 遍历从temp表取出的数据
        for (TempDO i : tempDOList) {

            // 转换一下
            TempModel tm = tempDOtoMODEL(i);

            // 判断Map中是否存在以 tm.getTag() 为键名的List集合
            // 不存在则先实例化一个List，存在则直接添加到List中，并放入Map里面
            List<TempModel> tempModels = listMap.get(tm.getTag());
            if (tempModels == null) {
                tempModels = new ArrayList<>();
            }

            tempModels.add(tm);
            listMap.put(tm.getTag(), tempModels);

        }

        saveAnalyzeResult(listMap);

        locationService.truncateTempFor24Hours();
    }

    private TempModel tempDOtoMODEL(TempDO tempDO) {
        if (tempDO == null) {
            return null;
        }
        TempModel tempModel = new TempModel();
        BeanUtils.copyProperties(tempDO, tempModel);
        return tempModel;
    }

    private void saveAnalyzeResult(Map<String, List<TempModel>> map) throws BusinessException {
        for (String key : map.keySet()) {
            List<TempModel> modelList = map.get(key);
            int len = modelList.size();
            if (len >= 10) {
                int start = 0;
                int end = 0;
                int probe = 1;
                for ( ; probe < len; ) {
                    int less = (int) (
                            modelList.get(probe).getCurTime()
                            - modelList.get(end).getCurTime());
                    if (less < 62000) {
                        if (probe == len - 1) {
                            end = probe;
                            resToDB(modelList.get(end).getUserTel(),
                                    modelList.get(start).getCurTime(),
                                    modelList.get(end).getCurTime(),
                                    modelList.get(end).getTag(),
                                    modelList.get(end).getLocDescribe());
                        } else {
                            end++;
                        }
                    } else if (less >= 62000) {
                        if (probe == modelList.size() - 1) {
                            end = probe;
                        }
                        resToDB(modelList.get(end).getUserTel(),
                                modelList.get(start).getCurTime(),
                                modelList.get(end).getCurTime(),
                                modelList.get(end).getTag(),
                                modelList.get(end).getLocDescribe());
                        start = probe;
                        end = probe;
                    }
                    probe++;
                }
            }
        }
    }

    // 分析结果存入analyze_result数据库
    private void resToDB(String telephone, Long beginT, Long finishT, String tag, String locDes) throws BusinessException {
        ResultModel resultModel = new ResultModel();
        resultModel.setUserTel(telephone);
        resultModel.setBeginTime(beginT);
        resultModel.setFinishTime(finishT);
        resultModel.setResultTag(tag);
        resultModel.setResultDesc(locDes);
        locationService.addResultToResult(resultModel);
    }
}

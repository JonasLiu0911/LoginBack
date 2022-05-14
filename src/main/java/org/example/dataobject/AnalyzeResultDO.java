package org.example.dataobject;

import java.util.Date;

public class AnalyzeResultDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column analyze_result.result_id
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    private Integer resultId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column analyze_result.user_tel
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    private String userTel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column analyze_result.begin_time
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    private Long beginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column analyze_result.finish_time
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    private Long finishTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column analyze_result.result_desc
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    private String resultDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column analyze_result.result_tag
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    private String resultTag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column analyze_result.result_id
     *
     * @return the value of analyze_result.result_id
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public Integer getResultId() {
        return resultId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column analyze_result.result_id
     *
     * @param resultId the value for analyze_result.result_id
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column analyze_result.user_tel
     *
     * @return the value of analyze_result.user_tel
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public String getUserTel() {
        return userTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column analyze_result.user_tel
     *
     * @param userTel the value for analyze_result.user_tel
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column analyze_result.begin_time
     *
     * @return the value of analyze_result.begin_time
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public Long getBeginTime() {
        return beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column analyze_result.begin_time
     *
     * @param beginTime the value for analyze_result.begin_time
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column analyze_result.finish_time
     *
     * @return the value of analyze_result.finish_time
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public Long getFinishTime() {
        return finishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column analyze_result.finish_time
     *
     * @param finishTime the value for analyze_result.finish_time
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column analyze_result.result_desc
     *
     * @return the value of analyze_result.result_desc
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public String getResultDesc() {
        return resultDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column analyze_result.result_desc
     *
     * @param resultDesc the value for analyze_result.result_desc
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc == null ? null : resultDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column analyze_result.result_tag
     *
     * @return the value of analyze_result.result_tag
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public String getResultTag() {
        return resultTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column analyze_result.result_tag
     *
     * @param resultTag the value for analyze_result.result_tag
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    public void setResultTag(String resultTag) {
        this.resultTag = resultTag == null ? null : resultTag.trim();
    }

}
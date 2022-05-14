package org.example.dao;

import org.example.dataobject.AnalyzeResultDO;

import java.util.List;

public interface AnalyzeResultDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table analyze_result
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    int deleteByPrimaryKey(Integer resultId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table analyze_result
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    int insert(AnalyzeResultDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table analyze_result
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    int insertSelective(AnalyzeResultDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table analyze_result
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    AnalyzeResultDO selectByPrimaryKey(Integer resultId);

    List<AnalyzeResultDO> selectByTel(String userTel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table analyze_result
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    int updateByPrimaryKeySelective(AnalyzeResultDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table analyze_result
     *
     * @mbg.generated Thu May 05 20:38:21 CST 2022
     */
    int updateByPrimaryKey(AnalyzeResultDO record);
}
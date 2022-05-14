package org.example.dao;

import org.example.dataobject.TempDO;

import java.util.List;

public interface TempDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table temp
     *
     * @mbg.generated Sun May 08 08:58:16 CST 2022
     */
    int deleteByPrimaryKey(Integer tempId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table temp
     *
     * @mbg.generated Sun May 08 08:58:16 CST 2022
     */
    int insert(TempDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table temp
     *
     * @mbg.generated Sun May 08 08:58:16 CST 2022
     */
    int insertSelective(TempDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table temp
     *
     * @mbg.generated Sun May 08 08:58:16 CST 2022
     */
    TempDO selectByPrimaryKey(Integer tempId);

    List<TempDO> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table temp
     *
     * @mbg.generated Sun May 08 08:58:16 CST 2022
     */
    int updateByPrimaryKeySelective(TempDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table temp
     *
     * @mbg.generated Sun May 08 08:58:16 CST 2022
     */
    int updateByPrimaryKey(TempDO record);
}
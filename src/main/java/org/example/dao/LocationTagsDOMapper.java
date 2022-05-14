package org.example.dao;

import org.example.dataobject.LocationTagsDO;

import java.util.List;

public interface LocationTagsDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table location_tags
     *
     * @mbg.generated Thu May 05 19:09:23 CST 2022
     */
    int deleteByPrimaryKey(Integer locId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table location_tags
     *
     * @mbg.generated Thu May 05 19:09:23 CST 2022
     */
    int insert(LocationTagsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table location_tags
     *
     * @mbg.generated Thu May 05 19:09:23 CST 2022
     */
    int insertSelective(LocationTagsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table location_tags
     *
     * @mbg.generated Thu May 05 19:09:23 CST 2022
     */
    LocationTagsDO selectByPrimaryKey(Integer locId);

    List<LocationTagsDO> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table location_tags
     *
     * @mbg.generated Thu May 05 19:09:23 CST 2022
     */
    int updateByPrimaryKeySelective(LocationTagsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table location_tags
     *
     * @mbg.generated Thu May 05 19:09:23 CST 2022
     */
    int updateByPrimaryKey(LocationTagsDO record);
}
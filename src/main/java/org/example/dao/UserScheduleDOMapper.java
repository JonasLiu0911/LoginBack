package org.example.dao;

import org.apache.catalina.User;
import org.example.dataobject.UserScheduleDO;
import org.example.service.model.ScheduleModel;

import java.util.List;

public interface UserScheduleDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_schedule
     *
     * @mbg.generated Sat Apr 02 11:10:08 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_schedule
     *
     * @mbg.generated Sat Apr 02 11:10:08 CST 2022
     */
    int insert(UserScheduleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_schedule
     *
     * @mbg.generated Sat Apr 02 11:10:08 CST 2022
     */
    int insertSelective(UserScheduleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_schedule
     *
     * @mbg.generated Sat Apr 02 11:10:08 CST 2022
     */
    UserScheduleDO selectByPrimaryKey(Integer id);

    List<UserScheduleDO> selectByUserId(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_schedule
     *
     * @mbg.generated Sat Apr 02 11:10:08 CST 2022
     */
    int updateByPrimaryKeySelective(UserScheduleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_schedule
     *
     * @mbg.generated Sat Apr 02 11:10:08 CST 2022
     */
    int updateByPrimaryKey(UserScheduleDO record);
}
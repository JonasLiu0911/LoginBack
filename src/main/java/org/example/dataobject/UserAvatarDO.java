package org.example.dataobject;

import java.util.Date;

public class UserAvatarDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_avatar.id
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_avatar.user_id
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_avatar.head_url
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    private String headUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_avatar.create_time
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_avatar.update_time
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_avatar.id
     *
     * @return the value of user_avatar.id
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_avatar.id
     *
     * @param id the value for user_avatar.id
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_avatar.user_id
     *
     * @return the value of user_avatar.user_id
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_avatar.user_id
     *
     * @param userId the value for user_avatar.user_id
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_avatar.head_url
     *
     * @return the value of user_avatar.head_url
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_avatar.head_url
     *
     * @param headUrl the value for user_avatar.head_url
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_avatar.create_time
     *
     * @return the value of user_avatar.create_time
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_avatar.create_time
     *
     * @param createTime the value for user_avatar.create_time
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_avatar.update_time
     *
     * @return the value of user_avatar.update_time
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_avatar.update_time
     *
     * @param updateTime the value for user_avatar.update_time
     *
     * @mbg.generated Fri Apr 15 09:22:41 CST 2022
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
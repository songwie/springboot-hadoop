package com.mogoroom.hadoop.dao.entity;

import java.io.Serializable;

import javax.annotation.Nullable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * The persistent class for the user_renter_portrait database table.
 * 
 */
@Entity
@Table(name="user_renter_portrait")
public class UserRenterPortrait implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;

	@JSONField (format="yyyy-MM-dd")  
	private Date createTime;
	
	private String cellphone;
	
	@Column(length=50)
	private String district;

	private Integer districtCount;

	@Column(length=5)
	private String hourseType;

	private Integer hourseTypeCount;

	@Column(length=5)
	private String isAirConditioner;

	private Integer isAirConditionerCount;

	@Column(length=5)
	private String isBalcony;

	private Integer isBalconyCount;

	@Column(length=5)
	private String isToilet;

	private Integer isToiletCount;

	@JSONField (format="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date lastLoginTime;

	@Column(length=5)
	private String metroLine;

	private Integer metroLineCount;

	@Column(length=5)
	private String rentType;

	private Integer rentTypeCount;

	@Column(length=5)
	private String roomMate;

	private Integer roomMateCount;

	private Integer startRoomPrice;
	
	private Integer endRoomPrice;

	@Column(length=5)
	private String roomSourceType;

	private Integer roomSourceTypeCount;

	@Column(length=5)
	private String roomType;

	private Integer roomTypeCount;

	@Column(length=100)
	private String searchWord;

	private Integer searchWordCount;

	@Column(length=5)
	private String sortType;

	private Integer sortTypeCount;

	@Column(length=20)
	private String tradeArea;

	private Integer tradeAreaCount;

	@Column(length=5)
	private String turnWay;

	private Integer turnWayCount;

	private Integer userId;
	
	@Column(name="active",nullable=true) 
	private Integer active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getDistrictCount() {
		return districtCount;
	}

	public void setDistrictCount(Integer districtCount) {
		this.districtCount = districtCount;
	}

	public String getHourseType() {
		return hourseType;
	}

	public void setHourseType(String hourseType) {
		this.hourseType = hourseType;
	}

	public Integer getHourseTypeCount() {
		return hourseTypeCount;
	}

	public void setHourseTypeCount(Integer hourseTypeCount) {
		this.hourseTypeCount = hourseTypeCount;
	}

	public String getIsAirConditioner() {
		return isAirConditioner;
	}

	public void setIsAirConditioner(String isAirConditioner) {
		this.isAirConditioner = isAirConditioner;
	}

	public Integer getIsAirConditionerCount() {
		return isAirConditionerCount;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public void setIsAirConditionerCount(Integer isAirConditionerCount) {
		this.isAirConditionerCount = isAirConditionerCount;
	}

	public String getIsBalcony() {
		return isBalcony;
	}

	public void setIsBalcony(String isBalcony) {
		this.isBalcony = isBalcony;
	}

	public Integer getIsBalconyCount() {
		return isBalconyCount;
	}

	public void setIsBalconyCount(Integer isBalconyCount) {
		this.isBalconyCount = isBalconyCount;
	}

	public String getIsToilet() {
		return isToilet;
	}

	public void setIsToilet(String isToilet) {
		this.isToilet = isToilet;
	}

	public Integer getIsToiletCount() {
		return isToiletCount;
	}

	public void setIsToiletCount(Integer isToiletCount) {
		this.isToiletCount = isToiletCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getMetroLine() {
		return metroLine;
	}

	public void setMetroLine(String metroLine) {
		this.metroLine = metroLine;
	}

	public Integer getMetroLineCount() {
		return metroLineCount;
	}

	public void setMetroLineCount(Integer metroLineCount) {
		this.metroLineCount = metroLineCount;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public Integer getRentTypeCount() {
		return rentTypeCount;
	}

	public void setRentTypeCount(Integer rentTypeCount) {
		this.rentTypeCount = rentTypeCount;
	}

	public String getRoomMate() {
		return roomMate;
	}

	public void setRoomMate(String roomMate) {
		this.roomMate = roomMate;
	}

	public Integer getRoomMateCount() {
		return roomMateCount;
	}

	public void setRoomMateCount(Integer roomMateCount) {
		this.roomMateCount = roomMateCount;
	}

	public Integer getStartRoomPrice() {
		return startRoomPrice;
	}

	public void setStartRoomPrice(Integer startRoomPrice) {
		this.startRoomPrice = startRoomPrice;
	}

	public Integer getEndRoomPrice() {
		return endRoomPrice;
	}

	public void setEndRoomPrice(Integer endRoomPrice) {
		this.endRoomPrice = endRoomPrice;
	}

	public String getRoomSourceType() {
		return roomSourceType;
	}

	public void setRoomSourceType(String roomSourceType) {
		this.roomSourceType = roomSourceType;
	}

	public Integer getRoomSourceTypeCount() {
		return roomSourceTypeCount;
	}

	public void setRoomSourceTypeCount(Integer roomSourceTypeCount) {
		this.roomSourceTypeCount = roomSourceTypeCount;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getRoomTypeCount() {
		return roomTypeCount;
	}

	public void setRoomTypeCount(Integer roomTypeCount) {
		this.roomTypeCount = roomTypeCount;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public Integer getSearchWordCount() {
		return searchWordCount;
	}

	public void setSearchWordCount(Integer searchWordCount) {
		this.searchWordCount = searchWordCount;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Integer getSortTypeCount() {
		return sortTypeCount;
	}

	public void setSortTypeCount(Integer sortTypeCount) {
		this.sortTypeCount = sortTypeCount;
	}

	public String getTradeArea() {
		return tradeArea;
	}

	public void setTradeArea(String tradeArea) {
		this.tradeArea = tradeArea;
	}

	public Integer getTradeAreaCount() {
		return tradeAreaCount;
	}

	public void setTradeAreaCount(Integer tradeAreaCount) {
		this.tradeAreaCount = tradeAreaCount;
	}

	public String getTurnWay() {
		return turnWay;
	}

	public void setTurnWay(String turnWay) {
		this.turnWay = turnWay;
	}

	public Integer getTurnWayCount() {
		return turnWayCount;
	}

	public void setTurnWayCount(Integer turnWayCount) {
		this.turnWayCount = turnWayCount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserRenterPortrait [id=" + id + ", createTime=" + createTime + ", cellphone=" + cellphone
				+ ", district=" + district + ", districtCount=" + districtCount + ", hourseType=" + hourseType
				+ ", hourseTypeCount=" + hourseTypeCount + ", isAirConditioner=" + isAirConditioner
				+ ", isAirConditionerCount=" + isAirConditionerCount + ", isBalcony=" + isBalcony + ", isBalconyCount="
				+ isBalconyCount + ", isToilet=" + isToilet + ", isToiletCount=" + isToiletCount + ", lastLoginTime="
				+ lastLoginTime + ", metroLine=" + metroLine + ", metroLineCount=" + metroLineCount + ", rentType="
				+ rentType + ", rentTypeCount=" + rentTypeCount + ", roomMate=" + roomMate + ", roomMateCount="
				+ roomMateCount + ", startRoomPrice=" + startRoomPrice + ", endRoomPrice=" + endRoomPrice
				+ ", roomSourceType=" + roomSourceType + ", roomSourceTypeCount=" + roomSourceTypeCount + ", roomType="
				+ roomType + ", roomTypeCount=" + roomTypeCount + ", searchWord=" + searchWord + ", searchWordCount="
				+ searchWordCount + ", sortType=" + sortType + ", sortTypeCount=" + sortTypeCount + ", tradeArea="
				+ tradeArea + ", tradeAreaCount=" + tradeAreaCount + ", turnWay=" + turnWay + ", turnWayCount="
				+ turnWayCount + ", userId=" + userId + ", active=" + active + "]";
	}
	
	

}
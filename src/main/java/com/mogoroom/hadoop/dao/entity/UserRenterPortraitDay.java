package com.mogoroom.hadoop.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the user_renter_portrait_day database table.
 * 
 */
@Entity
@Table(name="user_renter_portrait_day")
public class UserRenterPortraitDay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name = "id")
	private Integer id;
	
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

	public UserRenterPortraitDay() {
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getDistrictCount() {
		return this.districtCount;
	}

	public void setDistrictCount(Integer districtCount) {
		this.districtCount = districtCount;
	}

	public String getHourseType() {
		return this.hourseType;
	}

	public void setHourseType(String hourseType) {
		this.hourseType = hourseType;
	}

	public Integer getHourseTypeCount() {
		return this.hourseTypeCount;
	}

	public void setHourseTypeCount(Integer hourseTypeCount) {
		this.hourseTypeCount = hourseTypeCount;
	}

	public String getIsAirConditioner() {
		return this.isAirConditioner;
	}

	public void setIsAirConditioner(String isAirConditioner) {
		this.isAirConditioner = isAirConditioner;
	}

	public Integer getIsAirConditionerCount() {
		return this.isAirConditionerCount;
	}

	public void setIsAirConditionerCount(Integer isAirConditionerCount) {
		this.isAirConditionerCount = isAirConditionerCount;
	}

	public String getIsBalcony() {
		return this.isBalcony;
	}

	public void setIsBalcony(String isBalcony) {
		this.isBalcony = isBalcony;
	}

	public Integer getIsBalconyCount() {
		return this.isBalconyCount;
	}

	public void setIsBalconyCount(Integer isBalconyCount) {
		this.isBalconyCount = isBalconyCount;
	}

	public String getIsToilet() {
		return this.isToilet;
	}

	public void setIsToilet(String isToilet) {
		this.isToilet = isToilet;
	}

	public Integer getIsToiletCount() {
		return this.isToiletCount;
	}

	public void setIsToiletCount(Integer isToiletCount) {
		this.isToiletCount = isToiletCount;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getMetroLine() {
		return this.metroLine;
	}

	public void setMetroLine(String metroLine) {
		this.metroLine = metroLine;
	}

	public Integer getMetroLineCount() {
		return this.metroLineCount;
	}

	public void setMetroLineCount(Integer metroLineCount) {
		this.metroLineCount = metroLineCount;
	}

	public String getRentType() {
		return this.rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public Integer getRentTypeCount() {
		return this.rentTypeCount;
	}

	public void setRentTypeCount(Integer rentTypeCount) {
		this.rentTypeCount = rentTypeCount;
	}

	public String getRoomMate() {
		return this.roomMate;
	}

	public void setRoomMate(String roomMate) {
		this.roomMate = roomMate;
	}

	public Integer getRoomMateCount() {
		return this.roomMateCount;
	}

	public void setRoomMateCount(Integer roomMateCount) {
		this.roomMateCount = roomMateCount;
	}

	public String getRoomSourceType() {
		return this.roomSourceType;
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

	public void setRoomSourceType(String roomSourceType) {
		this.roomSourceType = roomSourceType;
	}

	public Integer getRoomSourceTypeCount() {
		return this.roomSourceTypeCount;
	}

	public void setRoomSourceTypeCount(Integer roomSourceTypeCount) {
		this.roomSourceTypeCount = roomSourceTypeCount;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getRoomTypeCount() {
		return this.roomTypeCount;
	}

	public void setRoomTypeCount(Integer roomTypeCount) {
		this.roomTypeCount = roomTypeCount;
	}

	public String getSearchWord() {
		return this.searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public Integer getSearchWordCount() {
		return this.searchWordCount;
	}

	public void setSearchWordCount(Integer searchWordCount) {
		this.searchWordCount = searchWordCount;
	}

	public String getSortType() {
		return this.sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Integer getSortTypeCount() {
		return this.sortTypeCount;
	}

	public void setSortTypeCount(Integer sortTypeCount) {
		this.sortTypeCount = sortTypeCount;
	}

	public String getTradeArea() {
		return this.tradeArea;
	}

	public void setTradeArea(String tradeArea) {
		this.tradeArea = tradeArea;
	}

	public Integer getTradeAreaCount() {
		return this.tradeAreaCount;
	}

	public void setTradeAreaCount(Integer tradeAreaCount) {
		this.tradeAreaCount = tradeAreaCount;
	}

	public String getTurnWay() {
		return this.turnWay;
	}

	public void setTurnWay(String turnWay) {
		this.turnWay = turnWay;
	}

	public Integer getTurnWayCount() {
		return this.turnWayCount;
	}

	public void setTurnWayCount(Integer turnWayCount) {
		this.turnWayCount = turnWayCount;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserRenterPortraitDay [id=" + id + ", createTime=" + createTime + ", cellphone=" + cellphone
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
				+ turnWayCount + ", userId=" + userId + "]";
	}

}
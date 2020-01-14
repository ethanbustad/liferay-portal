/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommerceAddress service. Represents a row in the &quot;CommerceAddress&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.model.impl.CommerceAddressModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.model.impl.CommerceAddressImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAddress
 * @generated
 */
@ProviderType
public interface CommerceAddressModel
	extends AttachedModel, BaseModel<CommerceAddress>, GroupedModel,
			ShardedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce address model instance should use the {@link CommerceAddress} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce address.
	 *
	 * @return the primary key of this commerce address
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce address.
	 *
	 * @param primaryKey the primary key of this commerce address
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the external reference code of this commerce address.
	 *
	 * @return the external reference code of this commerce address
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this commerce address.
	 *
	 * @param externalReferenceCode the external reference code of this commerce address
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the commerce address ID of this commerce address.
	 *
	 * @return the commerce address ID of this commerce address
	 */
	public long getCommerceAddressId();

	/**
	 * Sets the commerce address ID of this commerce address.
	 *
	 * @param commerceAddressId the commerce address ID of this commerce address
	 */
	public void setCommerceAddressId(long commerceAddressId);

	/**
	 * Returns the group ID of this commerce address.
	 *
	 * @return the group ID of this commerce address
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this commerce address.
	 *
	 * @param groupId the group ID of this commerce address
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this commerce address.
	 *
	 * @return the company ID of this commerce address
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce address.
	 *
	 * @param companyId the company ID of this commerce address
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce address.
	 *
	 * @return the user ID of this commerce address
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce address.
	 *
	 * @param userId the user ID of this commerce address
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce address.
	 *
	 * @return the user uuid of this commerce address
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce address.
	 *
	 * @param userUuid the user uuid of this commerce address
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce address.
	 *
	 * @return the user name of this commerce address
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce address.
	 *
	 * @param userName the user name of this commerce address
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce address.
	 *
	 * @return the create date of this commerce address
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce address.
	 *
	 * @param createDate the create date of this commerce address
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce address.
	 *
	 * @return the modified date of this commerce address
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce address.
	 *
	 * @param modifiedDate the modified date of this commerce address
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this commerce address.
	 *
	 * @return the fully qualified class name of this commerce address
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this commerce address.
	 *
	 * @return the class name ID of this commerce address
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this commerce address.
	 *
	 * @param classNameId the class name ID of this commerce address
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this commerce address.
	 *
	 * @return the class pk of this commerce address
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this commerce address.
	 *
	 * @param classPK the class pk of this commerce address
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the name of this commerce address.
	 *
	 * @return the name of this commerce address
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this commerce address.
	 *
	 * @param name the name of this commerce address
	 */
	public void setName(String name);

	/**
	 * Returns the description of this commerce address.
	 *
	 * @return the description of this commerce address
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this commerce address.
	 *
	 * @param description the description of this commerce address
	 */
	public void setDescription(String description);

	/**
	 * Returns the street1 of this commerce address.
	 *
	 * @return the street1 of this commerce address
	 */
	@AutoEscape
	public String getStreet1();

	/**
	 * Sets the street1 of this commerce address.
	 *
	 * @param street1 the street1 of this commerce address
	 */
	public void setStreet1(String street1);

	/**
	 * Returns the street2 of this commerce address.
	 *
	 * @return the street2 of this commerce address
	 */
	@AutoEscape
	public String getStreet2();

	/**
	 * Sets the street2 of this commerce address.
	 *
	 * @param street2 the street2 of this commerce address
	 */
	public void setStreet2(String street2);

	/**
	 * Returns the street3 of this commerce address.
	 *
	 * @return the street3 of this commerce address
	 */
	@AutoEscape
	public String getStreet3();

	/**
	 * Sets the street3 of this commerce address.
	 *
	 * @param street3 the street3 of this commerce address
	 */
	public void setStreet3(String street3);

	/**
	 * Returns the city of this commerce address.
	 *
	 * @return the city of this commerce address
	 */
	@AutoEscape
	public String getCity();

	/**
	 * Sets the city of this commerce address.
	 *
	 * @param city the city of this commerce address
	 */
	public void setCity(String city);

	/**
	 * Returns the zip of this commerce address.
	 *
	 * @return the zip of this commerce address
	 */
	@AutoEscape
	public String getZip();

	/**
	 * Sets the zip of this commerce address.
	 *
	 * @param zip the zip of this commerce address
	 */
	public void setZip(String zip);

	/**
	 * Returns the commerce region ID of this commerce address.
	 *
	 * @return the commerce region ID of this commerce address
	 */
	public long getCommerceRegionId();

	/**
	 * Sets the commerce region ID of this commerce address.
	 *
	 * @param commerceRegionId the commerce region ID of this commerce address
	 */
	public void setCommerceRegionId(long commerceRegionId);

	/**
	 * Returns the commerce country ID of this commerce address.
	 *
	 * @return the commerce country ID of this commerce address
	 */
	public long getCommerceCountryId();

	/**
	 * Sets the commerce country ID of this commerce address.
	 *
	 * @param commerceCountryId the commerce country ID of this commerce address
	 */
	public void setCommerceCountryId(long commerceCountryId);

	/**
	 * Returns the latitude of this commerce address.
	 *
	 * @return the latitude of this commerce address
	 */
	public double getLatitude();

	/**
	 * Sets the latitude of this commerce address.
	 *
	 * @param latitude the latitude of this commerce address
	 */
	public void setLatitude(double latitude);

	/**
	 * Returns the longitude of this commerce address.
	 *
	 * @return the longitude of this commerce address
	 */
	public double getLongitude();

	/**
	 * Sets the longitude of this commerce address.
	 *
	 * @param longitude the longitude of this commerce address
	 */
	public void setLongitude(double longitude);

	/**
	 * Returns the phone number of this commerce address.
	 *
	 * @return the phone number of this commerce address
	 */
	@AutoEscape
	public String getPhoneNumber();

	/**
	 * Sets the phone number of this commerce address.
	 *
	 * @param phoneNumber the phone number of this commerce address
	 */
	public void setPhoneNumber(String phoneNumber);

	/**
	 * Returns the default billing of this commerce address.
	 *
	 * @return the default billing of this commerce address
	 */
	public boolean getDefaultBilling();

	/**
	 * Returns <code>true</code> if this commerce address is default billing.
	 *
	 * @return <code>true</code> if this commerce address is default billing; <code>false</code> otherwise
	 */
	public boolean isDefaultBilling();

	/**
	 * Sets whether this commerce address is default billing.
	 *
	 * @param defaultBilling the default billing of this commerce address
	 */
	public void setDefaultBilling(boolean defaultBilling);

	/**
	 * Returns the default shipping of this commerce address.
	 *
	 * @return the default shipping of this commerce address
	 */
	public boolean getDefaultShipping();

	/**
	 * Returns <code>true</code> if this commerce address is default shipping.
	 *
	 * @return <code>true</code> if this commerce address is default shipping; <code>false</code> otherwise
	 */
	public boolean isDefaultShipping();

	/**
	 * Sets whether this commerce address is default shipping.
	 *
	 * @param defaultShipping the default shipping of this commerce address
	 */
	public void setDefaultShipping(boolean defaultShipping);

	/**
	 * Returns the type of this commerce address.
	 *
	 * @return the type of this commerce address
	 */
	public int getType();

	/**
	 * Sets the type of this commerce address.
	 *
	 * @param type the type of this commerce address
	 */
	public void setType(int type);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(CommerceAddress commerceAddress);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceAddress> toCacheModel();

	@Override
	public CommerceAddress toEscapedModel();

	@Override
	public CommerceAddress toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}
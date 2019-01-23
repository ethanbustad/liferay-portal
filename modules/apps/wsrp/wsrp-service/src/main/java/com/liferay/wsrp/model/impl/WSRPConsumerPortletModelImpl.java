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

package com.liferay.wsrp.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.WSRPConsumerPortletModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the WSRPConsumerPortlet service. Represents a row in the &quot;WSRP_WSRPConsumerPortlet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link WSRPConsumerPortletModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link WSRPConsumerPortletImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletImpl
 * @see WSRPConsumerPortlet
 * @see WSRPConsumerPortletModel
 * @generated
 */
@ProviderType
public class WSRPConsumerPortletModelImpl extends BaseModelImpl<WSRPConsumerPortlet>
	implements WSRPConsumerPortletModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a wsrp consumer portlet model instance should use the {@link WSRPConsumerPortlet} interface instead.
	 */
	public static final String TABLE_NAME = "WSRP_WSRPConsumerPortlet";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "wsrpConsumerPortletId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "wsrpConsumerId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "portletHandle", Types.VARCHAR },
			{ "lastPublishDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("wsrpConsumerPortletId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("wsrpConsumerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("portletHandle", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table WSRP_WSRPConsumerPortlet (uuid_ VARCHAR(75) null,wsrpConsumerPortletId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,wsrpConsumerId LONG,name VARCHAR(75) null,portletHandle VARCHAR(255) null,lastPublishDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_WSRPConsumerPortlet";
	public static final String ORDER_BY_JPQL = " ORDER BY wsrpConsumerPortlet.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY WSRP_WSRPConsumerPortlet.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.wsrp.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.wsrp.model.WSRPConsumerPortlet"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.wsrp.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.model.WSRPConsumerPortlet"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.wsrp.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.wsrp.model.WSRPConsumerPortlet"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long PORTLETHANDLE_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long WSRPCONSUMERID_COLUMN_BITMASK = 8L;
	public static final long NAME_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.wsrp.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.wsrp.model.WSRPConsumerPortlet"));

	public WSRPConsumerPortletModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _wsrpConsumerPortletId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWsrpConsumerPortletId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _wsrpConsumerPortletId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return WSRPConsumerPortlet.class;
	}

	@Override
	public String getModelClassName() {
		return WSRPConsumerPortlet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("wsrpConsumerPortletId", getWsrpConsumerPortletId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("wsrpConsumerId", getWsrpConsumerId());
		attributes.put("name", getName());
		attributes.put("portletHandle", getPortletHandle());
		attributes.put("lastPublishDate", getLastPublishDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long wsrpConsumerPortletId = (Long)attributes.get(
				"wsrpConsumerPortletId");

		if (wsrpConsumerPortletId != null) {
			setWsrpConsumerPortletId(wsrpConsumerPortletId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long wsrpConsumerId = (Long)attributes.get("wsrpConsumerId");

		if (wsrpConsumerId != null) {
			setWsrpConsumerId(wsrpConsumerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String portletHandle = (String)attributes.get("portletHandle");

		if (portletHandle != null) {
			setPortletHandle(portletHandle);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortletId;
	}

	@Override
	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortletId = wsrpConsumerPortletId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getWsrpConsumerId() {
		return _wsrpConsumerId;
	}

	@Override
	public void setWsrpConsumerId(long wsrpConsumerId) {
		_columnBitmask |= WSRPCONSUMERID_COLUMN_BITMASK;

		if (!_setOriginalWsrpConsumerId) {
			_setOriginalWsrpConsumerId = true;

			_originalWsrpConsumerId = _wsrpConsumerId;
		}

		_wsrpConsumerId = wsrpConsumerId;
	}

	public long getOriginalWsrpConsumerId() {
		return _originalWsrpConsumerId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@Override
	public String getPortletHandle() {
		if (_portletHandle == null) {
			return "";
		}
		else {
			return _portletHandle;
		}
	}

	@Override
	public void setPortletHandle(String portletHandle) {
		_columnBitmask |= PORTLETHANDLE_COLUMN_BITMASK;

		if (_originalPortletHandle == null) {
			_originalPortletHandle = _portletHandle;
		}

		_portletHandle = portletHandle;
	}

	public String getOriginalPortletHandle() {
		return GetterUtil.getString(_originalPortletHandle);
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				WSRPConsumerPortlet.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			WSRPConsumerPortlet.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public WSRPConsumerPortlet toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (WSRPConsumerPortlet)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		WSRPConsumerPortletImpl wsrpConsumerPortletImpl = new WSRPConsumerPortletImpl();

		wsrpConsumerPortletImpl.setUuid(getUuid());
		wsrpConsumerPortletImpl.setWsrpConsumerPortletId(getWsrpConsumerPortletId());
		wsrpConsumerPortletImpl.setCompanyId(getCompanyId());
		wsrpConsumerPortletImpl.setCreateDate(getCreateDate());
		wsrpConsumerPortletImpl.setModifiedDate(getModifiedDate());
		wsrpConsumerPortletImpl.setWsrpConsumerId(getWsrpConsumerId());
		wsrpConsumerPortletImpl.setName(getName());
		wsrpConsumerPortletImpl.setPortletHandle(getPortletHandle());
		wsrpConsumerPortletImpl.setLastPublishDate(getLastPublishDate());

		wsrpConsumerPortletImpl.resetOriginalValues();

		return wsrpConsumerPortletImpl;
	}

	@Override
	public int compareTo(WSRPConsumerPortlet wsrpConsumerPortlet) {
		int value = 0;

		value = getName().compareTo(wsrpConsumerPortlet.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WSRPConsumerPortlet)) {
			return false;
		}

		WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)obj;

		long primaryKey = wsrpConsumerPortlet.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		WSRPConsumerPortletModelImpl wsrpConsumerPortletModelImpl = this;

		wsrpConsumerPortletModelImpl._originalUuid = wsrpConsumerPortletModelImpl._uuid;

		wsrpConsumerPortletModelImpl._originalCompanyId = wsrpConsumerPortletModelImpl._companyId;

		wsrpConsumerPortletModelImpl._setOriginalCompanyId = false;

		wsrpConsumerPortletModelImpl._setModifiedDate = false;

		wsrpConsumerPortletModelImpl._originalWsrpConsumerId = wsrpConsumerPortletModelImpl._wsrpConsumerId;

		wsrpConsumerPortletModelImpl._setOriginalWsrpConsumerId = false;

		wsrpConsumerPortletModelImpl._originalPortletHandle = wsrpConsumerPortletModelImpl._portletHandle;

		wsrpConsumerPortletModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<WSRPConsumerPortlet> toCacheModel() {
		WSRPConsumerPortletCacheModel wsrpConsumerPortletCacheModel = new WSRPConsumerPortletCacheModel();

		wsrpConsumerPortletCacheModel.uuid = getUuid();

		String uuid = wsrpConsumerPortletCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			wsrpConsumerPortletCacheModel.uuid = null;
		}

		wsrpConsumerPortletCacheModel.wsrpConsumerPortletId = getWsrpConsumerPortletId();

		wsrpConsumerPortletCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			wsrpConsumerPortletCacheModel.createDate = createDate.getTime();
		}
		else {
			wsrpConsumerPortletCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			wsrpConsumerPortletCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			wsrpConsumerPortletCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		wsrpConsumerPortletCacheModel.wsrpConsumerId = getWsrpConsumerId();

		wsrpConsumerPortletCacheModel.name = getName();

		String name = wsrpConsumerPortletCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			wsrpConsumerPortletCacheModel.name = null;
		}

		wsrpConsumerPortletCacheModel.portletHandle = getPortletHandle();

		String portletHandle = wsrpConsumerPortletCacheModel.portletHandle;

		if ((portletHandle != null) && (portletHandle.length() == 0)) {
			wsrpConsumerPortletCacheModel.portletHandle = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			wsrpConsumerPortletCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			wsrpConsumerPortletCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return wsrpConsumerPortletCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", wsrpConsumerPortletId=");
		sb.append(getWsrpConsumerPortletId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", wsrpConsumerId=");
		sb.append(getWsrpConsumerId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", portletHandle=");
		sb.append(getPortletHandle());
		sb.append(", lastPublishDate=");
		sb.append(getLastPublishDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConsumerPortlet");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wsrpConsumerPortletId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerPortletId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wsrpConsumerId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletHandle</column-name><column-value><![CDATA[");
		sb.append(getPortletHandle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastPublishDate</column-name><column-value><![CDATA[");
		sb.append(getLastPublishDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = WSRPConsumerPortlet.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			WSRPConsumerPortlet.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _wsrpConsumerPortletId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _wsrpConsumerId;
	private long _originalWsrpConsumerId;
	private boolean _setOriginalWsrpConsumerId;
	private String _name;
	private String _portletHandle;
	private String _originalPortletHandle;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private WSRPConsumerPortlet _escapedModel;
}
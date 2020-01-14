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

package com.liferay.commerce.product.content.search.web.internal.display.context;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.content.render.list.CPContentListRenderer;
import com.liferay.commerce.product.content.render.list.CPContentListRendererRegistry;
import com.liferay.commerce.product.content.render.list.entry.CPContentListEntryRenderer;
import com.liferay.commerce.product.content.render.list.entry.CPContentListEntryRendererRegistry;
import com.liferay.commerce.product.content.search.web.internal.configuration.CPSearchResultsPortletInstanceConfiguration;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.type.CPType;
import com.liferay.commerce.product.type.CPTypeServicesTracker;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPSearchResultsDisplayContext {

	public CPSearchResultsDisplayContext(
			CPContentListEntryRendererRegistry
				cpContentListEntryRendererRegistry,
			CPContentListRendererRegistry cpContentListRendererRegistry,
			CPDefinitionHelper cpDefinitionHelper,
			CPTypeServicesTracker cpTypeServicesTracker,
			HttpServletRequest httpServletRequest)
		throws ConfigurationException {

		_cpContentListEntryRendererRegistry =
			cpContentListEntryRendererRegistry;
		_cpContentListRendererRegistry = cpContentListRendererRegistry;
		_cpDefinitionHelper = cpDefinitionHelper;
		_cpTypeServicesTracker = cpTypeServicesTracker;
		_httpServletRequest = httpServletRequest;

		_cpRequestHelper = new CPRequestHelper(httpServletRequest);

		PortletDisplay portletDisplay = _cpRequestHelper.getPortletDisplay();

		_cpSearchResultsPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				CPSearchResultsPortletInstanceConfiguration.class);
	}

	public Map<String, String> getCPContentListEntryRendererKeys() {
		Map<String, String> cpContentListEntryRendererKeys = new HashMap<>();

		for (CPType cpType : getCPTypes()) {
			String cpTypeName = cpType.getName();

			cpContentListEntryRendererKeys.put(
				cpTypeName, getCPTypeListEntryRendererKey(cpTypeName));
		}

		return cpContentListEntryRendererKeys;
	}

	public List<CPContentListEntryRenderer> getCPContentListEntryRenderers(
		String cpType) {

		return _cpContentListEntryRendererRegistry.
			getCPContentListEntryRenderers(
				CPPortletKeys.CP_SEARCH_RESULTS, cpType);
	}

	public String getCPContentListRendererKey() {
		RenderRequest renderRequest = _cpRequestHelper.getRenderRequest();

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		String value = portletPreferences.getValue(
			"cpContentListRendererKey", null);

		if (Validator.isNotNull(value)) {
			return value;
		}

		List<CPContentListRenderer> cpContentListRenderers =
			getCPContentListRenderers();

		if (cpContentListRenderers.isEmpty()) {
			return StringPool.BLANK;
		}

		CPContentListRenderer cpContentListRenderer =
			cpContentListRenderers.get(0);

		if (cpContentListRenderer == null) {
			return StringPool.BLANK;
		}

		return cpContentListRenderer.getKey();
	}

	public List<CPContentListRenderer> getCPContentListRenderers() {
		return _cpContentListRendererRegistry.getCPContentListRenderers(
			CPPortletKeys.CP_SEARCH_RESULTS);
	}

	public CPDataSourceResult getCPDataSourceResult() throws PortalException {
		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(_cpRequestHelper.getCompanyId());

		CPQuery cpQuery = new CPQuery();

		String orderByCol = getOrderByCol();

		if (orderByCol.equals("price-low-to-high")) {
			cpQuery.setOrderByCol1("basePrice");
			cpQuery.setOrderByType1("ASC");
		}
		else if (orderByCol.equals("price-high-to-low")) {
			cpQuery.setOrderByCol1("basePrice");
			cpQuery.setOrderByType1("DESC");
		}
		else if (orderByCol.equals("new-items")) {
			cpQuery.setOrderByCol1(Field.CREATE_DATE);
			cpQuery.setOrderByType1("DESC");
		}
		else if (orderByCol.equals("name-ascending")) {
			cpQuery.setOrderByCol1("name_localized");
			cpQuery.setOrderByType1("ASC");
		}

		if (orderByCol.equals("name-descending")) {
			cpQuery.setOrderByCol1("name_localized");
			cpQuery.setOrderByType1("DESC");
			cpQuery.setOrderByCol2(Field.NAME);
			cpQuery.setOrderByType2("DESC");
		}
		else {
			cpQuery.setOrderByCol2(Field.NAME);
			cpQuery.setOrderByType2("ASC");
		}

		return _cpDefinitionHelper.search(
			_cpRequestHelper.getScopeGroupId(), searchContext, cpQuery,
			getPaginationStart(), getPaginationEnd());
	}

	public String getCPTypeListEntryRendererKey(String cpType) {
		RenderRequest renderRequest = _cpRequestHelper.getRenderRequest();

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		String value = portletPreferences.getValue(
			cpType + "--cpTypeListEntryRendererKey", null);

		if (Validator.isNotNull(value)) {
			return value;
		}

		List<CPContentListEntryRenderer> cpContentListEntryRenderers =
			getCPContentListEntryRenderers(cpType);

		if (cpContentListEntryRenderers.isEmpty()) {
			return StringPool.BLANK;
		}

		CPContentListEntryRenderer cpContentListEntryRenderer =
			cpContentListEntryRenderers.get(0);

		if (cpContentListEntryRenderer == null) {
			return StringPool.BLANK;
		}

		return cpContentListEntryRenderer.getKey();
	}

	public List<CPType> getCPTypes() {
		return _cpTypeServicesTracker.getCPTypes();
	}

	public String getDisplayStyle() {
		return _cpSearchResultsPortletInstanceConfiguration.displayStyle();
	}

	public long getDisplayStyleGroupId() {
		if (_displayStyleGroupId > 0) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId =
			_cpSearchResultsPortletInstanceConfiguration.displayStyleGroupId();

		if (_displayStyleGroupId <= 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			_displayStyleGroupId = themeDisplay.getScopeGroupId();
		}

		return _displayStyleGroupId;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_httpServletRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			StringPool.BLANK);
	}

	public int getPaginationDelta() {
		return ParamUtil.getInteger(
			PortalUtil.getOriginalServletRequest(_httpServletRequest), "delta",
			_cpSearchResultsPortletInstanceConfiguration.paginationDelta());
	}

	public int getPaginationEnd() {
		if (getPaginationPage() == 0) {
			return getPaginationDelta();
		}

		return getPaginationPage() * getPaginationDelta();
	}

	public int getPaginationPage() {
		return ParamUtil.getInteger(
			PortalUtil.getOriginalServletRequest(_httpServletRequest), "start");
	}

	public int getPaginationStart() {
		if (getPaginationPage() == 0) {
			return 0;
		}

		return (getPaginationPage() - 1) * getPaginationDelta();
	}

	public String getPaginationType() {
		return _cpSearchResultsPortletInstanceConfiguration.paginationType();
	}

	public SearchContainer<CPCatalogEntry> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = buildSearchContainer(
			getCPDataSourceResult(), getPaginationPage(), "start",
			getPaginationDelta(), "delta");

		return _searchContainer;
	}

	public String getSelectionStyle() {
		return _cpSearchResultsPortletInstanceConfiguration.selectionStyle();
	}

	public boolean hasCommerceChannel() throws PortalException {
		CommerceContext commerceContext =
			(CommerceContext)_httpServletRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);

		long commerceChannelId = commerceContext.getCommerceChannelId();

		if (commerceChannelId > 0) {
			return true;
		}

		return false;
	}

	public boolean isPaginate() {
		return _cpSearchResultsPortletInstanceConfiguration.paginate();
	}

	public boolean isSelectionStyleADT() {
		String selectionStyle = getSelectionStyle();

		if (selectionStyle.equals("adt")) {
			return true;
		}

		return false;
	}

	public boolean isSelectionStyleCustomRenderer() {
		String selectionStyle = getSelectionStyle();

		if (selectionStyle.equals("custom")) {
			return true;
		}

		return false;
	}

	protected SearchContainer<CPCatalogEntry> buildSearchContainer(
		CPDataSourceResult cpDataSourceResult, int paginationStart,
		String paginationStartParameterName, int paginationDelta,
		String paginationDeltaParameterName) {

		PortletRequest portletRequest =
			_cpRequestHelper.getLiferayPortletRequest();
		DisplayTerms displayTerms = null;
		DisplayTerms searchTerms = null;
		String curParam = paginationStartParameterName;
		int cur = paginationStart;
		int delta = paginationDelta;
		List<String> headerNames = null;
		String emptyResultsMessage = null;
		String cssClass = null;

		SearchContainer<CPCatalogEntry> searchContainer = new SearchContainer<>(
			portletRequest, displayTerms, searchTerms, curParam, cur, delta,
			getPortletURL(), headerNames, emptyResultsMessage, cssClass);

		searchContainer.setDeltaParam(paginationDeltaParameterName);
		searchContainer.setResults(cpDataSourceResult.getCPCatalogEntries());
		searchContainer.setTotal(cpDataSourceResult.getLength());

		return searchContainer;
	}

	protected List<CPCatalogEntry> getCPCatalogEntries(
		List<Document> documents) {

		List<CPCatalogEntry> cpCatalogEntries = new ArrayList<>();

		for (Document document : documents) {
			cpCatalogEntries.add(
				_cpDefinitionHelper.getCPCatalogEntry(
					document, _cpRequestHelper.getLocale()));
		}

		return cpCatalogEntries;
	}

	protected PortletURL getPortletURL() {
		final String urlString = getURLString();

		return new NullPortletURL() {

			@Override
			public String toString() {
				return urlString;
			}

		};
	}

	protected HttpServletRequest getSharedRequest() {
		return PortalUtil.getOriginalServletRequest(_httpServletRequest);
	}

	protected String getURLString() {
		return HttpUtil.getCompleteURL(getSharedRequest());
	}

	private final CPContentListEntryRendererRegistry
		_cpContentListEntryRendererRegistry;
	private final CPContentListRendererRegistry _cpContentListRendererRegistry;
	private final CPDefinitionHelper _cpDefinitionHelper;
	private final CPRequestHelper _cpRequestHelper;
	private final CPSearchResultsPortletInstanceConfiguration
		_cpSearchResultsPortletInstanceConfiguration;
	private final CPTypeServicesTracker _cpTypeServicesTracker;
	private long _displayStyleGroupId;
	private final HttpServletRequest _httpServletRequest;
	private SearchContainer<CPCatalogEntry> _searchContainer;

}
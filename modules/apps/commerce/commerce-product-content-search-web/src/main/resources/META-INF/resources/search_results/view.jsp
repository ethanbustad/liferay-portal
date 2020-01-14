<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
CPSearchResultsDisplayContext cpSearchResultsDisplayContext = (CPSearchResultsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Map<String, Object> contextObjects = new HashMap<>();

contextObjects.put("cpSearchResultsDisplayContext", cpSearchResultsDisplayContext);

SearchContainer<CPCatalogEntry> cpCatalogEntrySearchContainer = cpSearchResultsDisplayContext.getSearchContainer();

List<CPCatalogEntry> results = cpCatalogEntrySearchContainer.getResults();

String orderByCol = cpSearchResultsDisplayContext.getOrderByCol();
%>

<c:choose>
	<c:when test="<%= !cpSearchResultsDisplayContext.hasCommerceChannel() %>">
		<div class="alert alert-info mx-auto">
			<liferay-ui:message key="this-site-does-not-have-a-channel" />
		</div>
	</c:when>
	<c:when test="<%= cpSearchResultsDisplayContext.isSelectionStyleADT() %>">
		<liferay-ddm:template-renderer
			className="<%= CPSearchResultsPortlet.class.getName() %>"
			contextObjects="<%= contextObjects %>"
			displayStyle="<%= cpSearchResultsDisplayContext.getDisplayStyle() %>"
			displayStyleGroupId="<%= cpSearchResultsDisplayContext.getDisplayStyleGroupId() %>"
			entries="<%= results %>"
		/>

		<c:if test="<%= cpSearchResultsDisplayContext.isPaginate() %>">
			<aui:form useNamespace="<%= false %>">
				<liferay-ui:search-paginator
					markupView="lexicon"
					searchContainer="<%= cpCatalogEntrySearchContainer %>"
					type="<%= cpSearchResultsDisplayContext.getPaginationType() %>"
				/>
			</aui:form>
		</c:if>
	</c:when>
	<c:when test="<%= cpSearchResultsDisplayContext.isSelectionStyleCustomRenderer() %>">
		<div class="m-0 mb-3 row">
			<div class="d-flex ml-auto pull-right">
				<p class="mb-auto mr-3 mt-auto">
					<liferay-ui:message arguments="<%= cpCatalogEntrySearchContainer.getTotal() %>" key="x-products-available" />
				</p>

				<aui:select cssClass="commerce-order-by" label="<%= StringPool.BLANK %>" name="orderBy" onChange='<%= renderResponse.getNamespace() + "changeOrderBy();" %>' wrapperCssClass="mb-0">
					<aui:option label="sort-by" selected="<%= orderByCol.equals(StringPool.BLANK) %>" value="<%= null %>" />
					<aui:option label="price-low-to-high" selected='<%= orderByCol.equals("price-low-to-high") %>' value="price-low-to-high" />
					<aui:option label="price-high-to-low" selected='<%= orderByCol.equals("price-high-to-low") %>' value="price-high-to-low" />
					<aui:option label="new-items" selected='<%= orderByCol.equals("new-items") %>' value="new-items" />
					<aui:option label="name-ascending" selected='<%= orderByCol.equals("name-ascending") %>' value="name-ascending" />
					<aui:option label="name-descending" selected='<%= orderByCol.equals("name-descending") %>' value="name-descending" />
				</aui:select>
			</div>
		</div>

		<liferay-commerce-product:product-list-renderer
			CPDataSourceResult = "<%= cpSearchResultsDisplayContext.getCPDataSourceResult() %>"
			entryKeys = "<%= cpSearchResultsDisplayContext.getCPContentListEntryRendererKeys() %>"
			key = "<%= cpSearchResultsDisplayContext.getCPContentListRendererKey() %>"
		/>

		<c:if test="<%= cpSearchResultsDisplayContext.isPaginate() %>">
			<aui:form useNamespace="<%= false %>">
				<liferay-ui:search-paginator
					markupView="lexicon"
					searchContainer="<%= cpCatalogEntrySearchContainer %>"
					type="<%= cpSearchResultsDisplayContext.getPaginationType() %>"
				/>
			</aui:form>
		</c:if>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />changeOrderBy',
		function() {
			var orderBy = docuement.getElementById('#<portlet:namespace />orderBy').val();

			var portletURL = new Liferay.PortletURL.createURL('<%= currentURLObj %>');

			portletURL.setParameter("orderByCol", orderBy);

			window.location.replace(portletURL.toString());
		},
		['liferay-portlet-url']
	);
</aui:script>
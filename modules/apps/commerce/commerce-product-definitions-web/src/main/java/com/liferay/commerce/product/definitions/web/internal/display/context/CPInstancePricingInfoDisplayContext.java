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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPInstancePricingInfoDisplayContext
	extends CPInstanceDisplayContext {

	public CPInstancePricingInfoDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CommercePriceFormatter commercePriceFormatter,
			CPDefinitionOptionRelService cpDefinitionOptionRelService,
			CPInstanceService cpInstanceService,
			CPInstanceHelper cpInstanceHelper,
			CommerceCurrencyLocalService commerceCurrencyLocalService)
		throws PortalException {

		super(
			actionHelper, httpServletRequest, commercePriceFormatter,
			cpDefinitionOptionRelService, cpInstanceService, cpInstanceHelper);

		_commerceCurrencyLocalService = commerceCurrencyLocalService;
	}

	public String getCommerceCurrencyCode() throws PortalException {
		CommerceCurrency commerceCurrency = getCommerceCurrency();

		if (commerceCurrency != null) {
			return commerceCurrency.getCode();
		}

		return StringPool.BLANK;
	}

	public BigDecimal round(BigDecimal value) throws PortalException {
		CommerceCurrency commerceCurrency = getCommerceCurrency();

		if (commerceCurrency == null) {
			return value;
		}

		return commerceCurrency.round(value);
	}

	protected CommerceCurrency getCommerceCurrency() throws PortalException {
		CPInstance cpInstance = getCPInstance();

		CommerceCatalog commerceCatalog = cpInstance.getCommerceCatalog();

		return _commerceCurrencyLocalService.getCommerceCurrency(
			commerceCatalog.getCompanyId(),
			commerceCatalog.getCommerceCurrencyCode());
	}

	private final CommerceCurrencyLocalService _commerceCurrencyLocalService;

}
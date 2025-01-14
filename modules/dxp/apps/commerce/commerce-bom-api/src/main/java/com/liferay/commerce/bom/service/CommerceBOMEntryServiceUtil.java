/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.bom.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceBOMEntry. This utility wraps
 * <code>com.liferay.commerce.bom.service.impl.CommerceBOMEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMEntryService
 * @generated
 */
public class CommerceBOMEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.bom.service.impl.CommerceBOMEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.bom.model.CommerceBOMEntry
			addCommerceBOMEntry(
				long userId, int number, String cpInstanceUuid, long cProductId,
				long commerceBOMDefinitionId, double positionX,
				double positionY, double radius)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceBOMEntry(
			userId, number, cpInstanceUuid, cProductId, commerceBOMDefinitionId,
			positionX, positionY, radius);
	}

	public static void deleteCommerceBOMEntry(long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommerceBOMEntry(commerceBOMEntryId);
	}

	public static java.util.List
		<com.liferay.commerce.bom.model.CommerceBOMEntry> getCommerceBOMEntries(
				long commerceBOMDefinitionId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceBOMEntries(
			commerceBOMDefinitionId, start, end);
	}

	public static int getCommerceBOMEntriesCount(long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceBOMEntriesCount(commerceBOMDefinitionId);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntry
			getCommerceBOMEntry(long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommerceBOMEntry(commerceBOMEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntry
			updateCommerceBOMEntry(
				long commerceBOMEntryId, int number, String cpInstanceUuid,
				long cProductId, double positionX, double positionY,
				double radius)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCommerceBOMEntry(
			commerceBOMEntryId, number, cpInstanceUuid, cProductId, positionX,
			positionY, radius);
	}

	public static CommerceBOMEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceBOMEntryService, CommerceBOMEntryService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMEntryService.class);

		ServiceTracker<CommerceBOMEntryService, CommerceBOMEntryService>
			serviceTracker =
				new ServiceTracker
					<CommerceBOMEntryService, CommerceBOMEntryService>(
						bundle.getBundleContext(),
						CommerceBOMEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
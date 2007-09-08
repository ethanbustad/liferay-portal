/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.messageboards.service.impl;

import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.CompanyImpl;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.base.MBThreadLocalServiceBaseImpl;
import com.liferay.portlet.messageboards.service.persistence.MBMessageFlagFinder;
import com.liferay.portlet.messageboards.service.persistence.MBMessageFlagUtil;
import com.liferay.portlet.messageboards.service.persistence.MBMessageUtil;
import com.liferay.portlet.messageboards.service.persistence.MBThreadFinder;
import com.liferay.portlet.messageboards.service.persistence.MBThreadUtil;
import com.liferay.portlet.messageboards.util.Indexer;
import com.liferay.portlet.tags.service.TagsAssetLocalServiceUtil;

import java.io.IOException;

import java.rmi.RemoteException;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.queryParser.ParseException;

/**
 * <a href="MBThreadLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MBThreadLocalServiceImpl extends MBThreadLocalServiceBaseImpl {

	public void deleteThread(long threadId)
		throws PortalException, SystemException {

		MBThread thread = MBThreadUtil.findByPrimaryKey(threadId);

		deleteThread(thread);
	}

	public void deleteThread(MBThread thread)
		throws PortalException, SystemException {

		MBMessage rootMessage = MBMessageUtil.findByPrimaryKey(
			thread.getRootMessageId());

		// Lucene

		try {
			Indexer.deleteMessages(
				rootMessage.getCompanyId(), thread.getThreadId());
		}
		catch (IOException ioe) {
			_log.error("Deleting index " + thread.getThreadId(), ioe);
		}
		catch (ParseException pe) {
			_log.error("Deleting index " + thread.getThreadId(), pe);
		}

		// File attachments

		long companyId = rootMessage.getCompanyId();
		String portletId = CompanyImpl.SYSTEM_STRING;
		long repositoryId = CompanyImpl.SYSTEM;
		String dirName = thread.getAttachmentsDir();

		try {
			DLServiceUtil.deleteDirectory(
				companyId, portletId, repositoryId, dirName);
		}
		catch (NoSuchDirectoryException nsde) {
		}
		catch (RemoteException re) {
			throw new SystemException(re);
		}

		// Messages

		Iterator itr = MBMessageUtil.findByThreadId(
			thread.getThreadId()).iterator();

		while (itr.hasNext()) {
			MBMessage message = (MBMessage)itr.next();

			// Tags

			TagsAssetLocalServiceUtil.deleteAsset(
				MBMessage.class.getName(), message.getMessageId());

			// Message flags

			MBMessageFlagUtil.removeByMessageId(message.getMessageId());

			// Resources

			if (!message.isDiscussion()) {
				ResourceLocalServiceUtil.deleteResource(
					message.getCompanyId(), MBMessage.class.getName(),
					ResourceImpl.SCOPE_INDIVIDUAL, message.getMessageId());
			}

			// Message

			MBMessageUtil.remove(message.getMessageId());
		}

		// Thread

		MBThreadUtil.remove(thread.getThreadId());
	}

	public void deleteThreads(long categoryId)
		throws PortalException, SystemException {

		Iterator itr = MBThreadUtil.findByCategoryId(categoryId).iterator();

		while (itr.hasNext()) {
			MBThread thread = (MBThread)itr.next();

			deleteThread(thread);
		}
	}

	public int getCategoriesThreadsCount(List categoryIds)
		throws SystemException {

		return MBThreadFinder.countByCategoryIds(categoryIds);
	}

	public List getGroupThreads(long groupId, int begin, int end)
		throws SystemException {

		return MBThreadFinder.findByGroupId(groupId, begin, end);
	}

	public List getGroupThreads(long groupId, long userId, int begin, int end)
		throws SystemException {

		return getGroupThreads(groupId, userId, false, begin, end);
	}

	public List getGroupThreads(
			long groupId, long userId, boolean subscribed, int begin, int end)
		throws SystemException {

		if (userId <= 0) {
			return MBThreadFinder.findByGroupId(groupId, begin, end);
		}
		else {
			if (subscribed) {
				return MBThreadFinder.findByS_G_U(groupId, userId, begin, end);
			}
			else {
				return MBThreadFinder.findByG_U(groupId, userId, begin, end);
			}
		}
	}

	public int getGroupThreadsCount(long groupId) throws SystemException {
		return MBThreadFinder.countByGroupId(groupId);
	}

	public int getGroupThreadsCount(long groupId, long userId)
		throws SystemException {

		return getGroupThreadsCount(groupId, userId, false);
	}

	public int getGroupThreadsCount(
			long groupId, long userId, boolean subscribed)
		throws SystemException {

		if (userId <= 0) {
			return MBThreadFinder.countByGroupId(groupId);
		}
		else {
			if (subscribed) {
				return MBThreadFinder.countByS_G_U(groupId, userId);
			}
			else {
				return MBThreadFinder.countByG_U(groupId, userId);
			}
		}
	}

	public MBThread getThread(long threadId)
		throws PortalException, SystemException {

		return MBThreadUtil.findByPrimaryKey(threadId);
	}

	public List getThreads(long categoryId, int begin, int end)
		throws SystemException {

		return MBThreadUtil.findByCategoryId(categoryId, begin, end);
	}

	public int getThreadsCount(long categoryId) throws SystemException {
		return MBThreadUtil.countByCategoryId(categoryId);
	}

	public boolean hasReadThread(long userId, long threadId)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);

		if (user.isDefaultUser()) {
			return true;
		}

		int total = MBMessageUtil.countByThreadId(threadId);
		int read = MBMessageFlagFinder.countByU_T(userId, threadId);

		if (total != read) {
			return false;
		}
		else {
			return true;
		}
	}

	public MBThread updateThread(long threadId, int viewCount)
		throws PortalException, SystemException {

		MBThread thread = MBThreadUtil.findByPrimaryKey(threadId);

		thread.setViewCount(viewCount);

		MBThreadUtil.update(thread);

		return thread;
	}

	private static Log _log = LogFactory.getLog(MBThreadLocalServiceImpl.class);

}
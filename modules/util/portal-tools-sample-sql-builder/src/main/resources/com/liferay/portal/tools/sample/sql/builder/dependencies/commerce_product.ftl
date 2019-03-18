${dataFactory.initCommerceProductModels(groupId)}

<#list dataFactory.CPDefinitionLocalizationModels as cpDefinitionLocalizationModel>
	${dataFactory.toInsertSQL(cpDefinitionLocalizationModel)}
</#list>

<#list dataFactory.CPDefinitionModels as cpDefinitionModel>
	${dataFactory.toInsertSQL(cpDefinitionModel)}

	<@insertAssetEntry
		_categoryAndTag=false
		_entry=cpDefinitionModel
	/>
</#list>

<#list dataFactory.CPFriendlyURLEntryModels as cpFriendlyURLEntryModel>
	${dataFactory.toInsertSQL(cpFriendlyURLEntryModel)}

	${dataFactory.getCSVWriter("cpfriendlyurlentry").write(cpFriendlyURLEntryModel.urlTitle + "\n")}
</#list>

<#list dataFactory.CPInstanceModels as cpInstanceModel>
	${dataFactory.toInsertSQL(cpInstanceModel)}
</#list>

<#list dataFactory.CProductModels as cProductModel>
	${dataFactory.toInsertSQL(cProductModel)}
</#list>

<#list dataFactory.CPTaxCategoryModels as cpTaxCategoryModel>
	${dataFactory.toInsertSQL(cpTaxCategoryModel)}
</#list>

<#assign
	layoutName = "commerce_product"
	portletId = "com_liferay_commerce_product_content_web_internal_portlet_CPContentPortlet"

	layoutModel = dataFactory.newLayoutModel(groupId, layoutName, "", portletId)
/>

<@insertLayout _layoutModel=layoutModel />
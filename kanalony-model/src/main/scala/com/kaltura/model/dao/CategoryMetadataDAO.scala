package com.kaltura.model.dao

import com.kaltura.core.utils.KalturaImpersonationClient
import com.kaltura.model.entities.CategoryMetadata

/**
 * Created by orlylampert on 1/9/17.
 */
object CategoryMetadataDAO extends DAOBase[CategoryMetadata, String] {
  def getById(partnerId: Int, categoryId: String): Option[CategoryMetadata] = {
    withPartnerImpersonation(partnerId, s"fetch category data for category $categoryId", Some(CategoryMetadata("-1", "Missing Category Name"))) { kalturaAPI =>
      val name = getCategoryName(kalturaAPI, categoryId)
      Some(CategoryMetadata(categoryId, name))
    }
  }

  def getCategoryName(kalturaAPI: KalturaImpersonationClient ,categoryId: String): String = {
    val kCategory = kalturaAPI.getCategoryService.get(categoryId.toInt)
    kCategory.name
  }


}

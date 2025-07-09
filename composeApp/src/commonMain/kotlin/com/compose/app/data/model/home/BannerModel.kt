package com.compose.app.data.model.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerModel(
    @SerialName("banner_code")
    val bannerCode: Int? = null,

    @SerialName("banner_name")
    val bannerName: String? = null,

    @SerialName("banner_url")
    val bannerUrl: String? = null,

    @SerialName("portal_document_path")
    val portalDocumentPath: String? = null,

    @SerialName("portal_document_type")
    val portalDocumentType: String? = null,

    @SerialName("portal_document_url")
    val portalDocumentUrl: String? = null,

    @SerialName("portal_document_size")
    val portalDocumentSize: String? = null,

    @SerialName("portal_document_order")
    val portalDocumentOrder: Int? = null,

    @SerialName("document_remark")
    val documentRemark: String? = null, // assuming it's nullable string

    @SerialName("portal_is_default")
    val portalIsDefault: Boolean? = null,

    @SerialName("app_document_path")
    val appDocumentPath: String? = null,

    @SerialName("app_document_type")
    val appDocumentType: String? = null,

    @SerialName("app_document_url")
    val appDocumentUrl: String? = null,

    @SerialName("app_document_size")
    val appDocumentSize: String? = null,

    @SerialName("app_document_order")
    val appDocumentOrder: Int? = null,

    @SerialName("app_is_default")
    val appIsDefault: Boolean? = null,

    @SerialName("tab_document_path")
    val tabDocumentPath: String? = null,

    @SerialName("tab_document_type")
    val tabDocumentType: String? = null,

    @SerialName("tab_document_url")
    val tabDocumentUrl: String? = null,

    @SerialName("tab_document_size")
    val tabDocumentSize: String? = null,

    @SerialName("tab_document_order")
    val tabDocumentOrder: Int? = null,

    @SerialName("tab_is_default")
    val tabIsDefault: Boolean? = null
)
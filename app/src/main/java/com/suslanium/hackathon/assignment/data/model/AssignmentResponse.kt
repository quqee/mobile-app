package com.suslanium.hackathon.assignment.data.model

import com.google.gson.annotations.SerializedName

data class AssignmentResponse(
    @SerializedName("statement_id"              ) var statementId             : String? = null,
    @SerializedName("area_name"                 ) var areaName                : String? = null,
    @SerializedName("length"                    ) var length                  : Int?    = null,
    @SerializedName("road_type"                 ) var roadType                : String? = null,
    @SerializedName("surface_type"              ) var surfaceType             : String? = null,
    @SerializedName("direction"                 ) var direction               : String? = null,
    @SerializedName("deadline"                  ) var deadline                : String? = null,
    @SerializedName("create_time"               ) var createTime              : String? = null,
    @SerializedName("description"               ) var description             : String? = null,
    @SerializedName("status"                    ) var status                  : String? = null,
    @SerializedName("organization_performer_id" ) var organizationPerformerId : String? = null,
    @SerializedName("organization_creator_id"   ) var organizationCreatorId   : String? = null
)

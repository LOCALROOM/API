package com.anranyus.apirequest.model

data class QRCode(val data: QRCodeInfo, val code: Int)
data class QRCodeInfo(val qurl:String,val qrimg:String)
data class QRCodeStatusInfo(val code:Int,
                            val message:String,
                            val cookie:String)
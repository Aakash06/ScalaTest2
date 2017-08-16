package models

case class Item(id :Long,name:String,vendor:Vendor,rating:Int,category:String,price: Double,stock : Int)
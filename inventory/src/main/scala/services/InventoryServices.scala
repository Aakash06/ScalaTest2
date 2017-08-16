package services

import models.Item

import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class InventoryServices {

  private val InventoryMap : mutable.Map[String,List[Item]]= mutable.Map(
    "Electronics" -> List(Item(1,"Samsung Mobile","Darpit",5,"Electronics",25.6,58),
      Item(1,"Samsung Mobile","Darpit",5,"Electronics",25.6,58)),
  "Mobile"->List(Item(1,"Samsung Mobile","Darpit",5,"Mobile",25.6,58)))

  def searchByCtaegory(category:String): Future[List[Item]] =Future
  {
    if(InventoryMap.contains(category)){
      InventoryMap(category)
    }
    else{
      Nil
    }
  }

  def sortByPrice(category:String,sortBy:String): Future[List[Item]] = Future{

    val list: List[Item] = InventoryMap(category)

    val sortedListOfItems = list.sortBy(_.price)

    if(sortBy.equalsIgnoreCase("LowToHigh")){
      sortedListOfItems
    }
    else if(sortBy.equalsIgnoreCase("Default")){
      sortedListOfItems.reverse
    }
    else{
      sortedListOfItems.reverse
    }
  }

  def sortByrating(category:String,sortBy:String): Future[List[Item]] = Future{

    val list: List[Item] = InventoryMap(category)

    val sortedListOfItems = list.sortBy(_.rating)

    if(sortBy.equalsIgnoreCase("LowToHigh")){
      sortedListOfItems
    }
      else if(sortBy.equalsIgnoreCase("Default")){
      sortedListOfItems.reverse
    }
    else{
      sortedListOfItems.reverse
    }
  }

  /*
  def updateItemCount(itemId:Long,count:Int): Future[Item] =Future{

    val item:Option[Item] = inventory.item.find(_.id== itemId)

    item match {
      case Some(item)=> {
        val updateStock:Int = f(item.stock,count)
        val updatedItem:Item = item.copy(stock=updateStock)
        updatedItem
      }
      case None => throw new NoSuchElementException
    }
  }*/
}

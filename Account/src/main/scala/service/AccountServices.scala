package services

import java.text.SimpleDateFormat
import java.util.Calendar
import scala.concurrent.ExecutionContext.Implicits.global
import models.UserData
import scala.collection.mutable
import scala.concurrent.Future

class AccountServices {

  private val userDataMap : mutable.Map[Long,UserData]= mutable.Map(
    8447018441L -> UserData("Aakash","Jain",8447018441L,"Aakash06","aakashjain"),
    8978456525L -> UserData("Kapil","Sharma",8978456525L,"Kapil14","kapilSharma")
  )

  private val userDataToken : mutable.Map[Long,String]= mutable.Map(
    8447018441L -> "Aakash06", 8978456525L -> "kapil14")

  def addUser(firstName:String,lastName:String,phoneNumber:Long,password:String,confirmPassword:String,userName:String): Future[String] = Future{
    if (password.equals(confirmPassword)){
      s"Password doesn't match"
    }
    else if(userDataMap.contains(phoneNumber)){
      s"SomeOne is already registered by this Mobile Number"
    }
    else {
      userDataMap += (phoneNumber->UserData(firstName,lastName,phoneNumber,password,userName))
      s"Congratulations,You are successfully added in Database"
    }
  }

  def Authenticate(mobileNumber:Long,userName:String,password:String)=Future{

    val checkValues = for{(mobileNumber,user)<-userDataMap
                          boolResult = if(user.userName.equals(userName) && user.password.equals(password)){
                            true
                          } else false
    }yield (mobileNumber,boolResult)

    if(checkValues.toList.map(_._2)contains(true))
    {

      val format = new SimpleDateFormat("d-M-y")
      val currentDate = format.format(Calendar.getInstance().getTime())
      val accessToken = s"$userName $currentDate"
      userDataToken +=(mobileNumber->accessToken)
      accessToken
    }
    else{
      s"Sorry Username or password doesn't match"
    }
  }
}

package service

import services.AccountServices
import org.scalatest.AsyncFunSuite

class AccountServicesTest extends AsyncFunSuite {

  val accountServices = new AccountServices

  test("Testing Add user "){
    val result = accountServices.addUser("aakash","sharma",789456123L,"dellvostro","dellvostro","hellomoto")
    result.map(e =>assert(e == "Congratulations,You are successfully added in Database"))
  }

  test("Testing if mobile Number is already existing"){
    val result = accountServices.addUser("aakash","sharma",8447018441L,"dellvostro","dellvostro","hellomoto")
    result.map(e =>assert(e == "SomeOne is already registered by this Mobile Number"))
  }

  test("Password Doesn't match"){
    val result = accountServices.addUser("aakash","sharma",8447018441L,"dell","dellvostro","hellomoto")
    result.map(e =>assert(e == "Password doesn't match"))
  }

}

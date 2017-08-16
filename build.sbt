
lazy val commonSettings = Seq (
  version := "1.0",
  scalaVersion := "2.12.2",
  libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test"
)

lazy val Account  = project.settings(commonSettings)

lazy val apiResources  = project.dependsOn(inventory,checkoutServices,notification)
                                .settings(commonSettings)
lazy val checkoutServices = project.dependsOn(inventory).settings(commonSettings)

lazy val dashboard = project.settings(commonSettings).dependsOn(apiResources)

lazy val inventory = project.settings(commonSettings)

lazy val notification= project.settings(commonSettings)

lazy val root = project.in(file(".")).aggregate(Account,apiResources,checkoutServices,
  dashboard,inventory,notification)

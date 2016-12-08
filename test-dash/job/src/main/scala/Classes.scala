
package io.kensu.testdash


  //---//

val customVars = config.getConfig("notebook.custom.vars")
// custom variables
  val rootDir = customVars.getString("rootDir") 

     
/* -- Code Cell: Some(EFB96FCC9F1B471FA8A694C7F4D57649) -- */ 

  case class ModelDefinitionVersion(version: Int)


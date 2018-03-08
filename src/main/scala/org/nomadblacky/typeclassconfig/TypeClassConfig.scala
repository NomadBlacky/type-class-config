package org.nomadblacky.typeclassconfig

import com.typesafe.config.{Config => TSConfig, ConfigFactory}

object TypeClassConfig {

  val tsConfig: TSConfig = ConfigFactory.load()

}

package org.nomadblacky.typeclassconfig

import com.typesafe.config.Config

import scala.util.Try

trait ConfigExtension[T] {
  def !(path: String)(implicit config: Config): T = apply(path).get
  def ?(path: String)(implicit config: Config): Option[T] = apply(path).toOption
  def apply(path: String)(implicit config: Config): Try[T]
}

object ConfigExtension {
  object int extends ConfigExtension[Int] {
    def apply(path: String)(implicit config: Config): Try[Int] = Try(config.getInt(path))
  }
}
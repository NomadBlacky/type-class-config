package org.nomadblacky.typeclassconfig

import com.typesafe.config.{Config, ConfigException, ConfigFactory}
import org.scalatest.{FunSpec, Matchers}

import scala.util.{Failure, Try}

class ConfigExtensionTest extends FunSpec with Matchers {

  import ConfigExtension._

  implicit val testConfig: Config = ConfigFactory.load()

  describe("int") {
    describe("apply") {
      it("should returns Try[Int]") {
        int("int") shouldBe Try(10)
      }

      it("should returns Failure if the configuration not found") {
        int("unknown") shouldBe a [Failure[_]]
      }

      it("should returns Failure if the configuration is invalid value") {
        int("invalid.int") shouldBe a [Failure[_]]
      }
    }

    describe("?") {
      it("should returns Option[Int]") {
        int ? "int" shouldBe Option(10)
      }

      it("should returns None if the configuration not found") {
        int ? "unknown" shouldBe None
      }

      it("should returns None if the configuration is invalid value") {
        int ? "invalid.int" shouldBe None
      }
    }

    describe("!") {
      it("should returns Int") {
        int ! "int" shouldBe 10
      }

      it("should throws ConfigException.Missing if the configuration not found") {
        the [ConfigException.Missing] thrownBy (int ! "unknown")
      }

      it("should throws ConfigException.WrongType if the configuration now found") {
        the [ConfigException.WrongType] thrownBy (int ! "invalid.int")
      }
    }
  }
}

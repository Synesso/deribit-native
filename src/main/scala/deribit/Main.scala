package deribit

import java.io.{FileWriter, PrintWriter}

import sttp.client._
import scala.annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {

    @tailrec
    def process(startTime: Long, instrument: String)(effect: Trade => Unit): Unit = {
      val (count, sorting, includeOld) = (100, "asc", true)
      val uri =
        uri"https://www.deribit.com/api/v2/public/get_last_trades_by_instrument_and_time?count=$count&start_timestamp=$startTime&instrument_name=$instrument&sorting=$sorting&include_old=$includeOld"
      val request = basicRequest.get(uri)
      implicit val backend: SttpBackend[Identity, Nothing, NothingT] = CurlBackend()
      val response = request.send().body match {
        case Left(t) => t
        case Right(s) => s
      }
      val trades = Trade.parse(response)
      if (trades.isEmpty) {}
      else {
        trades.foreach(effect)
        process(trades.last.timestamp + 1, instrument) {
          effect
        }
      }
    }

    if (args.length == 0) {
      println("Usage: derebit [instrument1] [instrument2] ...")
      println("  e.g. derebit BTC-24JUL20-8500-P")
    } else {
      args.map(_.toUpperCase()).foreach { instrument =>
        println(Trade.csvHeader)
        process(0, instrument) { trade =>
          println(trade.csv)
        }
      }
    }
  }


}

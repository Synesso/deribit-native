package deribit

case class Trade(
  trade_seq: Long,
  trade_id: String,
  timestamp: Long,
  price: Double,
  mark_price: Double,
  iv: Double,
  instrument_name: String,
  index_price: Double,
  direction: String,
  amount: Double
) {
  def csv = s"$trade_id,$timestamp,$price,$mark_price,$iv,$index_price,$direction,$amount"
}

object Trade {
  import upickle.default.{macroRW, ReadWriter => RW}
  implicit def uPickleFormatter: RW[Trade] = macroRW

  def parse(string: String): List[Trade] = {
    val doc = ujson.read(string)
    upickle.default.read[List[Trade]](doc("result")("trades"))
  }

  val csvHeader = "trade id, timestamp, price, mark price, iv, index price, direction, amount"
}
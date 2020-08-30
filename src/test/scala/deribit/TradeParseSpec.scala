package deribit

import utest._

object TradeParseSpec extends TestSuite {
  override def tests: Tests = Tests {

    test("parse a valid document") {
      val doc =
        """
          |{
          |  "jsonrpc": "2.0",
          |  "result": {
          |    "trades": [
          |      {
          |        "trade_seq": 1,
          |        "trade_id": "84394869",
          |        "timestamp": 1594285903076,
          |        "tick_direction": 1,
          |        "price": 0.0085,
          |        "mark_price": 0.00843251,
          |        "iv": 51.17,
          |        "instrument_name": "BTC-24JUL20-8500-P",
          |        "index_price": 9390.42,
          |        "direction": "buy",
          |        "amount": 0.1
          |      },
          |      {
          |        "trade_seq": 2,
          |        "trade_id": "84394886",
          |        "timestamp": 1594285906113,
          |        "tick_direction": 1,
          |        "price": 0.0085,
          |        "mark_price": 0.00842144,
          |        "iv": 51.16,
          |        "instrument_name": "BTC-24JUL20-8500-P",
          |        "index_price": 9390.03,
          |        "direction": "buy",
          |        "amount": 0.1
          |      }
          |    ],
          |    "has_more": true
          |  },
          |  "usIn": 1598691526769120,
          |  "usOut": 1598691526834973,
          |  "usDiff": 65853,
          |  "testnet": false
          |}
          |""".stripMargin


      assert(Trade.parse(doc) == List(
        Trade(1, "84394869", 1594285903076L, 0.0085, 0.00843251, 51.17, "BTC-24JUL20-8500-P", 9390.42, "buy", 0.1),
        Trade(2, "84394886", 1594285906113L, 0.0085, 0.00842144, 51.16, "BTC-24JUL20-8500-P", 9390.03, "buy", 0.1)
      ))
    }

  }
}

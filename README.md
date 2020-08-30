# deribit-native
Scala native sample project. It fetches trade history from derebit &amp; converts to CSV.

```
$ ./derebit BTC-24JUL20-8500-P
trade id, timestamp, price, mark price, iv, index price, direction, amount
84394869,1594285903076,0.0085,0.00843251,51.17,9390.42,buy,0.1
84394886,1594285906113,0.0085,0.00842144,51.16,9390.03,buy,0.1
…
```

## Building

`sbt nativeLink`

You may have unmet lib dependencies. For example `libidn`, which can be fixed on OSX with `brew install libidn`.
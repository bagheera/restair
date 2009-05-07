== Info: About to connect() to localhost port 3000 (#0)
== Info:   Trying 127.0.0.1... == Info: connected
== Info: Connected to localhost (127.0.0.1) port 3000 (#0)
=> Send header, 204 bytes (0xcc)
0000: POST /v1/booking HTTP/1.1
001b: User-Agent: curl/7.16.3 (i386-pc-win32) libcurl/7.16.3 OpenSSL/0
005b: .9.8e zlib/1.2.3
006d: Host: localhost:3000
0083: Accept: */*
0090: Content-Type: application/xhtml+xml
00b5: Content-Length: 598
00ca: 
=> Send data, 598 bytes (0x256)
0000: <div class="flightBooking">  <span class="flightNumber">102</spa
0040: n>  <div class="travellers">    <div class="traveller">      <sp
0080: an class="firstName">firstName0</span>      <span class="lastNam
00c0: e">lastName0</span>      <span class="gender">F</span>    </div>
0100:     <div class="traveller">      <span class="firstName">firstNa
0140: me1</span>      <span class="lastName">lastName1</span>      <sp
0180: an class="gender">F</span>    </div>    <div class="traveller"> 
01c0:      <span class="firstName">firstName2</span>      <span class=
0200: "lastName">lastName2</span>      <span class="gender">F</span>  
0240:   </div>  </div></div>
<= Recv header, 90 bytes (0x5a)
0000: HTTP/1.1 201 The request has been fulfilled and resulted in a ne
0040: w resource being created
<= Recv header, 37 bytes (0x25)
0000: Date: Sun, 14 Oct 2007 07:53:35 GMT
<= Recv header, 38 bytes (0x26)
0000: Server: Noelios-Restlet-Engine/1.0.5
<= Recv header, 37 bytes (0x25)
0000: Content-Type: application/xhtml+xml
<= Recv header, 28 bytes (0x1c)
0000: Transfer-Encoding: chunked
<= Recv header, 2 bytes (0x2)
0000: 
<= Recv data, 984 bytes (0x3d8)
0000: 3d1
0005: <?xml version="1.0" encoding="UTF-8"?><!DOCTYPE html PUBLIC "-//
0045: W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml
0085: 1/DTD/xhtml1-transitional.dtd">
00a6: <html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="e
00e6: n"><body><div class="flightBooking">
010c:   <span class="bookingReference">PXWR201</span>
013d:   <span class="bookingStatus">CONFD</span>
0169:   <span class="flightNumber">102</span>
0192:   <div class="travellers">
01ae:     <div class="traveller">
01cb:       <span class="firstName">firstName0</span>
01fc:       <span class="lastName">lastName0</span>
022b:       <span class="gender">F</span>
0250:     </div>
025c:     <div class="traveller">
0279:       <span class="firstName">firstName1</span>
02aa:       <span class="lastName">lastName1</span>
02d9:       <span class="gender">F</span>
02fe:     </div>
030a:     <div class="traveller">
0327:       <span class="firstName">firstName2</span>
0358:       <span class="lastName">lastName2</span>
0387:       <span class="gender">F</span>
03ac:     </div>
03b8:   </div>
03c2: </div></body></html>
<= Recv data, 5 bytes (0x5)
0000: 0
0003: 
== Info: Connection #0 to host localhost left intact
== Info: Closing connection #0

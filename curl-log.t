**********************
Windows PowerShell Transcript Start
Start time: 20071014133828
Username  : CORPORATE\srinaray 
Machine	  : SNARAYANL (Microsoft Windows NT 5.1.2600 Service Pack 2) 
**********************
Transcript started, output file is C:\Documents and Settings\srinaray\My Documents\PowerShell_transcript.20071014133828.txt




***************************SEATS UNAVAILABLE TEST



PS C:\pgms\curl> .\curl --trace-ascii aa -d "@d:\mycode\restair\src\makeBooking.t" -H "Content-Type: application/xhtml+xml" http://localhost:3000/v1/booking
PS C:\pgms\curl> cat aa
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
<= Recv header, 32 bytes (0x20)
0000: HTTP/1.1 500 Seats unavailable
<= Recv header, 37 bytes (0x25)
0000: Date: Sun, 14 Oct 2007 08:10:35 GMT
<= Recv header, 38 bytes (0x26)
0000: Server: Noelios-Restlet-Engine/1.0.5
<= Recv header, 45 bytes (0x2d)
0000: Content-Type: text/html; charset=ISO-8859-1
<= Recv header, 21 bytes (0x15)
0000: Content-Length: 290
<= Recv header, 2 bytes (0x2)
0000: 
<= Recv data, 290 bytes (0x122)
0000: <html>.<head>.   <title>Status page</title>.</head>.<body>.<h3>S
0040: eats unavailable</h3><p>You can get technical details <a href="h
0080: ttp://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.5.1"
00c0: >here</a>.<br/>.Please continue your visit at our <a href="/">ho
0100: me page</a>..</p>.</body>.</html>.
== Info: Connection #0 to host localhost left intact
== Info: Closing connection #0



***************OVERBOOK A FLIGHT - FOURTH BOOKING FAILS AS ONLY ONE SEAT LEFT

PS C:\pgms\curl> .\curl --trace-ascii aa -d "@d:\mycode\restair\src\makeBooking.t" -H "Content-Type: application/xhtml+xml" http://localhost:3000/v1/booking
PS C:\pgms\curl> .\curl --trace-ascii aa -d "@d:\mycode\restair\src\makeBooking.t" -H "Content-Type: application/xhtml+xml" http://localhost:3000/v1/booking
PS C:\pgms\curl> .\curl --trace-ascii aa -d "@d:\mycode\restair\src\makeBooking.t" -H "Content-Type: application/xhtml+xml" http://localhost:3000/v1/booking
PS C:\pgms\curl> .\curl --trace-ascii aa -d "@d:\mycode\restair\src\makeBooking.t" -H "Content-Type: application/xhtml+xml" http://localhost:3000/v1/booking



**************NOW CANCEL THE 2ND BOOKING

PS C:\pgms\curl> .\curl --trace-ascii aa -X DELETE http://localhost:3000/v1/booking/PXWR202
PS C:\pgms\curl> cat aa
== Info: About to connect() to localhost port 3000 (#0)
== Info:   Trying 127.0.0.1... == Info: connected
== Info: Connected to localhost (127.0.0.1) port 3000 (#0)
=> Send header, 156 bytes (0x9c)
0000: DELETE /v1/booking/PXWR202 HTTP/1.1
0025: User-Agent: curl/7.16.3 (i386-pc-win32) libcurl/7.16.3 OpenSSL/0
0065: .9.8e zlib/1.2.3
0077: Host: localhost:3000
008d: Accept: */*
009a: 
<= Recv header, 32 bytes (0x20)
0000: HTTP/1.1 200 Booking cancelled
<= Recv header, 37 bytes (0x25)
0000: Date: Sun, 14 Oct 2007 14:59:15 GMT
<= Recv header, 38 bytes (0x26)
0000: Server: Noelios-Restlet-Engine/1.0.5
<= Recv header, 37 bytes (0x25)
0000: Content-Type: application/xhtml+xml
<= Recv header, 28 bytes (0x1c)
0000: Transfer-Encoding: chunked
<= Recv header, 2 bytes (0x2)
0000: 
<= Recv data, 1058 bytes (0x422)
0000: 416
0005: <?xml version="1.0" encoding="UTF-8"?><!DOCTYPE html PUBLIC "-//
0045: W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml
0085: 1/DTD/xhtml1-transitional.dtd">
00a6: <html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="e
00e6: n"><body><div class="flightBooking">
010c:   <span class="url">http://localhost:3000/v1/booking/PXWR202</sp
014c: an>
0151:   <span class="bookingReference">PXWR202</span>
0182:   <span class="bookingStatus">CNCLD</span>
01ae:   <span class="flightNumber">102</span>
01d7:   <div class="travellers">
01f3:     <div class="traveller">
0210:       <span class="firstName">firstName0</span>
0241:       <span class="lastName">lastName0</span>
0270:       <span class="gender">F</span>
0295:     </div>
02a1:     <div class="traveller">
02be:       <span class="firstName">firstName1</span>
02ef:       <span class="lastName">lastName1</span>
031e:       <span class="gender">F</span>
0343:     </div>
034f:     <div class="traveller">
036c:       <span class="firstName">firstName2</span>
039d:       <span class="lastName">lastName2</span>
03cc:       <span class="gender">F</span>
03f1:     </div>
03fd:   </div>
0407: </div></body></html>
041d: 0
0420: 
== Info: Connection #0 to host localhost left intact
== Info: Closing connection #0
PS C:\pgms\curl> 
This utility allows bidirectional converting between native twx files and json files

Can be useful when you only have a TWX export of your entities, but you need to only have a binary twx file available.

### How to use

Make sure you have java installed.

Converting twx to json:
`java -jar convertor.jar t2j inputFile.twx outputFile.json`

Converting json to twx:
`java -jar convertor.jar j2t inputFile.json outputFile.twx`

### Download:
ftp://rostorage.ptcnet.ptc.com/SHARE/Petrisor/Thingworx/smileUtils-1.0-SNAPSHOT-all.jar (use guest1:guest) 

### Info

The twx format is a file in the [smile format](https://en.wikipedia.org/wiki/Smile_(data_interchange_format)). The smile format is a Jackson developed format, representing a binary json.
This binary json is then compressed using LZ4.
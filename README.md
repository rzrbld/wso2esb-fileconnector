# wso2 esb file connector

Based on original fileconnector-1.0.0: https://github.com/wso2/esb-connectors/tree/master/fileconnector

#### Difference between original and this version:
this file connector allow's you to decode base64 strings before saving file.
For example:
you got binary\text or whatever file and you send this file, with or without MTOM or SwA, on WSO2ESB. In ESB logs you will see something like:
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns0="http://rzrbld.ru/ns0/fakeschema/">
   <soapenv:Header>
      <ns0:FileName>myHelloWorld.txt</ns0:FileName>
   </soapenv:Header>
   <soapenv:Body>
      <ns0:SaveFile>
         <ns0:FileContent>SGVsbG8gd29ybGQhISE=</ns0:FileContent>
      </ns0:SaveFile>
   </soapenv:Body>
</soapenv:Envelope>
```
and you wanna store this content as decoded base64 file:
```sh
$ cat myHelloWord.txt
Hello world!!!
```
instead of encoded base64:
```sh
$ cat myHelloWord.txt
SGVsbG8gd29ybGQhISE=
```
with this file connector, this is quite simple, only thing that you need to set is encoding property in &lt;fileconnector.create&gt; section to base64, default values like US-ASCII, UTF-8, UTF-16 is still supporting:
```xml
...
<property name="encoding" value="base64"></property>
...
<fileconnector.create>
    <filelocation>{$ctx:FileLocation}</filelocation>
    <file>{$ctx:FileName}</file>
    <content>{$ctx:FileContent}</content>
    <encoding>{$ctx:encoding}</encoding>
 </fileconnector.create>
```



#### Build pre-requisites:
 * Maven 3.x
 * Java 1.7 or above

#### Tested Platform: 
 - UBUNTU 13.10, Mac OSx 10., Ubuntu 15.10
 - WSO2 ESB 4.9.0-ALPHA, WSO2 ESB 4.8.1
 - Java 1.7, OpenJDK 7

### pre-requisites for integration tests for WSO2 ESB File Connector:
STEPS:
1. Download ESB 4.9.0-ALPHA by navigating the following the URL: https://svn.wso2.org/repos/wso2/scratch/ESB/.

2. Copy the wso2esb-4.9.0-ALPHA.zip in to location "{ESB_Connector_Home}/repository/".

3. Please do these changes on fileconnector.xml (fileconenctor.xml file can be found from <fileconnetor>/src/test/resources/artifacts/ESB/config/proxies/fileconnector/).
    1) Change the file locations with the accessible file locations.

    2) Copy an 'mp4' or a large file at the location specified in <fileconnector.copylarge></fileconnector.copylarge> for copylarge method.

    3) Create a file at the location specified in <fileconnector.copy></fileconnector.copy> for copy method.

    3) Create at least one folder and a file at the location specified in  <filelocation></filelocation> archive method.

4. Make sure that fileconnector is specified as a module in ESB_Connector_Parent pom.
    ```xml
       <module>fileconnector/fileconnector-1.0.0</module>
    ```

5. Navigate to "{ESB_Connector_Home}/" and run the following command.
    ```sh
      $ mvn clean install
    ```

### License
licensed according to the terms of Apache License, Version 2. ( http://www.apache.org/licenses/LICENSE-2.0 )

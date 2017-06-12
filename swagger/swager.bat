if "%1"=="hide" goto CmdBegin
start mshta vbscript:createobject("wscript.shell").run("""%~0"" hide",0)(window.close)&&exit
:CmdBegin

http-server -p 8081 swagger-editor-2.0 >> out.txt  2 > err.txt
cd D:\ResearchProject\TODO-Web\swagger\swagger-app
node index.js >> out.txt  2 > err.txt
echo "start success"
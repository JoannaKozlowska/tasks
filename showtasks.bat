call runcrud
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo RUNCRUD has errors - breaking work
goto fail

:browser
"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo BROWSER is not responding
goto fail

:fail
echo.
echo there were errors

:end
echo.
echo Work is finished